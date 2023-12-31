/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao.conx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Conexion {  //con base de datos en la nube
   public static final String DEFAULT_DATE_STRING_FORMAT_PE = "dd/MM/yyyy";
    public static final String DEFAULT_DATE_STRING_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static Connection conn = null;

    /*Establece la conexión con la base de datos MySQL en la nube. 
    Utiliza la clase DriverManager para obtener la conexión utilizando el 
    controlador JDBC para MySQL. Los parámetros de conexión, como la URL de la base de datos, 
    el nombre de usuario y la contraseña, están especificados en el método. 
    Si la conexión aún no está establecida, se crea y se devuelve.*/
    public static Connection connectSQLite() {
        try {
            //Class.forName("org.sqlite.JDBC");
            //String dbURL = "jdbc:sqlite:data/db_ventas.db?foreign_keys=on;";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
                      
            if (conn == null) {
              conn = DriverManager.getConnection(
              "jdbc:mysql://us-east.connect.psdb.cloud/sys_ventas?sslMode=VERIFY_IDENTITY",
              "8n6cl25s4vefxtpybhzt",
              "xpscale_pw_nUkKkXFOKGEfT7ScV8DWWbHrhPmuxgybxpY46utyzUGxx");  
            }
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión" + e);
        }
        return conn;
    }

    /* Cierra la conexión con la base de datos SQLite. Verifica si la conexión no es nula y la cierra.*/
    public static void closeSQLite(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connectSQLite();

        try ( PreparedStatement stmt = connectSQLite().prepareStatement("SELECT * FROM cliente")) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("nombrers")+"\t"+rs.getString("dniruc"));
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
    }    
}
