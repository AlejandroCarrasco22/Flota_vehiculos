/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pares_vista_control.tecnico;

import javax.swing.JOptionPane;

/**
 *
 * @author g01
 */
public class TecnicoVista extends javax.swing.JFrame {
    private final CtrlVistaTecnico controlador;
    
    /**
     * Creates new form TecnicoDePuntoFuncionalidadesVista
     */
    public TecnicoVista() {
        initComponents();
        controlador = new CtrlVistaTecnico(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonRegistrarRecepcionVehiculo = new javax.swing.JButton();
        botonCrearOrdenReparacion = new javax.swing.JButton();
        botonAnadirIncidenciasAccidente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonRegistrarRecepcionVehiculo.setText("Registrar Recepcion Vehiculo Taller");
        botonRegistrarRecepcionVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarRecepcionVehiculoActionPerformed(evt);
            }
        });

        botonCrearOrdenReparacion.setText("Crear Orden Reparacion");
        botonCrearOrdenReparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearOrdenReparacionActionPerformed(evt);
            }
        });

        botonAnadirIncidenciasAccidente.setText("Añadir Incidencias Accidente");
        botonAnadirIncidenciasAccidente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirIncidenciasAccidenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonAnadirIncidenciasAccidente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonRegistrarRecepcionVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(botonCrearOrdenReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonRegistrarRecepcionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonCrearOrdenReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonAnadirIncidenciasAccidente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCrearOrdenReparacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearOrdenReparacionActionPerformed
        // TODO add your handling code here:
        controlador.cambiarVistaCrearOrdenReparacion();
    }//GEN-LAST:event_botonCrearOrdenReparacionActionPerformed

    private void botonRegistrarRecepcionVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarRecepcionVehiculoActionPerformed
        // TODO add your handling code here:
        controlador.cambiarVistaRegistrarRecepcionVehiculo();
        
    }//GEN-LAST:event_botonRegistrarRecepcionVehiculoActionPerformed

    private void botonAnadirIncidenciasAccidenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirIncidenciasAccidenteActionPerformed
        // TODO add your handling code here:
        controlador.cambiarVistaAnadirIncidenciasAccidente();
    }//GEN-LAST:event_botonAnadirIncidenciasAccidenteActionPerformed

    public void mostrarPanel(String text){
        JOptionPane.showMessageDialog(null, text);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAnadirIncidenciasAccidente;
    private javax.swing.JButton botonCrearOrdenReparacion;
    private javax.swing.JButton botonRegistrarRecepcionVehiculo;
    // End of variables declaration//GEN-END:variables
}
