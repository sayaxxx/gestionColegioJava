package modelo;

import controlador.ClsConexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClsAcudiente {

// DECLARACION DE VARIABLES
    private String docAcu;
    private String nomAcu;
    private String apeAcu;
    private String dirAcu;
    private String telAcu;
    private String emaAcu;
    public ResultSet datosAcudiente;
    ClsConexion objConec = new ClsConexion();

// METODOS *********************************************************
// METODO GUARDAR ACUDIENTE
    public void guardar() {
        System.out.println("ENTRA AL METODO GUARDAR");
        try {
            objConec.conectar();
            String sql = "INSERT INTO acudiente (docAcu, nomAcu, apeAcu, dirAcu, telAcu, emaAcu) VALUES (?,?,?,?,?,?)";
            objConec.sql = objConec.con.prepareStatement(sql);

            objConec.sql.setString(1, getDocAcu());
            objConec.sql.setString(2, getNomAcu());
            objConec.sql.setString(3, getApeAcu());
            objConec.sql.setString(4, getDirAcu());
            objConec.sql.setString(5, getTelAcu());
            objConec.sql.setString(6, getEmaAcu());
            objConec.sql.executeUpdate();
            objConec.cerrar();
            JOptionPane.showMessageDialog(null, "DATOS DEL ACUDIENTE GUARDADOS CON EXITO");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR EN ACUDIENTE  " + e);
        }
    }

// METODO PARA BUSCAR ACUDIENTE
    public void buscar() {
        try {
            objConec.conectar();
            String sql = "SELECT * FROM acudiente WHERE DocAcu = ?";
            objConec.sql = objConec.con.prepareStatement(sql);
            objConec.sql.setString(1, getDocAcu());
            datosAcudiente = objConec.sql.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + e);
        }
    }

// METODO PARA ACTUALIZAR DATOS ACUDIENTE
    public void actualizar() {
        try {
            objConec.conectar();
            String sql = "UPDATE acudiente SET NomAcu=?, ApeAcu=?, DirAcu=?, TelAcu=?, EmaAcu=? WHERE DocAcu=?";
            objConec.sql = objConec.con.prepareStatement(sql);
            objConec.sql.setString(1, getNomAcu());
            objConec.sql.setString(2, getApeAcu());
            objConec.sql.setString(3, getDirAcu());
            objConec.sql.setString(4, getTelAcu());
            objConec.sql.setString(5, getEmaAcu());
            objConec.sql.setString(6, getDocAcu());
            objConec.sql.executeUpdate();
            objConec.cerrar();
            JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR" + e);
        }
    }

// METODO PARA GUARDAR LA RELACION
    public boolean guardarRelacion(String docAcu, String docEst) {
        try {
            objConec.conectar();
            String sql = "INSERT INTO acudientexestudiante (docAcuAcuxEst, docEstAcuxEst) VALUES (?,?)";
            objConec.sql = objConec.con.prepareStatement(sql);
            objConec.sql.setString(1, docAcu);
            objConec.sql.setString(2, docEst);
            objConec.sql.executeUpdate();
            objConec.cerrar();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ASOCIAR ESTUDIANTE " + e.getMessage());
            return false;
        }
    }

// METODO PARA BUSCAR ESTUDIANTES ASOCIADOS A ACUDIENTE
    public void consultarEstudiantes(String docAcu) {
        try {
            objConec.conectar();
            String sql = "SELECT e.DocEst, e.NomEst, e.ApeEst, e.TelEst "
                    + "FROM estudiante e "
                    + "INNER JOIN acudientexestudiante ae ON e.DocEst = ae.docEstAcuxEst "
                    + "WHERE ae.docAcuAcuxEst = ?";
            objConec.sql = objConec.con.prepareStatement(sql);
            objConec.sql.setString(1, docAcu);
            datosAcudiente = objConec.sql.executeQuery();
        } catch (Exception e) {
            System.out.println("ERROR EN CONSULTA RELACIONAL " + e);
        }
    }

// GETTERS AND SETTERS ******************************************
    public String getDocAcu() {
        return docAcu;
    }

    public void setDocAcu(String docAcu) {
        this.docAcu = docAcu;
    }

    public String getNomAcu() {
        return nomAcu;
    }

    public void setNomAcu(String nomAcu) {
        this.nomAcu = nomAcu;
    }

    public String getApeAcu() {
        return apeAcu;
    }

    public void setApeAcu(String apeAcu) {
        this.apeAcu = apeAcu;
    }

    public String getDirAcu() {
        return dirAcu;
    }

    public void setDirAcu(String dirAcu) {
        this.dirAcu = dirAcu;
    }

    public String getTelAcu() {
        return telAcu;
    }

    public void setTelAcu(String telAcu) {
        this.telAcu = telAcu;
    }

    public String getEmaAcu() {
        return emaAcu;
    }

    public void setEmaAcu(String emaAcu) {
        this.emaAcu = emaAcu;
    }

}
