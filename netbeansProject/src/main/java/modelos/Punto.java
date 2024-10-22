/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author g01
 */
public class Punto {
    private String localizacion;
    private int numeroVia;
    private String email;
    private String telefono;

    public Punto(String localizacion, int numeroVia, String email, String telefono) {
        if (localizacion==null)
            throw new IllegalArgumentException("localizacion null not allowed");
        if (localizacion.isEmpty())
            throw new IllegalArgumentException("localizacion empty not allowed");
        if(numeroVia <= 0)
            throw new IllegalArgumentException("numeroVia can't be less than 1");
        if (email == null)
            throw new IllegalArgumentException("email null not allowed");
        if (email.isEmpty())
            throw new IllegalArgumentException("email empty not allowed");
        if (telefono == null)
            throw new IllegalArgumentException("telefono null not allowed");
        if (telefono.isEmpty())
            throw new IllegalArgumentException("telefono empty not allowed");
        
        this.localizacion = localizacion;
        this.numeroVia = numeroVia;
        this.email = email;
        this.telefono = telefono;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        if (localizacion==null)
            throw new IllegalArgumentException("localizacion null not allowed");
        if (localizacion.isEmpty())
            throw new IllegalArgumentException("localizacion empty not allowed");
        this.localizacion = localizacion;
    }

    public int getNumeroVia() {
        return numeroVia;
    }

    public void setNumeroVia(int numeroVia) {
        if(numeroVia <= 0)
            throw new IllegalArgumentException("numeroVia can't be less than 1");
        this.numeroVia = numeroVia;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException("email null not allowed");
        if (email.isEmpty())
            throw new IllegalArgumentException("email empty not allowed");
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null)
            throw new IllegalArgumentException("telefono null not allowed");
        if (telefono.isEmpty())
            throw new IllegalArgumentException("telefono empty not allowed");
        this.telefono = telefono;
    }
    
    
    
    
}
