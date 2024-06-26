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
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    /**
     * Initializes the controller class.
     */
    private static Vehiculo seleccionado;

    public static Vehiculo getSeleccionado() {
        return seleccionado;
    }
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
    private int pagina;
    private final int items = 5;
    @FXML
    private Button seleccionar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        // Use relative path to load the car data
        String rutaArchivoCarros = "carros.txt";
        vehiculos = Vehiculo.cargarListaCarros(rutaArchivoCarros);
        llenarComboBoxes(rutaArchivoCarros);
        mostrarvehiculo();
    }

    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Eleccion");
    }

    private void mostrarvehiculo() {
        lista.getItems().clear();
        LinkedList<Vehiculo> paginaActual = vehiculos.sublist(pagina * items, (pagina + 1) * items);
        System.out.println(paginaActual);
        // Mostrar los vehículos en el ListView
        paginaActual.forEach(vehiculo -> {
            Label label = new Label(vehiculo.getMarca() + "-" + vehiculo.getModelo() + " - $" + vehiculo.getPrecio());
            // Configurar evento de clic en cada vehículo
            label.setOnMouseClicked(event -> {
                // Acción al hacer clic en el vehículo (redireccionar, por ejemplo)
                System.out.println("Clic en " + vehiculo.getMarca() + " " + vehiculo.getModelo());
                this.seleccionado = vehiculo;
            });
            lista.getItems().add(label); // Agregar el Label creado a la lista
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

        LinkedList<Vehiculo> filtrados = new LinkedList<>();
        for (Vehiculo vehiculo : vehiculos) {
            boolean pasaFiltro = true;

            // Aplicar filtros
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

            // Agregar vehículo a la lista filtrada si pasa todos los filtros
            if (pasaFiltro) {
                filtrados.addLast(vehiculo);
            }
        }

        // Ordenar según el criterio seleccionado
        if ("Precio".equals(ordenarPor)) {
            filtrados.sort((v1, v2) -> Double.compare(v1.getPrecio(), v2.getPrecio()));
        } else if ("Kilometraje".equals(ordenarPor)) {
            filtrados.sort((v1, v2) -> Integer.compare(v1.getKilometraje(), v2.getKilometraje()));
        }

        vehiculos = filtrados; // Actualizar la lista de vehículos original

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

        // Añadir opciones de ordenamiento
        orden.getItems().addAll("Precio", "Kilometraje");
    }

    @FXML
    private void seleccionar(MouseEvent event) throws IOException {
        if (seleccionado != null) {
            App.setRoot("Eleccion)");
        } else {
            System.out.println("No se ha seleccionado ningún vehículo");
        }
    }

}
