package modelo;

import controlador.ClsConexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClsMateria {

// DECLARACION DE VARIABLES
    private int codMat;
    private String nomMat;
    private String graMat;
    public ResultSet datosMateria;

    ClsConexion objConec = new ClsConexion();
    public ResultSet datosConsecutivo;

// METODOS *************************************************************
// METODO PARA TRAEL EL ID DE LA BASE DE DATOS
    public void consecutivo() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("select count(*)+1 from materia");
            objConec.sql.executeQuery();
            datosConsecutivo = objConec.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SQL AL GENERAR EL CONSECUTIVO" + e);
        }
    }

// METODO PARA GUARDAR LOS DATOS DE MATERIA
    public void guardar() {
        System.out.println("ENTRA AL METODO GUARDAR");
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("insert into materia values(?,?,?)");
            objConec.sql.setInt(1, getCodMat());
            objConec.sql.setString(2, getNomMat());
            objConec.sql.setString(3, getGraMat());
            objConec.sql.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS DE LA MATERIA GUARDADOS CON EXITO");
            objConec.cerrar();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR EN MATERIA" + e);
        }
    }

// METODO PARA BUSCAR MATERIAS
    public void buscar() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("SELECT * FROM materia WHERE CodMat=?");
            objConec.sql.setInt(1, getCodMat());
            objConec.sql.executeQuery();
            datosMateria = objConec.sql.getResultSet();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SQL AL BUSCAR LA MATERIA" + e);
        }
    }

// METODO PARA ACTUALIZAR DATOS MATERIA
    public void Actualizar() {
        try {
            objConec.conectar();
            objConec.sql = objConec.con.prepareStatement("UPDATE materia SET NomMat=?, GraMat=? WHERE CodMat=?");

            objConec.sql.setString(1, getNomMat());
            objConec.sql.setString(2, getGraMat());
            objConec.sql.setInt(3, getCodMat());
            objConec.sql.executeUpdate();
            objConec.cerrar();
            JOptionPane.showMessageDialog(null, "Registro actualizado con éxito");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "error al actualizar:" + e);
        }
    }

// METODO PARA LISTAR NOMBRE DE MATERIAS EN EL COMBO
    public void listarNombreMaterias() {
        try {
            objConec.conectar();
            String sql = "SELECT nomMat FROM materia ORDER BY nomMat ASC";
            objConec.sql = objConec.con.prepareStatement(sql);

            datosMateria = objConec.sql.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LISTAR MATERIAS " + e.getMessage());
        }
    }

// METODO PARA BUSCAR GRADO POR NOMBRE DE MATERIA
    public void buscarPorNombre() {
        try {
            objConec.conectar();
            String sql = "SELECT codMat, graMat FROM materia WHERE nomMat = ?";
            objConec.sql = objConec.con.prepareStatement(sql);
            objConec.sql.setString(1, getNomMat());

            datosMateria = objConec.sql.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR GRADO: " + e.getMessage());
        }
    }

// METODO PARA GUARDAR MATERIA X PROFESOR
    public boolean guardarAsignacion(int codMat, String docPro, String graMat) {
        try {
            objConec.conectar();
            String sql = "INSERT INTO materiaxprofesor (codMatMatxPro, docProMatxPro, graMatxPro) VALUES (?,?,?)";
            objConec.sql = objConec.con.prepareStatement(sql);
            objConec.sql.setInt(1, codMat);
            objConec.sql.setString(2, docPro);
            objConec.sql.setString(3, graMat);

            objConec.sql.executeUpdate();
            objConec.cerrar();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ASIGNAR MATERIA " + e.getMessage());
        }
        return false;
    }

// GETTERS AND SETTERS ******************************************
    public int getCodMat() {
        return codMat;
    }

    public void setCodMat(int codMat) {
        this.codMat = codMat;
    }

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public String getGraMat() {
        return graMat;
    }

    public void setGraMat(String graMat) {
        this.graMat = graMat;
    }

}
