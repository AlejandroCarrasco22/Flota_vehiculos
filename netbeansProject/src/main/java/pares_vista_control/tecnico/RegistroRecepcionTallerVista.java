
package pares_vista_control.tecnico;

import javax.swing.JOptionPane;

/**
 *
 * @author g01
 */
public class RegistroRecepcionTallerVista extends javax.swing.JFrame {
    private final CtrlVistaRegistroRecepcionTaller controlador;
    
    /**
     * Creates new form TecnicoPuntoVista
     */
    public RegistroRecepcionTallerVista() {
        initComponents();
        botonComprobarReserva.setEnabled(false);
        botonConfirmarRegistro.setEnabled(false);
        botonCancelarRegistro.setEnabled(false);
        controlador = new CtrlVistaRegistroRecepcionTaller(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonConfirmarRegistro = new javax.swing.JButton();
        botonCancelarRegistro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDatosVehiculo = new javax.swing.JTextArea();
        EtiquetaMatricula = new javax.swing.JLabel();
        botonCerrarSesion = new javax.swing.JButton();
        botonComprobarReserva = new javax.swing.JButton();
        campoMatricula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonConfirmarRegistro.setText("Confirmar Registro");
        botonConfirmarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarRegistroActionPerformed(evt);
            }
        });

        botonCancelarRegistro.setText("Cancelar Registro");
        botonCancelarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarRegistroActionPerformed(evt);
            }
        });

        campoDatosVehiculo.setColumns(20);
        campoDatosVehiculo.setRows(5);
        jScrollPane1.setViewportView(campoDatosVehiculo);

        EtiquetaMatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaMatricula.setText("Matricula vehiculo:");

        botonCerrarSesion.setText("Cerrar Sesion");
        botonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarSesionActionPerformed(evt);
            }
        });

        botonComprobarReserva.setText("Comprobar vehiculo");
        botonComprobarReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComprobarReservaActionPerformed(evt);
            }
        });

        campoMatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoMatriculaFocusGained(evt);
            }
        });
        campoMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoMatriculaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(EtiquetaMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonComprobarReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonConfirmarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonCancelarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonComprobarReserva, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(EtiquetaMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoMatricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonConfirmarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(botonCancelarRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConfirmarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarRegistroActionPerformed
        // TODO add your handling code here:
        String matricula = campoMatricula.getText();
        controlador.confirmarRegistro(matricula);
    }//GEN-LAST:event_botonConfirmarRegistroActionPerformed

    private void botonComprobarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComprobarReservaActionPerformed
        campoDatosVehiculo.setText("");
        controlador.comprobarMatriculaVehiculo();
    }//GEN-LAST:event_botonComprobarReservaActionPerformed

    private void botonCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarRegistroActionPerformed
        // TODO add your handling code here:
        controlador.cancelarRegistro();
    }//GEN-LAST:event_botonCancelarRegistroActionPerformed

    private void botonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarSesionActionPerformed
        // TODO add your handling code here:
        controlador.cerrarSesion();
    }//GEN-LAST:event_botonCerrarSesionActionPerformed

    private void campoMatriculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoMatriculaKeyTyped
        // TODO add your handling code here:
        botonComprobarReserva.setEnabled(true);
    }//GEN-LAST:event_campoMatriculaKeyTyped

    private void campoMatriculaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoMatriculaFocusGained
        // TODO add your handling code here:
        campoMatricula.setText("");
    }//GEN-LAST:event_campoMatriculaFocusGained

    public void mostrarPanel(String text){
        JOptionPane.showMessageDialog(null, text);
    }
    
    public void registroCancelado() {
        campoMatricula.setText("");
        campoDatosVehiculo.setText("");
        botonComprobarReserva.setEnabled(false);
        botonConfirmarRegistro.setEnabled(false);
        botonCancelarRegistro.setEnabled(false);
    }
    
    public void registroCreadoExistosamente() {
        campoDatosVehiculo.setText("El registro se ha creado exitosamente");
        botonComprobarReserva.setEnabled(false);
        botonConfirmarRegistro.setEnabled(false);
        botonCancelarRegistro.setEnabled(false);
    }

    public void errorCreacionRegistro() {
        JOptionPane.showMessageDialog(null, "El registro no se ha creado exitosamente",
                "Error", JOptionPane.ERROR_MESSAGE);
        campoMatricula.setText("");
        campoDatosVehiculo.setText("");
        botonComprobarReserva.setEnabled(false);
        botonConfirmarRegistro.setEnabled(false);
        botonCancelarRegistro.setEnabled(false);
    }

    public void errorVehiculoNoEnReparacion() {
        JOptionPane.showMessageDialog(null, "La matrícula que se ha introducido no tiene asociada ninguna orden de reparación",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void errorMatriculaNoEncontrada() {
        JOptionPane.showMessageDialog(null, "La matrícula que se ha introducido no corresponde con ningún vehículo",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    public void errorVehiculoNoEnTaller(){
       JOptionPane.showMessageDialog(null, "El vehículo seleccionado no se encuentra en un taller",
                "Error", JOptionPane.ERROR_MESSAGE); 
    }
    
    public void mostrarDatosVehiculo(String datosMatricula) {
        campoDatosVehiculo.setText(datosMatricula);
        botonConfirmarRegistro.setEnabled(true);
        botonCancelarRegistro.setEnabled(true);
    }
    
    public String obtenerMatricula() {
        return campoMatricula.getText();
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EtiquetaMatricula;
    private javax.swing.JButton botonCancelarRegistro;
    private javax.swing.JButton botonCerrarSesion;
    private javax.swing.JButton botonComprobarReserva;
    private javax.swing.JButton botonConfirmarRegistro;
    private javax.swing.JTextArea campoDatosVehiculo;
    private javax.swing.JTextField campoMatricula;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    

    

    
}
