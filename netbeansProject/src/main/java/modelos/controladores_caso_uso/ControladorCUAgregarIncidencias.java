/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.controladores_caso_uso;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import modelos.Incidencia;
import persistencia.daos.DAOIncidencia;


/**
 *
 * @author g01
 */
public class ControladorCUAgregarIncidencias {
    private final ArrayList <Incidencia> incidencias;
    private final int idEntrega;
    
    
    public ControladorCUAgregarIncidencias(int idEntrega){
        this.idEntrega = idEntrega;
        
        // Obtenemos posibles incidencias anteriores que pudiera haber para la entrega correspondiente.
        List<String> incidenciasJSONString = DAOIncidencia.obtenerIncidenciasAnteriores(idEntrega);
        incidencias = new ArrayList<>();
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader;
        JsonObject incidenciaJSON;
                
        for (String incidenciaJSONString : incidenciasJSONString){
            
            reader = factory.createReader(new StringReader(incidenciaJSONString));
            incidenciaJSON = reader.readObject();
            
            incidencias.add(new Incidencia(incidenciaJSON.getInt("IdIncidencia"), incidenciaJSON.getString("TipoIncidencia"), 
                    incidenciaJSON.getString("Descripcion"), incidenciaJSON.getInt("IdEntrega")));
        }
    }

    public void nuevaIncidencia(String tipoIncidencia, String descripcionIncidencia) {
        int idIncidencia = DAOIncidencia.registrarNuevaIncidencia(this.idEntrega, tipoIncidencia, descripcionIncidencia);
        Incidencia i = new Incidencia(idIncidencia, tipoIncidencia, descripcionIncidencia, idEntrega);
        
        incidencias.add(i);
    }

    public String obtenerDatosIncidencias() {
        String datosIncidencias = "";
        
        for(Incidencia i : incidencias){
            String tipo = null;
            
            // Volvemos a pasar el formato del tipo de incidencia
            // de nuevo al formato salida.
            switch(i.getTipoIncidencia()){
                case "Accidente":
                    tipo = "Accidente";
                    break;
                case "Averia":
                    tipo = "Avería";
                    break;
                case "SuciedadExtrema":
                    tipo = "Suciedad extrema";
                    break;
                case "RetrasoEnLaEntrega":
                    tipo = "Retraso en la entrega";
                    break;
                default:
                    break;
            }
            
            datosIncidencias = datosIncidencias.concat("-> "+tipo+": "+i.getDescripcionIncidencia()+"\n");
        }
        
        // Devuelve un String que representa el conjunto de incidencias de la entrega en curso.
        return datosIncidencias;
    }
    
    


    
    
    
    
    
   
}
