/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pares_vista_control.empleado;

import javax.swing.JOptionPane;

/**
 *
 * @author g01
 */
public class IdentificarseVista extends javax.swing.JFrame {
    private final CtrlVistaIdentificarse controlador;

    /**
     * Creates new form MedicionTemperatura
     */
    public IdentificarseVista() {
        initComponents();
        controlador = new CtrlVistaIdentificarse(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelSuperior = new javax.swing.JPanel();
        PanelEtiquetasCampos = new javax.swing.JPanel();
        EtiquetaNIF = new javax.swing.JLabel();
        CampoNIF = new javax.swing.JTextField();
        EtiquetaPassword = new javax.swing.JLabel();
        CampoPassword = new javax.swing.JPasswordField();
        PanelInferior = new javax.swing.JPanel();
        BotonAceptar = new javax.swing.JButton();
        BotonBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        PanelSuperior.setLayout(new java.awt.GridLayout(1, 2));

        PanelEtiquetasCampos.setLayout(new java.awt.GridLayout(2, 2));

        EtiquetaNIF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaNIF.setText("NIF:");
        EtiquetaNIF.setMaximumSize(new java.awt.Dimension(10000, 10000));
        EtiquetaNIF.setPreferredSize(new java.awt.Dimension(300, 40));
        PanelEtiquetasCampos.add(EtiquetaNIF);

        CampoNIF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoNIFActionPerformed(evt);
            }
        });
        PanelEtiquetasCampos.add(CampoNIF);

        EtiquetaPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EtiquetaPassword.setText("Contraseña:");
        EtiquetaPassword.setMaximumSize(new java.awt.Dimension(10000, 10000));
        EtiquetaPassword.setPreferredSize(new java.awt.Dimension(300, 40));
        PanelEtiquetasCampos.add(EtiquetaPassword);
        PanelEtiquetasCampos.add(CampoPassword);

        PanelSuperior.add(PanelEtiquetasCampos);

        getContentPane().add(PanelSuperior);

        PanelInferior.setLayout(new java.awt.GridLayout(1, 2));

        BotonAceptar.setText("Aceptar");
        BotonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAceptarActionPerformed(evt);
            }
        });
        PanelInferior.add(BotonAceptar);

        BotonBorrar.setText("Borrar");
        BotonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBorrarActionPerformed(evt);
            }
        });
        PanelInferior.add(BotonBorrar);

        getContentPane().add(PanelInferior);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoNIFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoNIFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoNIFActionPerformed

    private void BotonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAceptarActionPerformed
        controlador.procesaEventoIdentificarse();
    }//GEN-LAST:event_BotonAceptarActionPerformed

    private void BotonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBorrarActionPerformed
        CampoNIF.setText("");
        CampoPassword.setText("");
    }//GEN-LAST:event_BotonBorrarActionPerformed

    public String getDni(){
        return CampoNIF.getText();
    }
    
    public String getPassword(){
        return String.valueOf(CampoPassword.getPassword());
    }
    
    public void mostrarPanel(String text){
        JOptionPane.showMessageDialog(null, text);
    }
    
    public void errorNifPasswordNoCoincidente(){
        JOptionPane.showMessageDialog(null, "El NIF o el password es incorrecto.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void errorNoActivo(){
        JOptionPane.showMessageDialog(null, "El empleado especificado no está activo.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
     
    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAceptar;
    private javax.swing.JButton BotonBorrar;
    private javax.swing.JTextField CampoNIF;
    private javax.swing.JPasswordField CampoPassword;
    private javax.swing.JLabel EtiquetaNIF;
    private javax.swing.JLabel EtiquetaPassword;
    private javax.swing.JPanel PanelEtiquetasCampos;
    private javax.swing.JPanel PanelInferior;
    private javax.swing.JPanel PanelSuperior;
    // End of variables declaration//GEN-END:variables

   
}
