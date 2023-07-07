/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.app.dao.conx.ConnS;
import pe.edu.upeu.app.modelo.CarritoTO;

/**
 *
 * @author ACER
 */
public class CarritoDao implements CarritoDaoI {

    /*Se crea una instancia de la clase ConnS para establecer la conexión con la base de datos.*/
    ConnS instance = ConnS.getInstance();
    
    /*Se obtiene la conexión a la base de datos utilizando el método getConnection() de la 
    instancia de ConnS.*/
    Connection coneccionx = instance.getConnection();
    
    /*Se declaran las variables para preparar y ejecutar las consultas SQL y para almacenar 
    los resultados de las consultas.*/
    PreparedStatement ps;
    ResultSet rs;

    @Override
    
    /* Este método implementa la operación de obtener una lista de elementos del carrito 
    asociados a un determinado DNIRUC. Se ejecuta una consulta SQL utilizando un parámetro 
    de entrada (dniruc) y se recuperan los resultados en un objeto ResultSet. Luego, se 
    crea una lista de objetos CarritoTO a partir de los resultados y se retorna la lista.*/
    public List<CarritoTO> lista(String dniruc) {
        List<CarritoTO> listaCarrito = new ArrayList();
        String sql = "select * from carrito where dniruc=?";
        try {
            ps = coneccionx.prepareStatement(sql);
            ps.setString(1, dniruc);
            rs = ps.executeQuery();
            while (rs.next()) {
                CarritoTO to = new CarritoTO();
                to.setIdCarrito(rs.getInt("id_carrito"));
                to.setDniruc(rs.getString("dniruc"));
                to.setIdProducto(rs.getInt("id_producto"));
                to.setNombreProducto(rs.getString("nombre_producto"));
                to.setCantidad(rs.getDouble("cantidad"));
                to.setPunitario(rs.getDouble("punitario"));
                to.setPtotal(rs.getDouble("ptotal"));
                to.setEstado(rs.getInt("estado"));
                listaCarrito.add(to);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return listaCarrito;
    }

    @Override
    
    /*Este método implementa la operación de crear un nuevo elemento en el carrito. 
    Se ejecuta una consulta SQL de inserción utilizando los atributos del objeto CarritoTO pasado 
    como parámetro. El método retorna el índice resultante de la ejecución de la consulta.*/
    public int crear(CarritoTO to) {
        int idx = 0;
        int i = 0;
        String sql = "insert into carrito(dniruc, id_producto, nombre_producto, "
                + "cantidad, punitario,ptotal,estado) values(?,?,?,?,?,?,?)";
        try {
            ps = coneccionx.prepareStatement(sql);
            ps.setString(++i, to.getDniruc());
            ps.setInt(++i, to.getIdProducto());
            ps.setString(++i, to.getNombreProducto());
            ps.setDouble(++i, to.getCantidad());
            ps.setDouble(++i, to.getPunitario());
            ps.setDouble(++i, to.getPtotal());
            ps.setInt(++i, to.getEstado());
            idx = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return idx;
    }

    @Override
    
    /*Este método implementa la operación de eliminar un elemento del carrito mediante su identificador 
    (id_carrito). Se ejecuta una consulta SQL de eliminación utilizando el parámetro id. El método 
    retorna el índice resultante de la ejecución de la consulta.*/
    public int delete(int id) {
        int idx = 0;
        int i = 0;
        String sql = "delete from carrito where id_carrito=?";
        try {
            ps = coneccionx.prepareStatement(sql);
            ps.setInt(++i, id);
            idx = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return idx;
    }

    @Override
    
    /*Este método implementa la operación de eliminar todos los elementos del carrito asociados a un 
    determinado DNIRUC (dni). Se ejecuta una consulta SQL de eliminación utilizando el parámetro dni. 
    El método retorna el índice resultante de la ejecución de la consulta.*/
    public int deleteCarAll(String dni) {
        int idx = 0;
        int i = 0;
        String sql = "delete from carrito where dniruc=?";
        try {
            ps = coneccionx.prepareStatement(sql);
            ps.setString(++i, dni);
            idx = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return idx;
    }
}
