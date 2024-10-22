/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.controladores_caso_uso;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import modelos.Sesion;
import persistencia.daos.DAOOrdenDeReparacion;
import persistencia.daos.DAORegistroEntradaSalidaTaller;
import persistencia.daos.DAOVehiculo;

/**
 *
 * @author g01
 */
public class ControladorCURegistrarRecepcion {
        
    public boolean crearRegistro(String matricula) {
        
        boolean registroCreado = true;
        String datosVehiculoJSONString  = DAOVehiculo.encontrarVehiculoEnTaller(matricula);        
        String propuestoParaBaja = "PropuestoParaBaja";
        
        if(datosVehiculoJSONString != null){
            JsonReaderFactory factory = Json.createReaderFactory(null);
            JsonReader reader = factory.createReader(new StringReader(datosVehiculoJSONString));
            JsonObject datosVehiculoJSON = reader.readObject();
            
            String estadoVehiculo;
           
            if(datosVehiculoJSON.getBoolean(propuestoParaBaja)){
                estadoVehiculo = propuestoParaBaja;
                registroCreado = DAOVehiculo.actualizarEstadoVehiculoSinPunto(matricula, estadoVehiculo);   
                
            }else{
                estadoVehiculo = "EnPreparacion";
                registroCreado = DAOVehiculo.actualizarEstadoVehiculoSinPunto(matricula, estadoVehiculo);
                
            }
        }
        return registroCreado;
    }

    public boolean registrarEntradaPunto(String matricula){
        return DAORegistroEntradaSalidaTaller.consultaIDRegistroEntrada(matricula);        
    }
    
    // Comprueba si hay un vehiculo con la matricula indicada en estado "enTaller"
    public String comprobarMatriculaEnTaller(String matricula) {
        String datosVehiculoJSONString  = DAOVehiculo.encontrarVehiculoEnTaller(matricula);
        String datosVehiculo = null;
        String propuestoParaBaja = "PropuestoParaBaja";
        
        
        if(datosVehiculoJSONString != null){
            JsonReaderFactory factory = Json.createReaderFactory(null);
            JsonReader reader = factory.createReader(new StringReader(datosVehiculoJSONString));
            JsonObject datosVehiculoJSON = reader.readObject();
            
            if(!datosVehiculoJSON.getBoolean(propuestoParaBaja)){
            
                datosVehiculo = "DATOS DEL VEHÍCULO \nMatricula: " + datosVehiculoJSON.getString("Matricula") + "\nEstado: " + datosVehiculoJSON.getString("EstadoVehiculo") +  
                        "\nColor: " + datosVehiculoJSON.getString("ColorVehiculo") + "\nFecha Orden de Reparacion: " + datosVehiculoJSON.getString("Momento") + "\nRazones: "
                        + datosVehiculoJSON.getString("Razones") + "\nEntrada al taller: " + datosVehiculoJSON.getString("LlegadaAlTaller") + "\nSalida del taller: "
                        + datosVehiculoJSON.getString("SalidaDelTaller");
                
            }else{
                datosVehiculo = "DATOS DEL VEHÍCULO \nMatricula: " + datosVehiculoJSON.getString("Matricula") + "\nEstado: " + datosVehiculoJSON.getString("EstadoVehiculo") +  
                        "\nColor: " + datosVehiculoJSON.getString("ColorVehiculo") + "\nFecha Orden de Reparacion: " + datosVehiculoJSON.getString("Momento") + "\nRazones: "
                        + datosVehiculoJSON.getString("Razones") + "\nEntrada al taller: " + datosVehiculoJSON.getString("LlegadaAlTaller") + "\nSalida del taller: "
                        + datosVehiculoJSON.getString("SalidaDelTaller") + "\nCausa Baja: " + datosVehiculoJSON.getString("RazonesBaja");
            }
            
            
        }

        return datosVehiculo;
    }
    
    
    
    public boolean comprobarMatriculaExistente(String matricula){
        return DAOVehiculo.encontrarVehiculo(matricula);
    }
    
    public boolean comprobarOrdenDeReparacion(String matricula){
        return DAOOrdenDeReparacion.encontrarOrdenDeReparacion(matricula);
    }
    
    public String obtenerNifSesion() {
        return Sesion.getInstance().getNif();    
    }
    
}
