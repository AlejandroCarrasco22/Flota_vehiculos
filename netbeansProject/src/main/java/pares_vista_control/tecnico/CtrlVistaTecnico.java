/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control.tecnico;

import pares_vista_control.GestorDeInterfazDeUsuario;

/**
 *
 * @author g01
 */
public class CtrlVistaTecnico {
    private final TecnicoVista vista;
    
    public CtrlVistaTecnico(TecnicoVista vista){
        this.vista = vista;
    }

    public void cambiarVistaCrearOrdenReparacion() {
        mostrarAccionNoImplementada();
    }

    public void cambiarVistaRegistrarRecepcionVehiculo() {
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaRegistrarRecepcionTaller();
    }

    public void cambiarVistaAnadirIncidenciasAccidente() {
        mostrarAccionNoImplementada();
    }
    
    private void mostrarAccionNoImplementada(){
        String msg = "Opción no implementada";
        vista.mostrarPanel(msg);
    }
    
}
