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
    //vehiculo sin servicio
     public Vehiculo(String marca, String modelo, int year, double precio, int kilometraje, String motor,
                    String transmision, double peso, String ubicacion, LinkedList<String> fotos) {
        this.id = generarID();
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
        this.servicio = new LinkedList<>();  
    }
    //vehiculos que ya tienen id: mas que todo para los que estan en el txt
    public Vehiculo(String id, String marca, String modelo, int year, double precio, int kilometraje, String motor, 
            String transmision, double peso, String ubicacion, LinkedList<String> fotos, LinkedList<Servicio> servicio) {
        this.id= id;
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
    

    public LinkedList<String> getFotos() {
        return fotos;
    }

    public void setFotos(LinkedList<String> fotos) {
        this.fotos = fotos;
    }
    
    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("id=").append(id).append(",")
      .append("Marca='").append(marca).append("',")
      .append("Modelo='").append(modelo).append("',")
      .append("Año=").append(year).append(",")
      .append("Precio=").append(precio).append(",")
      .append("Kilometraje=").append(kilometraje).append(",")
      .append("Motor='").append(motor).append("',")
      .append("Transmision='").append(transmision).append("',")
      .append("Peso=").append(peso).append(",")
      .append("Ubicacion='").append(ubicacion).append("',");
    sb.append("Fotos=");
    for (int i = 0; i < fotos.size(); i++) {
        sb.append(fotos.get(i));
        if (i < fotos.size() - 1) {
            sb.append(";");
        }
    }
    sb.append(",Servicios=");
    for (int i = 0; i < servicio.size(); i++) {
        Servicio serv = servicio.get(i);
        sb.append(serv.getFecha()).append("|")
          .append(serv.getDescripion()).append("|")
          .append(serv.getTiposervicio()).append("|")
          .append(serv.getCosto());
        if (i < servicio.size() - 1) {
            sb.append(";");
        }
    }

    return sb.toString();
}
    


    @Override
     public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehicle = (Vehiculo) o;
        return year == vehicle.year && Double.compare(vehicle.precio, precio) == 0 && kilometraje == vehicle.kilometraje && Objects.equals(marca, vehicle.marca) && Objects.equals(modelo, vehicle.modelo) && Objects.equals(motor, vehicle.motor) && Objects.equals(ubicacion, vehicle.ubicacion);
    }
     
     public static LinkedList<Vehiculo> cargarListaCarros(String nomArchivo) {
    LinkedList<Vehiculo> listaVehiculos = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(nomArchivo))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            String[] partes = line.split(",");
            if (partes.length < 8) {
                System.out.println("Línea incompleta: " + line);
                continue;
            }
            
            String id = null;
            String marca = null;
            String modelo = null;
            int year = 0;
            double precio = 0.0;
            int kilometraje = 0;
            String motor = null;
            String transmision = null;
            double peso = 0.0;
            String ubicacion = null;
            LinkedList<String> fotos = new LinkedList<>();
            LinkedList<Servicio> servicios = new LinkedList<>();
            
            for (String parte : partes) {
                String[] atributo = parte.split("=");
                if (atributo.length < 2) {
                    continue;
                }
                String key = atributo[0].trim();
                String value = atributo[1].trim();
                
                switch (key) {
                    case "id":
                        id = value;
                        break;
                    case "Marca":
                        marca = value.substring(1, value.length() - 1);
                        break;
                    case "Modelo":
                        modelo = value.substring(1, value.length() - 1);
                        break;
                    case "Año":
                        year = Integer.parseInt(value);
                        break;
                    case "Precio":
                        precio = Double.parseDouble(value);
                        break;
                    case "Kilometraje":
                        kilometraje = Integer.parseInt(value);
                        break;
                    case "Motor":
                        motor = value.substring(1, value.length() - 1);
                        break;
                    case "Transmision":
                        transmision = value.equals("null") ? null : value.substring(1, value.length() - 1);
                        break;
                    case "Peso":
                        peso = Double.parseDouble(value);
                        break;
                    case "Ubicacion":
                        ubicacion = value.substring(1, value.length() - 1);
                        break;
                    case "Fotos":
                        parseFotos(value, fotos);
                        break;
                    case "Servicios":
                        parseServicios(value, servicios);
                        break;
                    default:
                        // Manejar otros campos si es necesario
                        break;
                }
            }
            
            Vehiculo vehiculo = new Vehiculo(id, marca, modelo, year, precio, kilometraje, motor, transmision, peso, ubicacion, fotos, servicios);
            listaVehiculos.addLast(vehiculo);
        }
    } catch (IOException e) {
        System.out.println("Error al cargar vehículos: " + e.getMessage());
    }
    return listaVehiculos;
}

