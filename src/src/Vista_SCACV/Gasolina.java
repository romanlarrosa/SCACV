/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_SCACV;
 
import java.awt.Font;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Gasolina extends JPanel
{
    
    private Arc2D visorCombustible;
    private GeneralPath aguja;
    private AffineTransform t;
    private double combustible;
    
    
    public Gasolina() {
        
        this.combustible = 0.0;
        
        this.setSize(330, 170);
        this.visorCombustible = new Arc2D.Double(20.0, 55.0, 100.0, 100.0, 0.0, 180.0, 1);
        (this.aguja = new GeneralPath()).moveTo(5.0f, 0.0f);
        this.aguja.lineTo(0.0f, 5.0f);
        this.aguja.lineTo(-50.0f, 0.0f);
        this.aguja.lineTo(0.0f, -5.0f);
        this.aguja.closePath();
        this.t = new AffineTransform();
        this.setBackground( new Color(41,62,69));
       
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.translate(50, 50);
        g2.setFont(new Font("Verdana", 0, 16));
        g2.setPaint(Color.white);
        g2.draw(this.visorCombustible);
       
        g2.drawString("COMBUSTIBLE", 20, 25);
        g2.drawString("R", 10, 108);
        g2.drawString("1/4", 21, 66);
        g2.drawString("1/2", 63, 51);
        g2.drawString("3/4", 102, 63);
        g2.drawString("1", 120, 108);
        g2.translate(70, 105);
        this.t.setToRotation(Math.toRadians( this.combustible * 180.0 / 100.0));
        g2.transform(this.t);
        g2.setPaint(new Color(193, 124, 116));
        g2.fill(this.aguja);
    }
    
    public void repintar(double _combustible) {
        this.combustible = _combustible;
        this.repaint();
    }
}


