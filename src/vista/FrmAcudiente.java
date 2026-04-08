package vista;

import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsAcudiente;
import modelo.ClsEstudiante;

public class FrmAcudiente extends javax.swing.JFrame {

    ClsAcudiente objAcu;
    ClsEstudiante objEst;

    public FrmAcudiente() {
        initComponents();
        objAcu = new ClsAcudiente();
        objEst = new ClsEstudiante();
        this.llenarCombo();
    }

// METODO PARA REGRESAR AL INDEX
    public void regresar() {
        FrmIndex objIndex = new FrmIndex();
        objIndex.setVisible(true);
        this.dispose();
    }

// METODO PARA CAPTURAR LOS CAMPOS
    public void capturar() {
        objAcu.setDocAcu(txtDocAcu.getText());
        objAcu.setNomAcu(txtNomAcu.getText());
        objAcu.setApeAcu(txtApeAcu.getText());
        objAcu.setDirAcu(txtDirAcu.getText());
        objAcu.setTelAcu(txtTelAcu.getText());
        objAcu.setEmaAcu(txtEmaAcu.getText());
    }

// METODO PARA GUARDAR ACUDIENTE
    public void guardar() {
        this.capturar();
        objAcu.guardar();
    }

// METODO PARA LIMPIAR CAMPOS
    public void limpiar() {
        txtDocAcu.setText(null);
        txtNomAcu.setText(null);
        txtApeAcu.setText(null);
        txtDirAcu.setText(null);
        txtTelAcu.setText(null);
        txtEmaAcu.setText(null);
    }

// METODO PARA BUSCAR ACUDIENTE
    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento que desea buscar");
            objAcu.setDocAcu(bus);
            objAcu.buscar();
            if (objAcu.datosAcudiente.next() == true) {
                txtDocAcu.setText(objAcu.datosAcudiente.getString(1));
                txtNomAcu.setText(objAcu.datosAcudiente.getString(2));
                txtApeAcu.setText(objAcu.datosAcudiente.getString(3));
                txtDirAcu.setText(objAcu.datosAcudiente.getString(4));
                txtTelAcu.setText(objAcu.datosAcudiente.getString(5));
                txtEmaAcu.setText(objAcu.datosAcudiente.getString(6));
                this.llenarTablaEstudiante(txtDocAcu.getText());
                this.estadoBotones(true, false, true, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "EL PROFESOR NO EXISTE");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL VISUALIZAR LOS DATOS DEL PROFESOR");
        }
    }

// METODO PARA ACTUALIZAR DATOS ACUDIENTE
    public void actualizar() {
        try {
            this.capturar();
            objAcu.actualizar();
            this.defecto();
            this.limpiar();
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Fallas Tecnicas" + e);
        }
    }

// METODO MAESTRO PARA HABILITAR CAMPOS
    public void habilitarCampos(boolean estado) {
        txtDocAcu.setEditable(estado);
        txtNomAcu.setEditable(estado);
        txtApeAcu.setEditable(estado);
        txtDirAcu.setEditable(estado);
        txtTelAcu.setEditable(estado);
        txtEmaAcu.setEditable(estado);
    }

// METODO MAESTRO PARA MANIPULAR BOTONES
    public void estadoBotones(boolean bNuevo, boolean bGuardar, boolean bBuscar, boolean bModificar, boolean bActualizar) {
        btnNuevo.setEnabled(bNuevo);
        btnGuardar.setEnabled(bGuardar);
        btnBuscar.setEnabled(bBuscar);
        btnModificar.setEnabled(bModificar);
        btnActualizar.setEnabled(bActualizar);
    }

// METODO PARA DEJAR TODO COMO AL INICIO
    public void defecto() {
        this.habilitarCampos(false);
        this.estadoBotones(true, false, true, false, false);
    }

// METODO PARA ASOCIAR ESTUDIANTE CON ACUDIENTE
    public void agregarEstudiante() {
        String docAcu = txtDocAcu.getText();
        String docEst = cbEstudiantes.getSelectedItem().toString();

        if (docAcu.isEmpty() || docEst.equals("Seleccione...")) {
            JOptionPane.showMessageDialog(null, "DEBE BUSCAR UN ACUDIENTE Y ESTUDIANTE");
            return;
        }
        if (objAcu.guardarRelacion(docAcu, docEst)) {
            JOptionPane.showMessageDialog(null, "ESTUDIANTE ASIGNADO");
            llenarTablaEstudiante(docAcu);
            this.llenarTablaEstudiante(docAcu);
        }
    }

// METODO PARA LLENAR EL COMBO DE ESTUDIANTES
    public void llenarCombo() {
        cbEstudiantes.removeAllItems();
        cbEstudiantes.addItem("Seleccionar...");
        objEst.listarDocumentos();
        try {
            while (objEst.datosEst.next()) {
                cbEstudiantes.addItem(objEst.datosEst.getString("DocEst"));
            }
        } catch (Exception e) {
            System.out.println("ERROR AL LLENAR EL COMBO " + e);
        }
    }

