/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.util.Objects;

/**
 *
 * @author DHAMAR
 */
public class Servicio {
    private String fecha;
    private String Descripion;
    private Double costo;
    private TipoServicio tiposervicio;

    public Servicio( String fecha, String Descripion, TipoServicio tiposervicio, Double costo) {
        this.fecha = fecha;
        this.Descripion = Descripion;
        this.costo = costo;
        this.tiposervicio = tiposervicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripion() {
        return Descripion;
    }

    public void setDescripion(String Descripion) {
        this.Descripion = Descripion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public TipoServicio getTiposervicio() {
        return tiposervicio;
    }

    public void setTiposervicio(TipoServicio tiposervicio) {
        this.tiposervicio = tiposervicio;
    }
    
    
    @Override
    public String toString() {
        return "Servicio\n" + "Tipo de Servicio: " + tiposervicio + "\nfecha: " 
                + fecha + "\nDescripion: " + Descripion + "\ncosto: " + costo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

 
    
    
    
    
}
