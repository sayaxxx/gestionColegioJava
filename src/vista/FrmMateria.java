package vista;

import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ClsMateria;

public class FrmMateria extends javax.swing.JFrame {

    ClsMateria objMat;

    public FrmMateria() {
        initComponents();
        objMat = new ClsMateria();
    }

// METODOS **********************************************
// METODO PARA REGRESAR AL INDEX    
    public void regresar() {
        FrmIndex objIndex = new FrmIndex();
        objIndex.setVisible(true);
        this.dispose();
    }

// METODO PARA TRAER EL ID DE LA BASE DE DATOS
    public void consecutivo() {
        objMat.consecutivo();
        try {
            if (objMat.datosConsecutivo.next()) {
                txtCodigo.setText(objMat.datosConsecutivo.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se puede generar el código: " + e);
        }
    }

// METODO PARA AGREGAR UNA NUEVA MATERIA
    public void nuevo() {
        this.limpiar();
        this.consecutivo();
        this.habilitarCampos(true);
        this.estadoBotones(false, true, false, false, false);
    }

// METODO PARA LIMPIAR CAMPOS
    public void limpiar() {
        txtCodigo.setText(null);
        txtNom.setText(null);
        cbGra.setSelectedIndex(0);
    }

// METODO PARA DEJAR TODO COMO AL PRINCIPIO
    public void defecto() {
        this.limpiar();
        this.habilitarCampos(false);
        this.estadoBotones(true, false, true, false, false);
    }

// METODO PARA CAPTURAR LOS CAMPOS DE TEXTO Y CBO
    public boolean capturar() {
        if (txtNom.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "EL NOMBRE DE LA MATERIA ES OBLIGATORIO");
            return false;
        }
        try {
            objMat.setCodMat(Integer.parseInt(txtCodigo.getText()));
            objMat.setNomMat(txtNom.getText().trim());
            objMat.setGraMat(cbGra.getSelectedItem().toString());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR EN EL FORMATO DEL CODIGO");
            return false;
        }
    }

// METODO PARA PODER EDITAR DATOS
    public void modificar() {
        this.habilitarCampos(true);
        this.estadoBotones(false, false, false, false, true);
    }

// METODO PARA GUARDAR DATOS EN LA BASE DE DATOS
    public void guardar() {
        if (this.capturar()) {
            objMat.guardar();
            this.defecto();
        }
    }

// METODO PARA HABILITAR CAMPOS
    public void habilitarCampos(boolean estado) {
        txtCodigo.setEditable(false);
        txtNom.setEditable(estado);
        cbGra.setEnabled(estado);
    }

// METODO PARA MANIPULAR ESTADO DE BOTONES
    public void estadoBotones(boolean bNuevo, boolean bGuardar, boolean bBuscar, boolean bModificar, boolean bActualizar) {
        btnNuevo.setEnabled(bNuevo);
        btnGuardar.setEnabled(bGuardar);
        btnBuscar.setEnabled(bBuscar);
        btnModificar.setEnabled(bModificar);
        btnActualizar.setEnabled(bActualizar);
    }

// METODO PARA BUSCAR MATERIAS
    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("DIGITE EL CÓDIGO DE LA MATERIA A BUSCAR");

            if (bus == null || bus.trim().isEmpty()) {
                return;
            }

            objMat.setCodMat(Integer.parseInt(bus));
            objMat.buscar();

            if (objMat.datosMateria.next()) {
                txtCodigo.setText(objMat.datosMateria.getString(1));
                txtNom.setText(objMat.datosMateria.getString(2));
                cbGra.setSelectedItem(objMat.datosMateria.getString(3));

                this.habilitarCampos(false);
                this.estadoBotones(true, false, true, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "LA MATERIA NO EXISTE");
                this.defecto();
            }
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR: " + e.getMessage());
        }

    }

// METODO PARA ACTUALIZAR DATOS DE MATERIA
    public void actualizar() {
        if (this.capturar()) {
            try {
                objMat.Actualizar();
                this.defecto();
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "FALLO AL ACTUALIZAR: " + e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        cbGra = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setText("Codigo");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Grado");

        txtCodigo.setEditable(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtNom.setEditable(false);

        cbGra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR...", "PRIMERO", "SEGUNDO", "TERCERO", "CUARTO", "QUINTO" }));
        cbGra.setEnabled(false);

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

        btnImprimir.setText("Imprimir");
        btnImprimir.setEnabled(false);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Copeland", 3, 24)); // NOI18N
        jLabel4.setText("MATERIAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(btnImprimir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRegresar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addContainerGap(56, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addComponent(cbGra, 0, 1, Short.MAX_VALUE))
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbGra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnBuscar)
                    .addComponent(btnModificar)
                    .addComponent(btnActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir)
                    .addComponent(btnRegresar))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // BOTONES ******************************************************************
    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMateria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMateria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbGra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
