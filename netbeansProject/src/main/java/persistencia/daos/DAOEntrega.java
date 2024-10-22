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
import java.sql.Timestamp;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author g01
 */
public class DAOEntrega {
    private static boolean closeConnection = true;
    
    // Busca el máximo ID de entre las instancias de Entrega guardadas en la BD.
    public static final String MAX_IDENTREGA = "SELECT MAX(ID) as ultimoId FROM ENTREGAS";
    
    // Consulta para insertar una nueva instancia de Entrega.
    public static final String INSERT_NEW_ENTREGA = "INSERT INTO ENTREGAS VALUES (?, ?, ?, ?)";

    private DAOEntrega() {
        throw new IllegalStateException("Utility class");
    }
    
    public static int insertarNuevaEntrega(Timestamp momento, int idAlquiler, String nifEmpleado) {
        
        DBConnection connection = DBConnection.getInstance();
        if (closeConnection) connection.openConnection();
        int ultimoIdEntrega = 0;
        
        try (
            PreparedStatement s = connection.getStatement(INSERT_NEW_ENTREGA);
            PreparedStatement ultIdEntrega = connection.getStatement(MAX_IDENTREGA);
        ) {
            // Buscamos el máximo valor de id de entre las instancias de Entrega, de forma
            // que la nueva instancia que se va a añadir tenga como id de Entrega el valor encontrado
            // más 1.
            try(ResultSet result = ultIdEntrega.executeQuery()){
                if(result.next()){
                ultimoIdEntrega = result.getInt("ultimoId");
                }
            
                s.setInt(1, ultimoIdEntrega+1);
                s.setTimestamp(2, momento);
                s.setInt(3, idAlquiler);
                s.setString(4, nifEmpleado);
                s.executeUpdate();
            }
            
                   
            
        } catch (SQLException ex) {
            ultimoIdEntrega = -1; // En caso de que no haga el registro de manera exitosa, se devuelve un -1.
            Logger.getLogger(DAOEntrega.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (closeConnection) connection.closeConnection();
        
        // Devuelve el id de la Entrega creado.
        return ultimoIdEntrega;
    }
    

    
}
