package com.example.clinica.controller.UIControllers;

import com.example.clinica.HelloApplication;
import com.example.clinica.controller.AppController;
import com.example.clinica.models.Sectie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SectiiController {

    AppController controller;
    ObservableList<Sectie> sectiiObservableList;

    @FXML
    ListView<Sectie> sectiiListView;

    @FXML
    public void initialize(){
        controller = HelloApplication.getController();

        initializeListView();
        loadSectii();
    }

    private void initializeListView(){
        sectiiListView.setCellFactory(sectiiListView -> new ListCell<>(){
            @Override
            protected void updateItem(Sectie item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null){
                    setText("");
                }
                else{
                    setText(item.getNume());
                }
            }
        });
    }

    private void loadSectii(){
        List<Sectie> sectii = (List<Sectie>) controller.findAllSectii();
        sectiiObservableList = FXCollections.observableList(sectii);
        sectiiListView.setItems(sectiiObservableList);
    }

    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {

        if(mouseEvent.getClickCount() == 2){

                Stage consultationStage = new Stage();
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("adauga_consultatie.fxml"));
                Scene consultatieScene;
                try {
                    consultatieScene = new Scene(loader.load(), 600, 400);
                }
                catch(IOException e){
                    throw new RuntimeException(e);
                }
                consultationStage.setScene(consultatieScene);
                consultationStage.setTitle("Adauga Consultatie");

                AdaugaConsultatieController adaugaConsultatieController = loader.getController();
                adaugaConsultatieController.setController(controller);
                adaugaConsultatieController.setStage(consultationStage);
                adaugaConsultatieController.setSectie(sectiiListView.getSelectionModel().getSelectedItem());

                consultationStage.showAndWait();
        }

    }

}
