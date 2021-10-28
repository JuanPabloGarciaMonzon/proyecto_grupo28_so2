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
public class BarberoD extends Thread{
    int intervalo_corte = 1;
    public Cola_clientes clientes;
    
    boolean pausado = false;
    
    public BarberoD(Cola_clientes p_clientes, int tiempo_corte){
        this.clientes = p_clientes;
        this.intervalo_corte = tiempo_corte;
    }
    
    public void cortarcabello() throws InterruptedException{
        this.clientes.Sacar_cliente();
        Thread.sleep(this.intervalo_corte * 1000);
        this.clientes.Aumentar_completados();
    }
    
    public void setTiempo(int tiempo){
        this.intervalo_corte = tiempo;
    }
    
    public void run(){
        while(true){
            try {
                synchronized(this){
                    while(this.pausado){
                        wait();
                    }
                }
                this.cortarcabello();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
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
