/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import pe.edu.upeu.app.util.UtilsX;

/**
 *
 * @author ACER
 */

// Define una clase llamada "FondoPanel" que extiende la clase "JPanel" de la biblioteca Swing de Java.
public class FondoPanel extends JPanel {

    private BufferedImage image;
    public String nombreImg = "fondo.png";
    public int cornerRadius = 0;
    UtilsX obj = new UtilsX();

    public FondoPanel() {
    }

    /*Este constructor permite la creación de una instancia de la clase "FondoPanel" con valores específicos
    para el nombre de la imagen de fondo ("imgName") y el radio de las esquinas ("cornerRadius").*/
    public FondoPanel(String imgName, int cornerRadius) {
        this.nombreImg = imgName;
        this.cornerRadius = cornerRadius;
    }

    /*Se utiliza para indicar que un método está sobrescribiendo un método de la clase padre o de una interfaz,
    y ayuda a garantizar que se realice correctamente la sobrescritura de métodos en Java.*/
    @Override
    
    /*El método paint(Graphics g) en el código se utiliza para cargar una imagen desde un archivo, 
    aplicar esquinas redondeadas a la imagen, dibujar la imagen en el componente gráfico, 
    establecer la opacidad del componente y realizar la pintura estándar.*/
    public void paint(Graphics g) {
        try {
            image = ImageIO.read(obj.getFile(nombreImg));
        } catch (Exception e) {
        }
        image = makeRoundedCorner(image, cornerRadius);
        g.setColor(getBackground());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }

    /*El método makeRoundedCorner toma una imagen original y un radio de esquina como entrada, 
    y devuelve una nueva imagen con esquinas redondeadas utilizando la clase RoundRectangle2D 
    y operaciones de dibujo en Graphics2D.*/
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h,
                cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }

}
