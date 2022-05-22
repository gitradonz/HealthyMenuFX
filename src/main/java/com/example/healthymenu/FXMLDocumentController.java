package com.example.healthymenu;

import com.example.healthymenu.model.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class FXMLDocumentController {

    ObservableList<Food> comida;
    List <Food> textoComida;

    @FXML
    private TableView<Food> tableFood;
    @FXML
    private TableColumn<Food, String> colNomComida;
    @FXML
    private TableColumn<Food, String> colCategoria;
    @FXML
    private TableColumn<Food, Integer> colPesoG;
    @FXML
    private TableColumn<Food, Float> colPesoOz;
    @FXML
    private TextField lbNombreComida;
    @FXML
    private ChoiceBox<String> selectCat;
    @FXML
    private Button btBorrar;
    @FXML
    private TextField textoPeso;
    @FXML
    private Button btAdd;


    //Lista de acciones a realizar cuando se inicializa el programa (Rellenar las categorias, leemos el fichero de comidas
    //rellenamos la lista de comida con lo leido...)
    @FXML
    public void initialize() {
        selectCat.setItems(FXCollections.observableArrayList("Fruits and vegetables", "Meat and fish", "Milk derivatives", "Other"));
        comida = FXCollections.observableArrayList();
        textoComida = readFile();
        for(int i = 0; i< textoComida.size(); i++)
                    comida.add(new Food(textoComida.get(i).getName(),
                    textoComida.get(i).getCategory(),
                    textoComida.get(i).getWeight()));
        colNomComida.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPesoG.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colPesoOz.setCellValueFactory(new PropertyValueFactory<>("weightInOz"));
        tableFood.setItems(comida);
    }


    //Con este método leemos el contenido del fichero food.rtf, lo separamos mediante ";"
    // y lo guardamos en textoComida para acceder posteriormente.
    private static List<Food> readFile() {
        try {
            return Files.lines(Paths.get("food.rtf")).map(line ->
                    new Food(line.split(";")[0],line.split(";")[1],
                    Integer.parseInt(line.split(";")[2]))).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Método para obtener la comida de la lista
    public List<Food> getComida() {
        return comida;
    }

    //Método con control de campos vacios que nos añade a la lista de comida la seleccion del nuevo alimento
    //y nos lo guarda mediante saveFile
    @FXML
    private void addFood(ActionEvent event) {
        if (lbNombreComida.getText().equals("") ||
                selectCat.getSelectionModel().getSelectedIndex() < 0 ||
                textoPeso.getText().equals(""))
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setContentText("Los campos no pueden estar vacios");
            dialog.setHeaderText("Error añadiendo datos");
            dialog.showAndWait();
        }else{
            comida.add(new Food(
                lbNombreComida.getText(),
                selectCat.getSelectionModel().getSelectedItem(),
                Integer.parseInt(textoPeso.getText())));
            saveFile(comida);
        }
    }

    //Método para borrar comida de la list
    @FXML
    private void delFood(ActionEvent event) {

        Food selectedFood = tableFood.getSelectionModel().getSelectedItem();
        comida.remove(selectedFood);
        saveFile(comida);
    }


    //Método para guardar nuestra lista de alimentos en el archivo food.rtf
    private static void saveFile(List<Food> food) {
        try (PrintWriter pw = new PrintWriter("food.rtf")) {
            food.stream().forEach(f -> pw.println(f.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Método para acceder a la vista del ChartView
    @FXML
    private void goToChartView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChartView.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }

}
