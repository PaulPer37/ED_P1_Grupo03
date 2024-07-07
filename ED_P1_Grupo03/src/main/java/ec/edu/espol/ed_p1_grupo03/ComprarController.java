/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class ComprarController implements Initializable {

    @FXML
    private ComboBox<String> marcas;
    @FXML
    private ComboBox<String> modelos;
    @FXML
    private ComboBox<String> preciomin;
    @FXML
    private ComboBox<String> preciomax;
    @FXML
    private ComboBox<String> kilomin;
    @FXML
    private ComboBox<String> kilomax;
    @FXML
    private ComboBox<String> orden;
    @FXML
    private ListView<Label> lista;
    @FXML
    private Button anterior;
    @FXML
    private Button siguiente;
    @FXML
    private Text volver;
    private LinkedList<Vehiculo> vehiculos;
    private LinkedList<Vehiculo> vehiculosOriginales;
    private int pagina;
    private final int items = 5;
    @FXML
    private Button seleccionar;
    @FXML
    private Button resetButton; // Agrega esto
    @FXML
    private CheckBox Mantenimiento;
    @FXML
    private CheckBox Reparacion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Vehiculo.copiarVehiculosDeUsuarios("usuarios.txt", "carros.txt");
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        seleccionar.setOnMouseClicked(event -> {
            try {
                seleccionar(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
// Configuración de eventos para los CheckBox
        Mantenimiento.selectedProperty().addListener((observable, oldValue, newValue) -> filtro(null));
        Reparacion.selectedProperty().addListener((observable, oldValue, newValue) -> filtro(null));

        String rutaArchivoCarros = "carros.txt";
        vehiculos = Vehiculo.cargarListaCarros(rutaArchivoCarros);
        vehiculosOriginales = Vehiculo.cargarListaCarros(rutaArchivoCarros); // Guardar la lista original
        llenarComboBoxes(rutaArchivoCarros);
        mostrarvehiculo();

        resetButton.setOnMouseClicked(event -> resetFiltros()); // Configura el evento del botón reset
    }

    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }

    private void mostrarvehiculo() {
        lista.getItems().clear();
        LinkedList<Vehiculo> paginaActual = vehiculos.sublist(pagina * items, (pagina + 1) * items);
        System.out.println(paginaActual);
        paginaActual.forEach(vehiculo -> {
            Label label = new Label(vehiculo.getMarca() + "-" + vehiculo.getModelo() + " - $" + vehiculo.getPrecio());
            label.setOnMouseClicked(event -> {
                System.out.println("Clic en " + vehiculo.getMarca() + " " + vehiculo.getModelo());
                App.setCarrocomprar(vehiculo);
            });
            lista.getItems().add(label);
        });
    }

    @FXML
    private void filtro(ActionEvent event) {
        String marca = marcas.getValue();
        String modelo = modelos.getValue();
        String precioMin = preciomin.getValue();
        String precioMax = preciomax.getValue();
        String kmMin = kilomin.getValue();
        String kmMax = kilomax.getValue();
        String ordenarPor = orden.getValue();
        boolean seleccionarMantenimiento = Mantenimiento.isSelected();
        boolean seleccionarReparacion = Reparacion.isSelected();

        LinkedList<Vehiculo> filtrados = new LinkedList<>();
        for (Vehiculo vehiculo : vehiculos) {
            boolean pasaFiltro = true;
// Filtrar por mantenimiento si está seleccionado el CheckBox
            if (seleccionarMantenimiento) {
                boolean tieneMantenimiento = false;
                for (Servicio servicio : vehiculo.getServicio()) {
                    if (servicio.getTiposervicio() == TipoServicio.MANTENIMIENTO) {
                        tieneMantenimiento = true;
                        break;
                    }
                }
                if (!tieneMantenimiento) {
                    pasaFiltro = false;
                }
            }

            // Filtrar por reparación si está seleccionado el CheckBox
            if (seleccionarReparacion) {
                boolean tieneReparacion = false;
                for (Servicio servicio : vehiculo.getServicio()) {
                    if (servicio.getTiposervicio() == TipoServicio.REPARACION) {
                        tieneReparacion = true;
                        break;
                    }
                }
                if (!tieneReparacion) {
                    pasaFiltro = false;
                }
            }
            if (marca != null && !marca.equals(vehiculo.getMarca())) {
                pasaFiltro = false;
            }
            if (modelo != null && !modelo.equals(vehiculo.getModelo())) {
                pasaFiltro = false;
            }
            if (precioMin != null && Double.parseDouble(precioMin) > vehiculo.getPrecio()) {
                pasaFiltro = false;
            }
            if (precioMax != null && Double.parseDouble(precioMax) < vehiculo.getPrecio()) {
                pasaFiltro = false;
            }
            if (kmMin != null && Integer.parseInt(kmMin) > vehiculo.getKilometraje()) {
                pasaFiltro = false;
            }
            if (kmMax != null && Integer.parseInt(kmMax) < vehiculo.getKilometraje()) {
                pasaFiltro = false;
            }

            if (pasaFiltro) {
                filtrados.addLast(vehiculo);
            }
        }

        if ("Precio".equals(ordenarPor)) {
            filtrados.sort((v1, v2) -> Double.compare(v1.getPrecio(), v2.getPrecio()));
        } else if ("Kilometraje".equals(ordenarPor)) {
            filtrados.sort((v1, v2) -> Integer.compare(v1.getKilometraje(), v2.getKilometraje()));
        }

        vehiculos = filtrados;
        pagina = 0;
        mostrarvehiculo();
    }

    @FXML
    private void anterior(ActionEvent event) {
        if (pagina > 0) {
            pagina--;
            mostrarvehiculo();
        }
    }

    @FXML
    private void siguiente(ActionEvent event) {
        if ((pagina + 1) * items < vehiculos.size()) {
            pagina++;
            mostrarvehiculo();
        }
    }

    private void llenarComboBoxes(String rutaArchivo) {
        Set<String> marcasSet = new HashSet<>();
        Set<String> modelosSet = new HashSet<>();
        Set<String> preciosSet = new HashSet<>();
        Set<String> kilometrajesSet = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    marcasSet.add(data[1].split("=")[1].replace("'", ""));
                    modelosSet.add(data[2].split("=")[1].replace("'", ""));
                    preciosSet.add(data[4].split("=")[1]);
                    kilometrajesSet.add(data[5].split("=")[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        marcas.getItems().addAll(marcasSet);
        modelos.getItems().addAll(modelosSet);
        preciomin.getItems().addAll(preciosSet);
        preciomax.getItems().addAll(preciosSet);
        kilomin.getItems().addAll(kilometrajesSet);
        kilomax.getItems().addAll(kilometrajesSet);

        orden.getItems().addAll("Precio", "Kilometraje");
    }

    private void seleccionar(MouseEvent event) throws IOException {
        if (App.getCarrocomprar() != null) {
            App.setRoot("DetallesVehiculoComprar");
        } else {
            System.out.println("No se ha seleccionado ningún vehículo");
        }
    }

    @FXML
    private void resetFiltros() {
        marcas.setValue(null);
        modelos.setValue(null);
        preciomin.setValue(null);
        preciomax.setValue(null);
        kilomin.setValue(null);
        kilomax.setValue(null);
        orden.setValue(null);

        vehiculos = vehiculosOriginales.copy(); // Restaurar la lista original
        pagina = 0;
        mostrarvehiculo(); // Mostrar la lista de vehículos original
        // Desmarcar los CheckBox Mantenimiento y Reparacion
        Mantenimiento.setSelected(false);
        Reparacion.setSelected(false);
    }
}
