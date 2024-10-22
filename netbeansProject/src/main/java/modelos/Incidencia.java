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
public class Incidencia {
    private int idIncidencia;
    private String tipoIncidencia;
    private String descripcionIncidencia;
    private int idEntrega;
    private static final String ERRORNULL = "El String no puede ser null";
    private static final String ERRORVACIO = "El String no puede estar vacío";
    
    
    public Incidencia(int idIncidencia, String tipoIncidencia, String descripcionIncidencia, int idEntrega){
        this.idIncidencia = idIncidencia;
        
        if(tipoIncidencia == null){
            throw new IllegalArgumentException(ERRORNULL);
        }
        if(tipoIncidencia.isEmpty()){
            throw new IllegalArgumentException(ERRORVACIO);
        }
        
        this.tipoIncidencia = tipoIncidencia;
        
        if(descripcionIncidencia == null){
            throw new IllegalArgumentException(ERRORNULL);
        }
        if(descripcionIncidencia.isEmpty()){
            throw new IllegalArgumentException(ERRORVACIO);
        }
        
        this.descripcionIncidencia = descripcionIncidencia;
        
        this.idEntrega = idEntrega;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        if(tipoIncidencia == null){
            throw new IllegalArgumentException(ERRORNULL);
        }
        if(tipoIncidencia.isEmpty()){
            throw new IllegalArgumentException(ERRORVACIO);
        }
        this.tipoIncidencia = tipoIncidencia;
    }

    public String getDescripcionIncidencia() {
        return descripcionIncidencia;
    }

    public void setDescripcionIncidencia(String descripcionIncidencia) {
        if(descripcionIncidencia == null){
            throw new IllegalArgumentException(ERRORNULL);
        }
        if(descripcionIncidencia.isEmpty()){
            throw new IllegalArgumentException(ERRORVACIO);
        }
        this.descripcionIncidencia = descripcionIncidencia;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    

    

}
