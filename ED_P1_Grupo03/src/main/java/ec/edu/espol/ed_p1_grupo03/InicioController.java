/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author RUCO HOUSE
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contra;

    @FXML
    private Text crear;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crear.setOnMouseClicked(event -> {
            try {
                crearLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }  
    @FXML
    private void iniciar(ActionEvent event) throws IOException {
        String user = usuario.getText();
        String con = contra.getText();

        Usuario usuarioActual = validarInicio(user, con);
        if (usuarioActual != null) {          
            System.out.println("Usuario válido");
            App.setUsuarioActual(usuarioActual);
            App.setRoot("Eleccion");
        } else {
            System.out.println("Usuario o contraseña no válidos");
        }
    }

    private Usuario validarInicio(String usuario, String contra) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    String password = partes[1];
                    if (nombre.equals(usuario) && password.equals(contra)) {
                        return new Usuario(nombre, password);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    void crearLink(MouseEvent event) throws IOException {
        App.setRoot("CrearUser");
    }
}
