package modelo;

import controlador.ClsConexion;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ClsEstudiante {

    //DECLARACION DE VARIABLES
    private String docEst;
    private String nomEst;
    private String apeEst;
    private String dirEst;
    private String telEst;
    public ResultSet datosEst;

// DECLARAR OBJETO DE CONEXION
    ClsConexion objConec = new ClsConexion();

// METODOS ***********************************
// METODO PARA GUARDAR NUEVO ESTUDIANTE EN LA BASE DE DATOS
    public void guardar() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("insert into estudiante values(?,?,?,?,?)");
            objConec.sql.setString(1, getDocEst());
            objConec.sql.setString(2, getNomEst());
            objConec.sql.setString(3, getApeEst());
            objConec.sql.setString(4, getDirEst());
            objConec.sql.setString(5, getTelEst());
            objConec.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS DEL ESTUDIANTE GUARDADOS CON EXITO");
            objConec.cerrar();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR EN ESTUDIANTE " + e);
        }
    }

// METODO PARA BUSCAR ESTUDIANTE EN LA BASE DE DATOS
    public void buscar() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("SELECT * FROM estudiante WHERE DocEst=?");
            objConec.sql.setString(1, getDocEst());
            objConec.sql.executeQuery();
            datosEst = objConec.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SQL AL BUSCAR ESTUDIANTE" + e);
        }
    }

// METODO PARA ACTUALIZAR DATOS DEL ESTUDIANTE
    public void Actualizar() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("UPDATE estudiante SET nomEst =?,apeEst=?, dirEst =?, telEst=? WHERE docEst=?");
            objConec.sql.setString(1, getNomEst());
            objConec.sql.setString(2, getApeEst());
            objConec.sql.setString(3, getDirEst());
            objConec.sql.setString(4, getTelEst());
            objConec.sql.setString(5, getDocEst());

            objConec.sql.executeUpdate();
            objConec.cerrar();
            JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR" + e);
        }
    }

// METODO PARA TRAER DOCUMENTOS
    public void listarDocumentos() {
        try {
            objConec.conectar();
            String sql = "SELECT docEst FROM estudiante";
            objConec.sql = objConec.con.prepareStatement(sql);
            datosEst = objConec.sql.executeQuery();
        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR DOCUMENTOS " + e);
        }
    }

// GETTERS AND SETTERS **************************************************************
    public String getDocEst() {
        return docEst;
    }

    public void setDocEst(String docEst) {
        this.docEst = docEst;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getApeEst() {
        return apeEst;
    }

    public void setApeEst(String apeEst) {
        this.apeEst = apeEst;
    }

    public String getDirEst() {
        return dirEst;
    }

    public void setDirEst(String dirEst) {
        this.dirEst = dirEst;
    }

    public String getTelEst() {
        return telEst;
    }

    public void setTelEst(String telEst) {
        this.telEst = telEst;
    }

    public ClsConexion getObjConec() {
        return objConec;
    }

    public void setObjConec(ClsConexion objConec) {
        this.objConec = objConec;
    }

}
