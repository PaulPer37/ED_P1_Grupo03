/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.util.ArrayList;

/**
 *
 * @author jkrom
 */
public class Usuario {
    private String ID;
    private String password;
    private String nombre;
    private ArrayList<Usuario> usuariosRegistrados;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }
    

    public Usuario(String ID, String password, String nombre) {
        this.ID = ID;
        this.password = password;
        this.nombre = nombre;
    }
    public void registrarUsuario(Usuario usuario){
        usuariosRegistrados.add(usuario);
    }
    public boolean estaRegistrado(Usuario usuario){
        if(usuariosRegistrados.isEmpty())
            return false;
        for(Usuario user : usuariosRegistrados){
            if(usuario.getID().equals(user.getID()))
                return true;
        }
        return false;    
    }

    @Override
    public String toString() {
        return "Usuario{" + "ID=" + ID + ", password=" + password + ", nombre=" + nombre + ", usuariosRegistrados=" + usuariosRegistrados + '}';
    }
    
}
