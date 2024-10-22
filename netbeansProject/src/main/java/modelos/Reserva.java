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
public class Reserva {
    private int id;
    private String matricula;
    private String color;
    private String nombreModelo;
    private String nombreMarca;
    private String nifCliente;
    private String nombreUsuario;
    private String estadoReserva;
    private static final String NULLERROR = "Null not allowed";
    private static final String EMPTYERROR = "Empty not allowed";
    
    
    public Reserva(int id, String matricula, String color, String nombreModelo, 
                        String nombreMarca, String nifCliente, String nombreUsuario,
                        String estadoReserva){        
        if (matricula==null)
            throw new IllegalArgumentException(NULLERROR);
        if (matricula.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        if (color == null)
            throw new IllegalArgumentException(NULLERROR);
        if (color.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        if (nombreModelo == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nombreModelo.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        if (nombreMarca == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nombreMarca.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        if (nifCliente == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nifCliente.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        if (nombreUsuario == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nombreUsuario.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        if (estadoReserva == null)
            throw new IllegalArgumentException(NULLERROR);
        if (estadoReserva.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        
        this.id = id;
        this.matricula = matricula;
        this.color = color;
        this.nombreModelo = nombreModelo;
        this.nombreMarca = nombreMarca;
        this.nifCliente = nifCliente;
        this.nombreUsuario = nombreUsuario;
        this.estadoReserva = estadoReserva;
        
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        if (matricula==null)
            throw new IllegalArgumentException(NULLERROR);
        if (matricula.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null)
            throw new IllegalArgumentException(NULLERROR);
        if (color.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.color = color;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        if (nombreModelo == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nombreModelo.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.nombreModelo = nombreModelo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        if (nombreMarca == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nombreMarca.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.nombreMarca = nombreMarca;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        if (nifCliente == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nifCliente.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.nifCliente = nifCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null)
            throw new IllegalArgumentException(NULLERROR);
        if (nombreUsuario.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.nombreUsuario = nombreUsuario;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        if (estadoReserva == null)
            throw new IllegalArgumentException(NULLERROR);
        if (estadoReserva.isEmpty())
            throw new IllegalArgumentException(EMPTYERROR);
        this.estadoReserva = estadoReserva;
    }

    
    

}