private static void parseFotos(String value, LinkedList<String> fotos) {
    if (!value.isEmpty()) {
        String[] fotosArray = value.split(";");
        for (String foto : fotosArray) {
            fotos.addLast(foto.trim());
        }
    }
}

private static void parseServicios(String serviciosStr, LinkedList<Servicio> servicios) {
    for (String servicioStr : serviciosStr.split(";")) {
        String[] datosServicio = servicioStr.split("\\|");
        if (datosServicio.length == 4) {
            Servicio serv = new Servicio(datosServicio[0], datosServicio[1], TipoServicio.valueOf(datosServicio[2]), Double.parseDouble(datosServicio[3]));
            servicios.addLast(serv);
        }
    }
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
            if(vh.servicio!=null){
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
            }
            bw.newLine();
            bw.write(texto.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static synchronized String generarID() {
        int lastId = 0;
        File archivo = new File(idFile);

        // Leer el último ID desde el archivo
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String line = br.readLine();
                if (line != null) {
                    lastId = Integer.parseInt(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    // Incrementar el ID
        lastId++;

    // Guardar el nuevo ID en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(Integer.toString(lastId));
        } catch (IOException e) {
            e.printStackTrace();
        }

    return Integer.toString(lastId);
}
    public void editarVehiculo(Vehiculo veditado, String nomfile,Usuario user){
        LinkedList<Vehiculo> vehiculosUser = cargarListaCarros(nomfile);
        for(Vehiculo v : vehiculosUser){
            if(v.id.equals(veditado.getId())){
                if(veditado.getMarca() != null){
                    v.setMarca(veditado.getMarca());
                }
                if(veditado.getModelo() != null){
                    v.setModelo(veditado.getModelo());
                }
                if(null != Integer.toString(veditado.getYear())){
                    v.setYear(veditado.getYear());
                }
                if(Double.toString(v.getPrecio()) != null){
                    v.setPrecio(v.getPrecio());
                }
                if(Integer.toString(veditado.getKilometraje()) != null){
                    v.setKilometraje(veditado.getKilometraje());
                }
                if(veditado.getMotor() != null){
                    v.setMotor(veditado.getMotor());
                }
                if(veditado.getTransmision() != null){
                    v.setTransmision(veditado.getTransmision());
                }
                if(veditado.getUbicacion() != null){
                    v.setUbicacion(veditado.getUbicacion());
                }
                if(veditado.getFotos() != null){
                    v.setFotos(veditado.getFotos());
                }
                if(veditado.getServicio() != null){
                    v.setServicio((LinkedList<Servicio>) veditado.getServicio());
                }
            }
            
            }
        user.setVehiculos(vehiculosUser);
        user.guardarVehiculos();
        
    }
    public static void guardarVehiculoEnArchivo(Vehiculo vehiculo, String nombreArchivo) {
        //crearCarro(vehiculo,nombreArchivo);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(vehiculo.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public  String formatoGuardarCarro(Vehiculo vh) {
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
            if(vh.servicio!=null){
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
            
    }
    
     return texto.toString();   
    }   
 
}
