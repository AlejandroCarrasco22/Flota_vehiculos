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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;


/**
 * @author g01
 */


public class DAOVehiculo {
    
    // Consulta para obtener datos de los vehículos fuera de punto que están asignados al punto gestionado por el empleado
    // especificado (por su NIF).
    public static final String SELECT_VEHICULOS_FUERA_DE_PUNTO_BY_NIF = "SELECT V.MATRICULA, EV.NOMBRE AS EstadoVehiculo, "
            + "V.COLOR, M.NOMBRE AS NombreModelo, MR.NOMBRE AS NombreMarca FROM EMPLEADOS E, PUNTOS P, VEHICULOS V, "
            + "ESTADOSVEHICULO EV, MODELOS M, MARCAS MR WHERE E.DESTINADOEN = P.ID AND V.PUNTOASIGNADO = P.ID "
            + "AND V.SITUADOEN != P.ID AND V.ESTADO = EV.ID AND V.MODELO = M.ID AND M.MARCA = MR.ID AND "
            + "E.NIF=?";
    
    // Devuelve el id correspondiente al estado proporcionado.
    public static final String SELECT_IDESTADOVEHICULO_BY_NOMBRE = "SELECT ID FROM ESTADOSVEHICULO EV WHERE EV.NOMBRE = ?";
    
    // Devuelve el id del punto donde está destinado el empleado, dado el nif de éste
    public static final String SELECT_IDPUNTO_BY_NIF = "SELECT DESTINADOEN FROM EMPLEADOS E WHERE E.NIF = ?";
    
    // Actualiza el estado de los vehiculos cuya matricula coincide con la proporcionada.
    public static final String UPDATE_ESTADOVEHICULO_BY_MATRICULA = "UPDATE VEHICULOS SET ESTADO = ?, SITUADOEN = ? WHERE MATRICULA = ?";
    
    //Actualiza el estado del vehiculo cuya matricula coincide con la proporcionada sin tener en cuenta el punto
     public static final String UPDATE_ESTADOVEHICULO = "UPDATE VEHICULOS SET ESTADO = ? WHERE MATRICULA = ?";
    
    //Selecciona el vehiculo con esa matricula si existe
    public static final String SELECT_VEHICULO_BY_MATRICULA = "SELECT * FROM VEHICULOS V WHERE V.MATRICULA = ?";
    
    //Comprueba que haya un vehiculo con una orden de reparacion
    
    //Devuelve el vehiculo con Matriculo ? en caso de que se encuentre en estado "enTaller"
    public static final String SELECT_VEHICULO_ENTALLER = "SELECT V.MATRICULA, EV.NOMBRE AS ESTADO, V.COLOR, O.MOMENTO, O.RAZONES, RES.LLEGADAALTALLER, RES.SALIDADELTALLER, RES.PROPUESTOPARABAJA, RES.RAZONESPARABAJA\n" +
"FROM VEHICULOS V, ESTADOSVEHICULO EV, ORDENESDEREPARACION O, REGISTROSENTRADASALIDATALLER RES\n" +
"WHERE V.ESTADO = EV.ID AND O.VEHICULO = V.MATRICULA AND O.ID = RES.ORDENDEREPARACION AND V.MATRICULA = ? AND EV.NOMBRE = 'EnTaller' AND RES.LLEGADAALTALLER IS NOT NULL AND RES.SALIDADELTALLER IS NOT NULL AND RES.ENTRADAENPUNTO IS NULL";

    public static final String MATRICULA = "Matricula";
    public static final String ESTADOVEHICULO = "EstadoVehiculo";
    public static final String COLOR = "Color";
    public static final String MATRICULANONULL = "CLa matrícula no puede ser null";
    public static final String MATRICULAVACIA = "La matricula está vacía";
    public static final String MOMENTO = "Momento";
    public static final String PROPUESTOPARABAJA = "PropuestoParaBaja";
    public static final String RAZONES = "Razones";
    
    
    private DAOVehiculo() {
        throw new IllegalStateException("Utility class");
    }
    
