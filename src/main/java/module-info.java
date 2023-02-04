module com.example.clinica {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clinica to javafx.fxml;
    exports com.example.clinica;
}