/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Dario
 */
public class CreaIngiunzioneController {
    private final SelezionaEvasoreForm form = new SelezionaEvasoreForm(this);
    private final IngiunzioneDao ingiunzioneDao = new IngiunzioneDao();
    private final UtenzaDao evasoreDao = new UtenzaDao();
    private final LettureDao lettureDao = new LettureDao();
    private CreaIngiunzioneForm creaForm = null;
   
    private double importoDaPagare = 0.0;
    private int idUtenza = 0;
    private int codContatore = 0;
    private String nome = null;
    private String cognome = null;
    private String citta = null;
    private String indirizzo = null;
    private String codiceFiscale = null;
    private int mcTotale = 0;
    public void start() {
        form.setVisible(true);
    }
    // Effettua la query che ricerca tutti gli evasori nel db
    // mostra un messaggio di errore se la query non va a buon fine
    // dice al form di settare la tabella degli evasori
    public void sendDatoInserito(String dato) {
        ResultSet rst= null;
        try {
            rst = evasoreDao.getLitaEvasori(dato);
        }
        catch(SQLException e) {
           JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(form,"Problema fatale: contattare amministratore","Errore", JOptionPane.ERROR_MESSAGE);
           form.setVisible(false);
        }
      // se la query non è andata a buon fine rst sarà uguale a null
      // quindi solo se è diverso da null la tabella del form deve essere settata
       if(rst!=null)
       form.setEvasori(rst);
       else
       form.setEvasori(null);
    }
 
    public void submit(String idEvasore) {
       form.setVisible(false);
       try {
       ResultSet evasore;    
       evasore = evasoreDao.getEvasore(idEvasore);
       
       creaForm = new CreaIngiunzioneForm(this);
       if(evasore.next())
        idUtenza=evasore.getInt("idUtenza");
       
       ResultSet letture = lettureDao.getLetture(idUtenza);
       
       calcolaImporto(letture);
       
       letture.beforeFirst();

        nome=evasore.getString("nome");
        cognome=evasore.getString("cognome");
        citta=evasore.getString("citta");
        codiceFiscale=evasore.getString("cF");   
        indirizzo=evasore.getString("indirizzo");
       
       
       creaForm.show(nome,cognome,citta,codiceFiscale,idUtenza,indirizzo,letture,importoDaPagare);
       
       }
       catch (SQLException e) {
           JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
           JOptionPane.showMessageDialog(form,"Problema fatale: contattare amministratore","Errore", JOptionPane.ERROR_MESSAGE);
           form.setVisible(false);
       }
    }
    
    private void calcolaImporto(ResultSet letture) throws SQLException {

        try {    
            while(letture.next()) {  

                   mcTotale=mcTotale+letture.getInt(1);
                          
            }    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(form,"Non è stato possibile cacolare l'importo","Errore", JOptionPane.ERROR_MESSAGE);
        }
        importoDaPagare = importoDaPagare+0.892*mcTotale; 
    }
    public void pushedCrea() throws SQLException {
        
        try {
            System.out.println("creo ingiunzione");
            ingiunzioneDao.create(importoDaPagare,"NE",null,0,mcTotale,null,idUtenza);
            System.out.println("ingiunzione creata");
        }
        catch(SQLException e) {
             throw new QueryExcep("Eorre durante aggiornamento database\n");
        }
        JOptionPane.showMessageDialog(form,"Ingiunzione creata con successo","Message", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void pushedIndietroEvasore() {
        form.dispose();
    }
    
    public void pushedIndietroIngiunzione() {
        form.setVisible(true);
        creaForm.dispose();
    }
}
        