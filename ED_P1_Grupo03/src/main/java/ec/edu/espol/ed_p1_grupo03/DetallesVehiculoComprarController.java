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
import javafx.scene.input.MouseEvent;
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
    private ImageView listaImagenes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculo = App.getCarrocomprar(); // Obtener el vehículo desde tu método getSeleccionado()

        if (vehiculo != null) {
            imagenes = vehiculo.getFotos(); // Obtener las imágenes del vehículo si vehiculo no es nulo
            if (imagenes != null && !imagenes.isEmpty()) {
                mostrarDetalles();
                mostrarImagen();
            } else {
                System.err.println("El vehículo no tiene imágenes o la lista de imágenes es nula.");
            }
        } else {
            System.err.println("El vehículo seleccionado es nulo.");
        }

        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void mostrarDetalles() {
        if (vehiculo != null) {
            marcaDetalle.setText(vehiculo.getMarca());
            modeloDetalle.setText(vehiculo.getModelo());
            transmisionDetalle.setText(vehiculo.getTransmision());
            añoDetalle.setText(String.valueOf(vehiculo.getYear()));
            precioDetalle.setText("$" + vehiculo.getPrecio());
        } else {
            System.err.println("El vehículo es nulo en mostrarDetalles.");
        }
    }

    @FXML
    private void siguiente(ActionEvent event) {
        if ((pagina + 1) < imagenes.size()) {
            pagina++;
            mostrarImagen();
        } else {
            System.err.println("No hay más imágenes para mostrar.");
        }
    }

    @FXML
    private void anterior(ActionEvent event) {
        if (pagina > 0) {
            pagina--;
            mostrarImagen();
        } else {
            System.err.println("No hay imágenes anteriores para mostrar.");
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
        App.setRoot("Comprar");
    }
}