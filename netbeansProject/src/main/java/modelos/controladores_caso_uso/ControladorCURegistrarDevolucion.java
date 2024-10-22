/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.controladores_caso_uso;

import java.io.StringReader;
import java.sql.Timestamp;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import modelos.Alquiler;
import modelos.Entrega;
import modelos.Reserva;
import modelos.Sesion;
import persistencia.daos.DAOEntrega;
import persistencia.daos.DAOReserva;
import persistencia.daos.DAOVehiculo;

/**
 *
 * @author g01
 */
public class ControladorCURegistrarDevolucion {
    private Reserva reservaConsultada;
    private Alquiler alquilerReserva;
    private String nifEmpleado;
    private Entrega entregaVehiculo;


    public String consultarReserva(String idReserva, String nifEmpleado) {
        String reservaJSONString = DAOReserva.obtenerReservaEnCurso(idReserva, nifEmpleado);
        String datosReserva = null;
        
        // En caso de haber una reserva para el id y el nif dados.
        if(reservaJSONString != null){
            JsonReaderFactory factory = Json.createReaderFactory(null);
            JsonReader reader = factory.createReader(new StringReader(reservaJSONString));
            JsonObject reservaJSON = reader.readObject();

            Reserva r = new Reserva(Integer.parseInt(idReserva), reservaJSON.getString("Matricula"), reservaJSON.getString("ColorVehiculo"), 
                            reservaJSON.getString("NombreModelo"), reservaJSON.getString("NombreMarca"), reservaJSON.getString("NIFCliente"),
                            reservaJSON.getString("NombreUsuario"), reservaJSON.getString("EstadoReserva"));

            this.reservaConsultada = r;

            Alquiler a = new Alquiler(reservaJSON.getInt("IDAlquiler"), Integer.parseInt(idReserva));

            this.alquilerReserva = a;

            datosReserva = "Vehículo: "+r.getNombreMarca()+" "+r.getNombreModelo()+" "+r.getColor()+" matricula "+r.getMatricula()+
                    "\nCliente: "+r.getNifCliente()+", "+r.getNombreUsuario();
        
        }
        
        // El nif del empleado de atención al cliente encargado de la devolución, pues lo necesitaremos más adelante.
        this.nifEmpleado = nifEmpleado;
        
        // Si no hay resultados en la búsqueda, se devuelve un nulo.
        return datosReserva;
    }

    public boolean comprobarReservaActiva() {
        return "EnAlquiler".equals(this.reservaConsultada.getEstadoReserva());
    }

    public boolean crearEntrega() {
        Timestamp momento = new Timestamp(System.currentTimeMillis());
        boolean entregaRegistrada = true;
        int idAlquiler = this.alquilerReserva.getIdAlquiler();
        
        // Inserta en la BD una nueva Entrega, correspondiente a la devolución en curso.
        int idEntrega = DAOEntrega.insertarNuevaEntrega(momento, idAlquiler, 
                this.nifEmpleado);
        
        if(idEntrega == -1){
            // Si se devuelve un -1, significará que el registro no ha sido exitoso.
            entregaRegistrada = false;
        }
        else {
            
            Entrega e = new Entrega(idEntrega, momento, this.alquilerReserva.getIdAlquiler(),
                        this.nifEmpleado);
        
            this.entregaVehiculo = e;
        }
        
        // Será True si se ha realizado la inserción en la BD de forma exitosa.
        return entregaRegistrada;
    }

    public boolean actualizarEstadoReserva(String estadoReserva) {
        int idReserva = this.reservaConsultada.getId();
        
        return DAOReserva.actualizarEstadoReserva(idReserva, estadoReserva);
    }
    
    public boolean actualizarEstadoVehiculo(String estadoVehiculo) {
        String matricula = this.reservaConsultada.getMatricula();
        
        // El tercer parámetro, el nif del empleado, nos servirá para obtener el ID del punto en el que se encuentra el empleado
        // encargado de la devolución que se está llevando a cabo, para poder así indicar que el vehículo está situado en este punto.
        return DAOVehiculo.actualizarEstadoVehiculo(matricula, estadoVehiculo, this.nifEmpleado);
    }

    public int obtenerIdEntregaEnCurso() {
        return this.entregaVehiculo.getIdEntrega();
    }
    
    public int obtenerIdReserva(){
        return this.reservaConsultada.getId();
    }
    
    public String obtenerMatriculaVehiculoReserva(){
        return this.reservaConsultada.getMatricula();
    }   

    public String obtenerNifSesion() {
        return Sesion.getInstance().getNif();    
    }
    
   
}
