package com.example.clinica.controller.UIControllers;

import com.example.clinica.controller.AppController;
import com.example.clinica.models.Consultatie;
import com.example.clinica.models.Medic;
import com.example.clinica.models.Sectie;
import com.example.clinica.validation.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class AdaugaConsultatieController {

    private AppController controller;
    private Stage stage;
    private Sectie sectie;

    @FXML
    Label sectieLabel;

    @FXML
    ChoiceBox<Medic> medicChoiceBox;
    ObservableList<Medic> medicObservableList;

    @FXML
    Spinner<Integer> hourSpinner;

    @FXML
    DatePicker dataPicker;

    @FXML
    TextField cnpTextField;

    @FXML
    TextField numeTextField;

    public void setController(AppController controller){
        this.controller = controller;
    }

    public void setSectie(Sectie sectie) {
        this.sectie = sectie;
        sectieLabel.setText(sectie.getNume());

        initializeMedicChoiceBox();
        initializeSpinner();
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private void initializeSpinner(){
        SpinnerValueFactory<Integer> spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(8, 18, 8);
        hourSpinner.setValueFactory(spinnerValueFactory);
    }

    private void initializeMedicChoiceBox(){
        List<Medic> medicList = (List<Medic>) controller.findAllMedicsForSectie(sectie.getId());
        medicObservableList = FXCollections.observableList(medicList);
        medicChoiceBox.setItems(medicObservableList);
    }

    @FXML
    public void confirmConsultatie(){
        try{
            Medic medic = medicChoiceBox.getValue();
            LocalDate date = dataPicker.getValue();
            Integer hour = hourSpinner.getValue();
            Long CNP = Long.parseLong(cnpTextField.getText());
            String numePacient = numeTextField.getText();

            Consultatie consultatie = new Consultatie(0L, medic.getId(), CNP, numePacient, date, hour);
            Long consultatieID = controller.saveConsultatie(consultatie);
            consultatie.setID(consultatieID);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Consultatia a fost salvata!");
            alert.showAndWait();

            stage.close();
        }
        catch(ValidationException | NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

}
