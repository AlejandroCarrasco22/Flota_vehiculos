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


public class DAOReserva {
    
    // Consulta para obtener los datos de una reserva dado su ID y el nif del empleado que está destinado en el punto
    // en el que se está realizando la entrega.
    public static final String SELECT_RESERVA_BY_ID_AND_NIF = "SELECT V.MATRICULA, V.COLOR AS ColorVehiculo, M.NOMBRE AS NombreModelo, "
            + "MR.NOMBRE AS NombreMarca, C.NIF AS NIFCliente, U.NOMBRE AS NombreUsuario, ER.NOMBRE AS EstadoReserva, A.ID AS IDAlquiler "
            + "FROM EMPLEADOS E, PUNTOS P, VEHICULOS V, MODELOS M, MARCAS MR, CLIENTES C, USUARIOS U, RESERVAS R, "
            + "ESTADOSRESERVA ER, ALQUILERES A WHERE E.DESTINADOEN = P.ID AND P.ID = R.PUNTOENTREGA AND A.RESERVA = R.ID "
            + "AND R.VEHICULO = V.MATRICULA AND V.MODELO = M.ID AND M.MARCA = MR.ID AND R.CLIENTE = C.NIF AND C.NIF = U.NIF "
            + "AND R.ESTADO = ER.ID AND R.ID = ? AND E.NIF = ?";
    
    // Devuelve el id correspondiente al estado proporcionado.
    public static final String SELECT_IDESTADORESERVA_BY_NOMBRE = "SELECT ID FROM ESTADOSRESERVA ER WHERE ER.NOMBRE = ?";
    // Modifica el estado de la reserva dado su id correspondiente.
    public static final String UPDATE_ESTADORESERVA_BY_ID = "UPDATE RESERVAS SET ESTADO = ? WHERE ID = ?";

    
    private DAOReserva() {
        throw new IllegalStateException("Utility class");
    }
    
    public static String obtenerReservaEnCurso(String idReserva, String nifEmpleado) {
        if(idReserva == null)
            throw new IllegalArgumentException("Id no puede ser null");
        if(idReserva.isEmpty())
            throw new IllegalArgumentException("El id está vacío");
        
        if(nifEmpleado == null)
            throw new IllegalArgumentException("Id no puede ser null");
        if(nifEmpleado.isEmpty())
            throw new IllegalArgumentException("El id está vacío");
        
        String reserva = null;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s = connection.getStatement(SELECT_RESERVA_BY_ID_AND_NIF);
        ){ 
            
            // Añadimos el ID de la reserva que se nos ha pasado a la consulta.
            s.setInt(1, Integer.parseInt(idReserva));
            s.setString(2, nifEmpleado);
            
            try (ResultSet result = s.executeQuery()) {
                while (result.next()) {
                                        
                    JsonObject reservaJSON = Json.createObjectBuilder()
                    .add("Matricula", result.getString("Matricula"))
                    .add("ColorVehiculo", result.getString("ColorVehiculo"))
                    .add("NombreModelo", result.getString("NombreModelo")) 
                    .add("NombreMarca", result.getString("NombreMarca"))
                    .add("NIFCliente", result.getString("NIFCliente"))
                    .add("NombreUsuario", result.getString("NombreUsuario"))
                    .add("EstadoReserva", result.getString("EstadoReserva"))
                    .add("IDAlquiler", result.getInt("IDAlquiler"))
                    .build();
                    
                    // Pasamos el objeto JSON a String para posteriormente devolverlo.
                    reserva = reservaJSON.toString();
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
                
        // Si la consulta anterior no encuentra ninguna coincidencia, se devolverá un valor nulo.
        return reserva;
    }

    public static boolean actualizarEstadoReserva(int idReserva, String estadoReserva) {
        if(estadoReserva == null)
            throw new IllegalArgumentException("El estado no puede ser null");
        if(estadoReserva.isEmpty())
            throw new IllegalArgumentException("El estado está vacío");
        
        boolean actualizacionOk = true;
        int idEstadoReserva;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s1 = connection.getStatement(SELECT_IDESTADORESERVA_BY_NOMBRE);
                PreparedStatement s2 = connection.getStatement(UPDATE_ESTADORESERVA_BY_ID);
        ){ 
            s1.setString(1, estadoReserva);
            
            try(ResultSet resultS1 = s1.executeQuery()){
                if(resultS1.next()){
                    idEstadoReserva = resultS1.getInt("Id");
                }
                else{
                    // Para el caso en el que no se encuentre el estado dado dentro de los estados de ESTADORESERVA.
                    actualizacionOk = false;
                    return actualizacionOk;
                }
            }
            
            
            s2.setInt(1, idEstadoReserva);
            s2.setInt(2, idReserva);
            s2.executeUpdate();
                        
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
        
        
        return actualizacionOk;
    }
    

    
}
