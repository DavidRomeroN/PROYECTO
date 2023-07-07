/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLayeredPane;

/**
 *
 * @author ACER
 */

/*Permite crear instancias de PanelBorder que hereden las funcionalidades de JLayeredPane 
y se puedan personalizar o agregar funcionalidades adicionales según sea necesario.*/
public class PanelBorder extends JLayeredPane {
    public PanelBorder() {
    }

    @Override
    
    /*Se establece el suavizado de bordes, se establece el color de dibujo según el color de fondo 
    del componente y se dibuja un rectángulo redondeado en las dimensiones del componente.*/
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
    }
}
