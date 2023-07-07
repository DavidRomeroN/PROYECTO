/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */

/* La clase JHintTextField es una extensión de JTextField que permite mostrar un 
texto de sugerencia o indicación en el campo de texto cuando está vacío o sin foco.
Al implementar la interfaz FocusListener, puede personalizarse el comportamiento 
del campo de texto cuando se obtiene o pierde el foco.*/
public class JHintTextField extends JTextField implements FocusListener {
    private String hint;

    /*El constructor JHintTextField() se utiliza para crear una instancia 
    de la clase JHintTextField y agregarla como un FocusListener al campo de texto, 
    lo que permite que la instancia responda a los eventos de cambio de foco del campo de texto.*/
    public JHintTextField() {
        this.addFocusListener(this);
    }

    @Override
    
    /*El método focusGained se utiliza para borrar el texto de sugerencia y cambiar 
    el color de fuente cuando el campo de texto obtiene el foco, lo que permite que el usuario 
    ingrese su propio texto sin la sugerencia visible.*/
    public void focusGained(FocusEvent e) {
        if (this.getText().toString().equals(hint)) {
            this.setText("");
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    
    /*El método focusLost se utiliza para restaurar el texto de sugerencia 
    cuando el campo de texto pierde el foco y no se ha ingresado ningún texto por parte del usuario.*/
    public void focusLost(FocusEvent e) {
        if (this.getText().equals("")) {
            this.setHintText(this.hint);
        }
    }

    /*El método setHintText se utiliza para establecer el texto de sugerencia en el campo de texto, 
    asignando el texto proporcionado a la variable hint, estableciendo ese texto como el contenido del campo de texto 
    y cambiando el color de fuente a gris claro.*/
    public void setHintText(String hint) {
        this.hint = hint;
        this.setText(hint);
        this.setForeground(Color.LIGHT_GRAY);
    }
}
