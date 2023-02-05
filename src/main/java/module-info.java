module com.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.clinica to javafx.fxml;
    exports com.example.clinica;

    opens com.example.clinica.models to javafx.fxml;
    exports com.example.clinica.models;

    opens com.example.clinica.utils.utils to javafx.fxml;
    exports com.example.clinica.utils.utils;

    opens com.example.clinica.controller to javafx.fxml;
    exports com.example.clinica.controller;

    opens com.example.clinica.controller.UIControllers to javafx.fxml;
    exports com.example.clinica.controller.UIControllers;
}