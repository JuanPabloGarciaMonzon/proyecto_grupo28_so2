/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acopio;

import java.awt.Color;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JLabel;

/**
 *
 * 
 */
public class Cajas {
    LinkedList<String> lista_productos = new LinkedList<>();
    
    JLabel cajas[] = new JLabel[20];
    
    Ventana v;
    boolean esta_usada = false;
    
    ReentrantLock bloqueo = new ReentrantLock();
    Condition no_llena = bloqueo.newCondition();
    Condition no_vacia = bloqueo.newCondition();
    Condition esta_en_uso = bloqueo.newCondition();
    
    public void setCajas(JLabel p_cajas[], Ventana v){
        this.cajas = p_cajas;
        this.v = v;
    }
    
    public void add(int tipo) throws InterruptedException{
        int i = -1;
        try{
            this.bloqueo.lock();
            while(this.lista_productos.size() == 20){
                System.out.println("LA ESTANTERIA YA NO PUEDE RECIBIR CAJAS PEQUEÑAS");
                this.no_llena.await();
            }
            i = this.lista_productos.size() + 1;
            System.out.println("Se inserto en [" + String.valueOf(this.lista_productos.size()) + "] - una caja pequeña");
            this.lista_productos.add("p");
            //this.cajas[this.lista_productos.size() - 1].setBackground(Color.red);
            v.actualizarlabel(this.lista_productos.size() - 1, true);
            this.no_vacia.signalAll();
        }catch(Exception e){

        }finally{
            this.bloqueo.unlock();
        }   
    }
   
    public void add_bb() throws InterruptedException{
        int i = -1;
        try{
            this.bloqueo.lock();
            while(this.lista_productos.size() == 20 || this.lista_productos.size() + 2 > 20){
                System.out.println("LA ESTANTERIA YA NO PUEDE RECIBIR CAJAS GRANDES");
                this.no_llena.await();
            }
            i = this.lista_productos.size() + 2;
            System.out.println("Se inserto en [" + String.valueOf(this.lista_productos.size()) + "] - una caja grande");
            this.lista_productos.add("g");
            this.lista_productos.add("g");
            v.actualizarlabel_bb(this.lista_productos.size() - 2, this.lista_productos.size() - 1, true);
            this.no_vacia.signalAll();
        }catch(Exception e){

        }finally{
            this.bloqueo.unlock();
        }
    }

    public String get(){
        String i = "";
        try{
            this.bloqueo.lock();
            while(this.lista_productos.size() == 0){
                System.out.println("LA ESTANTERIA ESTA VACIA");
                this.no_vacia.await();
            }
            int auxindex = -1;
            for(int in = 0; in <  this.lista_productos.size(); in++){
                String aux = this.lista_productos.get(in);
                if(aux.compareTo("p") == 0){
                    auxindex = in;
                    break;
                }
            }
            if(auxindex != -1){
                String auximprimir = "Se removio en [" + auxindex + "]";
                this.lista_productos.remove(auxindex);
                System.out.println(auximprimir + " - una caja pequeña");
                v.actualizarlabel(auxindex, false);
            }else{
                System.out.println("LA ESTANTERIA YA NO TIENE CAJAS PEQUEÑAS");
            }
            this.no_llena.signalAll();
        }catch(Exception e){
            
        }finally{
            this.bloqueo.unlock();
            return i;
        }
    }
    
    public String get_bb(){
        String i = "";
        try{
            this.bloqueo.lock();
            while(this.lista_productos.size() == 0){
                System.out.println("LA ESTANTERIA ESTA VACIA");
                this.no_vacia.await();
            }
            int auxindex = -1;
            for(int in = 0; in < this.lista_productos.size(); in++){
                String aux = this.lista_productos.get(in);
                if(aux.compareTo("g") == 0){
                    auxindex = in;
                    break;
                }
            }
            if(auxindex != -1){
                String auximprimir = "Se removio en [" + auxindex + "]";
                this.lista_productos.remove(auxindex);
                this.lista_productos.remove(auxindex);
                System.out.println(auximprimir + " - una caja grande");
                v.actualizarlabel_bb(auxindex, auxindex + 1, false);
            }else{
                System.out.println("LA ESTANTERIA YA NO TIENE CAJAS GRANDES");
            }
            this.no_llena.signalAll();
        }catch(Exception e){
            
        }finally{
            this.bloqueo.unlock();
            return i ;
        }
    }
    
    public LinkedList<String> get_lista(){
        return this.lista_productos;
    }
}
