package vista;

import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ClsEstudiante;

public class FrmEstudiante extends javax.swing.JFrame {
    //DECLARACION OBJETO ESTUDIANTE

    ClsEstudiante objEst;

    public FrmEstudiante() {
        initComponents();
        objEst = new ClsEstudiante();
    }

// DECLARACION DE METODOS ********************************
// METODO PARA REGRESAR AL INDEX
    public void regresar() {
        FrmIndex objIndex = new FrmIndex();
        objIndex.setVisible(true);
        this.dispose();
    }

// METODO PARA CAPTURAR LOS DATOS DE LOS INPUTS
    public boolean capturar() {
        if (txtDoc.getText().isEmpty() || txtNom.getText().isEmpty() || txtApe.getText().isEmpty() || txtDir.getText().isEmpty() || txtTel.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "TODOS LOS CAMPOS SON OBLIGATORIOS");
            return false;
        }
        objEst.setDocEst(txtDoc.getText());
        objEst.setNomEst(txtNom.getText());
        objEst.setApeEst(txtApe.getText());
        objEst.setDirEst(txtDir.getText());
        objEst.setTelEst(txtTel.getText());
        return true;
    }

// METODO PARA GUARDAR LOS DATOS EN LA BASE DE DATOS
    public void guardar() {
        // SI EL METODO CAPTURAR DEVUELVE TRUE ENTONCES GUARDE
        if (this.capturar()) {
            objEst.guardar();
            this.limpiar();
            this.defecto();
        }
    }

// METODO PARA LIMPIAR LAS CASILLAS DEL FORMULARIO
    public void limpiar() {
        txtDoc.setText(null);
        txtNom.setText(null);
        txtApe.setText(null);
        txtDir.setText(null);
        txtTel.setText(null);
    }

// METODO PARA HABILITAR CASILLAS DE NUEVO REGISTRO
    public void nuevo() {
        this.limpiar();
        this.habilitarCampos(true);
        this.estadoBotones(false, true, false, false, false);
    }

// METODO PARA DEJAR TODO COMO AL PRINCIPIO
    public void defecto() {
        this.limpiar();
        this.habilitarCampos(false);
        this.estadoBotones(true, false, true, false, false);
    }

// METODO MAESTRO PARA HABILITAR LOS CAMPOS
    public void habilitarCampos(boolean estado) {
        txtDoc.setEditable(estado);
        txtNom.setEditable(estado);
        txtApe.setEditable(estado);
        txtDir.setEditable(estado);
        txtTel.setEditable(estado);
    }

// METODO MAESTRO PARA MANIPULAR BOTONES
    public void estadoBotones(boolean bNuevo, boolean bGuardar, boolean bBuscar, boolean bModificar, boolean bActualizar) {
        btnNuevo.setEnabled(bNuevo);
        btnGuardar.setEnabled(bGuardar);
        btnBuscar.setEnabled(bBuscar);
        btnModificar.setEnabled(bModificar);
        btnActualizar.setEnabled(bActualizar);
    }

// METODO PARA MODIFICAR LOS DATOS
    public void modificar() {
        this.habilitarCampos(true);
        txtDoc.setEditable(false);
        this.estadoBotones(false, false, false, false, true);
    }

// METODO PARA BUSCAR
    public void buscar() {
        try {
            this.limpiar();
            String bus = JOptionPane.showInputDialog("Digite el documento que desea buscar");

            if (bus == null || bus.trim().isEmpty()) {
                return;
            }
            objEst.setDocEst(bus);
            objEst.buscar();
            if (objEst.datosEst.next()) {
                txtDoc.setText(objEst.datosEst.getString(1));
                txtNom.setText(objEst.datosEst.getString(2));
                txtApe.setText(objEst.datosEst.getString(3));
                txtDir.setText(objEst.datosEst.getString(4));
                txtTel.setText(objEst.datosEst.getString(5));

                btnModificar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "EL ESTUDIANTE NO EXISTE");
                btnModificar.setEnabled(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL VISUALIZAR LOS DATOS DEL ESTUDIANTE");
        }
    }

// METODO PARA ACTUALIZAR
    public void actualizar() {
        if (this.capturar()) {
            try {
                objEst.Actualizar();
                this.defecto();
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDoc = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtApe = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        bntListar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Documento");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        jLabel4.setText("Direccion");

        jLabel5.setText("Telefono");

        txtDoc.setEditable(false);

        txtNom.setEditable(false);

        txtApe.setEditable(false);

        txtDir.setEditable(false);

        txtTel.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Centaur", 3, 24)); // NOI18N
        jLabel6.setText("FORMULARIO ESTUDIANTE");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        bntListar.setText("Listar");
        bntListar.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDir, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txtDoc)
                            .addComponent(txtNom)
                            .addComponent(txtApe)
                            .addComponent(txtTel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnActualizar)
                                .addGap(18, 18, 18)
                                .addComponent(bntListar)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegresar)
                                .addGap(50, 50, 50)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnBuscar)
                    .addComponent(btnModificar)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntListar)
                    .addComponent(btnRegresar)
                    .addComponent(btnActualizar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();                // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.modificar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntListar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtDoc;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
