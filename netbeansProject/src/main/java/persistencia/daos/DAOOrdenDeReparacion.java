/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.DBConnection;

/**
 * @author g01
 */

public class DAOOrdenDeReparacion {
    
    public static final String SELECT_VEHICULO_WITH_ORDENDEREPARACION = "SELECT O.VEHICULO FROM ORDENESDEREPARACION O WHERE O.VEHICULO = ?";
    
    private DAOOrdenDeReparacion() {
        throw new IllegalStateException("Utility class");
    }
    
    public static boolean encontrarOrdenDeReparacion(String matricula){
        if(matricula == null)
            throw new IllegalArgumentException("La matricula no puede ser null");
        if(matricula.isEmpty())
            throw new IllegalArgumentException("La matricula está vacía");
        
        boolean isOrden = true;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement ps = connection.getStatement(SELECT_VEHICULO_WITH_ORDENDEREPARACION);
        ){ 
            
            // Añadimos el NIF que se nos ha pasado a la consulta.
            ps.setString(1, matricula);
            try(ResultSet resultS1 = ps.executeQuery()){            

                if(resultS1.next()){
                    resultS1.getString("vehiculo");
                }
                else{
                    // Para el caso en el que no se encuentre el estado dado dentro de los estados de ESTADOVEHICULO.
                    isOrden = false;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrdenDeReparacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
        
        
        return isOrden;
    }
}
