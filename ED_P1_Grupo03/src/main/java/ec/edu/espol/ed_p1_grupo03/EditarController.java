/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import static ec.edu.espol.ed_p1_grupo03.Vehiculo.cargarListaCarros;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */

public class EditarController implements Initializable {
    Vehiculo v=new Vehiculo();
    Usuario usuario;
    //private LinkedList<String> imagenes = new LinkedList<>();
    /**
     * Initializes the controller class.
     */
    private int pagina;
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
    @FXML
    private Button editar;
    @FXML
    private ImageView cargar;
    @FXML
    private ImageView eliminar;
    //private LinkedList<String> fotos;
    @FXML
    private ImageView flecha2;
    @FXML
    private ImageView fecha1;
    @FXML
    private ImageView imagenes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarIconos(false);
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
            ubicacion, vehiculoSeleccionado.getFotos(),  App.getVehiculoSelect().getServicio());
        
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
    mostrarIconos(false);
    Vehiculo.copiarVehiculosDeUsuarios("usuarios.txt", "carros.txt");
}
    

    private void seleccionarVehiculo() {
        String selected = cambovehiculos.getSelectionModel().getSelectedItem();
        
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
        editar.setDisable(!habilitar);
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

    @FXML
    private void editarImagen(MouseEvent event) {
        mostrarIconos(true);
        mostrarImagen();
        
        
    }

    @FXML
    private void cargarImagen(MouseEvent event) {
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
                vehiculoSeleccionado.getFotos().addLast(relativePath);

                System.out.println("Imagen guardada en: " + relativePath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al guardar la imagen.");
            }
        } else {
            System.out.println("No se seleccionó ninguna imagen.");
        }
        mostrarImagen();
    }

    @FXML
    private void eliminarImagen(MouseEvent event) {
        vehiculoSeleccionado.getFotos().remove(pagina);
    }

    @FXML
    private void siguiente(MouseEvent event) {
        if ((pagina + 1) < vehiculoSeleccionado.getFotos().size()) {
            pagina++;
            mostrarImagen();
        }
    }

    @FXML
    private void anterior(MouseEvent event) {
        if (pagina > 0) {
            pagina--;
            mostrarImagen();
        }
    }
    
    void mostrarIconos (boolean halilitar){
        cargar.setVisible(halilitar);
        eliminar.setVisible(halilitar);
        fecha1.setVisible(halilitar);
        flecha2.setVisible(halilitar);
        imagenes.setVisible(halilitar);
    }
    void mostrarImagen(){
        if (pagina >= 0 && pagina < vehiculoSeleccionado.getFotos().size()) {
            String imagenPath = vehiculoSeleccionado.getFotos().get(pagina);
            System.out.println("Intentando cargar imagen: " + imagenPath);
            URL imageUrl = getClass().getResource("/" + imagenPath);

            if (imageUrl != null) {
                String imagePath = imageUrl.toExternalForm();
                Image image = new Image(imagePath);
                imagenes.setImage(image);
            } else {
                System.err.println("No se encontró la imagen en la ruta: " + imagenPath);
            }
        } else {
            System.err.println("Índice de imagen fuera de límites: " + pagina);
        }
    }
}
