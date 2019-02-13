/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dario
 */
public class ModificaIngiunzioneForm extends javax.swing.JFrame {
    private final ModificaIngiunzioneController controller;
    private Double importo;
    /**
     * Creates new form ModificaIngiunzioneForm
     */
    public ModificaIngiunzioneForm(ModificaIngiunzioneController c) {
        setResizable(false);
        initComponents();
        
        controller=c;
    }
    
    public void show(String nome,String cognome,String importo,String mcTotale,String mora,String citta,String cf, String idUtenza,String indirizzo) {
       
        nomeTextField.setText(nome);
        cognomeTextField.setText(cognome);
        importoTextField.setText(importo);
        letturaTextField.setText(mcTotale);
        moraTextField.setText(mora);
        cittaTextField.setText(citta);
        cfTextField.setText(cf);
        codUtenzaTextField.setText(idUtenza);
        indirizzoTextField.setText(indirizzo);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeTextField = new javax.swing.JTextField();
        cognomeTextField = new javax.swing.JTextField();
        indirizzoTextField = new javax.swing.JTextField();
        codUtenzaTextField = new javax.swing.JTextField();
        cfTextField = new javax.swing.JTextField();
        cittaTextField = new javax.swing.JTextField();
        letturaTextField = new javax.swing.JTextField();
        importoTextField = new javax.swing.JTextField();
        moraTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        modificaButton = new javax.swing.JButton();
        indietroButton = new javax.swing.JButton();
        importoButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomeTextField.setEnabled(false);
        nomeTextField.setFocusable(false);
        getContentPane().add(nomeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 200, 34));

        cognomeTextField.setEnabled(false);
        cognomeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cognomeTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(cognomeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 200, 34));

        indirizzoTextField.setEnabled(false);
        getContentPane().add(indirizzoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 200, 34));

        codUtenzaTextField.setEnabled(false);
        getContentPane().add(codUtenzaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 200, 34));

        cfTextField.setEnabled(false);
        getContentPane().add(cfTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, 200, 34));

        cittaTextField.setEnabled(false);
        getContentPane().add(cittaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 200, 34));

        letturaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                letturaTextFieldActionPerformed(evt);
            }
        });
        letturaTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                letturaTextFieldKeyPressed(evt);
            }
        });
        getContentPane().add(letturaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 200, 34));

        importoTextField.setEnabled(false);
        importoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importoTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(importoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, 200, 34));

        moraTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moraTextFieldActionPerformed(evt);
            }
        });
        moraTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                moraTextFieldKeyPressed(evt);
            }
        });
        getContentPane().add(moraTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 100, 34));

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 50, 34));

        jLabel2.setText("Cognome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 170, 60, 20));

        jLabel3.setText("Indirizzo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 60, 34));

        jLabel4.setText("Lettura totale");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 80, 34));

        jLabel5.setText("Mora");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 30, -1));

        jLabel6.setText("CF");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 20, -1));

        jLabel7.setText("CodUtenza");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 70, 22));

        jLabel8.setText("Città");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 30, 10));

        jLabel9.setText("Importo da pagare");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, -1, -1));

        modificaButton.setText("Salva Modifiche");
        modificaButton.setEnabled(false);
        modificaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaButtonActionPerformed(evt);
            }
        });
        getContentPane().add(modificaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 160, 50));

        indietroButton.setText("Indietro");
        indietroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indietroButtonActionPerformed(evt);
            }
        });
        getContentPane().add(indietroButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 160, 50));

        importoButton.setText("Calcola importo");
        importoButton.setEnabled(false);
        importoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(importoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 200, 34));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoSoftSmall.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 90));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/whiteAndBlue.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 720, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void letturaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_letturaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_letturaTextFieldActionPerformed

    private void importoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importoTextFieldActionPerformed

    private void moraTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moraTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moraTextFieldActionPerformed

    private void cognomeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cognomeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cognomeTextFieldActionPerformed

    private void letturaTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_letturaTextFieldKeyPressed
          importoButton.setEnabled(true);
    }//GEN-LAST:event_letturaTextFieldKeyPressed

    private void moraTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_moraTextFieldKeyPressed
        importoButton.setEnabled(true);
    }//GEN-LAST:event_moraTextFieldKeyPressed

    private void importoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importoButtonActionPerformed
       importo= controller.getImporto(letturaTextField.getText(),moraTextField.getText());
       if(importo>=0)
        setImporto();
    }//GEN-LAST:event_importoButtonActionPerformed

    private void modificaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaButtonActionPerformed
        controller.pushedModifica();
        modificaButton.setEnabled(false);
    }//GEN-LAST:event_modificaButtonActionPerformed

    private void indietroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indietroButtonActionPerformed
        controller.pushedIndietro();
    }//GEN-LAST:event_indietroButtonActionPerformed
    public void setImporto() {
       importoTextField.setText(""+importo+"");
       importoButton.setEnabled(false);
       modificaButton.setEnabled(true);
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
            java.util.logging.Logger.getLogger(ModificaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cfTextField;
    private javax.swing.JTextField cittaTextField;
    private javax.swing.JTextField codUtenzaTextField;
    private javax.swing.JTextField cognomeTextField;
    private javax.swing.JButton importoButton;
    private javax.swing.JTextField importoTextField;
    private javax.swing.JButton indietroButton;
    private javax.swing.JTextField indirizzoTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField letturaTextField;
    private javax.swing.JButton modificaButton;
    private javax.swing.JTextField moraTextField;
    private javax.swing.JTextField nomeTextField;
    // End of variables declaration//GEN-END:variables
}