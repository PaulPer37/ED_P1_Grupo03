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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class RemoverController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Vehiculo vehiculo;
    private int pagina = 0;
    private LinkedList<String> imagenes = new LinkedList<>();
    
    @FXML
    private Label marcaDetalle;
    @FXML
    private Label modeloDetalle;
    @FXML
    private Label transmisionDetalle;
    @FXML
    private Label añoDetalle;
    @FXML
    private Label precioDetalle;
    @FXML
    private Button siguiente;
    @FXML
    private Button anterior;
    @FXML
    private Text volver;
    @FXML
    private ImageView listaImagenes;
    Usuario usuario;
    private Vehiculo vehiculoSeleccionado;
    LinkedList<Vehiculo> listacarros;
    @FXML
    private ComboBox<String> cambovehiculos;
    private Map<String, Vehiculo> vehiculoMap;
    @FXML
    private Button borrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = App.getUsuarioActual();
        listacarros = cargarListaCarros("vehiculos"+usuario.getID()+".txt");
        
        
        vehiculoMap = new HashMap<>();
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

    
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
    private void seleccionarVehiculo() {
        String selected = cambovehiculos.getSelectionModel().getSelectedItem();
        //Vehiculo vehiculoSeleccionado = vehiculoMap.get(selected);
        vehiculo = vehiculoMap.get(selected);
        imagenes = vehiculo.getFotos();
        mostrarDetalles();
    }

    private void mostrarDetalles() {
        marcaDetalle.setText(vehiculo.getMarca());
        modeloDetalle.setText(vehiculo.getModelo());
        transmisionDetalle.setText(vehiculo.getTransmision());
        añoDetalle.setText(String.valueOf(vehiculo.getYear()));
        precioDetalle.setText("$" + vehiculo.getPrecio());

        if (!imagenes.isEmpty()) {
            mostrarImagen();
        } else {
            System.err.println("El vehículo no tiene imágenes.");
        }
    }

    @FXML
    private void siguiente(ActionEvent event) {
        if ((pagina + 1) < imagenes.size()) {
            pagina++;
            mostrarImagen();
        }
    }

    @FXML
    private void anterior(ActionEvent event) {
        if (pagina > 0) {
            pagina--;
            mostrarImagen();
        }
    }

    private void mostrarImagen() {
        if (pagina >= 0 && pagina < imagenes.size()) {
            String imagenPath = imagenes.get(pagina);
            System.out.println("Intentando cargar imagen: " + imagenPath);
            URL imageUrl = getClass().getResource("/" + imagenPath);

            if (imageUrl != null) {
                String imagePath = imageUrl.toExternalForm();
                Image image = new Image(imagePath);
                listaImagenes.setImage(image);
            } else {
                System.err.println("No se encontró la imagen en la ruta: " + imagenPath);
            }
        } else {
            System.err.println("Índice de imagen fuera de límites: " + pagina);
        }
    }
    @FXML
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }
    @FXML
    private void borrar(MouseEvent event) {
        
    }
    
}
