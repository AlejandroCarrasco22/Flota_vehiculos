/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author g01
 */
public class Empleado {
    private String name;
    private String dni;
    private String rol;
    private int vinculo;
    private int disponibilidad;
    
    
    public Empleado(String name, String dni, String password, 
                        String rol, int vinculo, int disponibilidad){
        if (name==null)
            throw new IllegalArgumentException("Nombre null not allowed");
        if (name.isEmpty())
            throw new IllegalArgumentException("Nombre empty not allowed");
        if(dni == null)
            throw new IllegalArgumentException("Dni null not allowed");
        if (dni.isEmpty())
            throw new IllegalArgumentException("dni empty not allowed");
        if(password == null)
            throw new IllegalArgumentException("Password null not allowed");
        if (password.isEmpty())
            throw new IllegalArgumentException("password empty not allowed");
        if(rol == null)
            throw new IllegalArgumentException("Password null not allowed");
        if (rol.isEmpty())
            throw new IllegalArgumentException("password empty not allowed");
         if(vinculo < 1 || vinculo > 3)
            throw new IllegalArgumentException("Vinculo must be between 1 and 3");
        if(disponibilidad < 1 || disponibilidad > 3)
            throw new IllegalArgumentException("Disponibilidad must be between 1 and 3");
        this.name = name;
        this.dni = dni;
        this.rol = rol;
        this.vinculo = vinculo;
        this.disponibilidad = disponibilidad;
        
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String name) {
        if (name==null)
            throw new IllegalArgumentException("Nombre null not allowed");
        if (name.isEmpty())
            throw new IllegalArgumentException("Nombre empty not allowed");
        this.name = name;
    }

    public void setDni(String dni) {
        if(dni == null)
            throw new IllegalArgumentException("Dni null not allowed");
        if (dni.isEmpty())
            throw new IllegalArgumentException("dni empty not allowed");

        this.dni = dni;
    }
    
    public boolean estaActivo(){
        boolean activo = false;
        
        // Se considera que un empleado está activo cuando está 'Contratado' (valor 1 de vinculo)
        // y si su disponibilidad en el momento es de 'Trabajando' (valor 3 de disponibilidad).
        // Los valores enteros que corresponden a cada valor de cada enumerado están indicados
        // en el SQL.
        if(vinculo == 1 && disponibilidad == 3){
            activo = true;
        }
        
        return activo;
    }
    
    
    
    public String obtenerRolActual(){
        // 1 si es gerente, 2 si es empleado de atención al público en punto y 3 si es técnico en punto.
        return rol;
    }
    
    
}
