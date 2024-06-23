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
    private String ubicacion;
    private LinkedList<String> fotos;
    private LinkedList<Servicio> servicio;

    public Vehiculo() {
    }
    
    //vehiculo usado
    public Vehiculo(String marca, String modelo, int año, double precio, int kilometraje, String motor, String ubicacion, LinkedList<String> fotos,LinkedList<Servicio> servicio) {
        this.marca = marca;
        this.modelo = modelo;
        this.year = año;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.ubicacion = ubicacion;
        this.fotos = fotos;
        this.servicio = servicio;
    }

    //vehiculo nuevo
    public Vehiculo(String marca, String modelo, int año, double precio, int kilometraje, String motor,String ubicacion, LinkedList<String> fotos) {
        this.marca = marca;
        this.modelo = modelo;
        this.year = año;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.ubicacion = ubicacion;
        this.fotos = fotos;
        
        
        
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicación) {
        this.ubicacion = ubicación;
    }

    public List<Servicio> getServicio() {
        return servicio;
    }

    public void setServicio(LinkedList<Servicio> servicio) {
        this.servicio = servicio;
    }
    

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(LinkedList<String> fotos) {
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
        return year == vehicle.year && Double.compare(vehicle.precio, precio) == 0 && kilometraje == vehicle.kilometraje && Objects.equals(marca, vehicle.marca) && Objects.equals(modelo, vehicle.modelo) && Objects.equals(motor, vehicle.motor) && Objects.equals(ubicacion, vehicle.ubicacion);
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
                String ubicacion = tokens[6];
                //agregar fotos:
                LinkedList<String> fotos= new LinkedList<>();
                for(String foto :tokens[7].split(";")){
                    fotos.addFirst(foto);
                    
                }
                LinkedList<Servicio> servicio= new LinkedList<>();
                
                for(String serv : tokens[8].split(";")){
                    String[] items= serv.split("\\|");      //2019|Cambio de aceite regular|MANTENIMIENTO|100.0
                    Servicio ser=new Servicio(items[0],items[1],TipoServicio.valueOf(items[2]),Double.parseDouble(items[3]));
                    servicio.addFirst(ser);
                }
                
                Vehiculo v=new Vehiculo(marca,modelo,year,precio,kilometraje,motor,ubicacion,fotos,servicio);
                listavehiculo.addFirst(v);
            }
            
            
            
        } catch(IOException e){
            System.out.println("ERROR");
        }
         return listavehiculo;
     }
     
    public static void crearCarro(Vehiculo vh, String doc) {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(doc, true))) {
            //Toyota,Corolla,2015,15000,60000,1.8L,Quito,toyotacorolla20151.jpeg;toyotacorolla20152.jpeg,2016|Lev
            String texto=vh.marca+","+vh.modelo+","+vh.year+","+vh.precio+","+vh.kilometraje+","+vh.motor+","+vh.ubicacion+",";
            int cont=0;
            for(String cadena : vh.fotos){
                 if(cont<vh.fotos.size()-1){
                 texto = texto+cadena+";";
                 }else{
                     texto = texto+cadena;
                 }
            }
            texto+=",";
            for(Servicio cadena:vh.servicio){
                 String text=cadena.getFecha()+"|"+cadena.getDescripion()+"|"+cadena.getTiposervicio()+"|"+cadena.getCosto();
                if(cont<vh.servicio.size()-1){
                    texto+=text+";";
                }else{
                    texto+=text;
                }
            }
            bw.newLine();
            bw.write(texto);
             
             
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
