/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ACER
 */
public class MsgBox {
    JPanel p = new JPanel();
    UtilsX util = new UtilsX();
    ImageIcon icon;

    public MsgBox() {
    }

    
    
    public MsgBox(String msg, int tipoDialog, String iconPropio) {

        if (!iconPropio.trim().equals("")) {
            icon = new ImageIcon(util.getFile(iconPropio));
            JOptionPane.showMessageDialog(p, msg, "Mensaje", tipoDialog, icon);
        } else {
            JOptionPane.showMessageDialog(p, msg, "Mensaje", tipoDialog);
        }
    }

    public int showConfirmCustom(String msg, String title, String iconPropio) {
        int input;
        // 0=ok, 2=cancel
        if (!iconPropio.trim().equals("")) {
            icon = new ImageIcon(util.getFile(iconPropio));
            input = JOptionPane.showConfirmDialog(p, msg, title, JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, icon);
        } else {
            input = JOptionPane.showConfirmDialog(p, msg, title, JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return input;
    }
}
