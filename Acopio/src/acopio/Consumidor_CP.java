/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acopio;

import java.util.Random;

/**
 *
 * 
 */
public class Consumidor_CP extends Thread{
    public Cajas lista_productos;
    boolean pausado = false;
    int intervaloConsumidor = 0;
    
    public Consumidor_CP(Cajas lista_productos, int intervaloConsumidor) {
        this.lista_productos = lista_productos;
        this.intervaloConsumidor = intervaloConsumidor;
    }
    
    public void setTiempo(int tiempo){
        this.intervaloConsumidor = tiempo;
    }
    
    public void Consumir() throws InterruptedException{
        Thread.sleep(this.intervaloConsumidor * 1000);
        String i = lista_productos.get();
        Thread.sleep(100);
    }
    
    @Override
    public void run(){
        while(true){
            try{
                synchronized(this){
                    while(pausado){
                        wait();
                    }
                }
                Consumir();
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    
    synchronized void pausar_consumidor(){
        this.pausado = true;
        notify();
    }
    
    synchronized void reanudar_consumidor(){
        this.pausado = false;
        notify();
    }
}
