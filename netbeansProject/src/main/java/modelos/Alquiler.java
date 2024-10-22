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
public class Alquiler {
    private int idAlquiler;
    private int idReserva;
    
    
    public Alquiler(int idAlquiler, int idReserva){
        this.idAlquiler = idAlquiler;
        this.idReserva = idReserva;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

}
