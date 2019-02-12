/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author dario
 */
public class ModificaIngiunzioneController {
    private final ModificaIngiunzioneForm form = new ModificaIngiunzioneForm(this);
    private String idIngiunzione;
    private final IngiunzioneDao ingiunzioneDao = new IngiunzioneDao();
    private String nome,cognome,indirizzo,citta,cf,importo,mcTotale,mora,idUtenza;
    private Double nuovoImporto=0.0;
    private final RicercaIngiunzioneForm lastForm;
    public ModificaIngiunzioneController(RicercaIngiunzioneForm lastForm) {
        this.lastForm=lastForm;
    }
    
    public void start(String idIngiunzione) {
        form.setVisible(true);
        this.idIngiunzione=idIngiunzione;
        try {
            
            ResultSet datiIng=ingiunzioneDao.getIngiunzione(idIngiunzione);
     
            estraiDati(datiIng);
        }
        catch(SQLException e) {
           JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(form,"Problema fatale: contattare amministratore","Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void estraiDati(ResultSet rst) {
     try {
       ResultSetMetaData meta = rst.getMetaData();
      
       if(rst.next()) {
           nome=rst.getString(1);
           cognome=rst.getString(2);
           importo=rst.getString(3);
           mcTotale=rst.getString(4);
           mora=rst.getString(5);
           citta=rst.getString(6);
           cf=rst.getString(7);
           idUtenza=rst.getString(8);
           indirizzo=rst.getString(9);
 
           form.show(nome,cognome,importo,mcTotale,mora,citta,cf,idUtenza,indirizzo);
        }
       
      }
      catch(SQLException e){         
           JOptionPane.showMessageDialog(form,"Errore durante caricamento della pagina","Errore", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public Double getImporto(String lettura,String mora) {
        nuovoImporto = -1.0;
        mcTotale=lettura; this.mora=mora;
        if(lettura.length()<=0 || mora.length()<=0) {
            JOptionPane.showMessageDialog(form,"Non hai inserito valori di lettura e/o mora validi","Errore", JOptionPane.ERROR_MESSAGE);
            return nuovoImporto;
        }
        String regexMora = "[0-9]*";
        if(Integer.parseInt(lettura)<0 || !Pattern.matches(regexMora,mora)) {
            JOptionPane.showMessageDialog(form,"Non hai inserito valori di lettura e/o mora validi","Errore", JOptionPane.ERROR_MESSAGE);
            return nuovoImporto;
        }
        nuovoImporto = 0.892*(Double.parseDouble(lettura))+(Double.parseDouble(mora));
        return nuovoImporto;
    }
    
    public void pushedModifica() {
        try {
            ingiunzioneDao.cambiaImporto(idUtenza, nuovoImporto);
            ingiunzioneDao.cambiaLettura(idUtenza, mcTotale);
            ingiunzioneDao.cambiaMora(idUtenza,mora);
            JOptionPane.showMessageDialog(form,"Salvataggio modifica avvenuto con successo","Message", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(ModificaIngiunzioneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void pushedIndietro() {
        form.dispose();
        lastForm.setVisible(true);
    }
}
