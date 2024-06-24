/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class EleccionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Pane vender;
    @FXML
    private Pane editar;
    @FXML
    private Pane comprar;
    @FXML
    private Text cerrar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vender.setOnMouseClicked(event -> {
            try {
                venderLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        comprar.setOnMouseClicked(event -> {
            try {
                comprarLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        editar.setOnMouseClicked(event -> {
            try {
                editarLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        cerrar.setOnMouseClicked(event -> {
            try {
                cerrarLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }  

    @FXML
    void venderLink(MouseEvent event) throws IOException {
        App.setRoot("Vender");
    }
    @FXML
    void comprarLink(MouseEvent event) throws IOException {
        App.setRoot("CrearUser");
    }
    @FXML
    void cerrarLink(MouseEvent event) throws IOException {
        App.setRoot("Inicio");
    }
    @FXML
    void editarLink(MouseEvent event) throws IOException {
        App.setRoot("Inicio");
    }
}
