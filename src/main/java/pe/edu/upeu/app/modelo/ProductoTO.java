/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

/**
 *
 * @author ACER
 */
@Data
public class ProductoTO {

    public int idProducto, idMarca, idCategoria;
    public String nombre;
    public double pu, utilidad, stock;
}
