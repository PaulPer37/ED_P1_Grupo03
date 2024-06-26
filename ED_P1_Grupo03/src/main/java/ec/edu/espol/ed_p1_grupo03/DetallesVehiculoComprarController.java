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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author jkrom
 */
public class DetallesVehiculoComprarController implements Initializable {

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
    private ListView<ImageView> listaImagenes;

    public void initialize(URL url, ResourceBundle rb) {
        vehiculo = ComprarController.getSeleccionado(); // Obtener el vehículo desde tu método getSeleccionado()
        if (vehiculo != null) {
            imagenes = vehiculo.getFotos(); // Obtener las imágenes del vehículo si vehiculo no es nulo
            mostrarDetalles();
            mostrarImagen();
        }
    }

    private void mostrarDetalles() {
        marcaDetalle.setText(vehiculo.getMarca());
        modeloDetalle.setText(vehiculo.getModelo());
        transmisionDetalle.setText(vehiculo.getTransmision());
        añoDetalle.setText(String.valueOf(vehiculo.getYear()));
        precioDetalle.setText("$" + vehiculo.getPrecio());
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
        listaImagenes.getItems().clear();
        URL imageUrl = getClass().getResource("/ec.edu.espol.carros/" + imagenes.get(pagina) + ".jpg");

        if (imageUrl != null) {
            // Convertir la URL a una cadena de texto si es necesario
            String imagePath = imageUrl.toExternalForm();

            // Usar la URL para cargar la imagen en un ImageView, por ejemplo
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            listaImagenes.getItems().clear();
            listaImagenes.getItems().add(imageView);
            // Agregar el ImageView a tu escena o donde sea necesario
        } else {
            System.err.println("No se encontró la imagen en el directorio 'carros'");
        }

    }
}
