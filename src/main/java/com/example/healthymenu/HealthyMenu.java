package com.example.healthymenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HealthyMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HealthyMenu.class.getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 570, 440);
        stage.setTitle("HealthyMenu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}