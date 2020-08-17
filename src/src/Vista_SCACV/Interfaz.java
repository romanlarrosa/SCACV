/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_SCACV;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JApplet;
import Controlador_SCACV.*; //Solo para lanzar el Japplet

/**
 *
 * @author Marina
 */
public class Interfaz extends JApplet {

    Velocimetro velocimetro;
    AceleradorConsumo acelerador;
    PanelControl botonesControl;
    PalancaCambios palanca;
    Gasolina gasolina;
    BotonesRecambio recambios;
    Consola consola;
    Controlador controlador;
    
    public void init() {
        this.velocimetro = new Velocimetro();
        this.acelerador = new AceleradorConsumo();
        this.botonesControl = new PanelControl();
        this.palanca = new PalancaCambios();
        this.gasolina = new Gasolina();
        this.recambios = new BotonesRecambio();
        this.consola = new Consola();
        this.controlador = new Controlador(this);
        
        final GridBagLayout gridbag = new GridBagLayout();
        final GridBagConstraints pos = new GridBagConstraints();
        
        pos.fill = 1;
        this.resize(1300, 750);
        this.setBackground(Color.WHITE);
        this.setLayout(gridbag);
        pos.gridx = 0;
        pos.gridy = 0;
        pos.gridwidth = 1;
        pos.gridheight = 1;
        pos.weightx = 0.5;
        pos.weighty = 0.2;
        gridbag.setConstraints(this.velocimetro, pos);
        this.add(this.velocimetro);
        pos.gridx = 0;
        pos.gridy = 1;
        pos.gridwidth = 1;
        pos.gridheight = 1;
        pos.weightx = 0.5;
        pos.weighty = 0.5;
        gridbag.setConstraints(this.acelerador, pos);
        this.add(this.acelerador);
        pos.gridx = 1;
        pos.gridy = 0;
        pos.gridwidth = 1;
        pos.gridheight = 1;
        pos.weightx = 0.5;
        pos.weighty = 0.2;
        gridbag.setConstraints(this.botonesControl, pos);
        this.add(this.botonesControl);
        pos.gridx = 2;
        pos.gridy = 0;
        pos.gridwidth = 1;
        pos.gridheight = 1;
        pos.weightx = 0.3;
        pos.weighty = 0.2;
        gridbag.setConstraints(this.palanca, pos);
        this.add(this.palanca);
        pos.gridx = 1;
        pos.gridy = 1;
        pos.gridwidth = 1;
        pos.gridheight = 1;
        pos.weightx = 0.5;
        pos.weighty = 0.5;
        gridbag.setConstraints(this.gasolina, pos);
        this.add(this.gasolina);
        pos.gridx = 2;
        pos.gridy = 1;
        pos.gridwidth = 1;
        pos.gridheight = 1;
        pos.weightx = 0.5;
        pos.weighty = 0.5;
        gridbag.setConstraints(this.recambios, pos);
        this.add(this.recambios);
        pos.gridx = 0;
        pos.gridy = 2;
        pos.gridwidth = 3;
        pos.gridheight = 1;
        pos.weightx = 0.5;
        pos.weighty = 0.1;
        gridbag.setConstraints(this.consola, pos);
        this.add(this.consola);
        
        this.validate();
    }
    
    public AceleradorConsumo getAceleradorConsumo() {
        return acelerador;
    }

    public PanelControl getPanelControl() {
        return botonesControl;
    }

    public PalancaCambios getPalancaCambios() {
        return palanca;
    }

    public Gasolina getGasolina() {
        return gasolina;
    }

    public BotonesRecambio getBotonesRecambio() {
        return recambios;
    }

    public Consola getConsola() {
        return consola;
    }

    public Velocimetro getVelocimetro() {
        return velocimetro;
    }

    public void repintar(EstadoMovimiento estadoMovimiento, double velocidadAlmacenada, EstadoPalanca estadoPalanca, double velocidad, double distanciaRec, double distanciaTotal, int aceleracion, double consumo,double combustible, String _consola) {
        botonesControl.repintar(estadoMovimiento, velocidadAlmacenada);
        palanca.repintar(estadoPalanca);
        velocimetro.repintar(velocidad, distanciaRec, distanciaTotal);
        acelerador.repintar(aceleracion,consumo);
        gasolina.repintar(combustible);
        consola.repintar(_consola);
    }
}
