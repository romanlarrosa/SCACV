/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista_SCACV;

/**
 *
 * @author Marina
 */
public class Consola extends javax.swing.JPanel {

    /**
     * Creates new form Consola
     */
    public Consola() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mensajesConsola = new javax.swing.JLabel();

        setBackground(new java.awt.Color(41, 62, 69));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avisos", 0, 0, new java.awt.Font("Verdana", 0, 24), new java.awt.Color(221, 201, 180))); // NOI18N
        setFont(new java.awt.Font("Neon Pixel-7", 0, 24)); // NOI18N

        mensajesConsola.setBackground(new java.awt.Color(41, 62, 69));
        mensajesConsola.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        mensajesConsola.setForeground(new java.awt.Color(255, 255, 255));
        mensajesConsola.setText("N/A");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(mensajesConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mensajesConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel mensajesConsola;
    // End of variables declaration//GEN-END:variables
    public void repintar(String mensaje){
        if(mensaje == "")
            mensajesConsola.setText("N/A");
        else
            mensajesConsola.setText(mensaje);
    }

}
