/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML
    private Button crearuser;
    
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
        
        Comparator<String> comp = new Comparator<>() {
            @Override
            public int compare(String o1,String o2) {
                return o1.compareTo(o2);
            }
        };

        if (!(comp.compare(con, con2)==0)) {
            contra2.clear();
            System.out.println("Las contraseñas no coinciden");
            return;
        }

        if (validarcrearuser(user)) {
            Usuario nuevoUsuario = new Usuario(user, con);
            Usuario.guardarUsuario(nuevoUsuario);
            System.out.println("Usuario creado");
            App.setRoot("Inicio");
        } else {
            System.out.println("Nombre de usuario ya ocupado");
        }
    }
            

    private boolean validarcrearuser(String user) {
        
        return true;
    }

    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Inicio");
    }
}
