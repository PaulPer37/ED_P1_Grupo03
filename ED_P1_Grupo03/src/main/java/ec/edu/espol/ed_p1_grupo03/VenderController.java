/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class VenderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField precio;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private TextField anio;

    @FXML
    private TextField kilometraje;

    @FXML
    private TextField motor;
    @FXML
    private TextField transmision;
    @FXML
    private TextField ubicacion;
    @FXML
    private TextField peso;

    @FXML
    private Button cargar;
    
    @FXML
    private Button crear;

    @FXML
    private ImageView ver;

    private LinkedList<String> fotos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fotos = new LinkedList<>();
        cargar.setOnAction(event -> cargarImagen());
        crear.setOnAction(event -> crearVehiculo());
    }

    private void cargarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(cargar.getScene().getWindow());
        if (file != null) {
            try {
                // Guardar imagen en la ubicación deseada
                File destFile = new File("../carros/" + file.getName());
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                fotos.addLast(destFile.getAbsolutePath());
                ver.setImage(new Image(destFile.toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void crearVehiculo() {
        try {
            String marcaStr = marca.getText();
            String modeloStr = modelo.getText();
            String transmisionStr = transmision.getText();
            int anioInt = Integer.parseInt(anio.getText());
            double precioDouble = Double.parseDouble(precio.getText());
            double pesoDouble = Double.parseDouble(peso.getText());
            int kilometrajeInt = Integer.parseInt(kilometraje.getText());
            String motorStr = motor.getText();
            String ubicacionStr = ubicacion.getText();

            Vehiculo vehiculo = new Vehiculo(marcaStr, modeloStr, anioInt, precioDouble, kilometrajeInt, motorStr, ubicacionStr, fotos);

            Usuario usuarioActual = App.getUsuarioActual();
            if (usuarioActual != null) {
                usuarioActual.agregarVehiculo(vehiculo);
            }
        } catch (NumberFormatException e) {
            // Manejar la excepción si hay un error en la conversión de texto a número
            e.printStackTrace();
        }
    }
}
