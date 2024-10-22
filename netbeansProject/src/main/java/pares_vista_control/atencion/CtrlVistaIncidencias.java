/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control.atencion;

import modelos.controladores_caso_uso.ControladorCUAgregarIncidencias;
import pares_vista_control.GestorDeInterfazDeUsuario;

/**
 *
 * @author g01
 */
public class CtrlVistaIncidencias {
    private IncidenciasVista vista;
    private ControladorCUAgregarIncidencias controlador;
    private int idEntrega;
    private String tipoIncidencia;

    public CtrlVistaIncidencias(IncidenciasVista vista, int idEntrega) {
        this.vista = vista;
        this.idEntrega = idEntrega;
        this.controlador = new ControladorCUAgregarIncidencias(this.idEntrega);
        this.tipoIncidencia = null;
    }
    
    
    public void tipoIncidenciaSeleccionada() { 
        String tipoIncid = vista.getTipoIncidencia();
        // Modificamos el String del tipo de incidencia para que sea igual
        // que como está almacenado en la BD. De no haber coincidencia con ninguno de estos
        // tipos, el tipo de incidencia seguirá siendo null.
        switch(tipoIncid){
            case "Accidente":
                this.tipoIncidencia = "Accidente";
                break;
            case "Avería":
                this.tipoIncidencia = "Averia";
                break;
            case "Suciedad extrema":
                this.tipoIncidencia = "SuciedadExtrema";
                break;
            case "Retraso en la entrega":
                this.tipoIncidencia = "RetrasoEnLaEntrega";
                break;
            default:
                this.tipoIncidencia = null;
                break;
        }
    }
    
    public void agregarIncidencia() {
        String descripcionIncidencia = vista.getDescripcionIncidencia();
        // Comprobamos que se haya seleccionado un tipo de incidencia.
        if (this.tipoIncidencia == null){
            vista.faltaTipoIncidencia();
        }
        
        // Comprobamos también que se haya añadido una descripción de la incidencia.
        else if(descripcionIncidencia.isEmpty()){
            vista.faltaDescripcionIncidencia();
        }
        
        else{
            controlador.nuevaIncidencia(this.tipoIncidencia, descripcionIncidencia);
            String datosIncidencias = controlador.obtenerDatosIncidencias().concat("\n¿Quiere añadir otra incidencia?");
        
            vista.mostrarIncidencias(datosIncidencias);
        }
        
    }
    
    public void finalizarIncidencias() {
        // Se cierra esta vista y se vuelve de nuevo a la vista de la gestión de devoluciones.
        vista.dispose();
        GestorDeInterfazDeUsuario.volverAVistaDevolucionDesdeVistaIncidencias();
    }
    
    
    public void cerrarSesion(){
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaIdentificarse();
    }


}
