package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class ClsConexion {

public PreparedStatement sql;
public Connection con;

// METODO PARA ESTABLECER CONEXION CON LA BASE DE DATOS
    public void conectar(){
        String db = "jdbc:mysql://localhost:3306/colegio";
        String usuario = "root";
        String password = "1234";
        try {
            String controlador = "com.mysql.cj.jdbc.Driver";
            Class.forName(controlador);
            con = DriverManager.getConnection(db, usuario, password);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR AL CONECTAR: " + e);
        }
}

// METODO PARA CERRAR LA CONEXION CON LA BASE DE DATOS
public void cerrar(){
    try {
            if (sql != null) sql.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.err.println("ERROR CERRANDO " + e.getMessage());
        }
    }
}
