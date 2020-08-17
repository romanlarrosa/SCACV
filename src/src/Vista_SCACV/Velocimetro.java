/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_SCACV;

import java.awt.Shape;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Velocimetro extends JPanel
{
    private double velocidad;
    private double distanciaTotal;
    private double distanciaReciente;
    private Ellipse2D velocimetro;
    private Line2D segmentoCorto;
    private Line2D segmentoLargo;
    private Rectangle2D cuadroDistanciaTotal;
    private Rectangle2D cuadroDistanciaReciente;
    private GeneralPath aguja;
    private AffineTransform t;
    
    public Velocimetro(){
        this.velocidad = 0.0;
        this.distanciaTotal = 0.0;
        this.distanciaReciente = 0.0;
        this.setSize(400, 400);
        this.setBackground(new Color(41,62,69));
        this.velocimetro = new Ellipse2D.Double(-135.0, -135.0, 270.0, 270.0);
        this.segmentoCorto = new Line2D.Double(-135.0, 0.0, -127.0, 0.0);
        this.segmentoLargo = new Line2D.Double(-135.0, 0.0, -119.0, 0.0);
        this.cuadroDistanciaTotal = new Rectangle2D.Double(148.0, 230.0, 50.0, 20.0);
        this.cuadroDistanciaReciente = new Rectangle2D.Double(148.0, 250.0, 50.0, 20.0);
        (this.aguja = new GeneralPath()).moveTo(10.0f, 0.0f);
        this.aguja.lineTo(0.0f, 10.0f);
        this.aguja.lineTo(-130.0f, 0.0f);
        this.aguja.lineTo(0.0f, -10.0f);
        this.aguja.closePath();
        this.t = new AffineTransform();
    }
    
    public void paintComponent(final Graphics g) {
        final String kilometrosTotales = String.valueOf(Math.rint(this.distanciaTotal * 10.0) / 10.0);
        final String kilometrosRecientes = String.valueOf(Math.rint(this.distanciaReciente * 10.0) / 10.0);
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(Color.white);
        g2.setFont(new Font("Verdana", 0, 20));
        g2.drawString("0", 31, 243);
        g2.drawString("40", 12, 144);
        g2.drawString("80", 61, 59);
        g2.drawString("120", 156, 24);
        g2.drawString("160", 251, 56);
        g2.drawString("200", 302, 140);
        g2.drawString("240", 283, 241);

        g2.drawString(kilometrosTotales, 197 - g2.getFontMetrics().stringWidth(kilometrosTotales), 248);
        g2.drawString(kilometrosRecientes, 197 - g2.getFontMetrics().stringWidth(kilometrosRecientes), 268);
        g2.drawString("km T", 205, 248);
        g2.drawString("km", 205, 268);
        g2.draw(this.cuadroDistanciaTotal);
        g2.draw(this.cuadroDistanciaReciente);
        g2.translate(170, 160);
        g2.draw(this.velocimetro);
        this.t.setToRotation(Math.toRadians(-30.0));
        g2.transform(this.t);
        this.t.setToRotation(Math.toRadians(this.velocidad));
        g2.transform(this.t);
        g2.setPaint(new Color(193,124,116));
        g2.fill(this.aguja);
    }
    
    public void repintar(double vel, double distanciaRec, double distanciaT) {
        this.velocidad = vel;
        this.distanciaReciente = distanciaRec;
        this.distanciaTotal = distanciaT;
        this.repaint();
    }
}

