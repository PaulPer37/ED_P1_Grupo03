/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author DHAMAR
 */
public class AñadirServicioController implements Initializable {
    
    @FXML
    private Text volver;
    @FXML
    private ComboBox<TipoServicio> tiposerv;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField decha;
    @FXML
    private TextField costo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        tiposerv.getItems().setAll(TipoServicio.values());
        
    }    

    @FXML
    private void guardar(MouseEvent event) {
    
    }
    
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Editar");
    }
}
