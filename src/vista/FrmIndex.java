package vista;

import controlador.ClsConexion;

public class FrmIndex extends javax.swing.JFrame {

    public FrmIndex() {
        initComponents();
    }

//DECLARACION DE METODOS
    public void irAcudiente() {
        FrmAcudiente objAcudiente = new FrmAcudiente();
        objAcudiente.setVisible(true);
        this.dispose();
    }

// METODO PARA IR A ESTUDIANTE
    public void irEstudiante() {
        FrmEstudiante objEstudiante = new FrmEstudiante();
        objEstudiante.setVisible(true);
        this.dispose();
    }

// METODO PARA IR A PROFESOR
    public void irProfesor() {
        FrmProfesor objFrmProfesor = new FrmProfesor();
        objFrmProfesor.setVisible(true);
        this.dispose();
    }

// METODO PARA IR A MATERIA
    public void irMateria() {
        FrmMateria objFrmMateria = new FrmMateria();
        objFrmMateria.setVisible(true);
        this.dispose();
    }

// METODO PARA PROBAR CONEXION
    public void probarConexion() {
        ClsConexion objConexion = new ClsConexion();
        objConexion.conectar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAcudiente = new javax.swing.JButton();
        btnEstudiante = new javax.swing.JButton();
        btnProfesor = new javax.swing.JButton();
        btnMateria = new javax.swing.JButton();
        btnProbarConexion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnAcudiente.setFont(new java.awt.Font("Centaur", 3, 18)); // NOI18N
        btnAcudiente.setForeground(new java.awt.Color(0, 0, 255));
        btnAcudiente.setText("Acudiente");
        btnAcudiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcudienteActionPerformed(evt);
            }
        });

        btnEstudiante.setFont(new java.awt.Font("Centaur", 3, 18)); // NOI18N
        btnEstudiante.setForeground(new java.awt.Color(0, 0, 255));
        btnEstudiante.setText("Estudiante");
        btnEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstudianteActionPerformed(evt);
            }
        });

        btnProfesor.setFont(new java.awt.Font("Century", 3, 18)); // NOI18N
        btnProfesor.setForeground(new java.awt.Color(0, 51, 255));
        btnProfesor.setText("Profesor");
        btnProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfesorActionPerformed(evt);
            }
        });

        btnMateria.setFont(new java.awt.Font("Centaur", 3, 18)); // NOI18N
        btnMateria.setForeground(new java.awt.Color(0, 0, 255));
        btnMateria.setText("Materia");
        btnMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMateriaActionPerformed(evt);
            }
        });

        btnProbarConexion.setFont(new java.awt.Font("Centaur", 3, 18)); // NOI18N
        btnProbarConexion.setForeground(new java.awt.Color(0, 0, 255));
        btnProbarConexion.setText("Probar Conexion");
        btnProbarConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProbarConexionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Centaur", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 102));
        jLabel1.setText("INDEX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMateria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAcudiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProbarConexion, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnEstudiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAcudiente)
                .addGap(18, 18, 18)
                .addComponent(btnProfesor)
                .addGap(18, 18, 18)
                .addComponent(btnMateria)
                .addGap(18, 18, 18)
                .addComponent(btnProbarConexion)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAcudienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcudienteActionPerformed
        this.irAcudiente();
    }//GEN-LAST:event_btnAcudienteActionPerformed

    private void btnEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstudianteActionPerformed
        this.irEstudiante();
    }//GEN-LAST:event_btnEstudianteActionPerformed

    private void btnProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfesorActionPerformed
        this.irProfesor();
    }//GEN-LAST:event_btnProfesorActionPerformed

    private void btnMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMateriaActionPerformed
        this.irMateria();
    }//GEN-LAST:event_btnMateriaActionPerformed

    private void btnProbarConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProbarConexionActionPerformed
        this.probarConexion();
    }//GEN-LAST:event_btnProbarConexionActionPerformed

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
            java.util.logging.Logger.getLogger(FrmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcudiente;
    private javax.swing.JButton btnEstudiante;
    private javax.swing.JButton btnMateria;
    private javax.swing.JButton btnProbarConexion;
    private javax.swing.JButton btnProfesor;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
