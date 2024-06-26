package ec.edu.espol.ed_p1_grupo03;

import static ec.edu.espol.ed_p1_grupo03.Vehiculo.cargarListaCarros;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Usuario usuarioActual;
    private static Vehiculo carroeditar;
    private static Vehiculo carrocomprar;
    private LinkedList<Servicio> listaServicio = new LinkedList<>();
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Inicio"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public static void main(String[] args) {
        launch();
        for( Vehiculo v: cargarListaCarros("carros.txt")){
            System.out.println(v.toString());
        }
        
    }

    public static Vehiculo getCarroeditar() {
        return carroeditar;
    }

    public static Vehiculo getCarrocomprar() {
        return carrocomprar;
    }

    public static void setCarroeditar(Vehiculo carroeditar) {
        App.carroeditar = carroeditar;
    }

    public static void setCarrocomprar(Vehiculo carrocomprar) {
        App.carrocomprar = carrocomprar;
    }

    public LinkedList<Servicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(LinkedList<Servicio> listaServicio) {
        this.listaServicio = listaServicio;
    }
    

}