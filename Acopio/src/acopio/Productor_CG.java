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
public class Productor_CG extends Thread{
    Cajas lista_cajas;
    boolean pausado = false;
    int intervalo_CG = 0;

    public Productor_CG(Cajas lista_cajas, int intervalo_CG) {
        this.lista_cajas = lista_cajas;
        this.intervalo_CG = intervalo_CG;
    }
    
    public void setTiempo(int tiempo){
        this.intervalo_CG = tiempo;
    }
    
    public void Producir() throws InterruptedException{
        Thread.sleep(this.intervalo_CG * 1000);
        this.lista_cajas.add_bb();
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
            }catch(InterruptedException ex){
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
