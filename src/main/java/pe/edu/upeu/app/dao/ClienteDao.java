/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.app.dao.conx.ConnS;
import pe.edu.upeu.app.modelo.ClienteTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author ACER
 */

/*Establece la conexión con la base de datos y define los objetos necesarios 
para ejecutar las consultas SQL.*/
public class ClienteDao implements ClienteDaoI {

    Statement stmt = null;
    Vector columnNames;
    Vector visitdata;
    ConnS instance=ConnS.getInstance();
    Connection connection=instance.getConnection();
    
    static PreparedStatement ps;
    static ErrorLogger log = new ErrorLogger(ClienteDao.class.getName());
    ResultSet rs = null;

    public ClienteDao() {
        columnNames = new Vector();
        visitdata = new Vector();
    }

    @Override
    
    /*Inserta un nuevo registro de cliente en la base de datos. Utiliza una 
    sentencia SQL INSERT para agregar los valores correspondientes a los campos 
    de la tabla cliente.*/
    public int create(ClienteTO d) {
        int rsId = 0;
        String[] returns = {"dniruc"};
        String sql = "INSERT INTO cliente(dniruc, nombrers, tipo) "
                + "VALUES(?,?,?)";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setString(++i, d.getDniruc());
            ps.setString(++i, d.getNombrers());
            ps.setString(++i, d.getTipo());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try ( ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            //System.err.println("create:" + ex.toString());
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    
    /*Actualiza un registro de cliente existente en la base de datos. Utiliza 
    una sentencia SQL UPDATE para modificar los valores de los campos 
    correspondientes a un determinado DNIRUC.*/
    public int update(ClienteTO d) {
        System.out.println("actualizar d.getDniruc: " + d.getDniruc());
        int comit = 0;
        String sql = "UPDATE cliente SET "
                + "nombrers=?, "
                + "tipo=? "
                + "WHERE dniruc=?";
        int i = 0;
        try {
            //new Conexion().connectSQLite()
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getNombrers());
            ps.setString(++i, d.getTipo());
            ps.setString(++i, d.getDniruc());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    @Override
    
    /*Elimina un registro de cliente de la base de datos. Utiliza una sentencia 
    SQL DELETE para eliminar el registro cuyo DNIRUC coincida con el valor proporcionado.*/
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM cliente WHERE dniruc = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);
            // System.err.println("NO del " + ex.toString());
            throw new Exception("Detalle:" + ex.getMessage());
        }
        return comit;
    }

    @Override
    public List<ClienteTO> listCmb(String filter) {
        List<ClienteTO> ls = new ArrayList();
        ls.add(new ClienteTO());
        ls.addAll(listarClientes());
        return ls;
    }

    @Override
    
    /*Recupera una lista de todos los clientes almacenados en la base de datos. 
    Utiliza una sentencia SQL SELECT para obtener todos los registros de la tabla cliente, 
    y luego crea objetos ClienteTO y los agrega a la lista.*/
    public List listarClientes() {
        List<ClienteTO> listarclientes = new ArrayList();
        String sql = "SELECT * FROM cliente";
        try {
            //connection = new Conexion().connectSQLite();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteTO cli = new ClienteTO();
                cli.setDniruc(rs.getString("dniruc"));
                cli.setNombrers(rs.getString("nombrers"));
                cli.setTipo(rs.getString("tipo"));
                listarclientes.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarclientes;
    }

    @Override
    
    /*busca un cliente específico en la base de datos según su DNIRUC. Utiliza una 
    sentencia SQL SELECT con una cláusula WHERE para recuperar el registro correspondiente 
    al DNIRUC proporcionado.*/
    public ClienteTO buscarClientes(String dni) {
        ClienteTO cliente = new ClienteTO();
        String sql = "SELECT * FROM cliente WHERE dniruc = ?";
        try {
            //connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setDniruc(rs.getString("dniruc"));
                cliente.setNombrers(rs.getString("nombrers"));
                cliente.setTipo(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cliente;
    }

    @Override
    public void reportarCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    
    /*Recupera una lista de clientes que coinciden parcialmente con un filtro proporcionado. 
    Utiliza una sentencia SQL SELECT con una cláusula WHERE y el operador LIKE para obtener 
    los registros que coinciden con el filtro.*/
    public List<ModeloDataAutocomplet> listAutoComplet(String filter) {
        List<ModeloDataAutocomplet> listarclientes = new ArrayList();
        String sql = "SELECT * FROM cliente WHERE nombrers like ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, filter + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                ModeloDataAutocomplet.TIPE_DISPLAY = "ID";
                data.setIdx(rs.getString("dniruc"));
                data.setNombreDysplay(rs.getString("nombrers"));
                data.setOtherData(rs.getString("tipo"));
                listarclientes.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarclientes;
    }

}