    public static List<String> obtenerVehiculosFueraDePunto(String d) {
        if(d == null)
            throw new IllegalArgumentException("Id no puede ser null");
        if(d.isEmpty())
            throw new IllegalArgumentException("El id está vacío");
        
        ArrayList<String> listaVehiculos = new ArrayList<>();
        String vehiculo;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s = connection.getStatement(SELECT_VEHICULOS_FUERA_DE_PUNTO_BY_NIF);
        ){ 
            
            // Añadimos el NIF que se nos ha pasado a la consulta.
            s.setString(1, d);
            
            try (ResultSet result = s.executeQuery()) {
                while (result.next()) {
                                        
                    JsonObject vehiculoJSON = Json.createObjectBuilder()
                    .add(MATRICULA, result.getString(MATRICULA))
                    .add(ESTADOVEHICULO, result.getString(ESTADOVEHICULO))
                    .add(COLOR, result.getString(COLOR))
                    .add("NombreModelo", result.getString("NombreModelo")) 
                    .add("NombreMarca", result.getString("NombreMarca"))
                    .build();
                    
                    // Pasamos el objeto JSON a String para posteriormente devolverlo.
                    vehiculo = vehiculoJSON.toString();
                    listaVehiculos.add(vehiculo);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
                
        // Si la consulta anterior no encuentra ninguna coincidencia, se devolverá un valor nulo.
        return listaVehiculos;
    }
    
    public static boolean actualizarEstadoVehiculoSinPunto(String matricula, String estadoVehiculo){
         if(estadoVehiculo == null)
            throw new IllegalArgumentException("El estado no puede ser null");
        if(estadoVehiculo.isEmpty())
            throw new IllegalArgumentException("El estado está vacío");
        if(matricula == null)
            throw new IllegalArgumentException(MATRICULANONULL);
        if(matricula.isEmpty())
            throw new IllegalArgumentException(MATRICULAVACIA);
        
        boolean actualizacionOk = true;
        int idEstadoVehiculo;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s1 = connection.getStatement(SELECT_IDESTADOVEHICULO_BY_NOMBRE);
                PreparedStatement s2 = connection.getStatement(UPDATE_ESTADOVEHICULO);
        ){ 
            
            s1.setString(1, estadoVehiculo);
            try(ResultSet resultS1 = s1.executeQuery()){
                
                if(resultS1.next()){
                    idEstadoVehiculo = resultS1.getInt("Id");
                }
                else{
                    // Para el caso en el que no se encuentre el estado dado dentro de los estados de ESTADOVEHICULO.
                    actualizacionOk = false;

                    return actualizacionOk;
                }

                s2.setInt(1, idEstadoVehiculo);
                s2.setString(2, matricula);
                s2.executeUpdate();
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
        
        
        return actualizacionOk;
    }

    public static boolean actualizarEstadoVehiculo(String matricula, String estadoVehiculo, String nifEmpleado) {
        if(estadoVehiculo == null)
            throw new IllegalArgumentException("El estado no puede ser null");
        if(estadoVehiculo.isEmpty())
            throw new IllegalArgumentException("El estado está vacío");
        if(matricula == null)
            throw new IllegalArgumentException(MATRICULANONULL);
        if(matricula.isEmpty())
            throw new IllegalArgumentException(MATRICULAVACIA);
        if(nifEmpleado == null)
            throw new IllegalArgumentException("El nifEmpleado no puede ser null");
        if(nifEmpleado.isEmpty())
            throw new IllegalArgumentException("El nifEmpleado está vacío");
        
        boolean actualizacionOk = true;
        int idEstadoVehiculo;
        int idPunto;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement s1 = connection.getStatement(SELECT_IDESTADOVEHICULO_BY_NOMBRE);
                PreparedStatement s2 = connection.getStatement(SELECT_IDPUNTO_BY_NIF);
                PreparedStatement s3 = connection.getStatement(UPDATE_ESTADOVEHICULO_BY_MATRICULA);
        ){ 
            s1.setString(1, estadoVehiculo);
            
           try( ResultSet resultS1 = s1.executeQuery()){
               

                if(resultS1.next()){
                    idEstadoVehiculo = resultS1.getInt("Id");
                }
                else{
                    // Para el caso en el que no se encuentre el estado dado dentro de los estados de ESTADOVEHICULO.
                    actualizacionOk = false;
                    return actualizacionOk;
                }
            }
            
            s2.setString(1, nifEmpleado);
            
            try(ResultSet resultS2 = s2.executeQuery()){
                

                if(resultS2.next()){
                    idPunto = resultS2.getInt("DestinadoEn");
                }
                else{
                    // Para el caso en el que no se encuentre el empleado para el NIF especificado.
                    actualizacionOk = false;
                    return actualizacionOk;
                }

                s3.setInt(1, idEstadoVehiculo);
                s3.setInt(2, idPunto);
                s3.setString(3, matricula);
                s3.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
        
        
        return actualizacionOk;
    }
    
    public static boolean encontrarVehiculo(String matricula){
        if(matricula == null)
            throw new IllegalArgumentException(MATRICULANONULL);
        if(matricula.isEmpty())
            throw new IllegalArgumentException(MATRICULAVACIA);
        
        boolean isVehiculo = true;
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                
                PreparedStatement ps = connection.getStatement(SELECT_VEHICULO_BY_MATRICULA);
        ){ 
            
            // Añadimos el NIF que se nos ha pasado a la consulta.
            ps.setString(1, matricula);
            try(ResultSet resultS1 = ps.executeQuery()){            

                if(resultS1.next()){
                    resultS1.getString("matricula");
                }
                else{
                    // Para el caso en el que no se encuentre el estado dado dentro de los estados de ESTADOVEHICULO.
                    isVehiculo = false;

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
        
        return isVehiculo;
    }
    
    public static String encontrarVehiculoEnTaller(String matricula){
         if(matricula == null)
            throw new IllegalArgumentException("Id no puede ser null");
        if(matricula.isEmpty())
            throw new IllegalArgumentException("El id está vacío");
        
        String vehiculo = null;
        
        
        
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        
                
        try (
                
                PreparedStatement s = connection.getStatement(SELECT_VEHICULO_ENTALLER);
        ){ 
            
            // Añadimos el ID de la reserva que se nos ha pasado a la consulta.
            
            s.setString(1,matricula);
                
            try (ResultSet result = s.executeQuery()) {
                while (result.next()) {
                    
                    Timestamp momento = result.getTimestamp(MOMENTO) ;
                    Timestamp llegadaaltaller = result.getTimestamp("Llegadaaltaller") ;
                    Timestamp salidaDelTaller = result.getTimestamp("Salidadeltaller") ;

                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                    String momentoString = formatter.format(momento.toLocalDateTime());
                    String llegadaaltallerString = formatter.format(llegadaaltaller.toLocalDateTime());
                    String salidaDelTallerString = formatter.format(salidaDelTaller.toLocalDateTime());
                    
                    JsonObject reservaJSON;
                    
                    if(result.getBoolean(PROPUESTOPARABAJA)){
                        reservaJSON = Json.createObjectBuilder()
                        .add(MATRICULA, result.getString(MATRICULA))
                        .add(ESTADOVEHICULO, result.getString("Estado"))
                        .add("ColorVehiculo", result.getString(COLOR))
                        .add(MOMENTO, momentoString) 
                        .add(RAZONES, result.getString(RAZONES))
                        .add("LlegadaAlTaller", llegadaaltallerString)
                        .add("SalidaDelTaller", salidaDelTallerString)
                        .add(PROPUESTOPARABAJA, result.getBoolean(PROPUESTOPARABAJA))
                        .add("RazonesBaja", result.getString("RazonesParaBaja"))
                        .build();
                    }
                    else{
                        reservaJSON = Json.createObjectBuilder()
                        .add(MATRICULA, result.getString(MATRICULA))
                        .add(ESTADOVEHICULO, result.getString("Estado"))
                        .add("ColorVehiculo", result.getString(COLOR))
                        .add(MOMENTO, momentoString) 
                        .add(RAZONES, result.getString(RAZONES))
                        .add("LlegadaAlTaller", llegadaaltallerString)
                        .add("SalidaDelTaller", salidaDelTallerString)
                        .add(PROPUESTOPARABAJA, result.getBoolean(PROPUESTOPARABAJA))
                        .build();
                    }
                    
                    
                    
                    // Pasamos el objeto JSON a String para posteriormente devolverlo.
                    vehiculo = reservaJSON.toString();
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.closeConnection();
                
        // Si la consulta anterior no encuentra ninguna coincidencia, se devolverá un valor nulo.
        return vehiculo;
    }

    
}