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
public class Productor_CP extends Thread{
    Cajas lista_productos;
    boolean pausado = false;
    int intervalo_CP = 0;
    
    public Productor_CP(Cajas p_lista_productos, int intervalo_CP){
        this.lista_productos = p_lista_productos;
        this.intervalo_CP = intervalo_CP;
    }
    
    public void setTiempo(int tiempo){
        this.intervalo_CP = tiempo;
    }
    
    public void Producir() throws InterruptedException{
        Thread.sleep(this.intervalo_CP * 1000);
        this.lista_productos.add(0);
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
                Producir();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
    
    synchronized void pausar_productor(){
        this.pausado = true;
        notify();
    }
    
    synchronized void reanudar_productor(){
        this.pausado = false;
        notify();
    }
}
