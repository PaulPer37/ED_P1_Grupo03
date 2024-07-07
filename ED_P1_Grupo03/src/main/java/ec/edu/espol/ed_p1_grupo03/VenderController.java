/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class VenderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField precio;

    @FXML
    private TextField marca;

    @FXML
    private TextField modelo;

    @FXML
    private TextField anio;

    @FXML
    private TextField kilometraje;

    @FXML
    private TextField motor;
    @FXML
    private TextField transmision;
    @FXML
    private TextField ubicacion;
    @FXML
    private TextField peso;

    @FXML
    private Button cargar;
    
    @FXML
    private Button crear;

    @FXML
    private ImageView ver;

    private LinkedList<String> fotos;
    @FXML
    private Text volver;
    @FXML
    private Button servicios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fotos = new LinkedList<>();
        cargar.setOnAction(event -> cargarImagen());
        servicios.setOnAction(event -> {
            try {
                servicio();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        crear.setOnAction(event -> crearVehiculo());
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void cargarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(cargar.getScene().getWindow());
        if (file != null) {
            try {
                // Guardar la imagen en el directorio de recursos relativo
                String resourceDir = "src/main/resources/ec/edu/espol/carros/";

                // Copiar la imagen al directorio de recursos
                File destDir = new File(resourceDir);
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }

                // Obtener solo el nombre del archivo sin la ruta completa
                String fileName = file.getName();

                // Crear ruta relativa dentro del proyecto
                String relativePath = "ec/edu/espol/carros/" + fileName;

                // Copiar el archivo al directorio de recursos
                Path sourcePath = file.toPath();
                Path destinationPath = Paths.get(resourceDir + fileName);
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Agregar la ruta relativa a la lista de fotos
                fotos.addLast(relativePath);

                // Mostrar la imagen en la vista previa
                ver.setImage(new Image(getClass().getResource("/" + relativePath).toExternalForm()));

                System.out.println("Imagen guardada en: " + relativePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al guardar la imagen.");
            }
        } else {
            System.out.println("No se seleccionó ninguna imagen.");
        }
    }

    private void crearVehiculo() {
        try {
             String marcaStr = marca.getText();
            String modeloStr = modelo.getText();
            String transmisionStr = transmision.getText();
            int anioInt = Integer.parseInt(anio.getText());
            double precioDouble = Double.parseDouble(precio.getText());
            double pesoDouble = Double.parseDouble(peso.getText());
            int kilometrajeInt = Integer.parseInt(kilometraje.getText());
            String motorStr = motor.getText();
            String ubicacionStr = ubicacion.getText();

            // Crear el vehículo con la lista de fotos
            
            Vehiculo vehiculo = new Vehiculo(marcaStr, modeloStr, anioInt, precioDouble, kilometrajeInt,
                    motorStr, transmisionStr, pesoDouble, ubicacionStr, fotos,App.getListaServicio());
            Usuario usuarioActual = App.getUsuarioActual();
            if (usuarioActual != null) {
                usuarioActual.agregarVehiculo(vehiculo);
                String rutaArchivoCarros = "carros.txt";
                Vehiculo.guardarVehiculoEnArchivo(vehiculo, rutaArchivoCarros);
            }
            limpiarCampos();
            App.setListaServicio(new LinkedList<>());
            System.out.println("Vehículo creado y guardado correctamente");

        } catch (NumberFormatException e) {
            // Manejar la excepción si hay un error en la conversión de texto a número
            e.printStackTrace();
            System.out.println("Error en la conversión de texto a número.");
        }
        Vehiculo.copiarVehiculosDeUsuarios("usuarios.txt", "carros.txt");
    }

    // Método para limpiar campos después de crear el vehículo
    private void limpiarCampos() {
        marca.clear();
        modelo.clear();
        anio.clear();
        precio.clear();
        kilometraje.clear();
        motor.clear();
        transmision.clear();
        peso.clear();
        ubicacion.clear();
        ver.setImage(null);
    }

    @FXML
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }
    @FXML
    void servicio() throws IOException {
        App.setEstado("vender");
        App.setRoot("AñadirServicio");
    }
}
    
