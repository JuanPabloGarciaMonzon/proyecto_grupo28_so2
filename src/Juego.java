import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import java.awt.*;
 import java.awt.event.*;

 import javax.swing.*;
import javax.swing.event.*;


public class Juego extends Canvas {
    final BufferStrategy estrategia;
    boolean enJuego = true;
    final ArrayList<Elemento> entidades = new ArrayList<>();
    final ArrayList<Elemento> aRemover = new ArrayList<>();
    Nave nave1;
    Nave nave2;
    int navesRestantes=2;
    int puntos=0;

    public Juego(){
        

        JFrame contenedor = new JFrame("Space Invaders");
        contenedor.setPreferredSize(new Dimension(800,750));
       
        

        JPanel panel = (JPanel) contenedor.getContentPane();
        panel.setPreferredSize(new Dimension(800,750));
        panel.setLayout(null);
        setSize(800,750);
        //setLocationRelativeTo(null);
        
        panel.add(this);
        setIgnoreRepaint(true);
        contenedor.pack();
        contenedor.setResizable(false);
        contenedor.setVisible(true);
        contenedor.setDefaultCloseOperation(contenedor.EXIT_ON_CLOSE);
        
        contenedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new InputHandler());
        requestFocus();
        createBufferStrategy(2);
        estrategia = getBufferStrategy();
        iniciarEntidades();

        hiloNodriza nodriza = new hiloNodriza();

                nodriza.start();
     
    }

    public class InputHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 65) {
                if(!nave1.comprobarColision(nave2) ||
                    (nave1.posicionX-nave1.sprite.getAncho()) < nave2.posicionX) {
                    nave1.mover(-nave1.sprite.getAncho());
                }
            }
            if(e.getKeyCode() == 83) {
                Disparo shot = disparo((int)(nave1.posicionX + nave1.sprite.getAncho()/2),(int)(nave1.posicionY - nave1.sprite.getAlto()));
                entidades.add(shot);
                hiloDisparo hd = new hiloDisparo(shot, 1);
                hd.start();
            }
            if(e.getKeyCode() == 68) {
                if(!nave1.comprobarColision(nave2) ||
                    (nave1.posicionX+2*nave1.sprite.getAncho()) < nave2.posicionX) {
                    nave1.mover(nave1.sprite.getAncho());
                }
            }

            if(e.getKeyCode() == 74) {
                if(!nave2.comprobarColision(nave1) ||
                    (nave1.posicionX+nave1.sprite.getAncho()) < (nave2.posicionX-nave2.sprite.getAncho())) {
                    nave2.mover(-nave2.sprite.getAncho());
                }
            }
            if(e.getKeyCode() == 75) {
                Disparo shot = disparo((int)(nave2.posicionX + nave2.sprite.getAncho()/2),(int)(nave2.posicionY - nave2.sprite.getAlto()));
                entidades.add(shot);
                hiloDisparo hd = new hiloDisparo(shot, 2);
                hd.start();
            }
            if(e.getKeyCode() == 76) {
                if(!nave2.comprobarColision(nave1) ||
                    (nave1.posicionX+nave1.sprite.getAncho()) < (nave2.posicionX+nave2.sprite.getAncho())) {
                    nave2.mover(nave2.sprite.getAncho());
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == 65) nave1.mover(0);
            if(e.getKeyCode() == 68) nave1.mover(0);

            if(e.getKeyCode() == 74) nave2.mover(0);
            if(e.getKeyCode() == 76) nave2.mover(0);
        }
    }

    private void iniciarEntidades(){
        nave1 = new Nave(this, "sprites/ship.gif", 0,580);
        nave2 = new Nave(this, "sprites/ship2.gif", 500,580);
        entidades.add(nave1);
        entidades.add(nave2);
    }

   
       
    

    private Disparo disparo(int posicionX, int posicionY){
        return new Disparo(this, "sprites/shot.gif", posicionX, posicionY);
    }

    private Alien alien(){
        return new Alien(this, "sprites/alien.gif", 0, 0);
    }

    private Ventana ventana(){
        return new Ventana();
    }
    public void removerElemento(Elemento el){
        aRemover.add(el);
    }

    public void ciclo(){

        while(enJuego){
            Graphics2D g = (Graphics2D) estrategia.getDrawGraphics();
            g.setColor(Color.black);
            g.fillRect(0,0,800,750);

            Graphics2D g2 = (Graphics2D) estrategia.getDrawGraphics();
            //g2.fillRect(0,600,800,50);
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Times New Roman", Font.BOLD, 16));
            g2.drawString("Puntaje: "+puntos, 0, 650);
            g2.drawString("Tuc tuc: "+nave1.vida, 250, 650);
            g2.drawString("Alcon Milenario: "+nave2.vida, 550, 650);
            g2.drawString("Velocidad de los Invasores: ", 0, 700);
            
            

            try {
                for (Elemento el : entidades) {
                    el.dibujar(g);
                }
            }catch (Exception ignored){}

            try{
                for(Elemento el : entidades){
                    for(Elemento otro : entidades){
                        if(el == otro) continue;
                        if(el.comprobarColision(otro)){
                            el.colision(otro);
                            otro.colision(el);
                            if(navesRestantes==0) {
                                enJuego = false;
                                JOptionPane.showMessageDialog(this,"GG", "", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            }
                        }
                    }
                }
            }catch (Exception ignored){}

            entidades.removeAll(aRemover);
            aRemover.clear();

            g.dispose();
            estrategia.show();
        }
    }

    public static class hiloDisparo extends Thread{
        final Disparo disparo;

        public hiloDisparo(Disparo disparo, int numero){
            super("Disparando "+numero);
            this.disparo = disparo;
        }

        @Override
        public void run() {
            while (!disparo.usado){
                disparo.mover(-30);
                try {
                    Thread.sleep(100);
                }catch (Exception ignored){}
            }
        }
    }

    public class hiloNodriza extends Thread{
        public hiloNodriza(){
            super("Nave Nodriza");
        }

        @Override
        public void run() {
            int velocidad = 1;
            int contador = 0;
            int contadorAlienT = 0;
            while(enJuego){
                Alien alien = alien();
                entidades.add(alien);
                contador++;
                contadorAlienT++;
                if(contador==25){
                    velocidad++;
                    contador=0;
                }
                hiloMueveAlien mueveAlien = new hiloMueveAlien(alien, contadorAlienT);
                mueveAlien.start();
                try{
                    Thread.sleep(1000/velocidad);
                }catch (Exception ignored){}
            }
        }
    }

    public static class hiloMueveAlien extends Thread{
        final Alien alien;

        public hiloMueveAlien(Alien alien, int numero){
            super("Alien "+numero);
            this.alien = alien;
        }

        @Override
        public void run() {

            while (!alien.usado){
                alien.mover(20);
                try{
                    Thread.sleep(500);
                }catch (Exception ignored){}
            }
        }
    }
   

    public static void main(String []args){

        Ventana ini = new Ventana();
        ini.setVisible(true);
        
      Juego j = new Juego();
      j.ciclo();
    }
}
