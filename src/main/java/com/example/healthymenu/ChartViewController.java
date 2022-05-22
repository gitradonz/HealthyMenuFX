package com.example.healthymenu;

import com.example.healthymenu.model.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartViewController {

    @FXML
    PieChart pieChart;

    //Método para cargar ,rellenar y mostrar el chart.
    public void initialize(){

        //Declaramos nuestro loader padre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        try {
            Parent root = (Parent) loader.load();
        } catch (Exception e) {}

        //Cargamos el controlador de nuestra otra clase para obtener mediante el método getComida nuestro dato,
        //Posteriormente lo añadimos a nuestro chart y hacemos un mapa con nuestras categorias y pesos.
        FXMLDocumentController controller = loader.getController();
        List<Food> food = controller.getComida();
        pieChart.getData().clear();
        Map<String, Integer> result;
        result = food.stream().collect(Collectors.groupingBy
                (f -> f.getCategory(),Collectors.summingInt(f -> f.getWeight())));

        //Añadimos las entradas a nuestro chart
        result.forEach((cat, sum) -> {pieChart.getData().add(new PieChart.Data(cat, sum));});
    }

    //Método para regresar a la pantalla principal
    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FXMLDocument.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }


}
