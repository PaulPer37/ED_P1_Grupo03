/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class ComprarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<String> marcas;
    @FXML
    private ComboBox<String> modelos;
    @FXML
    private ComboBox<String> trans;
    @FXML
    private ComboBox<String> anios;
    @FXML
    private ComboBox<String> orden;
    @FXML
    private ListView<Label> lista;
    @FXML
    private Button anterior;
    @FXML
    private Button siguiente;
    @FXML
    private Text volver;
    private LinkedList<Vehiculo> vehiculos;
    private int pagina;
    private final int items = 5;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        vehiculos = Vehiculo.cargarListaCarros("carros.txt");
        mostrarvehiculo();
    }    
    @FXML
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }

    private void mostrarvehiculo() {
    lista.getItems().clear();
    LinkedList<Vehiculo> paginaActual = vehiculos.sublist(pagina * items, (pagina + 1) * items);
        System.out.println(paginaActual);
    // Mostrar los vehículos en el ListView
    paginaActual.forEach(vehiculo -> {
        Label label = new Label(vehiculo.getMarca() + " " + vehiculo.getModelo() + " - $" + vehiculo.getPrecio());
        // Configurar evento de clic en cada vehículo
        label.setOnMouseClicked(event -> {
            // Acción al hacer clic en el vehículo (redireccionar, por ejemplo)
            System.out.println("Clic en " + vehiculo.getMarca() + " " + vehiculo.getModelo());
        });
        lista.getItems().add(label); // Agregar el Label creado a la lista
    });
    }

    
    @FXML
    private void filtro(ActionEvent event) {
        // Implementación del método para manejar el evento de filtro
    }
    @FXML
    private void orden(ActionEvent event) {
        // Implementación del método para manejar el evento de filtro
    }
    @FXML
    private void anterior(ActionEvent event) {
        // Implementación del método para manejar el evento de filtro
    }
    @FXML
    private void siguiente(ActionEvent event) {
        // Implementación del método para manejar el evento de filtro
    }
}
