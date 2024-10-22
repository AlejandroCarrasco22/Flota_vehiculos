/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.controladores_caso_uso;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import modelos.Punto;
import modelos.Sesion;
import modelos.Vehiculo;
import persistencia.daos.DAOPunto;
import persistencia.daos.DAOVehiculo;

/**
 *
 * @author g01
 */
public class ControladorCUConsultarVehiculos {

    
    public List<String> obtenerListaVehiculos(String d) {
        List <String> listaVehiculosJSONString = DAOVehiculo.obtenerVehiculosFueraDePunto(d);
        
        // La lista de vehículos consultados, en formato String, que pasaremos a la Vista.
        ArrayList<String> vehiculosConsultadosString = new ArrayList<>();
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader;
        JsonObject vehiculoJSON;
        
        
        for(String vehiculoJSONString : listaVehiculosJSONString){
            reader = factory.createReader(new StringReader(vehiculoJSONString));
            vehiculoJSON = reader.readObject();
            
            Vehiculo v = new Vehiculo(vehiculoJSON.getString("Matricula"), vehiculoJSON.getString("EstadoVehiculo"),
                    vehiculoJSON.getString("Color"), vehiculoJSON.getString("NombreModelo"), vehiculoJSON.getString("NombreMarca"));
            
            vehiculosConsultadosString.add(v.getNombreMarca()+" "+v.getNombreModelo()+" "+v.getColor()+", matricula "+v.getMatricula()+
                    ", "+v.getEstadoVehiculo());
        }
        
        return vehiculosConsultadosString;
    }

    public String obtenerDatosPunto(String v) {
        // Extraemos la matrícula de los datos del vehículo pasado (el formato será el creado en el método
        // obtenerListaVehiculos() de esta clase.
        Pattern patronMatricula = Pattern.compile("matricula (.*?),");
        Matcher matcher = patronMatricula.matcher(v);
        
        // Tras aplicar la expresión regular a los datos del vehículo pasado (v), obtenemos la matrícula.
        matcher.find();
        String matricula = matcher.group(1);
        
        String puntoJSONString = DAOPunto.consultaPunto(matricula);
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(puntoJSONString));
        JsonObject puntoJSON = reader.readObject();
        
        Punto p = new Punto(puntoJSON.getString("Localizacion"), puntoJSON.getInt("NumeroVia"), puntoJSON.getString("Email"),
                    puntoJSON.getString("Telefono"));
        
        return "Direccion: "+p.getLocalizacion()+" "+p.getNumeroVia()+"\nEmail: "+p.getEmail()+"\nTelefono: "+
                p.getTelefono();

    }

    public String obtenerNifSesion() {
        return Sesion.getInstance().getNif();    
    }
    
    
    
    
   
}
