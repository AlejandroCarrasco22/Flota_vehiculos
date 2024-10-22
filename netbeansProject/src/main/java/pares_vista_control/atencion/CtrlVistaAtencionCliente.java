/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control.atencion;

import pares_vista_control.GestorDeInterfazDeUsuario;

/**
 *
 * @author g01
 */
public class CtrlVistaAtencionCliente {
    private final AtencionClienteVista vista;
    
    public CtrlVistaAtencionCliente(AtencionClienteVista vista){
        this.vista = vista;      
    }

    public void cambiarVistaCrearAlquiler() {
        mostrarAccionNoImplementada();
    }

    public void cambiarVistaConsultarReservasDia() {
        mostrarAccionNoImplementada();
    }

    public void cambiarVistaRegistrarDevolucionVehiculo() {
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaRegistrarDevolucionVehiculo();
    }

    public void cambiarVistaMostrarFacturas() {
        mostrarAccionNoImplementada();
    }

    public void cambiarVistaCrearfacturaIncidencias() {
        mostrarAccionNoImplementada();
    }
    
    private void mostrarAccionNoImplementada(){
        String msg = "Opción no implementada";
        vista.mostrarPanel(msg);
    }
    
}
