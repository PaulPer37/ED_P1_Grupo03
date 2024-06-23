/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author DHAMAR
 */
public class Vehiculo  {
    private String marca;
    private String modelo;
    private int year;
    private double precio;
    private int kilometraje;
    private String motor;
    private String transmision;
    private double peso;
    private String ubicacion;
    private List<String> fotos;
    private List<String> historialAccidente;
    private List<String> historialMantenimiento;

    public Vehiculo() {
    }
    
    //vehiculo usado
    public Vehiculo(String marca, String modelo, int año, double precio, int kilometraje, String motor, String transmision, double peso, String ubicacion, List<String> fotos) {
        this.marca = marca;
        this.modelo = modelo;
        this.year = año;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmision = transmision;
        this.peso = peso;
        this.ubicacion = ubicacion;
        this.fotos = fotos;
    }

    //vehiculo nuevo
    public Vehiculo(String marca, String modelo, int año, double precio, int kilometraje, String motor, String transmision, double peso, String ubicacion, List<String> fotos, List<String> historialAccidente, List<String> historialMantenimiento) {
        this.marca = marca;
        this.modelo = modelo;
        this.year = año;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmision = transmision;
        this.peso = peso;
        this.ubicacion = ubicacion;
        this.fotos = fotos;
        this.historialAccidente = historialAccidente;
        this.historialMantenimiento = historialMantenimiento;
        
        
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int año) {
        this.year = año;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmisión) {
        this.transmision = transmisión;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicación) {
        this.ubicacion = ubicación;
    }

    public List<String> getHistorialAccidente() {
        return historialAccidente;
    }

    public void setHistorialAccidente(List<String> historialAccidente) {
        this.historialAccidente = historialAccidente;
    }

    public List<String> getHistorialMantenimiento() {
        return historialMantenimiento;
    }

    public void setHistorialMantenimiento(List<String> historialMantenimiento) {
        this.historialMantenimiento = historialMantenimiento;
    }


    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
    
    public String toString() {
        return 
                "Marca='" + marca + "\n" +
                "Modelo='" + modelo + "\n" +
                "Año=" + year +"\n"+
                "Precio=" + precio +"\n"+
                "Kilometraje=" + kilometraje + "\n"+
                "Motor='" + motor + "\n" +
                "Ubicacion='" + ubicacion +"\n";
    }
    
    //añadir carro
    
    //eliminar carro
    
    // editar carro


    @Override
     public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehicle = (Vehiculo) o;
        return year == vehicle.year && Double.compare(vehicle.precio, precio) == 0 && kilometraje == vehicle.kilometraje && Double.compare(vehicle.peso, peso) == 0 && Objects.equals(marca, vehicle.marca) && Objects.equals(modelo, vehicle.modelo) && Objects.equals(motor, vehicle.motor) && Objects.equals(transmision, vehicle.transmision) && Objects.equals(ubicacion, vehicle.ubicacion);
    }
     
     public static LinkedList<Vehiculo> cargarListaCarros (String nomArchivo){
        LinkedList <Vehiculo> listavehiculo = new LinkedList<Vehiculo> ();
        try (BufferedReader br =new BufferedReader(new FileReader(nomArchivo));){
            String line;
            while((line=br.readLine()) !=null){
                String [] tokens = line.split(",");
               
                String marca = tokens[0];
                String modelo = tokens[1];
                int year = Integer.parseInt(tokens[2]);
                int kilometraje = Integer.parseInt(tokens[3]);
                double precio = Double.parseDouble(tokens[4]);
                String motor = tokens[5];
                String transmision = tokens[6];
                double peso = Double.parseDouble(tokens[7]);
                String ubicacion = tokens[8];
                //agregar fotos:
                LinkedList<String> fotos= new LinkedList<>();
                for(String foto :tokens[9].split(";")){
                    fotos.addFirst(foto);
                    
                }
                
                if(tokens.length>10){
                    LinkedList<String> historialaccidentes = new LinkedList<>();
                    LinkedList<String> historialreparaciones = new LinkedList<>();
               
                for(String acci :tokens[10].split(";")){
                    historialaccidentes.addFirst(acci);
                    
                }
                for(String repa :tokens[11].split(";")){
                    historialreparaciones.addFirst(repa);
                }
                
                Vehiculo v=new Vehiculo(marca,modelo,year,precio,kilometraje,motor,transmision,peso,ubicacion,fotos,historialaccidentes,historialreparaciones);
                listavehiculo.addFirst(v);
                }else{
                    
                
                Vehiculo v=new Vehiculo(marca,modelo,year,precio,kilometraje,motor,transmision,peso,ubicacion,fotos);
                listavehiculo.addFirst(v);
                }
            }
            
            
        } catch(IOException e){
            System.out.println("ERROR");
        }
         
         
         return listavehiculo;
     }
    
}
