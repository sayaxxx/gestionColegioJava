package vista;

import controlador.ClsConexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ClsMateria;
import modelo.ClsProfesor;

public class FrmProfesor extends javax.swing.JFrame {

    ClsProfesor objPro;
    ClsMateria objMat;
    ClsConexion objCon;

    public FrmProfesor() {
        initComponents();
        objPro = new ClsProfesor();
        objMat = new ClsMateria();
        objCon = new ClsConexion();
        this.defecto();
        this.cargarComboMaterias();
    }

// METODOS ***********************************************
// METODO REGRESAR AL INDEX
    public void regresar() {
        FrmIndex objIndex = new FrmIndex();
        objIndex.setVisible(true);
        this.dispose();
    }

// METODO PARA MANIPULAR CAMPOS
    public void habilitarCampos(boolean estado) {
        txtDocPro.setEditable(estado);
        txtNomPro.setEditable(estado);
        txtApePro.setEditable(estado);
        txtDirPro.setEditable(estado);
        txtTelPro.setEditable(estado);
        txtEmaPro.setEditable(estado);
        cbTitPro.setEnabled(estado);
    }

// METODO PARA MANIPULAR BOTONES
    public void estadoBotones(boolean bNuevo, boolean bGuardar, boolean bBuscar, boolean bModificar, boolean bActualizar) {
        btnNuevo.setEnabled(bNuevo);
        btnGuardar.setEnabled(bGuardar);
        btnBuscar.setEnabled(bBuscar);
        btnModificar.setEnabled(bModificar);
        btnActualizar.setEnabled(bActualizar);
    }

// METODO PARA LIMPIAR CAMPOS
    public void limpiar() {
        txtDocPro.setText(null);
        txtNomPro.setText(null);
        txtApePro.setText(null);
        txtDirPro.setText(null);
        txtTelPro.setText(null);
        txtEmaPro.setText(null);
        cbTitPro.setSelectedIndex(0);
    }

// METODO PARA VOLVER TODO COMO INICIO
    public void defecto() {
        this.limpiar();
        this.habilitarCampos(false);
        this.estadoBotones(true, false, true, false, false);
    }

// METODO PARA ESCRIBIR UN NUEVO PROFESOR
    public void nuevo() {
        this.limpiar();
        this.habilitarCampos(true);
        this.estadoBotones(false, true, false, false, false);
    }

// METODO PARA MODIFICAR DATOS DE PROFESOR
    public void modificar() {
        this.habilitarCampos(true);
        txtDocPro.setEditable(false);
        this.estadoBotones(false, false, false, false, true);
    }

// METODO PARA CAPTURAR LOS DATOS DE LOS CAMPOS
    public boolean capturar() {
        if (txtDocPro.getText().trim().isEmpty() || txtNomPro.getText().trim().isEmpty()
                || txtEmaPro.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "DOCUMENTO, NOMBRE Y EMAIL SON OBLIGATORIOS");
            return false;
        }
        objPro.setDocPro(txtDocPro.getText().trim());
        objPro.setNomPro(txtNomPro.getText().trim());
        objPro.setApePro(txtApePro.getText().trim());
        objPro.setDirPro(txtDirPro.getText().trim());
        objPro.setTelPro(txtTelPro.getText().trim());
        objPro.setEmaPro(txtEmaPro.getText().trim());
        objPro.setTitPro(cbTitPro.getSelectedItem().toString());
        return true;
    }

// METODO PARA GUARDAR DATOS DE PROFESOR
    public void guardar() {
        if (this.capturar()) {
            objPro.guardar();
            JOptionPane.showMessageDialog(null, "Profesor guardado correctamente");
            this.defecto();
        }
    }

