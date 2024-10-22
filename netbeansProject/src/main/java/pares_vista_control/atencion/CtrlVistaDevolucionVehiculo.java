/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control.atencion;

import modelos.controladores_caso_uso.ControladorCURegistrarDevolucion;
import pares_vista_control.GestorDeInterfazDeUsuario;

/**
 *
 * @author g01
 */
public class CtrlVistaDevolucionVehiculo {
    private final DevolucionVehiculoVista vista;
    private final ControladorCURegistrarDevolucion controlador;
    private final String nifEmpleado;
    private int idReserva;
    private String matricula;

    public CtrlVistaDevolucionVehiculo(DevolucionVehiculoVista vista) {
        this.vista = vista;
        this.controlador = new ControladorCURegistrarDevolucion();
        nifEmpleado = controlador.obtenerNifSesion();
        
        // Se inicializan a estos valores los campos del id reserva y matrícula, los cuales nos servirán
        // para determinar si hay una devolución en curso o no (en caso de no haberla, los campos tienen estos valores).
        this.idReserva = -1;
        this.matricula = null;
    }
    

    public void comprobarReserva() {
        String id = vista.obtenerIdReserva();
        boolean idOk = compruebaStringVacio(id);
        
        // Si los campos de idReserva son distintos a los que se han inicializado, significará que
        // hay una devolución en curso
        if(this.idReserva != -1 || this.matricula != null){
            vista.errorDevolucionEnCurso();
        }
        
        else{
            if(idOk){
                String datosReserva = controlador.consultarReserva(id, nifEmpleado);

                // Tras buscar la reserva dado su id y el nif del empleado encargado, si los datos
                // devueltos son nulos es que no se ha encontrado ninguna para esos datos, lo cual
                // se indicará en la vista.
                if(datosReserva == null){
                    vista.errorReservaInexistente();
                }
                else{

                    // En caso de que se haya encontrado una reserva, se comprueba que ésta esté en
                    // alquiler. Si no es así, se indicará en la vista. Si sí que está activa, 
                    // se mostrarán los datos de la reserva en la vista y se pedirá por pantalla
                    // la confirmación de la devolución.
                    boolean reservaActiva = controlador.comprobarReservaActiva();
                    if(reservaActiva){
                        vista.reservaEncontrada(datosReserva);
                        this.idReserva = this.controlador.obtenerIdReserva();
                        this.matricula = this.controlador.obtenerMatriculaVehiculoReserva();
                    }

                    else{
                        vista.errorReservaNoEnAlquiler();
                    }
                }
            }
            else{
                String msg = "El campo de texto está vacío, rellénelo correctamente.";
                vista.mostrarPanel(msg);
            }
        }
        
        
    }
    
    public void confirmarDevolucion() {
        boolean entregaCreada = controlador.crearEntrega();
        
        if(entregaCreada){
            vista.entregaCreadaExitosamente();
        }
        
        else{
            vista.errorCreacionEntrega();
        }
    }

    public void cancelarDevolucion() {
        vista.devolucionCancelada();
        this.idReserva = -1;
        this.matricula = null;
    }
   
    public void continuarSinIncidencias() {
        boolean actualizacionEstadoReservaOk = controlador.actualizarEstadoReserva("Finalizada");
        boolean actualizacionEstadoVehiculoOk = controlador.actualizarEstadoVehiculo("EnPreparacion");
        
        if (actualizacionEstadoReservaOk && actualizacionEstadoVehiculoOk){
            vista.registroFinalExitoso();
            
            // Los campos de id de reserva y matrícula vuelven a los valores de inicialización, pues ya no
            // hay una devolución en curso.
            this.idReserva = -1;
            this.matricula = null;
        }
        else{
            vista.errorRegistroFinal();
        }
    }
    
    
    
    public void agregarIncidencias() {
        // Para añadir las incidencias, necesitaremos el id de la entrega en curso y a la cual
        // se le asociarán las incidencias.
        int idEntrega = controlador.obtenerIdEntregaEnCurso();
        
        GestorDeInterfazDeUsuario.moverAVistaIncidencias(idEntrega);
    }
    
    public void cerrarSesion(){
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaIdentificarse();
    }

    
    private boolean compruebaStringVacio(String x) {
        if(x == null)
            throw new IllegalArgumentException("El String no puede ser null.");
        return !x.isEmpty();
    }

    

    
}
