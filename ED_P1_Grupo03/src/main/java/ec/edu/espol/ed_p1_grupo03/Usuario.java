/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
/**
 *
 * @author jkrom
 */
public class Usuario implements Serializable {
    private String ID;
    private String password;

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
    

    public Usuario(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public static boolean estaRegistrado(LinkedList<Usuario> usuarios, String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return ID + "," + password;
    }
    public static Usuario leerUsuario(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 2) {
            return new Usuario(partes[0], partes[1]);
        } else {
            return null;
        }
    }
    public static void guardarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write(usuario.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<Usuario> cargarUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Usuario usuario = Usuario.leerUsuario(linea);
                if (usuario != null) {
                    usuarios.addLast(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
