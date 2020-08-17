/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo_SCACV;

import java.util.Random;

/**
 *
 * @author Marina
 */
public class Modelo extends Thread{
    private double velocidad;
    private int revoluciones;
    private final int maximaAceleracion = 150;
    private final int cteFrenado = 250;
    private int aceleracion;
    private double combustible;
    private double consumo;
    private double combustibleAlIniciar;
    private boolean estadoCoche; //false=apagado true=encendido
    private double coeficienteFrenado;
    private double distanciaTotal;
    private double distanciaReciente;
    private double distanciaRepostaje;
    private final double rozamiento = 10;
    public boolean flag_repostar = false;
    public boolean flag_pastillas = false;
    public boolean flag_aceite = false;
    public boolean flag_revision = false;
    private double revoluciones_pastillas;
    private double revoluciones_aceite;
    private double revoluciones_revision;
    private double revoluciones_total = 0;
    

    public Modelo(){
        velocidad = 0.0;
        revoluciones = 0;
        aceleracion = 0;
        
        Random r = new Random();
        combustible = r.nextInt(85+1)+15; //100 litros máximo
        estadoCoche = false;
        coeficienteFrenado = 0.0;
        distanciaTotal = 0.0;
        distanciaReciente = 0.0;
        distanciaRepostaje = 0.0;
        combustibleAlIniciar = combustible;
        revoluciones_pastillas = 0;
        revoluciones_aceite = 0;
        revoluciones_revision = 0;
    }

    public int getRevoluciones() {
        return revoluciones;
    }
    
    public double getVelocidad() {
        return velocidad;
    }

    public int getAceleracion() {
        return aceleracion;
    }

    public double getCombustible() {
        return combustible;
    }
    
    public double getConsumo() {
        return consumo;
    }

    public boolean isEstadoCoche() {
        return estadoCoche;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public double getDitanciaReciente() {
        return distanciaReciente;
    }
    
    public void repostar(){
        combustible = 100.0;
        flag_repostar = false;
        combustibleAlIniciar = combustible;
    }
    
    public void arrancar(){
        
        estadoCoche = true; //encendido
        if(!this.isAlive()){
           this.start();
        }
    }
    
    public void parar(){
        estadoCoche = false;
        distanciaReciente = 0;
    }
    
    public void incrementarAceleracion(double incrAceleracion){
        if(aceleracion < maximaAceleracion  && aceleracion >= 0)
            aceleracion += incrAceleracion;
        if(aceleracion > maximaAceleracion)
            aceleracion = maximaAceleracion;
        if(aceleracion < 0)
            aceleracion = 0;   
    }
    
    public void frenar(){
        coeficienteFrenado = cteFrenado;
    }
    
    public void soltarFreno(){
        coeficienteFrenado = 0.0;
    }
    
    public void apagarAceleracion(){
        aceleracion = 0;
    }
    
    @Override
    public void run(){
            while(true){
                
                if(estadoCoche && combustible > 0){
                       // System.out.println("Revoluciones: "+revoluciones);
                        revoluciones += aceleracion - coeficienteFrenado - rozamiento;
                        
                        if(revoluciones > 4200)
                            revoluciones = 4200;
                        if(revoluciones < 0)
                            revoluciones = 0;

                        velocidad = 2*Math.PI*0.15*revoluciones*(0.06);
                        //System.out.println("Velocidad del coche: "+velocidad);
                        double incrementoAux = 2*Math.PI*0.15*revoluciones/60000;

                        distanciaReciente += incrementoAux;
                        distanciaTotal += incrementoAux;
                        
                        
                        combustible -= this.aceleracion * 5.0E-5;
                        if(combustible < 0)
                           combustible = 0;
                        
                        if(distanciaTotal - distanciaRepostaje == 0)
                            consumo = 0;
                        else
                            consumo = (combustibleAlIniciar - combustible)/(distanciaTotal - distanciaRepostaje)*100;
                        
                        revoluciones_total += revoluciones;
                        if(revoluciones_total - revoluciones_pastillas >= 1E2)
                            flag_pastillas = true;
                        if(revoluciones_total - revoluciones_aceite >= 1E3)
                            flag_aceite = true;
                        if(revoluciones_total - revoluciones_revision >= 1E4)
                            flag_revision = true;
                        if(combustible < 20){
                            flag_repostar = true;
                        }

                    }
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException ex) {}
                 }
             }

    public void cambiarAceite() {
        flag_aceite = false;
        revoluciones_aceite = revoluciones_total;
    }

    public void cambiarPastillas() {
        flag_pastillas = false;
        revoluciones_pastillas= revoluciones_total;
    }

    public void revision() {
        flag_revision = false;
        revoluciones_revision= revoluciones_total;
    }
   }



