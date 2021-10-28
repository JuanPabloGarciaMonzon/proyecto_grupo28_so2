/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package acopio;

import java.awt.Color;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.value.ObservableValue;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * 
 */
public class Ventana extends javax.swing.JFrame {

    boolean iniciado = false;
    JLabel estanteria[] = new JLabel[20]; 
    Cajas lista_productos = new Cajas();
    
    Productor_CP productores_cp[];
    Productor_CG productores_cg[];
    Consumidor_CP consumidores_cp[];
    Consumidor_CG consumidores_cg[];
    
    int cantidad_productores_cp, cantidad_productores_cg, cantidad_consumidores_cp, cantidad_consumidores_cg = 0;
    
    int espaciosocupados = 0;
    int cajaspequeñasingresadas = 0;
    int cajasgrandesingresadas = 0;
    int cajaspequeñasretiradas = 0;
    int cajasgrandesretiradas = 0;
    
    ImageIcon caja = new ImageIcon(getClass().getClassLoader().getResource("acopio/box.png"));
    ImageIcon cajag = new ImageIcon(getClass().getClassLoader().getResource("acopio/box1.png"));
    ImageIcon cajag1 = new ImageIcon(getClass().getClassLoader().getResource("acopio/box2.png"));
    ImageIcon entrega = new ImageIcon(getClass().getClassLoader().getResource("acopio/delivery.png"));
    ImageIcon entregag = new ImageIcon(getClass().getClassLoader().getResource("acopio/delivery1.png"));
    ImageIcon entregag1 = new ImageIcon(getClass().getClassLoader().getResource("acopio/delivery2.png"));
    
