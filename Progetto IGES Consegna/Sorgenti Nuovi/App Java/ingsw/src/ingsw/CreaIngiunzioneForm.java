/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Dario
 */
public class CreaIngiunzioneForm extends javax.swing.JFrame {

    CreaIngiunzioneController controller = null;
    public CreaIngiunzioneForm(CreaIngiunzioneController controller) {
        this.controller=controller;
        setResizable(false);
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

        nomeTextField = new javax.swing.JTextField();
        cognomeTextField = new javax.swing.JTextField();
        indirizzoTextField = new javax.swing.JTextField();
        codUtenzaTextField = new javax.swing.JTextField();
        codFiscaleTextField = new javax.swing.JTextField();
        importoTextField = new javax.swing.JTextField();
        cittaTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lettureList = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomeTextField.setEditable(false);
        nomeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(nomeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 140, -1));

        cognomeTextField.setEditable(false);
        getContentPane().add(cognomeTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 140, -1));

        indirizzoTextField.setEditable(false);
        getContentPane().add(indirizzoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 140, -1));

        codUtenzaTextField.setEditable(false);
        getContentPane().add(codUtenzaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 141, -1));

        codFiscaleTextField.setEditable(false);
        getContentPane().add(codFiscaleTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 141, -1));

        importoTextField.setEditable(false);
        importoTextField.setOpaque(false);
        importoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importoTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(importoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 132, -1));

        cittaTextField.setEditable(false);
        getContentPane().add(cittaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 141, -1));

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 50, 20));

        jLabel2.setText("Cognome");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel3.setText("Indirizzo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel5.setText("CodUtenza");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, -1));

        jLabel6.setText("Codice Fiscale");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        jLabel7.setText("Citta");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        lettureList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lettureList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lettureList.setEnabled(false);
        jScrollPane2.setViewportView(lettureList);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 130, 81));

        jLabel11.setText("Letture non pagate");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 130, -1));

        jButton2.setText("Crea");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 110, 40));

        jButton4.setText("Indietro");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, 110, 40));

        jLabel8.setText("Ingiunzione a carico di:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 130, -1));

        jLabel4.setText("Importo da pagare");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 130, 34));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoSoftSmall.png"))); // NOI18N
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 290, -1));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/whiteAndBlue.jpg"))); // NOI18N
        back.setLabelFor(back);
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeTextFieldActionPerformed

    private void importoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importoTextFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       pushedCrea();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        controller.pushedIndietroIngiunzione();
    }//GEN-LAST:event_jButton4ActionPerformed
    
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
            java.util.logging.Logger.getLogger(CreaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreaIngiunzioneForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
    
    }
    
    public void show(String nome,String cognome,String citta,String cf,int codUtenza,String indirizzo, ResultSet letture,double importoDaPagare) {
        this.setVisible(true);
        nomeTextField.setText(nome);
        cognomeTextField.setText(cognome);
        cittaTextField.setText(citta);
        codFiscaleTextField.setText(cf);
        String codUtenzaString = String.valueOf(codUtenza);
        codUtenzaTextField.setText(codUtenzaString);
        indirizzoTextField.setText(indirizzo);
        try {
            setList(letture);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Non è stato possibile mostrare le letture","Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        importoTextField.setText(String.valueOf(importoDaPagare));
    }
    
    private void setList(ResultSet letture) throws SQLException{
       
       DefaultListModel listModel= new DefaultListModel();
       while(letture.next()) {  
                int item = letture.getInt(1);
                listModel.addElement(item);             
            }
     
        lettureList.setModel(listModel);
    } 
    private void pushedCrea() {
        try {
        controller.pushedCrea();
        }
        catch(SQLException e) {JOptionPane.showMessageDialog(this, "Non è stato creare l'ingiunzione","Errore", JOptionPane.ERROR_MESSAGE);}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JTextField cittaTextField;
    private javax.swing.JTextField codFiscaleTextField;
    private javax.swing.JTextField codUtenzaTextField;
    private javax.swing.JTextField cognomeTextField;
    private javax.swing.JTextField importoTextField;
    private javax.swing.JTextField indirizzoTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lettureList;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField nomeTextField;
    // End of variables declaration//GEN-END:variables
}
