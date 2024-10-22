/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control.tecnico;

import modelos.controladores_caso_uso.ControladorCURegistrarRecepcion;
import pares_vista_control.GestorDeInterfazDeUsuario;


/**
 *
 * @author g01
 */
public class CtrlVistaRegistroRecepcionTaller {
    private final RegistroRecepcionTallerVista vista;
    private final ControladorCURegistrarRecepcion controlador;
    
    
    public CtrlVistaRegistroRecepcionTaller(RegistroRecepcionTallerVista vista){
        this.vista = vista;
        this.controlador = new ControladorCURegistrarRecepcion();
    }
    
    public void comprobarMatriculaVehiculo() {
        String matricula = vista.obtenerMatricula();
        boolean matriculaOk = compruebaStringVacio(matricula);
        
        if(matriculaOk){

            boolean matriculaExistente = controlador.comprobarMatriculaExistente(matricula);

            if(matriculaExistente){
               boolean ordenDeReparacion = controlador.comprobarOrdenDeReparacion(matricula);

               if(ordenDeReparacion){
                   String datosMatricula = controlador.comprobarMatriculaEnTaller(matricula); 
                   if (datosMatricula != null){
                       vista.mostrarDatosVehiculo(datosMatricula);
                   }else{
                       vista.errorVehiculoNoEnTaller();
                   }

               } else {
                   vista.errorVehiculoNoEnReparacion();
               }

            } else {
                vista.errorMatriculaNoEncontrada();
            }

        }else{
            String msg = "El campo de texto está vacío, rellénelo correctamente.";
            vista.mostrarPanel(msg);
        }
    }
    
    public void cerrarSesion() {
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaIdentificarse();    
    }

    public void cancelarRegistro() {
        vista.registroCancelado();
    }

    public void confirmarRegistro(String matricula) {
        if(matricula == null || matricula.isEmpty()){
            String msg = "No se ha introducido ninguna matricula.";
            vista.mostrarPanel(msg);
        }
        else{
            boolean registroCreado = controlador.crearRegistro(matricula);
            boolean registroActualizado;
            if(!registroCreado){
                vista.errorCreacionRegistro();  
            }
            else{
                registroActualizado = controlador.registrarEntradaPunto(matricula);
                if(registroActualizado)
                    vista.registroCreadoExistosamente();
                else
                     vista.errorCreacionRegistro();
            }
        }    
    }
    
    private boolean compruebaStringVacio(String x) {
        if(x == null)
            throw new IllegalArgumentException("El String no puede ser null.");
        return !x.isEmpty();
    }
    
    
    
}
