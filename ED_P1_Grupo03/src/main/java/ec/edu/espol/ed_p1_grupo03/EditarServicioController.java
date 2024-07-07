/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author DHAMAR
 */
public class EditarServicioController implements Initializable {
    Vehiculo vehiculo;
    Map<String, Servicio> servicioMap;
    Servicio sevSeleccionado;
    @FXML
    private TextField decrip;
    @FXML
    private TextField fecha;
    @FXML
    private TextField costo;
    @FXML
    private ComboBox<String> serviciosUser;
    //private ComboBox<TipoServicio> TipoServicio;
    @FXML
    private ComboBox<TipoServicio> TipoServ;
    private Text volver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculo = App.getVehiculoSelect();
        volver.setOnMouseClicked(event -> {
            try {
                volverLink(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        List<Servicio> servicios = vehiculo.getServicio();
        servicioMap = new HashMap<>();
        for (int i = 0; i < servicios.size(); i++) {
            Servicio s = servicios.get(i);
            String textItem = s.getDescripion();
            serviciosUser.getItems().add(textItem);
            servicioMap.put(textItem, s);
        }
        TipoServ.getItems().setAll(TipoServicio.values());
        serviciosUser.setOnAction(event -> seleccionarServicio());
        habilitarCamposEdicion(false);
           
    } 

    public void seleccionarServicio(){
        String Selected = serviciosUser.getSelectionModel().getSelectedItem();
        sevSeleccionado = servicioMap.get(Selected);
        if(sevSeleccionado != null){
            decrip.setText(sevSeleccionado.getDescripion());
            fecha.setText(sevSeleccionado.getFecha());
            costo.setText(Double.toString(sevSeleccionado.getCosto()));
            
            habilitarCamposEdicion(true);
            TipoServ.setValue(sevSeleccionado.getTiposervicio());
        } else{
            limpiarCamposEdicion();
            habilitarCamposEdicion(false);
        }
    }
    public void habilitarCamposEdicion(boolean habilitar){
        decrip.setDisable(!habilitar);
        fecha.setDisable(!habilitar);
        costo.setDisable(!habilitar);
        
    }
    private void limpiarCamposEdicion() {
        decrip.clear();
        fecha.clear();
        costo.clear();
        
    }

    @FXML
    private void guardar(MouseEvent event) {
        try{
        sevSeleccionado.setDescripion(decrip.getText());
        sevSeleccionado.setFecha(fecha.getText());
        sevSeleccionado.setCosto(Double.parseDouble(costo.getText()));
        /*
        for (int i = 0; i < vehiculo.getServicio().size(); i++) {
            if (vehiculo.getServicio().get(i).equals(sevSeleccionado)) {
                vehiculo.getServicio().remove(i);
                vehiculo.getServicio().addFirst(sevSeleccionado);
                break;
            }
        }
        */
        serviciosUser.getItems().clear();
        for (Servicio s : vehiculo.getServicio()) {
            serviciosUser.getItems().add(s.getDescripion());
        }
        TipoServ.getSelectionModel().clearSelection();
        serviciosUser.getSelectionModel().clearSelection();
        limpiarCamposEdicion();

        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        
    }
    void volverLink(MouseEvent event) throws IOException {
        App.setRoot("Editar");
    }
}
