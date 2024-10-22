/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.daos;

import persistencia.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;


import javax.json.Json;
import javax.json.JsonObject;

/**
 * @author g01
 */


public class DAOEmpleado {
    // Consulta correspondiente a la búsqueda de un empleado en función de su NIF y contraseña, del cual
    // queremos conocer otros datos, como su password, rol en la empresa...
    
    public static final String SELECT_EMPLEADO_BY_NIF = "SELECT * FROM EMPLEADOS E, USUARIOS U, ROLESENEMPRESA R, "
            + "VINCULACIONESCONLAEMPRESA V, DISPONIBILIDADES D, TIPOSDEROL TR WHERE E.NIF = U.NIF "
            + "AND E.NIF = R.EMPLEADO AND E.NIF = V.EMPLEADO "
            + "AND E.NIF = D.EMPLEADO AND TR.IdTipo= R.Rol AND E.NIF=? AND U.PASSWORD=?";
    
    private DAOEmpleado() {
        throw new IllegalStateException("Utility class");
    }
    
    public static String consultaEmpleadoPorLoginYPassword(String d, String p) {
        if(p == null)
            throw new IllegalArgumentException("Password can't be null");
        if(p.isEmpty())
            throw new IllegalArgumentException("Password is empty");
        if(d == null)
            throw new IllegalArgumentException("Login can't be null");
        if(d.isEmpty())
            throw new IllegalArgumentException("Login is empty");
        String empleadoJsonString=null;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s = connection.getStatement(SELECT_EMPLEADO_BY_NIF);
        ){ 
            
            // Añadimos el NIF que se nos ha pasado a la consulta.
            s.setString(1, d);
            
            // Añadimos el password que se nos ha pasado a la consulta.
            s.setString(2, p);
            try (ResultSet result = s.executeQuery()) {
                if (result.next()) {
                    
                    JsonObject empleadoJson = Json.createObjectBuilder()
                    .add("Nif", result.getString("Nif"))
                    .add("Nombre", result.getString("Nombre"))
                    .add("Password", result.getString("Password"))
                    .add("Rol", result.getString("NombreTipo")) 
                    .add("Vinculo", result.getInt("Vinculo"))
                    .add("Disponibilidad", result.getInt("Disponibilidad")) 
                    .build();
                    
                    // Como se observa, devolvemos los enteros que describen el Rol, Vinculo,
                    // y Disponibilidad en el SQL de la BD, pues si quisieramos devolver el String
                    // que describe el tipo de rol, vinculo o disponibilidad correspondiente, la
                    // consulta se alargaría demasiado.
                    
                    empleadoJsonString = empleadoJson.toString();
                    // Pasamos el objeto JSON a String para posteriormente devolverlo.
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
                
        // Si la consulta anterior no encuentra ninguna coincidencia, se devolverá un valor nulo.
        return empleadoJsonString;
    }

    
}
