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
public class CrearUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextField usuario;

    @FXML
    private PasswordField contra;
    
    @FXML
    private PasswordField contra2;

    @FXML
    private Text volver;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }  
    @FXML
   private void crearuser(ActionEvent event) throws IOException {
        String user = usuario.getText();
        String con = contra.getText();
        String con2 = contra2.getText();
        
        if(!con.equals(con2)){
            contra2.clear();
            System.out.println("No coinciden las contraseñas");
            return;
        }
        
        if (validarcrearuser(user)) {
            System.out.println("Usuario creado");
            App.setRoot("Inicio");
        } else {
            System.out.println("Nombre de usuario ya ocupado");
            return;
        }
    }
    

    private boolean validarcrearuser(String user) {
        
        return true;
    }

    @FXML
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Inicio");
    }
}
