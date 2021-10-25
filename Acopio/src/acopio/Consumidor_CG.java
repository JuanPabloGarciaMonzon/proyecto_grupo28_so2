/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acopio;

/**
 *
 * 
 */
public class Consumidor_CG extends Thread{
    Cajas lista_productos;
    boolean pausado = false;
    int intervalo_consumidor = 0;

    public Consumidor_CG(Cajas lista_productos, int intervalo_consumidor) {
        this.lista_productos = lista_productos;
        this.intervalo_consumidor = intervalo_consumidor;
    }
    
    public void setTiempo(int tiempo){
        this.intervalo_consumidor = tiempo;
    }
    
    public void Consumir() throws InterruptedException{
        Thread.sleep(this.intervalo_consumidor * 1000);
        this.lista_productos.get_bb();
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
