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

        
        if (validarinicio(user, con)) {
            System.out.println("Usuario valido");
            
        } else {
            System.out.println("Usuario o contraseña no valida");
            
        }
    }
    

    private boolean validarinicio(String username, String password) {
        
        return true;
    }

    @FXML
    void crearLink(MouseEvent event) throws IOException {
        App.setRoot("secondary");
    }
}
