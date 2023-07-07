package pe.edu.upeu.app.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import pe.edu.upeu.app.SistemaVentas;

/**
 *
 * @author ACER
 */

/* Crea una ventana principal para la aplicación GUI utilizando la clase JFrame.*/
public class GUIMain extends JFrame {

    JMenuBar menuBar;
    JMenu menu1;
    JMenuItem jmI2, menusalir, menusalirdefinitivo, menu3, menu4;
    JTabbedPane jtpane;
    JPanel jp;
    JScrollPane scrollPane;
    GUIMain dd;

    public GUIMain() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(new Dimension(screenSize.width, (screenSize.height) - 36));
        menuBar = new JMenuBar();
        menu1 = new JMenu("Archivo");

        jmI2 = new JMenuItem("Adm. Cliente");

        menu1.add(jmI2);
        menuBar.add(menu1);
        menu1 = new JMenu("Opciones");
        menuBar.add(menu1);

        menusalir = new JMenuItem("Cerrar sesion");
        menusalirdefinitivo = new JMenuItem("Salir programa");
        menu1.add(menusalir);
        menu1.add(menusalirdefinitivo);

        menu3 = new JMenuItem("G. Ventas");
        menuBar.add(menu3);

        menu4 = new JMenuItem("R. Ventas");
        menuBar.add(menu4);

        MenuItemListener menuItemListener = new MenuItemListener();
        //jmI1.addActionListener(menuItemListener);//accion
        jmI2.addActionListener(menuItemListener);
        menusalir.addActionListener(menuItemListener);
        menusalirdefinitivo.addActionListener(menuItemListener);
        //menu2.addActionListener(menuItemListener);
        menu3.addActionListener(menuItemListener);
        menu4.addActionListener(menuItemListener);
        jtpane = new JTabbedPane();
        this.getContentPane().add(BorderLayout.NORTH, menuBar);

        this.setVisible(true);
        dd = this;

    }

    class MenuItemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            jtpane.removeAll();
            Container contai = GUIMain.this.getContentPane();

            if (e.getSource() == jmI2) {
                System.out.println("verrr");

                MainCliente mc = new MainCliente();
                scrollPane = new JScrollPane(mc);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                jtpane.add("Adm. Cliente", scrollPane);

                contai.add(BorderLayout.CENTER, jtpane);
                contai.invalidate();
                contai.validate();
                contai.repaint();
            }

            if (e.getSource() == menusalir) {
                System.out.println("Saliendo");

                dd.dispose();

                new Login().setVisible(true);
            }
            if (e.getSource() == menusalirdefinitivo) {
                System.out.println("salir programa");

                dd.dispose();

            }

            if (e.getSource() == menu3) {
                System.out.println("hola mundo 3");

                MainVentas mv = new MainVentas();
                scrollPane = new JScrollPane(mv);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                jtpane.add("G. Ventas", scrollPane);

                contai.add(BorderLayout.CENTER, jtpane);
                contai.invalidate();
                contai.validate();
                contai.repaint();

            }
            if (e.getSource() == menu4) {
                System.out.println("hola mundo");

                ReporteVenta rv = new ReporteVenta();
                jtpane.add("R. Ventas", rv);

                contai.add(BorderLayout.CENTER, jtpane);
                contai.invalidate();
                contai.validate();
                contai.repaint();

            }
        }
    }

}
