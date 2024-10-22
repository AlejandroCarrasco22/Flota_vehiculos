/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pares_vista_control;

import pares_vista_control.tecnico.RegistroRecepcionTallerVista;
import pares_vista_control.tecnico.TecnicoVista;
import pares_vista_control.atencion.IncidenciasVista;
import pares_vista_control.empleado.IdentificarseVista;
import pares_vista_control.atencion.DevolucionVehiculoVista;
import pares_vista_control.atencion.AtencionClienteVista;
import pares_vista_control.gerente.GerenteVista;
import pares_vista_control.gerente.ConsultarVehiculosVista;

/**
 *
 * @author g01
 */
public class GestorDeInterfazDeUsuario {

    private static DevolucionVehiculoVista devolVehiculoVista;
    
    private GestorDeInterfazDeUsuario(){
        throw new IllegalStateException("Utility class");
    }
    
    public static void moverAVistaGerente(){
        GerenteVista gerenteVista = new GerenteVista();
        gerenteVista.setVisible(true);
    }
    
    public static void moverAVistaConsultarVehiculos(){
        ConsultarVehiculosVista consultarVehiculosVista = new ConsultarVehiculosVista();
        consultarVehiculosVista.setVisible(true);
    }
    
    public static void moverAVistaAtencionCliente(){
        AtencionClienteVista atencionVista = new AtencionClienteVista();
        atencionVista.setVisible(true);
    }
    
    public static void moverAVistaRegistrarDevolucionVehiculo(){
        devolVehiculoVista = new DevolucionVehiculoVista();
        devolVehiculoVista.setVisible(true);
    }
    
    public static void moverAVistaIncidencias(int idEntrega) {
        GestorDeInterfazDeUsuario.devolVehiculoVista.setVisible(false);
                
        IncidenciasVista incidenciasVista = new IncidenciasVista(idEntrega);
        incidenciasVista.setVisible(true);
    } 

    public static void volverAVistaDevolucionDesdeVistaIncidencias() {
        // Se vuelve a la vista de atención al cliente, que estaba ya creada pero no visible, y el proceso a seguir a continuación
        // es el mismo que se sigue cuando no hay incidencias (paso 7 del CU Registrar devolución).
        GestorDeInterfazDeUsuario.devolVehiculoVista.setVisible(true);
        
    }
    
    public static void moverAVistaTecnico(){
        TecnicoVista tecnicoVista = new TecnicoVista();
        tecnicoVista.setVisible(true);
    }
    
    public static void moverAVistaRegistrarRecepcionTaller(){
        RegistroRecepcionTallerVista registroRecepcionTallerVista = new RegistroRecepcionTallerVista();
        registroRecepcionTallerVista.setVisible(true);
    }
    
    public static void moverAVistaIdentificarse(){
        IdentificarseVista identificarseVista = new IdentificarseVista();
        identificarseVista.setVisible(true);
    }
    
}
