/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package barbero;

import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * 
 */
public class Barberia extends javax.swing.JFrame {

    int clientes_atendidos = 0;
    int clientes_se_van = 0;
    int clientes_completados = 0;
    int clientes_en_sala = 0;
    Cola_clientes cola = new Cola_clientes();
    boolean barbero_durmiendo = false;
    JLabel sillas[] = new JLabel[20];
    
    ImageIcon iconosilla = new ImageIcon(getClass().getClassLoader().getResource("barbero/silla.png"));
    Image iconoescalados = this.iconosilla.getImage().getScaledInstance(50, 100, Image.SCALE_SMOOTH);
    Icon iconosilla_g = new ImageIcon(iconoescalados);
    ImageIcon iconosentado = new ImageIcon(getClass().getClassLoader().getResource("barbero/sentado.png"));
    Image iconoescaladose = this.iconosentado.getImage().getScaledInstance(50, 100, Image.SCALE_SMOOTH);
    Icon iconosentado_l = new ImageIcon(iconoescaladose);
    
    boolean iniciado=false;
    
    BarberoD barbero;
    Cliente cliente[];
    
    int numero_clientes = 0;
    
    /** Creates new form Barberia */
    public Barberia() {
        initComponents();
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("barbero/barbero.png"));
        Image iconoescalado = icono.getImage().getScaledInstance(this.jLabel1.getWidth(), this.jLabel1.getHeight(), Image.SCALE_SMOOTH);
        Icon iconobarbero = new ImageIcon(iconoescalado);
        this.jLabel1.setIcon(iconobarbero);
        this.jLabel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.jLabel3.setText("Durmiendo");
        crearlabels();
        this.cola.setParametros(this);
        this.jLabel7.setText(String.valueOf(this.clientes_atendidos));
        this.jLabel9.setText(String.valueOf(this.clientes_se_van));
        this.jLabel11.setText(String.valueOf(this.clientes_completados));
        this.jLabel13.setText(String.valueOf(this.clientes_en_sala));
        
        this.jSpinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(iniciado){
                    barbero.setTiempo(Integer.valueOf(jSpinner1.getValue().toString()));
                }
            }
            
        });
        
        this.jSpinner2.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                if(iniciado){
                    for(int i = 0; i < numero_clientes; i++){
                        cliente[i].settiempo(Integer.valueOf(jSpinner2.getValue().toString()));
                    }
                }
            }
        });
    }
    
    private void crearlabels(){
        for(int i = 0; i < 20; i++){
            JLabel label = new JLabel();
            if( i < 10){
                label.setBounds(20 + (70 * i), 150, 50, 100);
            }else{
                label.setBounds(20 + (70 * (i - 10)), 290, 50, 100);
            }
            label.setIcon(iconosilla_g);
            label.setVisible(true);
            this.sillas[i] = label;
            this.jPanel1.add(this.sillas[i]);
        }
    }

    public void actualizar() throws InterruptedException{
        if(this.barbero_durmiendo){
            this.jLabel3.setText("Durmiendo");
            this.jLabel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        }else{
            this.jLabel3.setText("Cortando el pelo");
            this.jLabel1.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        System.out.println("Clientes atendidos: " + String.valueOf(this.clientes_atendidos));
        System.out.println("Clientes que se van: " + String.valueOf(this.clientes_se_van));
        System.out.println("Clientes completados: " + String.valueOf(this.clientes_completados));
        System.out.println("Clientes en sala: " + String.valueOf(this.clientes_en_sala));
        this.jLabel7.setText(String.valueOf(this.clientes_atendidos));
        this.jLabel9.setText(String.valueOf(this.clientes_se_van));
        this.jLabel11.setText(String.valueOf(this.clientes_completados));
        this.jLabel13.setText(String.valueOf(this.clientes_en_sala));
        for(int i = 0; i < this.cola.cola_clientes.size(); i++){
            this.sillas[i].setIcon(this.iconosentado_l);
        }
        for(int i = this.cola.cola_clientes.size(); i < 20; i++){
            this.sillas[i].setIcon(this.iconosilla_g);
        }
        Thread.sleep(500);
        this.repaint();
    }
    
    public void actualizaretiquetacompl(){
        this.jLabel11.setText(String.valueOf(this.clientes_completados));
    }
    
    public void actualizaretiquetasevan(){
        this.jLabel9.setText(String.valueOf(this.clientes_se_van));
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
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Estado del barbero:");

        jLabel3.setText("jLabel3");

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tiempo de corte de cabello:");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel5.setText("Tiempo de llegada entre clientes:");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        jLabel6.setText("Clientes que han visitado la barberia:");

        jLabel7.setText("jLabel7");

        jLabel8.setText("Clientes que se fueron sin esperar:");

        jLabel9.setText("jLabel9");

        jLabel10.setText("Clientes que completaron su corte:");

        jLabel11.setText("jLabel11");

        jLabel12.setText("Clientes en sala de espera:");

        jLabel13.setText("jLabel13");

        jLabel14.setText("Número de clientes:");

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner3))
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jButton1)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(296, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(this.jButton1.getText().compareTo("Iniciar") == 0){
            this.numero_clientes = Integer.parseInt(this.jSpinner3.getValue().toString());
            cliente = new Cliente[this.numero_clientes];
            barbero = new BarberoD(this.cola, Integer.valueOf(this.jSpinner1.getValue().toString()));
            for(int i = 0; i < this.numero_clientes; i++){
                cliente[i] = new Cliente(this.cola, Integer.valueOf(this.jSpinner2.getValue().toString()));
            }
            this.iniciado = true;
            this.barbero.start();
            for(int i = 0; i < this.numero_clientes; i++){
                this.cliente[i].start();
            }
            this.jButton1.setText("Pausar");
            this.jSpinner3.setEnabled(false);
        }else if(this.jButton1.getText().compareTo("Pausar") == 0){
            this.barbero.pausar();
            for(int i = 0; i < this.numero_clientes; i++){
                this.cliente[i].pausar();
            }
            this.jButton1.setText("Reanudar");
        }else if(this.jButton1.getText().compareTo("Reanudar") == 0){
            this.barbero.reanudar();
            for(int i = 0; i < this.numero_clientes; i++){
                this.cliente[i].reanudar();
            }
            
            this.jButton1.setText("Pausar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Barberia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Barberia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Barberia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Barberia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Barberia().setVisible(true);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    // End of variables declaration//GEN-END:variables

}
