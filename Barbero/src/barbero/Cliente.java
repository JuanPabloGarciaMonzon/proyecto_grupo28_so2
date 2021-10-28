/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barbero;

/**
 *
 * 
 */
public class Cliente extends Thread{
    int intervalollegada = 2;
    public Cola_clientes clientes;
    boolean pausado = false;
    
    public Cliente(Cola_clientes clientes, int intervalo){
        this.clientes = clientes;
        this.intervalollegada = intervalo;
    }
    
    public void settiempo(int tiempo){
        this.intervalollegada = tiempo;
    }
    
    public void Llegar() throws InterruptedException{
        Thread.sleep(this.intervalollegada * 1000);
        this.clientes.Agregar_cliente();
    }
    
    public void run(){
        while(true){
            try{
                synchronized(this){
                    while(this.pausado){
                        wait();
                    }
                }
                this.Llegar();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    synchronized void pausar(){
        this.pausado = true;
        notify();
    }
    
    synchronized void reanudar(){
        this.pausado = false;
        notify();
    }
}
