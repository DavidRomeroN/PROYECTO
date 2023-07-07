/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author ACER
 */

/*Estas variables se utilizan para almacenar y especificar los colores 
que se utilizarán en diferentes estados y eventos del botón personalizado.*/
public class MyButton extends JButton {
     public boolean over;
    private Color colorOver;
    private Color color;
    private Color colorClick;

    /*El método getColorOver() se utiliza para obtener el color utilizado 
    cuando el puntero del ratón está sobre el botón.  Esto permite acceder 
    al color utilizado y utilizarlo en otras partes del código según sea necesario.*/
    public Color getColorOver() {
        return colorOver;
    }

    /*Permite cambiar dinámicamente el color según las necesidades del programador 
    o las preferencias de diseño.*/
    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    //El método getColor() se utiliza para obtener el color por defecto del botón. 
    public Color getColor() {
        return color;
    }

    /*Permite cambiar dinámicamente el color según las necesidades del programador o 
    las preferencias de diseño. */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    
    /*Se establecen colores de fondo y trazo, se dibujan rectángulos para representar 
    el fondo y el borde en relieve del botón, y se llama al método paintComponent de 
    la clase base para completar la pintura estándar del botón. */
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(76, 181, 195));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(getBackground());
        g2.fillRect(2, 2, getWidth() - 4, getHeight() - 4);
        super.paintComponent(grphcs);
    }

    /*Establece el área de contenido no dibujada, define los colores utilizados 
    en diferentes estados del botón y agrega un escuchador de eventos de ratón 
    para manejar las interacciones del usuario con el botón.*/
    public MyButton() {
        setContentAreaFilled(false);
        colorOver = new Color(181, 242, 254);
        color = new Color(125, 224, 237);
        colorClick = new Color(158, 212, 237);
        addMouseListener(new MouseAdapter() {
            
            @Override
            
            /*El método mouseEntered se utiliza para cambiar el color de fondo 
            del botón cuando el puntero del ratón entra en su área.  Además, establece 
            la variable over en true para indicar que el puntero del ratón está sobre el botón.*/
            public void mouseEntered(MouseEvent me) {
                setBackground(getColorOver());
                over = true;
            }

            @Override
            
            /*Establece la variable over en false para indicar que el puntero del ratón ya no 
            está sobre el botón*/
            public void mouseExited(MouseEvent me) {
                setBackground(getColor());
                over = false;
            }

            @Override
            
            /*Al establecer el color de fondo como colorClick, se proporciona un efecto visual 
            para indicar que el botón ha sido presionado.*/
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    setBackground(colorClick);
                }
            }

            @Override
            
            /*Si el puntero del ratón sigue estando sobre el botón después de soltarlo, 
            se restablece el color de fondo utilizando getColorOver(). Si el puntero del ratón 
            ya no está sobre el botón, se restablece el color de fondo utilizando getColor().*/
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (over) {
                        setBackground(getColorOver());
                    } else {
                        setBackground(getColor());
                    }
                }
            }
        });
    }
}
