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
    private void iniciar(ActionEvent event) {
        String user = usuario.getText();
        String con = contra.getText();

        // Aquí va la lógica de autenticación, por ejemplo, validar el usuario y la contraseña
        if (validateLogin(user, con)) {
            System.out.println("Usuario valido");
            // Redirigir a la siguiente página después de un inicio de sesión exitoso
        } else {
            System.out.println("Usuario o contraseña no valida");
            // Mostrar mensaje de error de autenticación
        }
    }
    

    private boolean validateLogin(String username, String password) {
        // Aquí va la lógica de validación del usuario y la contraseña
        // Por ahora solo retorna true para simplificar
        return true;
    }

    @FXML
    void crearLink(MouseEvent event) throws IOException {
        App.setRoot("secondary");
    }
}
