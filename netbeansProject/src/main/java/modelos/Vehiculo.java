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
public class Vehiculo {
    private String matricula;
    private String estadoVehiculo;
    private String color;
    private String nombreModelo;
    private String nombreMarca;
    
    
    
    public Vehiculo(String matricula, String estadoVehiculo, String color, 
                        String nombreModelo, String nombreMarca){
        if (matricula==null)
            throw new IllegalArgumentException("Matricula null not allowed");
        if (matricula.isEmpty())
            throw new IllegalArgumentException("Matricula empty not allowed");
        if (estadoVehiculo == null)
            throw new IllegalArgumentException("EstadoVehiculo null not allowed");
        if (estadoVehiculo.isEmpty())
            throw new IllegalArgumentException("EstadoVehiculo empty not allowed");
        if (color == null)
            throw new IllegalArgumentException("Color null not allowed");
        if (color.isEmpty())
            throw new IllegalArgumentException("Color empty not allowed");
        if (nombreModelo == null)
            throw new IllegalArgumentException("NombreModelo null not allowed");
        if (nombreModelo.isEmpty())
            throw new IllegalArgumentException("NombreModelo empty not allowed");
        if (nombreMarca == null)
            throw new IllegalArgumentException("NombreMarca null not allowed");
        if (nombreMarca.isEmpty())
            throw new IllegalArgumentException("NombreMarca empty not allowed");
         
        this.matricula = matricula;
        this.estadoVehiculo = estadoVehiculo;
        this.color = color;
        this.nombreModelo = nombreModelo;
        this.nombreMarca = nombreMarca;
        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula==null)
            throw new IllegalArgumentException("Matricula null not allowed");
        if (matricula.isEmpty())
            throw new IllegalArgumentException("Matricula empty not allowed");
        this.matricula = matricula;
    }

    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(String estadoVehiculo) {
        if (estadoVehiculo == null)
            throw new IllegalArgumentException("EstadoVehiculo null not allowed");
        if (estadoVehiculo.isEmpty())
            throw new IllegalArgumentException("EstadoVehiculo empty not allowed");
        this.estadoVehiculo = estadoVehiculo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null)
            throw new IllegalArgumentException("Color null not allowed");
        if (color.isEmpty())
            throw new IllegalArgumentException("Color empty not allowed");
        this.color = color;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        if (nombreModelo == null)
            throw new IllegalArgumentException("NombreModelo null not allowed");
        if (nombreModelo.isEmpty())
            throw new IllegalArgumentException("NombreModelo empty not allowed");
        this.nombreModelo = nombreModelo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        if (nombreMarca == null)
            throw new IllegalArgumentException("NombreMarca null not allowed");
        if (nombreMarca.isEmpty())
            throw new IllegalArgumentException("NombreMarca empty not allowed");
        this.nombreMarca = nombreMarca;
    }
    

}
