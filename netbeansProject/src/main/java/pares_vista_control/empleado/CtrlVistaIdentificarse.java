/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pares_vista_control.empleado;

import modelos.controladores_caso_uso.ControladorCUIdentificarse;
import pares_vista_control.GestorDeInterfazDeUsuario;

/**
 *
 * @author g01
 */
public class CtrlVistaIdentificarse {
    private final IdentificarseVista vista;
    private final ControladorCUIdentificarse controlador;
   
    
    public CtrlVistaIdentificarse(IdentificarseVista vista){
        this.vista = vista;
        this.controlador = new ControladorCUIdentificarse();
    }
    
    public void procesaEventoIdentificarse(){
        String d = vista.getDni();
        String p = vista.getPassword();
        
        boolean dniOk = compruebaCharArrayNoVacio(d);
        boolean passwordOk = compruebaCharArrayNoVacio(p);
        
        if(dniOk && passwordOk){
            String rol = controlador.identificarEmpleado(d,p);
            
            // En caso de que el entero devuelto sea -1, significará que no ha habido
            // coincidencia para el NIF y password especificados, por lo que habrá que notificar a la vista.
            if(rol == null){
                vista.errorNifPasswordNoCoincidente();
            }
            else{
                
                // Si el empleado no está activo, notificamos a la vista del error.
                if(!controlador.comprobarEnActivo()){
                    vista.errorNoActivo();
                }
                else{
                    vista.dispose();
                    
                    // Guardamos en la sesión el nif del empleado autenticado.
                    controlador.guardarNifSesion(d);
                    switch (rol) {
                        case "GerenteDePunto":
                            GestorDeInterfazDeUsuario.moverAVistaGerente();
                            break;
                        case "AtencionEnPunto":
                            GestorDeInterfazDeUsuario.moverAVistaAtencionCliente();
                            break;
                        case "TecnicoEnPunto":
                            GestorDeInterfazDeUsuario.moverAVistaTecnico();
                            break;
                        default:
                            break;
                    }
                }

            }
            
        }
        else{
            String msg = "Hay algún campo de texto vacio, rellénelo correctamente!!";
            vista.mostrarPanel(msg);
        }
    }
    
    
    private boolean compruebaCharArrayNoVacio(String x){
        if(x == null)
            throw new IllegalArgumentException("El String no puede ser null.");
        return !x.isEmpty();
    }
    
   
}
