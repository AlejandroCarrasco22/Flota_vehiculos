/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control.gerente;

import pares_vista_control.GestorDeInterfazDeUsuario;

/**
 *
 * @author g01
 */
public class CtrlVistaGerente {
    private final GerenteVista vista;
    
    public CtrlVistaGerente(GerenteVista vista){
        this.vista = vista;      
    }

    public void cambiarVistaConsultarVehiculosPropuestosBaja() {
        mostrarAccionNoImplementada();
    }

    public void cambiarVistaConsultarVehiculosPunto() {
        vista.dispose();
        GestorDeInterfazDeUsuario.moverAVistaConsultarVehiculos();
    }
    
    private void mostrarAccionNoImplementada(){
        String msg = "Opción no implementada";
        vista.mostrarPanel(msg);
    }
}
