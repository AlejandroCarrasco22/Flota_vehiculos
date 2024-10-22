/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pares_vista_control.gerente;

import javax.swing.JOptionPane;

/**
 *
 * @author g01
 */
public class GerenteVista extends javax.swing.JFrame {
    private final CtrlVistaGerente controlador;
    
    /**
     * Creates new form GerenteEnPuntoVista
     */
    public GerenteVista() {
        initComponents();
        controlador = new CtrlVistaGerente(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonConsultaVehiculosPunto = new javax.swing.JButton();
        botonConsultarVehiculosPropuestosBaja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonConsultaVehiculosPunto.setText("Consulta Vehiculos Punto");
        botonConsultaVehiculosPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultaVehiculosPuntoActionPerformed(evt);
            }
        });

        botonConsultarVehiculosPropuestosBaja.setText("Consultar Vehiculos Propuestos Para Baja");
        botonConsultarVehiculosPropuestosBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConsultarVehiculosPropuestosBajaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonConsultarVehiculosPropuestosBaja, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(botonConsultaVehiculosPunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonConsultaVehiculosPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonConsultarVehiculosPropuestosBaja, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConsultarVehiculosPropuestosBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultarVehiculosPropuestosBajaActionPerformed
        // TODO add your handling code here:
        controlador.cambiarVistaConsultarVehiculosPropuestosBaja();
    }//GEN-LAST:event_botonConsultarVehiculosPropuestosBajaActionPerformed

    private void botonConsultaVehiculosPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConsultaVehiculosPuntoActionPerformed
        // TODO add your handling code here:
        controlador.cambiarVistaConsultarVehiculosPunto();
    }//GEN-LAST:event_botonConsultaVehiculosPuntoActionPerformed

    public void mostrarPanel(String text){
        JOptionPane.showMessageDialog(null, text);
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConsultaVehiculosPunto;
    private javax.swing.JButton botonConsultarVehiculosPropuestosBaja;
    // End of variables declaration//GEN-END:variables
}