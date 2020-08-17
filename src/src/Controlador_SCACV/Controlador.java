/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador_SCACV;
import Vista_SCACV.*;
import Modelo_SCACV.*;
/**
 *
 * @author Marina
 */


public class Controlador extends Thread{

    Modelo vehiculo;
    Interfaz interfaz;
    boolean velocidadAlmacenada = false;
    private double valorVelocidadAlmacenada = 0.0;
    EstadoPalanca estadoPalanca= EstadoPalanca.APAGADO;
    EstadoMovimiento estadoMovimiento = EstadoMovimiento.APAGADO;
    
    public Controlador(Interfaz i){
        
        vehiculo = new Modelo();
        interfaz = i;
        this.start();
        
    }
    @Override
    public void run(){
        while(true){
           //Primer paso: COCHE APAGADO -> solo se puede encender el coche, el freno no se puede pisar
            if(estadoPalanca != estadoPalanca.REINICIAR )
                switch(estadoMovimiento){
                    case APAGADO:
                        interfaz.getPanelControl().mantenerFreno(false);
                        interfaz.getPanelControl().mantenerAcelerador(false);
                        if(interfaz.getPanelControl().arrancar_o_parar){ //Estaba apagado y se enciende
                            vehiculo.arrancar();
                            estadoMovimiento = EstadoMovimiento.PARADO;
                           // System.out.println("CAMBIO A ESTADO PARADO");
                            interfaz.getPanelControl().arrancar_o_parar = false;
                        }
                        else{                                          //Estaba encendido y se apaga
                            estadoPalanca = EstadoPalanca.APAGADO;
                            velocidadAlmacenada = false;
                        }
                        //interfaz.repintar();
                     break;

                    case PARADO:
                        vehiculo.apagarAceleracion();
                        vehiculo.soltarFreno();
                        
                        interfaz.getPalancaCambios().flag_acelerar=false;
                        interfaz.getPalancaCambios().flag_apagar=false;
                        interfaz.getPalancaCambios().flag_mantener=false;
                        //Apagar motor COMUN
                        if(interfaz.getPanelControl().arrancar_o_parar){
                            vehiculo.parar();
                            estadoMovimiento = EstadoMovimiento.APAGADO;
                          //  System.out.println("CAMBIO A ESTADO APAGADO");
                            interfaz.getPanelControl().arrancar_o_parar = false;
                            velocidadAlmacenada = false; //Reiniciamos la velocidad almacenada cuando apagamos el coche
                            valorVelocidadAlmacenada = 0.0;
                        }
                        else if(interfaz.getPanelControl().acelerar_o_soltar){ //Pisar acelerador COMUN
                            interfaz.getPanelControl().mantenerFreno(false);
                            estadoMovimiento = EstadoMovimiento.ACELERANDO;

                            //System.out.println("CAMBIO A ESTADO ACELERANDO");
                            interfaz.getPanelControl().acelerar_o_soltar = false;
                            interfaz.getPanelControl().frenar_o_soltar = false;
                        }
                        else if(interfaz.getPanelControl().frenar_o_soltar){ //Pisar freno COMUN
                            estadoMovimiento = EstadoMovimiento.FRENANDO;

                           // System.out.println("CAMBIO A ESTADO FRENANDO");
                            interfaz.getPanelControl().acelerar_o_soltar = false;
                            interfaz.getPanelControl().frenar_o_soltar = false;
                        } 
                        //Reiniciar, solo si hay velocidadAlmacenada
                        if(interfaz.getPalancaCambios().flag_reiniciar){
                            interfaz.getPalancaCambios().flag_reiniciar = false;
                            if(velocidadAlmacenada){
                                 //System.out.println("CAMBIO A ESTADO REINICIANDO");
                                //NO implementado
                                estadoMovimiento = EstadoMovimiento.REINICIANDO;
                                estadoPalanca = EstadoPalanca.REINICIAR;
                            }
                        }
                    break;

                    case ACELERANDO: //Cuando aceleramos no podemos arrancar ni frenar
                        interfaz.getPanelControl().mantenerArrancar(true);
                        //Funcionalidad de acelerando
                        vehiculo.incrementarAceleracion(3);
                        vehiculo.soltarFreno();
                        
                        switch(estadoPalanca){ //Segun el estado de la palanca
                            case APAGADO: //Si la tenemos apagada, solo podemor ir hacia la posicion acelerar o soltar el acelerador
                                interfaz.getPanelControl().mantenerFreno(false);
                                interfaz.getPalancaCambios().flag_apagar=false;
                                interfaz.getPalancaCambios().flag_reiniciar=false;
                                interfaz.getPalancaCambios().flag_mantener=false;
                               

                                if(interfaz.getPanelControl().acelerar_o_soltar){ //Si soltamos el acelerador, volvemos a PARADO, la palanca se mantiene APAGADA
                                    estadoMovimiento = EstadoMovimiento.PARADO;
                                   // System.out.println("CAMBIO A ESTADO PARADO");
                                    interfaz.getPanelControl().acelerar_o_soltar=false;
                                    interfaz.getPalancaCambios().flag_acelerar=false;

                                }else if(interfaz.getPalancaCambios().flag_acelerar){ //Si cambiamos palanca a ACELERAR, se cambia a ACELERAR de la palanca
                                    interfaz.getPalancaCambios().flag_acelerar=false;
                                    estadoPalanca = EstadoPalanca.ACELERAR;
                                   // System.out.println("CAMBIO A PALANCA ACELERAR");
                                }
                            break;

                            case ACELERAR:

                                //No se puede pisar el acelerador, no se puede reiniciar PALANCA y no se puede apagar el coche
                                interfaz.getPanelControl().mantenerArrancar(true);
                                interfaz.getPanelControl().mantenerAcelerador(false);
                                interfaz.getPalancaCambios().flag_reiniciar = false;
                                interfaz.getPalancaCambios().flag_acelerar = false;

                                //Se puede pisar el freno
                                if(interfaz.getPanelControl().frenar_o_soltar){
                                    estadoMovimiento = EstadoMovimiento.PARADO;
                                   // System.out.println("CAMBIO A ESTADO PARADO");
                                    interfaz.getPanelControl().frenar_o_soltar = false;
                                    interfaz.getPanelControl().mantenerFreno(false);
                                   // System.out.println("CAMBIO A PALANCA APAGADA");
                                    estadoPalanca = EstadoPalanca.APAGADO; 
                                }
                                else if(interfaz.getPalancaCambios().flag_apagar){ //Se puede cambiar la palanca a APAGADO
                                    interfaz.getPalancaCambios().flag_apagar = false;
                                    estadoPalanca = EstadoPalanca.APAGADO;
                                   // System.out.println("CAMBIO A PALANCA APAGADA");
                                    estadoMovimiento = EstadoMovimiento.PARADO;
                                   // System.out.println("CAMBIO A ESTADO PARADO");   
                                }
                                else if(interfaz.getPalancaCambios().flag_mantener){ //Se puede cambiar la palanca a MANTENER
                                    interfaz.getPalancaCambios().flag_mantener = false;
                                    estadoPalanca = EstadoPalanca.MANTENER;
                                  //  System.out.println("CAMBIO A PALANCA MANTENER");
                                    estadoMovimiento = EstadoMovimiento.MANTENIENDO;
                                   // System.out.println("CAMBIO A ESTADO MANTENIENDO");   
                                    velocidadAlmacenada = true;
                                    valorVelocidadAlmacenada = vehiculo.getVelocidad();
                                }
                            break;
                        }
                    break;

                    case FRENANDO: //Cuando frenamos no podemos apagar el coche ni acelerar
                        //Funcionalidad de frenando
                        vehiculo.apagarAceleracion();
                        vehiculo.frenar();
                        
                        interfaz.getPanelControl().mantenerArrancar(true);
                        interfaz.getPanelControl().mantenerAcelerador(false);   
                        
                        //No podemos cambiar la palanca de sitio
                        interfaz.getPalancaCambios().flag_reiniciar = false;
                        interfaz.getPalancaCambios().flag_acelerar = false;
                        interfaz.getPalancaCambios().flag_apagar = false;
                        interfaz.getPalancaCambios().flag_mantener = false;
                        
                        //Solo podemos soltar el freno
                        if(interfaz.getPanelControl().frenar_o_soltar){
                            interfaz.getPanelControl().frenar_o_soltar = false;
                            estadoMovimiento = EstadoMovimiento.PARADO;
                          //  System.out.println("CAMBIO A ESTADO PARADO");
                        }
                    break;
                        
                    case MANTENIENDO:
                        //No se puede pisar el acelerador, no se puede reiniciar PALANCA y no se puede apagar el coche y no se puede acelerar PALANCA
                        interfaz.getPanelControl().mantenerArrancar(true);
                        interfaz.getPanelControl().mantenerAcelerador(false);
                        interfaz.getPalancaCambios().flag_reiniciar = false;
                        interfaz.getPalancaCambios().flag_acelerar = false;
                        interfaz.getPalancaCambios().flag_mantener = false;
                        
                        //FUncionalidad de mantener la velocidad
                       // System.out.println("Comparando "+ valorVelocidadAlmacenada + " con " +  vehiculo.getVelocidad());
                          if(valorVelocidadAlmacenada >= vehiculo.getVelocidad()){ //Entonces acelero
                            //  System.out.println("Acelero");
                               vehiculo.incrementarAceleracion(3);
                               vehiculo.soltarFreno();
                          }
                          else{ //Entonces freno
                            //  System.out.println("freno");
                                vehiculo.frenar();
                                vehiculo.apagarAceleracion();
                          }
                    
                        //Se puede pisar el freno
                        if(interfaz.getPanelControl().frenar_o_soltar){
                            estadoMovimiento = EstadoMovimiento.FRENANDO;
                          //  System.out.println("CAMBIO A ESTADO FRENANDO");
                            interfaz.getPanelControl().frenar_o_soltar = false;
                          //  System.out.println("CAMBIO A PALANCA APAGADA");
                            estadoPalanca = EstadoPalanca.APAGADO; 
                        }
                        else if(interfaz.getPalancaCambios().flag_apagar){ //Se puede cambiar la palanca a APAGADO
                            interfaz.getPalancaCambios().flag_apagar = false;
                            estadoPalanca = EstadoPalanca.APAGADO;
                           // System.out.println("CAMBIO A PALANCA APAGADA");
                            estadoMovimiento = EstadoMovimiento.PARADO;
                           // System.out.println("CAMBIO A ESTADO PARADO");   
                       } 
                    break;
                }
                else{ //Agrupamos la funcionalidad de la palanca en Reiniciar y el estado del coche en ACELERANDO o FRENANDO
                    
                    //No se puede apagar el coche ni pisar el acelerador ni cambiar el modo de la palanca
                    interfaz.getPanelControl().mantenerArrancar(true);
                    interfaz.getPanelControl().mantenerAcelerador(false);   
                    interfaz.getPalancaCambios().flag_reiniciar = false;
                    interfaz.getPalancaCambios().flag_acelerar = false;
                    interfaz.getPalancaCambios().flag_mantener = false;
                    interfaz.getPalancaCambios().flag_apagar = false;
                    
                    //Funcionalidad de reiniciar
                    if(valorVelocidadAlmacenada >= vehiculo.getVelocidad()){ //Entonces acelero
                        vehiculo.incrementarAceleracion(3);
                        vehiculo.soltarFreno();
                    }
                    else{ //Entonces freno
                        vehiculo.frenar();
                        vehiculo.apagarAceleracion();
                    }
                    
                    if(interfaz.getPanelControl().frenar_o_soltar){ //Solo salgo del estado REINICIANDO pisando el freno
                        interfaz.getPanelControl().mantenerFreno(false);
                        interfaz.getPanelControl().frenar_o_soltar = false;
                        estadoMovimiento = EstadoMovimiento.PARADO;
                       // System.out.println("CAMBIO A ESTADO PARADO");   
                        estadoPalanca = EstadoPalanca.APAGADO;
                       // System.out.println("CAMBIO A PALANCA APAGADA");   
                    }
                }
            if(!(estadoMovimiento == EstadoMovimiento.APAGADO && vehiculo.getVelocidad() == 0)){
                interfaz.getBotonesRecambio().hayQueAceite=false;
                interfaz.getBotonesRecambio().hayQuePastillas=false;
                interfaz.getBotonesRecambio().hayQueRevisar=false;
                interfaz.getBotonesRecambio().hayQueRepostar=false;
            }
            else{
                if(interfaz.getBotonesRecambio().hayQueAceite){
                    vehiculo.cambiarAceite();
                    interfaz.getBotonesRecambio().hayQueAceite = false;
                }
                if(interfaz.getBotonesRecambio().hayQuePastillas){
                    vehiculo.cambiarPastillas();
                    interfaz.getBotonesRecambio().hayQuePastillas = false;
                }
                if(interfaz.getBotonesRecambio().hayQueRevisar){
                    vehiculo.revision();
                    interfaz.getBotonesRecambio().hayQueRevisar = false;
                }
                if(interfaz.getBotonesRecambio().hayQueRepostar){
                    vehiculo.repostar();
                    interfaz.getBotonesRecambio().hayQueRepostar = false;
                }
            }
            
            String consola="";
            if(vehiculo.flag_pastillas)
                consola += "Hay que cambiar las pastillas | ";
            if(vehiculo.flag_aceite)
                consola += "Hay que cambiar el aceite | ";
            if(vehiculo.flag_revision)
                consola += "Hay que pasar una revisión | ";
            if(vehiculo.flag_repostar)
                consola += "Hay que repostar";
            interfaz.repintar(estadoMovimiento, valorVelocidadAlmacenada,
                              estadoPalanca,
                              vehiculo.getVelocidad(),vehiculo.getDitanciaReciente(), vehiculo.getDistanciaTotal(),
                              vehiculo.getAceleracion(), vehiculo.getConsumo(),
                              vehiculo.getCombustible(),
                              consola);
                
                
            try{
                Thread.sleep(100);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
