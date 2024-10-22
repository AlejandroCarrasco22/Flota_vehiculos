/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author g01
 */
public class Sesion {
    
    private static Sesion sesion;

    public static Sesion getInstance() {
        if (sesion==null) {
            sesion = new Sesion();
        }
        return sesion;
    }

    // Instance level
    
    private String nif;
    
    public String getNif() {
        return this.nif;
    }
    
    public void setNif(String nif){
        this.nif = nif;
    }
    
}
