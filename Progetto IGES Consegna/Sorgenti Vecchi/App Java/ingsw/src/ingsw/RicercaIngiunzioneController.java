/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.*;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author dario
 */
public class RicercaIngiunzioneController {
    private final IngiunzioneDao ingiunzioneDao = new IngiunzioneDao();
    private final UtenzaDao evasoreDao = new UtenzaDao();
    private final LettureDao lettureDao = new LettureDao();
    private final RicercaIngiunzioneForm form = new RicercaIngiunzioneForm(this);
    private String nome;
    private String cognome;
    private String idUtente;
    private String indirizzo;
    private String citta;
    private String inizio;
    private String fine;
    private String idIngiunzione;
    private final MenuForm lastForm;
    private ResultSet lastSearch;
    public RicercaIngiunzioneController(MenuForm lastForm) {
        this.lastForm=lastForm;
    }
    
    public void start() {
        form.setVisible(true);
    }
    
    public ResultSet pushedRicerca(String nome,String cognome,String idUtente,String idIngiunzione,String indirizzo,String citta,String inizio,String fine) {
      /*  this.nome=nome; this.cognome=cognome; this.idUtente=idUtente;
        this.idIngiunzione=idIngiunzione; this.indirizzo=indirizzo; this.citta=citta;
        this.inizio=inizio; this.fine=fine;*/
        ResultSet ingiunzioni = null;
        try {
            ingiunzioni = ingiunzioneDao.getIngiunzioni(nome, cognome, idUtente, idIngiunzione, indirizzo, citta, inizio, fine);
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(form,"Non è stato possibile effettuare la ricerca","Error", JOptionPane.ERROR_MESSAGE);
        }
        lastSearch=ingiunzioni;
        return ingiunzioni;
    }
    
    public void pushedSospendi(String id) {
        try {
           ingiunzioneDao.cambiaStato(id, "S");
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(form,"Non è stato possibile aggioranre l'ingiunzione","Error", JOptionPane.ERROR_MESSAGE);
        }
     
    }
    public void pushedCancella(String id) {
         try {
           ingiunzioneDao.cambiaStato(id, "C");
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(form,"Non è stato possibile aggioranre l'ingiunzione","Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            form.setIngiunzioni(ingiunzioneDao.getIngiunzioni("","","",id,"","","",""));
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(form,"Non è stato possibile aggioranre l'ingiunzione","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void pushedModifica(String id) {
        ModificaIngiunzioneController c = new ModificaIngiunzioneController(form);
        form.setVisible(false);
        c.start(id);
    }
    
      public void pushedIndietro() {
        form.dispose();
        lastForm.setVisible(true);
    }
      
    public void pushedPDF(String idIngiunzione) {
        GeneraPdf pdf = new GeneraPdf();
        ResultSet res = null;
        try {
        
            res = ingiunzioneDao.getIngiunzione(idIngiunzione);
            if(res.next()) {
                pdf.generaPdf(idIngiunzione, res.getString(8),res.getString(1),res.getString(2),res.getString(3),res.getString(5),res.getString(10),res.getString(4));
                JOptionPane.showMessageDialog(form, "Pdf generato con successo","Message", JOptionPane.INFORMATION_MESSAGE);
            }    
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore nella generazione del pdf", JOptionPane.ERROR_MESSAGE);
        }
    }
      
}
