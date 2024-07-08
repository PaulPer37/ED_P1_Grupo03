/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DHAMAR
 */
public class AñadirServicioController implements Initializable {
    App ap = new App();
    
    @FXML
    private ComboBox<TipoServicio> tiposerv;
    @FXML
    private TextField descripcion;
    @FXML
    private TextField costo;
    private Text volver;
    private Servicio servicioAñadido;
    private TextArea texto1;
    @FXML
    private TextField fecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        */
        tiposerv.getItems().setAll(TipoServicio.values());
        
    }

    @FXML
    private void guardar(MouseEvent event) {
        TipoServicio tipoServicio = tiposerv.getValue();
        String descripcionText = descripcion.getText();
        String fechaText = fecha.getText();
        Double costoText = Double.parseDouble(costo.getText());
        Servicio servicio = new Servicio(fechaText,descripcionText, tipoServicio, costoText);
        App.getListaServicio().addLast(servicio);
        
        limpiarCamposEdicion();
        
    }
        
    void volverLink(MouseEvent event) throws IOException {
        if(App.getEstado().equals("editar")){
            App.setRoot("Editar");
        }
        if(App.getEstado().equals("vender")){
            App.setRoot("Vender");
        }
    }
    
    private void limpiarCamposEdicion() {
        descripcion.clear();
        fecha.clear();
        costo.clear();
        tiposerv.getSelectionModel().clearSelection();
    }  
}