    /** Creates new form Ventana */
    public Ventana() {
        initComponents();
        this.jLabel1.setBackground(Color.blue);
        this.jLabel10.setBackground(Color.blue);
        this.jLabel11.setBackground(Color.blue);
        this.jLabel12.setBackground(Color.blue);
        this.jLabel13.setBackground(Color.blue);
        this.jLabel14.setBackground(Color.blue);
        this.jLabel15.setBackground(Color.blue);
        this.jLabel16.setBackground(Color.blue);
        this.jLabel17.setBackground(Color.blue);
        this.jLabel18.setBackground(Color.blue);
        this.jLabel19.setBackground(Color.blue);
        this.jLabel2.setBackground(Color.blue);
        this.jLabel20.setBackground(Color.blue);
        this.jLabel3.setBackground(Color.blue);
        this.jLabel4.setBackground(Color.blue);
        this.jLabel5.setBackground(Color.blue);
        this.jLabel6.setBackground(Color.blue);
        this.jLabel7.setBackground(Color.blue);
        this.jLabel8.setBackground(Color.blue);
        this.jLabel9.setBackground(Color.blue);
        this.jLabel1.setOpaque(true);
        this.jLabel10.setOpaque(true);
        this.jLabel11.setOpaque(true);
        this.jLabel12.setOpaque(true);
        this.jLabel13.setOpaque(true);
        this.jLabel14.setOpaque(true);
        this.jLabel15.setOpaque(true);
        this.jLabel16.setOpaque(true);
        this.jLabel17.setOpaque(true);
        this.jLabel18.setOpaque(true);
        this.jLabel19.setOpaque(true);
        this.jLabel2.setOpaque(true);
        this.jLabel20.setOpaque(true);
        this.jLabel3.setOpaque(true);
        this.jLabel4.setOpaque(true);
        this.jLabel5.setOpaque(true);
        this.jLabel6.setOpaque(true);
        this.jLabel7.setOpaque(true);
        this.jLabel8.setOpaque(true);
        this.jLabel9.setOpaque(true);
        iniciarestanteria();
        this.jLabel22.setText(String.valueOf(this.espaciosocupados));
        this.jLabel24.setText(String.valueOf(this.cajaspequeñasingresadas));
        this.jLabel26.setText(String.valueOf(this.cajasgrandesingresadas));
        this.jLabel28.setText(String.valueOf(this.cajaspequeñasretiradas));
        this.jLabel30.setText(String.valueOf(this.cajasgrandesretiradas));
        
        //TAMAÑO DE LOS PRODUCTORES
        this.cantidad_productores_cg = 10;
        this.cantidad_productores_cp = 10;
        
        //TAMAÑO DE LOS CONSUMIDORES
        this.cantidad_consumidores_cg = 10;
        this.cantidad_consumidores_cp = 10;
        
        this.jSpinner5.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(iniciado){
                    for(int i = 0; i < cantidad_productores_cp; i++){
                        productores_cp[i].setTiempo(Integer.valueOf(jSpinner5.getValue().toString()));
                    }
                }
            }
        
        } );
        
        this.jSpinner6.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(iniciado){
                    for(int i = 0;  i < cantidad_productores_cg; i++){
                        productores_cg[i].setTiempo(Integer.valueOf(jSpinner6.getValue().toString()));
                    }
                }
            }
            
        });
        
        this.jSpinner7.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(iniciado){
                    for(int i = 0; i < cantidad_consumidores_cp; i++){
                        consumidores_cp[i].setTiempo(Integer.parseInt(jSpinner7.getValue().toString()));
                    }
                }
            }
        
        });
        
        this.jSpinner8.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                if(iniciado){
                    for(int i = 0; i < cantidad_consumidores_cg; i++){
                        consumidores_cg[i].setTiempo(Integer.parseInt(jSpinner8.getValue().toString()));
                    }
                }
            }
            
        });
    }
    
    private void crear_productores(){
        for(int i = 0; i < this.cantidad_productores_cg; i++){
            this.productores_cg[i] = new Productor_CG(this.lista_productos, Integer.parseInt(this.jSpinner5.getValue().toString()));
        }
        for(int i = 0; i < this.cantidad_productores_cp; i++){
            this.productores_cp[i] = new Productor_CP(this.lista_productos, Integer.parseInt(this.jSpinner6.getValue().toString()));
        }
    }
    
    private void crear_consumidores(){
        for(int i = 0; i < this.cantidad_consumidores_cg; i++){
            this.consumidores_cg[i] = new Consumidor_CG(this.lista_productos, Integer.parseInt(this.jSpinner7.getValue().toString()));
        }
        for(int i = 0; i < this.cantidad_consumidores_cp; i++){
            this.consumidores_cp[i] = new Consumidor_CP(this.lista_productos, Integer.parseInt(this.jSpinner8.getValue().toString()));
        }
    }
    
    private void iniciarestanteria(){
        this.estanteria[0] = this.jLabel1;
        this.estanteria[1] = this.jLabel2;
        this.estanteria[2] = this.jLabel3;
        this.estanteria[3] = this.jLabel4;
        this.estanteria[4] = this.jLabel5;
        this.estanteria[5] = this.jLabel6;
        this.estanteria[6] = this.jLabel7;
        this.estanteria[7] = this.jLabel8;
        this.estanteria[8] = this.jLabel9;
        this.estanteria[9] = this.jLabel10;
        this.estanteria[10] = this.jLabel11;
        this.estanteria[11] = this.jLabel12;
        this.estanteria[12] = this.jLabel13;
        this.estanteria[13] = this.jLabel14;
        this.estanteria[14] = this.jLabel15;
        this.estanteria[15] = this.jLabel16;
        this.estanteria[16] = this.jLabel17;
        this.estanteria[17] = this.jLabel18;
        this.estanteria[18] = this.jLabel19;
        this.estanteria[19] = this.jLabel20;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel32 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel33 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jSpinner6 = new javax.swing.JSpinner();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSpinner7 = new javax.swing.JSpinner();
        jSpinner8 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Centro de Acopio");

        jPanel1.setName("Ventana"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Número de espacios ocupados:");

        jLabel22.setText("jLabel22");

        jLabel23.setText("Número de cajas pequeñas colocadas:");

        jLabel24.setText("jLabel24");

        jLabel25.setText("Número de cajas grandes colocadas:");

        jLabel26.setText("jLabel26");

        jLabel27.setText("Número de cajas pequeñas retiradas:");

        jLabel28.setText("jLabel28");

        jLabel29.setText("Número de cajas grandes retiradas:");

        jLabel30.setText("jLabel30");

        jLabel31.setText("Número de Productores de cajas pequeñas:");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel32.setText("Número de Productores de cajas grandes:");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel33.setText("Número de Consumidores de cajas pequeñas:");

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel34.setText("Número de Consumidores de cajas grandes:");

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel35.setText("Tiempo para Productores de cajas pequeñas:");

        jLabel36.setText("Tiempo para Productores de cajas grandes:");

        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel37.setText("Tiempo para Consumidores de cajas pequeñas:");

        jLabel38.setText("Tiempo para Consumidores de cajas grandes:");

        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner5)
                            .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner7)
                            .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                            .addComponent(jSpinner2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner4)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void actualizarlabel(int indice, boolean productor){
        if(productor){
            this.estanteria[indice].setIcon(this.caja);
            this.espaciosocupados++;
            this.cajaspequeñasingresadas++;
            System.out.println(this.espaciosocupados);
            try{
                Thread.sleep(100);
            }catch(Exception e){
                
            }
            
            for(int i = 0; i < this.lista_productos.get_lista().size(); i++){
                if(this.lista_productos.get_lista().get(i).compareTo("p") == 0){
                    this.estanteria[i].setIcon(this.caja);
                }else{
                    this.estanteria[i].setIcon(this.cajag);
                    this.estanteria[i+1].setIcon(this.cajag1);
                    i = i+1;
                }
            }
        }else{
            this.estanteria[indice].setIcon(this.entrega);
            this.espaciosocupados--;
            this.cajaspequeñasretiradas++;
            System.out.println(this.espaciosocupados);
            try{
                Thread.sleep(100);
            }catch(Exception e){
                
            }
            for(int i = 0; i < this.lista_productos.get_lista().size(); i++){
                if(this.lista_productos.get_lista().get(i).compareTo("p") == 0){
                    this.estanteria[i].setIcon(this.caja);
                }else{
                    this.estanteria[i].setIcon(this.cajag);
                    this.estanteria[i+1].setIcon(this.cajag1);
                    i = i+1;
                }
            }
            
        }
        this.jLabel22.setText(String.valueOf(this.espaciosocupados));
        this.jLabel24.setText(String.valueOf(this.cajaspequeñasingresadas));
        this.jLabel28.setText(String.valueOf(this.cajaspequeñasretiradas));
        this.repaint();
    }
    
    public void actualizarlabel_bb(int indice_inicio, int indice_fin, boolean productor){
        if(productor){
            this.estanteria[indice_inicio].setIcon(this.cajag);
            this.estanteria[indice_fin].setIcon(this.cajag1);
            try{
                Thread.sleep(100);
            }catch(Exception e){
                
            }
            this.espaciosocupados += 2;
            System.out.println(this.espaciosocupados);
            this.cajasgrandesingresadas++;
            for(int i = 0; i < this.lista_productos.get_lista().size(); i++){
                if(this.lista_productos.get_lista().get(i).compareTo("p") == 0){
                    this.estanteria[i].setIcon(this.caja);
                }else{
                    this.estanteria[i].setIcon(this.cajag);
                    this.estanteria[i+1].setIcon(this.cajag1);
                    i = i+1;
                }
            }
            for(int i = this.lista_productos.get_lista().size(); i < 20; i++){
                this.estanteria[i].setIcon(null);
            }
        }else{
            this.estanteria[indice_inicio].setIcon(this.entregag);
            this.estanteria[indice_fin].setIcon(this.entregag1);
            try{
                Thread.sleep(100);
            }catch(Exception e){
                
            }
            for(int i = 0; i < this.lista_productos.get_lista().size(); i++){
                if(this.lista_productos.get_lista().get(i).compareTo("p") == 0){
                    this.estanteria[i].setIcon(this.caja);
                }else{
                    this.estanteria[i].setIcon(this.cajag);
                    this.estanteria[i+1].setIcon(this.cajag1);
                    i = i+1;
                }
            }
            for(int i = this.lista_productos.get_lista().size(); i < 20; i++){
                this.estanteria[i].setIcon(null);
            }
            this.espaciosocupados -= 2;
            this.cajasgrandesretiradas++;
            System.out.println(this.espaciosocupados);
        }
        this.jLabel22.setText(String.valueOf(this.espaciosocupados));
        this.jLabel26.setText(String.valueOf(this.cajasgrandesingresadas));
        this.jLabel30.setText(String.valueOf(this.cajasgrandesretiradas));
        this.repaint();
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(this.jButton1.getText().compareTo("Iniciar") == 0){
            this.iniciar();
        }else if(this.jButton1.getText().compareTo("Pausa") == 0){
            this.jButton1.setText("Reanudar");
            for(int i = 0; i < this.cantidad_consumidores_cg; i++){
                this.consumidores_cg[i].pausar_consumidor();
            }
            for(int i = 0; i < this.cantidad_consumidores_cp; i++){
                this.consumidores_cp[i].pausar_consumidor();
            }
            for(int i = 0; i < this.cantidad_productores_cg; i++){
                this.productores_cg[i].pausar_productor();
            }
            for(int i = 0; i < this.cantidad_productores_cp; i++){
                this.productores_cp[i].pausar_productor();
            }
        }else{
            this.jButton1.setText("Pausa");
            for(int i = 0; i < this.cantidad_consumidores_cg; i++){
                this.consumidores_cg[i].reanudar_consumidor();
            }
            for(int i = 0; i < this.cantidad_consumidores_cp; i++){
                this.consumidores_cp[i].reanudar_consumidor();
            }
            for(int i = 0; i < this.cantidad_productores_cg; i++){
                this.productores_cg[i].reanudar_productor();
            }
            for(int i = 0; i < this.cantidad_productores_cp; i++){
                this.productores_cp[i].reanudar_productor();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void iniciar(){
        this.cantidad_consumidores_cg = Integer.parseInt(this.jSpinner4.getValue().toString());
        this.cantidad_consumidores_cp = Integer.parseInt(this.jSpinner3.getValue().toString());
        this.cantidad_productores_cg = Integer.parseInt(this.jSpinner2.getValue().toString());
        this.cantidad_productores_cp = Integer.parseInt(this.jSpinner1.getValue().toString());
        this.lista_productos.setCajas(this.estanteria, this);
        //CREANDO LOS HILOS
        this.productores_cg = new Productor_CG[this.cantidad_productores_cg];
        this.productores_cp = new Productor_CP[this.cantidad_productores_cp];
        this.consumidores_cg = new Consumidor_CG[this.cantidad_consumidores_cg];
        this.consumidores_cp = new Consumidor_CP[this.cantidad_consumidores_cp];
        crear_productores();
        crear_consumidores();
        this.jSpinner1.setEnabled(false);
        this.jSpinner2.setEnabled(false);
        this.jSpinner3.setEnabled(false);
        this.jSpinner4.setEnabled(false);
        for(int i = 0; i < this.cantidad_consumidores_cg; i++){
                this.consumidores_cg[i].start();
            }
            for(int i = 0; i < this.cantidad_consumidores_cp; i++){
                this.consumidores_cp[i].start();
            }
            for(int i = 0; i < this.cantidad_productores_cg; i++){
                this.productores_cg[i].start();
            }
            for(int i = 0; i < this.cantidad_productores_cp; i++){
                this.productores_cp[i].start();
            }
        this.iniciado = true;
        this.jButton1.setText("Pausa");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    // End of variables declaration//GEN-END:variables

}
