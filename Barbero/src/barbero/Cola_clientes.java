/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barbero;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 
 */
public class Cola_clientes {
    LinkedList<String> cola_clientes = new LinkedList();
    
    ReentrantLock bloqueo = new ReentrantLock();
    Condition no_llena = bloqueo.newCondition();
    Condition no_vacia = bloqueo.newCondition();
    
    Barberia b;
    
    public void setParametros(Barberia barberia){
        this.b = barberia;
    }
        
    public void Agregar_cliente(){
        try{
            this.bloqueo.lock();
            while(this.cola_clientes.size() == 20){
                System.out.println("El cliente se va");
                this.b.clientes_se_van++;
                this.b.actualizaretiquetasevan();
                this.no_llena.wait();
            }
            System.out.println("Ingreso cliente a la silla [" + this.cola_clientes.size() + "]");
            this.cola_clientes.add("c");
            this.b.clientes_atendidos++;
            this.b.clientes_en_sala++;
            this.b.actualizar();
            this.no_vacia.signalAll();
        }catch(Exception e){
            
        }finally{
            this.bloqueo.unlock();
        }
    }
    
    public void Sacar_cliente(){
        try{
            this.bloqueo.lock();
            while(this.cola_clientes.size() == 0){
                System.out.println("El barbero se duerme");
                this.b.barbero_durmiendo = true;
                this.b.actualizar();
                wait();
            }
            this.b.clientes_en_sala--;
            this.cola_clientes.removeFirst();
            
            this.b.barbero_durmiendo = false;
            this.b.actualizar();
            this.no_llena.signalAll();
        }catch(Exception e){
            
        }finally{
            this.bloqueo.unlock();
        }
    }
    
    public void Aumentar_completados(){
        if(!this.b.barbero_durmiendo){
            this.b.clientes_completados++;
            this.b.actualizaretiquetacompl();
        }
    }
}