// METODO PARA BUSCAR PROFESOR
    public void buscar() {
        try {
            String bus = JOptionPane.showInputDialog("Digite el documento del profesor a buscar");

            if (bus == null || bus.trim().isEmpty()) {
                return;
            }

            objPro.setDocPro(bus);
            objPro.buscar();

            if (objPro.datosProfesor.next()) {
                txtDocPro.setText(objPro.datosProfesor.getString(1));
                txtNomPro.setText(objPro.datosProfesor.getString(2));
                txtApePro.setText(objPro.datosProfesor.getString(3));
                txtDirPro.setText(objPro.datosProfesor.getString(4));
                txtTelPro.setText(objPro.datosProfesor.getString(5));
                txtEmaPro.setText(objPro.datosProfesor.getString(6));
                cbTitPro.setSelectedItem(objPro.datosProfesor.getString(7));

                this.llenarTablaAsignaciones(txtDocPro.getText());

                this.habilitarCampos(false);
                this.estadoBotones(true, false, true, true, false);
            } else {
                JOptionPane.showMessageDialog(null, "EL PROFESOR NO EXISTE");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR: " + e.getMessage());
        }
    }

// METODO PARA ACTUALIZAR DATOS DEL PROFESOR
    public void actualizar() {
        if (this.capturar()) {
            try {
                objPro.Actualizar();
                JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");
                this.defecto();
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error técnico: " + e);
            }
        }
    }

// METODO PARA CARGAR LAS MATERIAS EN EL COMBO BOX
    public void cargarComboMaterias() {
        cbMaterias.removeAllItems();
        cbMaterias.addItem("Seleccione...");

        try {
            objMat.listarNombreMaterias();
            while (objMat.datosMateria.next()) {
                cbMaterias.addItem(objMat.datosMateria.getString("nomMat"));
            }
        } catch (Exception e) {
            System.out.println("ERROR COMBO MATERIAS " + e);
        }
    }

// METODO PARA GUARDAR MATERIAS CON PROFESOR
    public void guardarMateriaProfesor() {
        String documento = txtDocPro.getText().trim();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE BUSCAR UN PROFESOR");
            return;
        }

        if (cbMaterias.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA MATERIA VALIDA");
            return;
        }

        try {
            objMat.setNomMat(cbMaterias.getSelectedItem().toString());
            objMat.buscarPorNombre();

            if (objMat.datosMateria.next()) {
                int codigoMat = objMat.datosMateria.getInt(1);
                String grado = txtGrado.getText();

                if (objMat.guardarAsignacion(codigoMat, documento, grado)) {
                    JOptionPane.showMessageDialog(null, "MATERIA ASIGNADA CORRECTAMENTE");

                    this.llenarTablaAsignaciones(documento);

                    cbMaterias.setSelectedItem(0);
                    txtGrado.setText("");
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR AL PROCESAR ASIGNACION " + e.getMessage());
        }
    }

// METODO PARA LLENAR LA TABLA
    public void llenarTablaAsignaciones(String doc) {
        DefaultTableModel modelo = (DefaultTableModel) tblMateriasPro.getModel();
        modelo.setRowCount(0);

        try {
            objCon.conectar();
            String sql = "SELECT m.nomMat, mp.graMatxPro "
                    + " FROM materia m "
                    + " INNER JOIN materiaxprofesor mp ON m.codMat = mp.codMatMatxPro "
                    + " WHERE mp.docProMatxPro = ?";

            objCon.sql = objCon.con.prepareStatement(sql);
            objCon.sql.setString(1, doc);
            ResultSet rs = objCon.sql.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString(1), rs.getString(2)});
            }
            objCon.cerrar();
        } catch (Exception e) {
            System.out.println("ERROR TABLA " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelProfesor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDocPro = new javax.swing.JTextField();
        txtNomPro = new javax.swing.JTextField();
        txtApePro = new javax.swing.JTextField();
        txtDirPro = new javax.swing.JTextField();
        txtTelPro = new javax.swing.JTextField();
        txtEmaPro = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        cbTitPro = new javax.swing.JComboBox<>();
        PanelMateriasProfesor = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        cbMaterias = new javax.swing.JComboBox<>();
        txtGrado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMateriasPro = new javax.swing.JTable();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelProfesor.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel1.setText("Documento");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Apellido");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("Direccion");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Telefono");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel7.setText("Titulo");

        txtDocPro.setEditable(false);

        txtNomPro.setEditable(false);
        txtNomPro.setText("         ");

        txtApePro.setEditable(false);
        txtApePro.setText("                           ");

        txtDirPro.setEditable(false);

        txtTelPro.setEditable(false);

        txtEmaPro.setEditable(false);

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel8.setText("PROFESOR");

        btnRegresar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        cbTitPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Tecnico", "Tecnologo", "Profesional", "Especialista", "Magister", "Doctor" }));

        javax.swing.GroupLayout PanelProfesorLayout = new javax.swing.GroupLayout(PanelProfesor);
        PanelProfesor.setLayout(PanelProfesorLayout);
        PanelProfesorLayout.setHorizontalGroup(
            PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProfesorLayout.createSequentialGroup()
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelProfesorLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelProfesorLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelProfesorLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)))
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDirPro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTelPro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEmaPro, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNomPro)
                    .addComponent(txtApePro)
                    .addComponent(txtDocPro)
                    .addComponent(cbTitPro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(35, 35, 35))
            .addGroup(PanelProfesorLayout.createSequentialGroup()
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImprimir))
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(btnRegresar)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        PanelProfesorLayout.setVerticalGroup(
            PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProfesorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDocPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApePro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDirPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelProfesorLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(21, 21, 21)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbTitPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(PanelProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnBuscar)
                    .addComponent(btnModificar)
                    .addComponent(btnActualizar)
                    .addComponent(btnImprimir))
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addGap(68, 68, 68))
        );

        jTabbedPane1.addTab("Profesor", PanelProfesor);

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel9.setText("Materia");

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel10.setText("Grado");

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton8.setText("Guardar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Buscar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Imprimir");
        jButton10.setEnabled(false);

        cbMaterias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMaterias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMateriasItemStateChanged(evt);
            }
        });

        txtGrado.setEditable(false);

        tblMateriasPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Grado"
            }
        ));
        tblMateriasPro.setEnabled(false);
        jScrollPane2.setViewportView(tblMateriasPro);

        javax.swing.GroupLayout PanelMateriasProfesorLayout = new javax.swing.GroupLayout(PanelMateriasProfesor);
        PanelMateriasProfesor.setLayout(PanelMateriasProfesorLayout);
        PanelMateriasProfesorLayout.setHorizontalGroup(
            PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMateriasProfesorLayout.createSequentialGroup()
                .addGroup(PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMateriasProfesorLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGrado, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(cbMaterias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanelMateriasProfesorLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10))
                    .addGroup(PanelMateriasProfesorLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        PanelMateriasProfesorLayout.setVerticalGroup(
            PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMateriasProfesorLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(PanelMateriasProfesorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Materias Profesor", PanelMateriasProfesor);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.regresar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.actualizar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        this.modificar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void cbMateriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMateriasItemStateChanged
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String seleccionado = cbMaterias.getSelectedItem().toString();

            if (!seleccionado.equals("Seleccione...")) {
                try {
                    objMat.setNomMat(seleccionado);
                    objMat.buscarPorNombre();

                    if (objMat.datosMateria.next()) {
                        txtGrado.setText(objMat.datosMateria.getString("graMat"));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR AL RECUPERAR GRADO " + e.getMessage());
                }
            } else {
                txtGrado.setText("");
            }
        }
    }//GEN-LAST:event_cbMateriasItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cbMaterias.setSelectedIndex(0);
        txtGrado.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.guardarMateriaProfesor();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMateriasProfesor;
    private javax.swing.JPanel PanelProfesor;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbMaterias;
    private javax.swing.JComboBox<String> cbTitPro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblMateriasPro;
    private javax.swing.JTextField txtApePro;
    private javax.swing.JTextField txtDirPro;
    private javax.swing.JTextField txtDocPro;
    private javax.swing.JTextField txtEmaPro;
    private javax.swing.JTextField txtGrado;
    private javax.swing.JTextField txtNomPro;
    private javax.swing.JTextField txtTelPro;
    // End of variables declaration//GEN-END:variables
}
