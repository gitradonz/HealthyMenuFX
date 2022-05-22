module com.example.healthymenu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.healthymenu to javafx.fxml;
    exports com.example.healthymenu;
    exports com.example.healthymenu.model;

    opens com.example.healthymenu.model to javafx.fxml;
}