/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_SCACV;

import Controlador_SCACV.EstadoPalanca;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Marina
 */
public class PalancaCambios extends javax.swing.JPanel {

    public boolean flag_apagar = false;
    public boolean flag_acelerar = false;
    public boolean flag_mantener = false;
    public boolean flag_reiniciar = false;
    private ArrayList<ImageIcon> imagenes;
    
    public PalancaCambios() {
        initComponents();
        imagenes = new ArrayList<ImageIcon>();
        
        imagenes.add(new ImageIcon(getClass().getResource("/images/palanca+.png")));
        imagenes.add(new ImageIcon(getClass().getResource("/images/palancaM.png")));
        imagenes.add(new ImageIcon(getClass().getResource("/images/palancaR.png")));
        imagenes.add(new ImageIcon(getClass().getResource("/images/palancaA.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonAcelerar = new javax.swing.JButton();
        botonMantener = new javax.swing.JButton();
        botonReiniciar = new javax.swing.JButton();
        botonApagar = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();

        setBackground(new java.awt.Color(41, 62, 69));
        setPreferredSize(new java.awt.Dimension(350, 306));

        botonAcelerar.setBackground(new java.awt.Color(221, 201, 180));
        botonAcelerar.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        botonAcelerar.setForeground(new java.awt.Color(41, 62, 69));
        botonAcelerar.setText("ACELERAR");
        botonAcelerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAcelerarActionPerformed(evt);
            }
        });

        botonMantener.setBackground(new java.awt.Color(221, 201, 180));
        botonMantener.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        botonMantener.setForeground(new java.awt.Color(41, 62, 69));
        botonMantener.setText("MANTENER");
        botonMantener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMantenerActionPerformed(evt);
            }
        });

        botonReiniciar.setBackground(new java.awt.Color(221, 201, 180));
        botonReiniciar.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        botonReiniciar.setForeground(new java.awt.Color(41, 62, 69));
        botonReiniciar.setText("REINICIAR");
        botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReiniciarActionPerformed(evt);
            }
        });

        botonApagar.setBackground(new java.awt.Color(221, 201, 180));
        botonApagar.setFont(new java.awt.Font("Verdana", 1, 13)); // NOI18N
        botonApagar.setForeground(new java.awt.Color(41, 62, 69));
        botonApagar.setText("APAGAR");
        botonApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonApagarActionPerformed(evt);
            }
        });

        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/palancaA.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(botonReiniciar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonMantener, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(botonAcelerar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imagen))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(botonAcelerar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(botonMantener, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(botonReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(botonApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonApagarActionPerformed
        flag_apagar = true;
        flag_acelerar = false;
        flag_mantener = false;
        flag_reiniciar = false;
    }//GEN-LAST:event_botonApagarActionPerformed

    private void botonAcelerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAcelerarActionPerformed
        flag_apagar = false;
        flag_acelerar = true;
        flag_mantener = false;
        flag_reiniciar = false;
    }//GEN-LAST:event_botonAcelerarActionPerformed

    private void botonMantenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMantenerActionPerformed
        flag_apagar = false;
        flag_acelerar = false;
        flag_mantener = true;
        flag_reiniciar = false;
    }//GEN-LAST:event_botonMantenerActionPerformed

    private void botonReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReiniciarActionPerformed
        flag_apagar = false;
        flag_acelerar = false;
        flag_mantener = false;
        flag_reiniciar = true;
    }//GEN-LAST:event_botonReiniciarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAcelerar;
    private javax.swing.JButton botonApagar;
    private javax.swing.JButton botonMantener;
    private javax.swing.JButton botonReiniciar;
    private javax.swing.JLabel imagen;
    // End of variables declaration//GEN-END:variables
    
    public void repintar(EstadoPalanca estado){
        switch(estado){
            case ACELERAR:
                imagen.setIcon(imagenes.get(0));
            break;
            case MANTENER:
                imagen.setIcon(imagenes.get(1));
            break;
            case REINICIAR:
                imagen.setIcon(imagenes.get(2));
            break;
            case APAGADO:
                imagen.setIcon(imagenes.get(3));
            break;
        }
    }





}
