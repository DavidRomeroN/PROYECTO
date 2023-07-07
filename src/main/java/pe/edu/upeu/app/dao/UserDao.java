/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.upeu.app.dao.conx.ConnS;
import pe.edu.upeu.app.modelo.UserTO;
import pe.edu.upeu.app.util.ErrorLogger;
import pe.edu.upeu.app.util.Utilidades;

/**
 *
 * @author ACER
 */
public class UserDao implements UserDaoI {

    ConnS instance = ConnS.getInstance();
    Connection coneccionx = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(UserDao.class.getName());

    @Override
    public int create(UserTO u) {
        int rsId = 0;
        Utilidades  ut = new Utilidades();
        String[] returns = {"user"};
        String sql = "INSERT INTO usuario(user, clave) "
                + " values(?, ?);";
        int i = 0;
        try {
            ps = coneccionx.prepareStatement(sql, returns);
            ps.setString(++i, u.getUser());
            ps.setString(++i, ut.Encriptar(u.getClave()));
            

            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            //System.err.println("create:" + ex.toString());
            log.log(java.util.logging.Level.SEVERE, "create", ex);
        }
        return rsId;

    }

    @Override
    public int delete(UserTO u) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    
    /* Este método se utiliza para realizar el inicio de sesión de un usuario. 
    Recibe un objeto UserTO que contiene los datos del usuario que intenta 
    iniciar sesión, como el nombre de usuario y la contraseña. La contraseña se 
    encripta y se compara con la contraseña almacenada en la base de datos. Devuelve 
    un valor booleano que indica si el inicio de sesión fue exitoso o no.*/
    public boolean login(UserTO u) {
        boolean rsId = false;
        Utilidades  ut = new Utilidades();
        
        String sql = "SELECT * "
                + "FROM usuario WHERE "
                + "user = ? and "
                + "clave = ? "
               
               ;
        int i = 0;
        try {
            ps = coneccionx.prepareStatement(sql);
            ps.setString(++i, u.getUser());
            ps.setString(++i, ut.Encriptar(u.getClave()));
            

           rs= ps.executeQuery();// 0 no o 1 si commit
             if (rs.next()) {
                   
                    
                    rsId=true;
                }
                else {rsId= false;
                }
        } catch (SQLException ex) {
            //System.err.println("create:" + ex.toString());
            log.log(java.util.logging.Level.SEVERE, "create", ex);
            rsId= false;
        }
        finally{
            try {
                rs.close();
            } catch (Exception e) {
            }
        
        }
        return rsId;
      
        
        
    }
    

}
