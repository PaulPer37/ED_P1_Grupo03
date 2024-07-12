/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class
 *
 * @author jkrom
 */
public class EliminarFavoritoController implements Initializable {

    @FXML
    private ListView<String> favoritos;
    @FXML
    private Button EliminarMarca;

    /**
     * Initializes the controller class.
     */
    LinkedList<String> lista = ComprarController.leerMarcasFavoritas("favoritos" + App.getUsuarioActual().getID() + ".txt");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        favoritos.getItems().clear();
        for (String marca : lista) {
            favoritos.getItems().add(marca);
        }
        favoritos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        EliminarMarca.setOnAction(event -> {
            // Obtener el ítem seleccionado
            String selectedItem = favoritos.getSelectionModel().getSelectedItem();

            // Si hay un ítem seleccionado, eliminarlo
            if (selectedItem != null) {
                favoritos.getItems().remove(selectedItem);
                lista.remove(selectedItem);
                // Reescribir el archivo con las marcas actualizadas
                GuardarFavoritos();
            }
        });
    }

    private void GuardarFavoritos() {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("favoritos" + App.getUsuarioActual().getID() + ".txt");
            java.nio.file.Files.write(path, lista);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
