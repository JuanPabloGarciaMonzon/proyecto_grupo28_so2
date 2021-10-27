import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Ventana extends JFrame{
    
    public Ventana(){
        setSize(350,130);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Inicio Invader Space");

        iniciarComponentes();

        
    }

    public Juego juego(){
        return new Juego();
    }

   
     public void iniciarComponentes(){
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        JLabel etiqueta1 = new JLabel("Indicar la Velocidad");
        etiqueta1.setBounds(10,10,150,30);
        panel.add(etiqueta1);
        JTextField cajaVelocidad = new JTextField();
        cajaVelocidad.setBounds(150,10,150,30);
        panel.add(cajaVelocidad);

        JButton mandardatos = new JButton("Enviar");
        mandardatos.setBounds(90,45,150,30);
        panel.add(mandardatos);
        
        mandardatos.addActionListener(e -> {
           // dispose();
           
            //Juego j = new Juego();
            //j.ciclo(); 
            });
    
            
         
         
          
                   
           
       

      
 

    }
}