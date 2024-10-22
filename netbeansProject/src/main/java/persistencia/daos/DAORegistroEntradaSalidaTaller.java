/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.DBConnection;

/**
 * @author g01
 */


public class DAORegistroEntradaSalidaTaller {
    
    public static final String SELECT_ID_REGISTROENTRADASALIDA_BY_MATRICULA = "SELECT RES.ORDENDEREPARACION FROM ORDENESDEREPARACION O, REGISTROSENTRADASALIDATALLER RES WHERE O.ID = RES.ORDENDEREPARACION AND O.VEHICULO = ?";
    
    public static final String UPDATE_ENTRADAENPUNTO = "UPDATE REGISTROSENTRADASALIDATALLER SET ENTRADAENPUNTO = ? " +
"WHERE ORDENDEREPARACION = ?";
    
    
    private DAORegistroEntradaSalidaTaller() {
      throw new IllegalStateException("Utility class");
    }

    public static boolean consultaIDRegistroEntrada(String matricula) {
        if(matricula == null)
            throw new IllegalArgumentException("matricula can't be null");
        if(matricula.isEmpty())
            throw new IllegalArgumentException("matricula is empty");
        
        boolean isCorrect = true;
        int idOrdenReparacion;
        
        Timestamp momento = new Timestamp(System.currentTimeMillis());
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s1 = connection.getStatement(SELECT_ID_REGISTROENTRADASALIDA_BY_MATRICULA);
                PreparedStatement s2 = connection.getStatement(UPDATE_ENTRADAENPUNTO);
        ){ 
            
            // Añadimos la matricula que se nos ha pasado a la consulta.
            s1.setString(1, matricula);
            try(ResultSet resultS1 = s1.executeQuery()){    
                if(resultS1.next()){
                    idOrdenReparacion = resultS1.getInt("Ordendereparacion");
                }
                else{
                    // Para el caso en el que no se encuentre el id dada dicha matricula en OrdenesDeReparacion.
                    isCorrect = false;
                    return isCorrect;
                }
            }
            s2.setTimestamp(1, momento);
            s2.setInt(2, idOrdenReparacion);
            s2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAORegistroEntradaSalidaTaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
        
        return isCorrect;
    }
    
}
