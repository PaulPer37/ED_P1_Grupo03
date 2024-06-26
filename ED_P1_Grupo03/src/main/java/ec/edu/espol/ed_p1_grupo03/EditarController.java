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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
    @FXML
    private Text volver;
    @FXML
    private FlowPane base;
    @FXML
    private Text texto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        App clas=new App();
        Usuario usuario = clas.getUsuarioActual() ;
        // TODO
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        // cuantos carros tiene el usuario?
        //mostrarMisCarros(usuario.getVehiculos());
    }    
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }
    
    public void mostrarMisCarros(LinkedList<Vehiculo> vehiculos){
        if(vehiculos.isEmpty()){
            texto.setText("No tienen ningún Vehiculo para editar.");
        }else{
            for(Vehiculo v: vehiculos){
                texto.setText("Editar.");
                HBox bx=new HBox();
                bx.setPadding(new Insets(10)); 
                bx.setSpacing(10); 
                bx.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: lightgray;"); 
                Label lb = new Label(v.getMarca()+"\n"+v.getModelo());
                ImageView img = new ImageView(v.getFotos().get(0));
                img.setFitHeight(50); // Ajustar la altura de la imagen
                img.setFitWidth(50); // Ajustar la anchura de la imagen
                bx.getChildren().add(lb);
                bx.getChildren().add(img);
                bx.setOnMouseClicked(event -> mostrarPanelEdicion(v));
                base.getChildren().add(bx);
            }
        }
    }
    
    public void mostrarPanelEdicion(Vehiculo vehiculo){
        //base.getChildren().clear();
        
        Stage editStage = new Stage();
        editStage.setTitle("Editar Vehículo");
        TextField precioField = new TextField(String.valueOf(vehiculo.getPrecio()));
        TextField marcaField = new TextField(vehiculo.getMarca());
        TextField modeloField = new TextField(vehiculo.getModelo());
        TextField añoField = new TextField(String.valueOf(vehiculo.getYear()));
        TextField kilometrajeField = new TextField(String.valueOf(vehiculo.getKilometraje()));
        TextField motorField = new TextField(vehiculo.getMotor());
        TextField transmisionField = new TextField(vehiculo.getTransmision());
        TextField pesoField = new TextField(String.valueOf(vehiculo.getPeso()));
        TextField ubicacionField = new TextField(vehiculo.getUbicacion());
        
        Button guardar = new Button("Guardar");

            // Crear un GridPane para organizar los controles
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        // Añadir los controles al GridPane
        grid.add(new Label("Precio:"), 0, 0);
        grid.add(precioField, 1, 0);
        grid.add(new Label("Marca:"), 0, 1);
        grid.add(marcaField, 1, 1);
        grid.add(new Label("Modelo:"), 0, 2);
        grid.add(modeloField, 1, 2);
        grid.add(new Label("Año:"), 0, 3);
        grid.add(añoField, 1, 3);
        grid.add(new Label("Kilometraje:"), 0, 4);
        grid.add(kilometrajeField, 1, 4);
        grid.add(new Label("Motor:"), 0, 5);
        grid.add(motorField, 1, 5);
        grid.add(new Label("Transmisión:"), 0, 6);
        grid.add(transmisionField, 1, 6);
        grid.add(new Label("Peso:"), 0, 7);
        grid.add(pesoField, 1, 7);
        grid.add(new Label("Ubicación:"), 0, 8);
        grid.add(ubicacionField, 1, 8);
        grid.add(guardar, 1, 9);
    }
}
