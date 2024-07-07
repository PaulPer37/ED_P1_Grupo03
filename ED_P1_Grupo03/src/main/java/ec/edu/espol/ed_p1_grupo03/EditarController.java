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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    Vehiculo v=new Vehiculo();
    Usuario usuario;
    /**
     * Initializes the controller class.
     */
    
    private Vehiculo vehiculoSeleccionado;
    Map<String, Vehiculo> vehiculoMap;
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
    @FXML
    private Button serviciosb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usuario = App.getUsuarioActual();
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
    try {
        String marca = marcaStr.getText();
        String modelo = modeloStr.getText();
        int year = Integer.parseInt(yearint.getText());
        String transmision = transmisiontext.getText();
        String ubicacion = ubicacionStr.getText();
        double precio = Double.parseDouble(precioDou.getText());
        int kilometraje = Integer.parseInt(kilometro.getText());
        String motor = motorStr.getText();
        double peso = Double.parseDouble(pesoDour.getText());

        Vehiculo veditado = new Vehiculo(
            vehiculoSeleccionado.getId(), marca, modelo, year, precio, 
            kilometraje, motor, transmision, peso, 
            ubicacion, vehiculoSeleccionado.getFotos(), App.getListaServicio());
        
        v.editarVehiculo(veditado, "vehiculos" + usuario.getID() + ".txt", usuario);
        
        // Actualizar la lista de vehículos
        listacarros = cargarListaCarros("vehiculos" + usuario.getID() + ".txt");
        
        // Limpiar y llenar nuevamente la ComboBox
        cambovehiculos.getItems().clear();
        vehiculoMap.clear();
        for (Vehiculo v : listacarros) {
            String textItem = v.getMarca() + " - " + v.getModelo() + " (" + v.getYear() + ")";
            cambovehiculos.getItems().add(textItem);
            vehiculoMap.put(textItem, v);
        }
        
        // Seleccionar el vehículo editado
        String selectedItem = veditado.getMarca() + " - " + veditado.getModelo() + " (" + veditado.getYear() + ")";
        cambovehiculos.getSelectionModel().select(selectedItem);
        
        texto1.setText("Vehículo editado correctamente.");
        limpiarCamposEdicion();
        habilitarCamposEdicion(false);
    } catch (NumberFormatException e) {
        texto1.setText("Por favor, introduce valores válidos.");
    }
}
    

    private void seleccionarVehiculo() {
        String selected = cambovehiculos.getSelectionModel().getSelectedItem();
        //Vehiculo vehiculoSeleccionado = vehiculoMap.get(selected);
        vehiculoSeleccionado = vehiculoMap.get(selected);
        App.setVehiculoSelect(vehiculoSeleccionado);
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
        serviciosb.setDisable(!habilitar);
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

    @FXML
    private void añadirServicio(MouseEvent event) throws IOException {
        App.setEstado("editar");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("EditarServicio.fxml"));
        Parent root = fxmlLoader.load();

        // Crea una nueva escena y un nuevo Stage (ventana)
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setTitle("Editar Servicio");
        newStage.setScene(scene);

        // Muestra la nueva ventana
        newStage.show();
        
    }
}
