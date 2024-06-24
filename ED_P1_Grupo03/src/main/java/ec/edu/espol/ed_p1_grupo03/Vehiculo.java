        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author DHAMAR
 */
public class Vehiculo  {
    
    private static String  idFile = "idFile.txt" ;
    private String id;
    private String marca;
    private String modelo;
    private int year;
    private double precio;
    private int kilometraje;
    private String motor;
    private String transmision;
    private double peso;
    private String ubicacion;
    private LinkedList<String> fotos;
    private LinkedList<Servicio> servicio;

    public Vehiculo() {
    }
    
    //vehiculo sin id- se le asigna uno.

    public Vehiculo(String marca, String modelo, int year, double precio, int kilometraje, String motor, String transmision, double peso, String ubicacion, LinkedList<String> fotos, LinkedList<Servicio> servicio) {
        this.id= generarID();
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmision = transmision;
        this.peso = peso;
        this.ubicacion = ubicacion;
        this.fotos = fotos;
        this.servicio = servicio;
    }
    
    //vehiculo si servicio
    public Vehiculo(String marca, String modelo, int year, double precio, int kilometraje, String motor, String transmision, double peso, String ubicacion, LinkedList<String> fotos) {
        this.id= generarID();
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.precio = precio;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmision = transmision;
        this.peso = peso;
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

    public static String getIdFile() {
        return idFile;
    }

    public static void setIdFile(String idFile) {
        Vehiculo.idFile = idFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(LinkedList<String> fotos) {
        this.fotos = fotos;
    }
    
    public String toString() {
        return 
                "id="+id+
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
                
                //String id = tokens[0];
                String marca = tokens[0];
                String modelo = tokens[1];
                int year = Integer.parseInt(tokens[2]);
                double precio = Double.parseDouble(tokens[3]);
                int kilometraje = Integer.parseInt(tokens[4]);
                String motor = tokens[5];
                String transmision = tokens[6];
                double peso = Double.parseDouble(tokens[7]);
                String ubicacion = tokens[8];
                //agregar fotos:
                LinkedList<String> fotos= new LinkedList<>();
                for(String foto :tokens[9].split(";")){
                    fotos.addFirst(foto);
                    
                }
                LinkedList<Servicio> servicio= new LinkedList<>();
                
                for(String serv : tokens[10].split(";")){
                    String[] items= serv.split("\\|");      //2019|Cambio de aceite regular|MANTENIMIENTO|100.0
                    Servicio ser=new Servicio(items[0],items[1],TipoServicio.valueOf(items[2]),Double.parseDouble(items[3]));
                    servicio.addFirst(ser);
                }
                
                Vehiculo v = new Vehiculo( marca, modelo, year, precio, kilometraje, motor, transmision, peso, ubicacion, fotos, servicio);
                listavehiculo.addFirst(v);
            }   
        } catch(IOException e){
            System.out.println("ERROR");
        }
         return listavehiculo;
     }
     
    public static void crearCarro(Vehiculo vh, String doc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(doc, true))) {
            StringBuilder texto = new StringBuilder();
            texto.append(vh.id).append(",")
                .append(vh.marca).append(",")
                .append(vh.modelo).append(",")
                .append(vh.year).append(",")
                .append(vh.precio).append(",")
                .append(vh.kilometraje).append(",")
                .append(vh.motor).append(",")
                .append(vh.transmision).append(",")
                .append(vh.peso).append(",")
                .append(vh.ubicacion).append(",");

            for (int i = 0; i < vh.fotos.size(); i++) {
                texto.append(vh.fotos.get(i));
                if (i < vh.fotos.size() - 1) {
                    texto.append(";");
                }
            }
            texto.append(",");
            for (int i = 0; i < vh.servicio.size(); i++) {
                Servicio serv = vh.servicio.get(i);
                texto.append(serv.getFecha()).append("|")
                     .append(serv.getDescripion()).append("|")
                     .append(serv.getTiposervicio()).append("|")
                     .append(serv.getCosto());
                if (i < vh.servicio.size() - 1) {
                    texto.append(";");
                }
            }
            bw.newLine();
            bw.write(texto.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static synchronized String generarID(){ //crea los id consecutivos y los agrega en un archivo para leer el ultimo y crear el siguiente.
        int lastid=0000;
        File archivo = new File(idFile);
        if(archivo.exists()){
        try(BufferedReader br=new BufferedReader(new FileReader(idFile))){
            String line = br.readLine();
            if(line!=null){
                lastid=Integer.parseInt(line);
            }  
        }catch(IOException e){
            e.printStackTrace();
        }
        lastid++;
        //cuarda el id en el archivo
        try(BufferedWriter bw=new BufferedWriter(new FileWriter (idFile))){
            bw.write(Integer.toString(lastid));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        }
        return Integer.toString(lastid);
        
    }
    public static void editarVehiculo(String id,String doc){
        
        
    }
    
        
        
 
}
