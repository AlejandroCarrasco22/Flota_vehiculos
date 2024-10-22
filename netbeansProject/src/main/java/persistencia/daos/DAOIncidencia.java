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

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * @author g01
 */
public class DAOIncidencia {
    private static boolean closeConnection = true;
    
    // Busca el máximo ID de entre las instancias de Incidencia guardadas en la BD.
    public static final String MAX_IDINCIDENCIA = "SELECT MAX(ID) as ultimoId FROM INCIDENCIAS";
    
    // Busca el id de tipo de incidencia correspondiente al nombre.
    public static final String SELECT_IDTIPOSINCIDENCIA_BY_NOMBRE = "SELECT ID FROM TIPOSDEINCIDENCIA TI WHERE TI.NOMBRE = ?";
    
    // Consulta para insertar una nueva instancia de Incidencia.
    public static final String INSERT_NEW_INCIDENCIA = "INSERT INTO INCIDENCIAS VALUES (?, ?, ?, ?, ?)";
    
    // Obtiene las incidencias de una entrega dado el id de ésta.
    public static final String SELECT_INCIDENCIA_BY_IDENTREGA = "SELECT I.ID, TI.NOMBRE AS TipoIncidencia, I.DESCRIPCION, I.ENTREGA FROM "
            + "INCIDENCIAS I, TIPOSDEINCIDENCIA TI WHERE I.TIPO = TI.ID AND I.ENTREGA = ?";

    private DAOIncidencia() {
        throw new IllegalStateException("Utility class");
    }
    
    public static int registrarNuevaIncidencia(int idEntrega, String tipoIncidencia, String descripcionIncidencia) {
        DBConnection connection = DBConnection.getInstance();
        if (closeConnection) connection.openConnection();
        int idIncidencia = 0;
        int idTipoIncidencia = 0;
        
        try (
            PreparedStatement s1 = connection.getStatement(MAX_IDINCIDENCIA);
            PreparedStatement s2 = connection.getStatement(SELECT_IDTIPOSINCIDENCIA_BY_NOMBRE);
            PreparedStatement s3 = connection.getStatement(INSERT_NEW_INCIDENCIA);
            
        ) {
            // Buscamos el máximo valor de id de entre las instancias de Incidencia, de forma
            // que la nueva instancia que se va a añadir tenga como id de Incidencia el valor encontrado,
            // más 1.
            try (ResultSet resultS1 = s1.executeQuery()){
                if(resultS1.next()){
                    idIncidencia = resultS1.getInt("ultimoId");
                }
            }
            
            
            // Obtenemos el Id correspondiente al nombre del tipo de incidencia proporcionado.
            s2.setString(1, tipoIncidencia);
            
            try(ResultSet resultS2 = s2.executeQuery()){
                if(resultS2.next()){
                    idTipoIncidencia = resultS2.getInt("Id");
                }
            }
            
            
            // Insertamos una nueva instancia de Incidencia.
            s3.setInt(1, idIncidencia+1);
            s3.setInt(2, idTipoIncidencia);
            s3.setString(3, descripcionIncidencia);
            s3.setFloat(4, (float) 0.0); // Valor por defecto del cargo extra.
            s3.setInt(5, idEntrega);
            s3.executeUpdate();
                   
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOIncidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (closeConnection) connection.closeConnection();
        
        // Devuelve el id de la Incidencia creada.
        return idIncidencia;
    }

    public static List<String> obtenerIncidenciasAnteriores(int idEntrega) {
        ArrayList<String> incidencias = new ArrayList<>();
        
        DBConnection connection = DBConnection.getInstance();
        if (closeConnection) connection.openConnection();
        
        try (
            PreparedStatement s = connection.getStatement(SELECT_INCIDENCIA_BY_IDENTREGA);
            
        ) {
            // Buscamos las incidencias asociadas a la entrega especificada.
            s.setInt(1, idEntrega);
            try(ResultSet result = s.executeQuery()){
                 while(result.next()){
                    JsonObject incidenciaJSON = Json.createObjectBuilder()
                        .add("IdIncidencia", result.getInt("Id"))
                        .add("TipoIncidencia", result.getString("TipoIncidencia"))
                        .add("Descripcion", result.getString("Descripcion"))
                        .add("IdEntrega", result.getInt("Entrega")) 
                        .build();

                        // Pasamos el objeto JSON a String para posteriormente devolverlo.
                        incidencias.add(incidenciaJSON.toString());
                }
            }
               
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOIncidencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (closeConnection) connection.closeConnection();
        
        // Devuelve el id de la Incidencia creada.
        return incidencias;
    }
    

    
}