//  METODO PARA LLENAR LA TABLA ESTUDIANTES
    public void llenarTablaEstudiante(String docAcu) {
        DefaultTableModel modelo = (DefaultTableModel) tblEstudiantes.getModel();
        modelo.setRowCount(0);
        try {
            objAcu.consultarEstudiantes(docAcu);
            while (objAcu.datosAcudiente.next()) {
                Object[] fila = new Object[4];
                fila[0] = objAcu.datosAcudiente.getString(1);
                fila[1] = objAcu.datosAcudiente.getString(2);
                fila[2] = objAcu.datosAcudiente.getString(3);
                fila[3] = objAcu.datosAcudiente.getString(4);
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDocAcu = new javax.swing.JTextField();
        txtNomAcu = new javax.swing.JTextField();
        txtDirAcu = new javax.swing.JTextField();
        txtEmaAcu = new javax.swing.JTextField();
        txtApeAcu = new javax.swing.JTextField();
        txtTelAcu = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnAgregarEstudiante = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cbEstudiantes = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtNomEst = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setText("Documento");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Direccion");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Telefono");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Apellido");

        txtDocAcu.setEditable(false);
        txtDocAcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocAcuActionPerformed(evt);
            }
        });

        txtNomAcu.setEditable(false);
        txtNomAcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomAcuActionPerformed(evt);
            }
        });

        txtDirAcu.setEditable(false);

        txtEmaAcu.setEditable(false);

        txtApeAcu.setEditable(false);

        txtTelAcu.setEditable(false);

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

        jButton6.setText("Imprimir");
        jButton6.setEnabled(false);

        jButton7.setText("Regresar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnAgregarEstudiante.setText("Agregar Estudiante ");
        btnAgregarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEstudianteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel7.setText("Documento");

        cbEstudiantes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEstudiantesItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel8.setText("Nombre y Apellido");

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Apellido", "Telefono"
            }
        ));
        jScrollPane4.setViewportView(tblEstudiantes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDocAcu, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txtNomAcu)
                            .addComponent(txtDirAcu)
                            .addComponent(txtEmaAcu))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtApeAcu, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(txtTelAcu)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregarEstudiante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomEst))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtApeAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtTelAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtEmaAcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnBuscar)
                    .addComponent(btnModificar)
                    .addComponent(btnActualizar)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEstudiante)
                    .addComponent(jLabel7)
                    .addComponent(cbEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNomEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDocAcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocAcuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocAcuActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.habilitarCampos(true);
        this.estadoBotones(false, true, false, false, false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtNomAcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomAcuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomAcuActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.capturar();
        objAcu.guardar();
        this.defecto();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.habilitarCampos(true);
        txtDocAcu.setEditable(false);
        this.estadoBotones(false, false, false, false, true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbEstudiantesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEstudiantesItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String seleccionado = cbEstudiantes.getSelectedItem().toString();
            if (!seleccionado.equals("Seleccione...")) {
                try {
                    objEst.setDocEst(seleccionado);
                    objEst.buscar();
                    if (objEst.datosEst.next()) {
                        txtNomEst.setText(objEst.datosEst.getString("NomEst") + " " + objEst.datosEst.getString("ApeEst"));
                    }
                } catch (SQLException e) {
                    System.err.println("Error al recuperar datos del estudiante: " + e);
                }
            } else {
                txtNomEst.setText("");
            }
        }
    }//GEN-LAST:event_cbEstudiantesItemStateChanged

    private void btnAgregarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEstudianteActionPerformed
        String docAcu = txtDocAcu.getText();
        String docEst = cbEstudiantes.getSelectedItem().toString();

        if (docAcu.isEmpty() || docEst.equals("Seleccione...")) {
            JOptionPane.showMessageDialog(null, "DEBE CARGAR UN ACUDIENTE Y SELECCIONAR UN ESTUDIANTE");
            return;
        }

        if (objAcu.guardarRelacion(docAcu, docEst)) {
            JOptionPane.showMessageDialog(null, "ESTUDIANTE AGREGADO CON EXITO");
            this.llenarTablaEstudiante(docAcu);
        }
    }//GEN-LAST:event_btnAgregarEstudianteActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAcudiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAcudiente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarEstudiante;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbEstudiantes;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable tblEstudiantes;
    private javax.swing.JTextField txtApeAcu;
    private javax.swing.JTextField txtDirAcu;
    private javax.swing.JTextField txtDocAcu;
    private javax.swing.JTextField txtEmaAcu;
    private javax.swing.JTextField txtNomAcu;
    private javax.swing.JTextField txtNomEst;
    private javax.swing.JTextField txtTelAcu;
    // End of variables declaration//GEN-END:variables
}
