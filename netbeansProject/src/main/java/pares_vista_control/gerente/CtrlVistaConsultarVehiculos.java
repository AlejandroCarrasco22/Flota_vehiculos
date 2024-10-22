/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pares_vista_control.gerente;

import modelos.controladores_caso_uso.ControladorCUConsultarVehiculos;
import java.util.List;
import pares_vista_control.GestorDeInterfazDeUsuario;


/**
 *
 * @author g01
 */
public class CtrlVistaConsultarVehiculos {
    private final ConsultarVehiculosVista vista;
    private final ControladorCUConsultarVehiculos controlador;
    private final String nifEmpleado;
   
    
    public CtrlVistaConsultarVehiculos(ConsultarVehiculosVista vista){
        this.vista = vista;
        this.controlador = new ControladorCUConsultarVehiculos();
        nifEmpleado = controlador.obtenerNifSesion();
    }
    
    public void solicitarVerVehiculos (){
        List <String> listaVehiculos = controlador.obtenerListaVehiculos(nifEmpleado);
        
        if(listaVehiculos.isEmpty()){
            vista.errorListaVehiculos("No se han encontrado vehículos.");
        }
        else{
            vista.mostrarVehiculos(listaVehiculos);
        }
    }
    
    public void seleccionarVehiculo(String v){
        String punto = controlador.obtenerDatosPunto(v);
        
        vista.mostrarDatosPunto(punto);
    }

    public void cerrarSesion(){
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaIdentificarse();
    }
    
    
   
}
