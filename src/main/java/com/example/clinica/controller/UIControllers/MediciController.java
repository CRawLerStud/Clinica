package com.example.clinica.controller.UIControllers;

import com.example.clinica.controller.AppController;
import com.example.clinica.models.Consultatie;
import com.example.clinica.models.Medic;
import com.example.clinica.utils.observer.Observer;
import com.example.clinica.utils.utils.ConsultatieEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.List;

public class MediciController implements Observer<ConsultatieEvent> {

    Medic medic;
    AppController controller;
    ObservableList<Consultatie> consultatieObservableList;

    @FXML
    ListView<Consultatie> consultatiiListView;

    @FXML
    public void setMedic(Medic medic){
        this.medic = medic;
        controller.addConsultatieObservers(this);
        initListView();
        updateConsultatii();
    }

    @FXML
    public void setController(AppController controller){
        this.controller = controller;
    }

    private void updateConsultatii() {

        List<Consultatie> consultatieList = (List<Consultatie>) controller.findAllConsultatiiForMedic(this.medic);
        consultatieObservableList = FXCollections.observableList(consultatieList);
        consultatiiListView.setItems(consultatieObservableList);

    }

    private void initListView() {

        consultatiiListView.setCellFactory(consultatiiListView -> new ListCell<>(){
            @Override
            protected void updateItem(Consultatie item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null){
                    setText("");
                }
                else {
                    setText(item.getNumePacient() + " " + item.getData() + " " + item.getOra());
                }
            }
        });

    }


    @Override
    public void update(ConsultatieEvent event) {
        updateConsultatii();
    }
}
