/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jkrom
 */
public class FavoritoController implements Initializable {

    @FXML
    private TextField marca;
    @FXML
    private Button Guardar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }


    @FXML
    private void guardar(ActionEvent event) {
    String palabra = marca.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("favoritos"+App.getUsuarioActual().getID() + ".txt", true))) {
            writer.write(palabra);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        marca.clear();
    }
}
