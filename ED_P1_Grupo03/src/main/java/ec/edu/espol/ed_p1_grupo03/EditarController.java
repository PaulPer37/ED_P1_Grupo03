/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import static ec.edu.espol.ed_p1_grupo03.Vehiculo.cargarListaCarros;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */

public class EditarController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    private Vehiculo vehiculoSeleccionado;
    private Map<String, Vehiculo> vehiculoMap;
    LinkedList<Vehiculo> listacarros;
    @FXML
    private Text volver;
    @FXML
    private Text texto;
    @FXML
    private TextField marcaStr;
    @FXML
    private TextField modeloStr;
    @FXML
    private TextField yearint;
    @FXML
    private TextField transmisiontext;
    @FXML
    private TextField ubicacionStr;
    @FXML
    private TextField precioDou;
    @FXML
    private TextField kilometro;
    @FXML
    private TextField motorStr;
    @FXML
    private TextField pesoDour;
    @FXML
    private ComboBox<String> cambovehiculos;
    @FXML
    private Button guardar;
    @FXML
    private Text texto1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App clas=new App();
        Usuario usuario = clas.getUsuarioActual();
        listacarros = cargarListaCarros("vehiculos"+usuario.getID()+".txt");
        vehiculoMap = new HashMap<>();
        // TODO
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
         int cont=0;
        for(Vehiculo v: usuario.getVehiculos()){
           
            System.out.println(v.getModelo());
            cont++;
        }
        System.out.println(""+cont);
        // cuantos carros tiene el usuario?
        for (int i = 0; i < listacarros.size(); i++) {
            Vehiculo v = listacarros.get(i);
            String textItem = v.getMarca()+" - "+v.getModelo() + " (" + v.getYear() + ")";
            cambovehiculos.getItems().add(textItem);
            vehiculoMap.put(textItem, v);
        }
        cambovehiculos.setOnAction(event -> seleccionarVehiculo());
        habilitarCamposEdicion(false);
    }    
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }
    
    @FXML
    private void guardar(MouseEvent event) {
        String marca = marcaStr.getText();
        String modelo= modeloStr.getText();
        Integer year = Integer.parseInt(yearint.getText());
        String transmision = transmisiontext.getText();
        String ubicacion = ubicacionStr.getText();
        Double precio = Double.parseDouble(precioDou.getText());
        Integer kilometraje = Integer.parseInt(kilometro.getText());
        String motor =motorStr.getText();
        Double peso = Double.parseDouble(pesoDour.getText());
        //Vehiculo veditado = new Vehiculo(vehiculoSeleccionado.getId()), marca, modelo, year,precio,kilometraje,motor, transmision, peso, motor);
        
        
    }

    private void seleccionarVehiculo() {
        String selected = cambovehiculos.getSelectionModel().getSelectedItem();
        //Vehiculo vehiculoSeleccionado = vehiculoMap.get(selected);
        vehiculoSeleccionado = vehiculoMap.get(selected);
        if (vehiculoSeleccionado != null) {
            marcaStr.setText(vehiculoSeleccionado.getMarca());
            modeloStr.setText(vehiculoSeleccionado.getModelo());
            yearint.setText(String.valueOf(vehiculoSeleccionado.getYear()));
            transmisiontext.setText(vehiculoSeleccionado.getTransmision());
            ubicacionStr.setText(vehiculoSeleccionado.getUbicacion());
            precioDou.setText(String.valueOf(vehiculoSeleccionado.getPrecio()));
            kilometro.setText(String.valueOf(vehiculoSeleccionado.getKilometraje()));
            motorStr.setText(vehiculoSeleccionado.getMotor());
            pesoDour.setText(String.valueOf(vehiculoSeleccionado.getPeso()));
            habilitarCamposEdicion(true);
            
        } else {
            limpiarCamposEdicion();
            habilitarCamposEdicion(false);
        }
    }

    private void habilitarCamposEdicion(boolean habilitar) {
        marcaStr.setDisable(!habilitar);
        modeloStr.setDisable(!habilitar);
        yearint.setDisable(!habilitar);
        transmisiontext.setDisable(!habilitar);
        ubicacionStr.setDisable(!habilitar);
        precioDou.setDisable(!habilitar);
        kilometro.setDisable(!habilitar);
        motorStr.setDisable(!habilitar);
        pesoDour.setDisable(!habilitar);
        guardar.setDisable(!habilitar);
    }
    
    private void limpiarCamposEdicion() {
        marcaStr.clear();
        modeloStr.clear();
        yearint.clear();
        transmisiontext.clear();
        ubicacionStr.clear();
        precioDou.clear();
        kilometro.clear();
        motorStr.clear();
        pesoDour.clear();
    }
    
  
}
