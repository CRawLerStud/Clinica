package com.example.clinica;

import com.example.clinica.controller.AppController;
import com.example.clinica.controller.UIControllers.MediciController;
import com.example.clinica.repository.ConsultatieRepository;
import com.example.clinica.repository.MedicRepository;
import com.example.clinica.repository.SectieRepository;
import com.example.clinica.service.ConsultatieService;
import com.example.clinica.service.MedicService;
import com.example.clinica.service.SectiiService;
import com.example.clinica.validation.ConsultatieValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static AppController appController;

    public static AppController getController() {
        return appController;
    }

    @Override
    public void start(Stage stage) throws IOException {

        String url = "jdbc:postgresql://localhost:5432/clinica";
        String username = "postgres";
        String password = "postgres";

        ConsultatieValidator consultatieValidator = new ConsultatieValidator();

        MedicRepository medicRepository = new MedicRepository(url, username, password);
        SectieRepository sectieRepository = new SectieRepository(url, username, password);
        ConsultatieRepository consultatieRepository = new ConsultatieRepository(url, username, password);

        MedicService medicService = new MedicService(medicRepository);
        SectiiService sectiiService = new SectiiService(sectieRepository);
        ConsultatieService consultatieService = new ConsultatieService(consultatieValidator, consultatieRepository);

        appController = new AppController(medicService, sectiiService, consultatieService);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sectii.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Sectii");
        stage.setScene(scene);
        stage.show();


        appController.findAllMedics().forEach(medic -> {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("medic.fxml"));
            Stage medic_stage = new Stage();
            Scene medic_scene;
            try{
                medic_scene = new Scene (loader.load(), 600, 400);
            }
            catch(IOException e){
                throw new RuntimeException(e);
            }

            MediciController mediciController = loader.getController();
            mediciController.setController(appController);
            mediciController.setMedic(medic);

            medic_stage.setTitle(medic.getNume() + " " + medic.getPrenume());
            medic_stage.setScene(medic_scene);
            medic_stage.show();
        });


    }

    public static void main(String[] args) {
        launch();
    }
}