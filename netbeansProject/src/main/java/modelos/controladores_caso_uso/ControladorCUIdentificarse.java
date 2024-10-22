/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.controladores_caso_uso;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import modelos.Empleado;
import modelos.Sesion;
import persistencia.daos.DAOEmpleado;
/**
 *
 * @author g01
 */
public class ControladorCUIdentificarse {
    private Empleado empleado;
    
    public String identificarEmpleado(String d, String p){
        if(p == null)
            throw new IllegalArgumentException("Password can't be null");
        if(p.isEmpty())
            throw new IllegalArgumentException("Password is empty");
        if(d == null)
            throw new IllegalArgumentException("Login can't be null");
        if(d.isEmpty())
            throw new IllegalArgumentException("Login is empty");
        
        String empleadoJsonString = DAOEmpleado.consultaEmpleadoPorLoginYPassword(d, p);
        
        this.empleado = null;
        
        // En caso de haber habido un usuario para el NIF y el password dados. De no haberlo, Empleado será null.
        if(empleadoJsonString != null){
            // Pasamos de nuevo a objeto JSON el string obtenido con los datos empleados,
            // de forma que podemos extraer los distintos campos de este para la creación
            // de una nueva instancia de Empleado.
            JsonReaderFactory factory = Json.createReaderFactory(null);
            JsonReader reader = factory.createReader(new StringReader(empleadoJsonString));
            JsonObject empleadoJson = reader.readObject();

            this.empleado = new Empleado(empleadoJson.getString("Nombre"), empleadoJson.getString("Nif"),
                       empleadoJson.getString("Password"),empleadoJson.getString("Rol"), 
                    empleadoJson.getInt("Vinculo"), empleadoJson.getInt("Disponibilidad"));
            
        }
        
        String rol = null; // En caso de no haber coincidencia de NIF, se devolverá null.
        
        if(this.empleado != null){
            rol = this.empleado.obtenerRolActual();
        }
         
        // Devolvemos el entero que corresponde al rol del empleado (1, 2 o 3)
        return rol;
    }
    
    public boolean comprobarEnActivo(){
        return empleado.estaActivo();
    }

    public void guardarNifSesion(String d) {
        Sesion.getInstance().setNif(d);    
    }
    
    
   
}
