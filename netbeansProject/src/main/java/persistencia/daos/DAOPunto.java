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


public class DAOPunto {
    
    // Consulta para obtener datos de los vehículos fuera de punto que están asignados al punto gestionado por el empleado
    // especificado (por su NIF).
    public static final String SELECT_PUNTO_ACTUAL_BY_MATRICULA = "SELECT D.NOMBREDELAVIA, D.NUMERO, P.EMAIL, P.TELEFONO "
            + "FROM VEHICULOS V, PUNTOS P, DIRECCIONES D WHERE V.SITUADOEN = P.ID AND P.LOCALIZACION = D.ID "
            + "AND V.MATRICULA=?";
    
    
    
    private DAOPunto() {
        throw new IllegalStateException("Utility class");
      }
    
    public static String consultaPunto(String v) {
        if(v == null)
            throw new IllegalArgumentException("Id no puede ser null");
        if(v.isEmpty())
            throw new IllegalArgumentException("El id está vacío");
        
        String punto = null;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s = connection.getStatement(SELECT_PUNTO_ACTUAL_BY_MATRICULA);
        ){ 
            
            // Añadimos la matrícula que se nos ha pasado a la consulta.
            s.setString(1, v);
            
            try (ResultSet result = s.executeQuery()) {
                while (result.next()) {
                                        
                    JsonObject puntoJSON = Json.createObjectBuilder()
                    .add("Localizacion", result.getString("NombreDeLaVia"))
                    .add("NumeroVia", result.getInt("Numero"))
                    .add("Email", result.getString("Email"))
                    .add("Telefono", result.getString("Telefono"))
                    .build();
                    
                    // Pasamos el objeto JSON a String para posteriormente devolverlo.
                    punto = puntoJSON.toString();
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOPunto.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
                
        // Si la consulta anterior no encuentra ninguna coincidencia, se devolverá un valor nulo.
        return punto;
    }

    
}
