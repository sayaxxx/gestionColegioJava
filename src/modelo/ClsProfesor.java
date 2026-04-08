package modelo;

import controlador.ClsConexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClsProfesor {

// DECLARACION DE VARIABLES
    private String docPro;
    private String nomPro;
    private String apePro;
    private String dirPro;
    private String telPro;
    private String emaPro;
    private String titPro;
    public ResultSet datosProfesor;

    ClsConexion objConec = new ClsConexion();

// METODO PARA GUARDAR DATOS PROFESOR
    public void guardar() {
        try {
            objConec.conectar();

            String consulta = "INSERT INTO profesor (docPro, nomPro, apePro, dirPro, telPro, emaPro, titPro) VALUES (?,?,?,?,?,?,?)";
            objConec.sql = objConec.con.prepareStatement(consulta);

            objConec.sql.setString(1, getDocPro());
            objConec.sql.setString(2, getNomPro());
            objConec.sql.setString(3, getApePro());
            objConec.sql.setString(4, getDirPro());
            objConec.sql.setString(5, getTelPro());
            objConec.sql.setString(6, getEmaPro());
            objConec.sql.setString(7, getTitPro());

            objConec.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS GUARDADOS CON ÉXITO");
            objConec.cerrar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR: " + e.getMessage());
        }
    }

// METODO PARA BUSCAR PROFESOR
    public void buscar() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("SELECT * FROM profesor WHERE DocPro=?",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            objConec.sql.setString(1, getDocPro());
            datosProfesor = objConec.sql.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR: " + e.getMessage());
        }
    }

// METODO PARA ACTUALIZAR DATOS DE PROFESOR
    public void Actualizar() {
        try {
            objConec.conectar();
            String consulta = "UPDATE profesor SET NomPro=?, apePro=?, dirPro=?, telPro=?, EmaPro=?, TitPro=? WHERE docPro=?";
            objConec.sql = objConec.con.prepareStatement(consulta);

            objConec.sql.setString(1, getNomPro());
            objConec.sql.setString(2, getApePro());
            objConec.sql.setString(3, getDirPro());
            objConec.sql.setString(4, getTelPro());
            objConec.sql.setString(5, getEmaPro());
            objConec.sql.setString(6, getTitPro());
            objConec.sql.setString(7, getDocPro());

            objConec.sql.executeUpdate();
            objConec.cerrar();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR: " + e.getMessage());
        }
    }

    // GETTERS AND SETTERS ************************
    public String getDocPro() {
        return docPro;
    }

    public void setDocPro(String docPro) {
        this.docPro = docPro;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public String getApePro() {
        return apePro;
    }

    public void setApePro(String apePro) {
        this.apePro = apePro;
    }

    public String getDirPro() {
        return dirPro;
    }

    public void setDirPro(String dirPro) {
        this.dirPro = dirPro;
    }

    public String getTelPro() {
        return telPro;
    }

    public void setTelPro(String telPro) {
        this.telPro = telPro;
    }

    public String getEmaPro() {
        return emaPro;
    }

    public void setEmaPro(String emaPro) {
        this.emaPro = emaPro;
    }

    public String getTitPro() {
        return titPro;
    }

    public void setTitPro(String titPro) {
        this.titPro = titPro;
    }

}
