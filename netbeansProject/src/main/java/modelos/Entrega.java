/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Timestamp;

/**
 *
 * @author g01
 */
public class Entrega {
    private int idEntrega;
    private Timestamp momento;
    private int idAlquiler;
    private String nifEmpleado;
    
    
    public Entrega(int idEntrega, Timestamp momento, int idAlquiler, String nifEmpleado){
        this.idEntrega = idEntrega;
        
        if(momento == null){
            throw new IllegalArgumentException("Timestamp no puede ser null.");
        }
        
        this.momento = momento;
        
        this.idAlquiler = idAlquiler;
        
        if(nifEmpleado == null){
            throw new IllegalArgumentException("El String no puede ser null.");
        }
        if(nifEmpleado.isEmpty()){
            throw new IllegalArgumentException("El String no puede estar vacío.");
        }
        
        this.nifEmpleado = nifEmpleado;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Timestamp getMomento() {
        return momento;
    }

    public void setMomento(Timestamp momento) {
        if(momento == null){
            throw new IllegalArgumentException("Timestamp no puede ser null.");
        }
        this.momento = momento;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getNifEmpleado() {
        return nifEmpleado;
    }

    public void setNifEmpleado(String nifEmpleado) {
        if(nifEmpleado == null){
            throw new IllegalArgumentException("El String no puede ser null.");
        }
        if(nifEmpleado.isEmpty()){
            throw new IllegalArgumentException("El String no puede estar vacío.");
        }
        this.nifEmpleado = nifEmpleado;
    }

    

}
