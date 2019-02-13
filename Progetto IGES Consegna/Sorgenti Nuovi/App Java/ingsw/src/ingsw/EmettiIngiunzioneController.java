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
 * @author dario
 */
public class EmettiIngiunzioneController {
    private final EmettiIngiunzioneForm form = new EmettiIngiunzioneForm(this);
    private final IngiunzioneDao ingiunzioneDao = new IngiunzioneDao();
    private String nProtocollo = null;
    private String idIngiunzione = null;
    public void start() {
        ResultSet listaIngiunzioni = null;
        listaIngiunzioni = ingiunzioneDao.getIngiunzioni();
        form.setVisible(true);
        try {
           
            if(listaIngiunzioni.getRow()>=0) {
                form.show(listaIngiunzioni);
            }
            else
                  JOptionPane.showMessageDialog(form,"Non ci sono ingiunzioni da emettere","Message", JOptionPane.INFORMATION_MESSAGE);
              
        }
        catch(SQLException e) {}
    }
    
    public String generaProtocollo(String idIngiunzione,String idUtenza) {
        this.idIngiunzione=idIngiunzione;
        nProtocollo="GCI-"+idUtenza+"-"+idIngiunzione;
        return nProtocollo;
    }
    
    public void pushedEmetti() {
        IngiunzioneDao ingiunzioneDao = new IngiunzioneDao();
        GeneraPdf pdf = new GeneraPdf();
        ResultSet res = null;
        try {
            
            ingiunzioneDao.setNumeroProtocollo(idIngiunzione, nProtocollo);
            res = ingiunzioneDao.getIngiunzione(idIngiunzione);
            if(res.next())
            pdf.generaPdf(idIngiunzione, res.getString(8),res.getString(1),res.getString(2),res.getString(3),res.getString(5), nProtocollo,res.getString(4));
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore nella generazione del pdf", JOptionPane.ERROR_MESSAGE);
        }    
        try {
           ingiunzioneDao.cambiaStato(idIngiunzione, "E");
           JOptionPane.showMessageDialog(form,"Ingiuzione emessa con successo e pdf generato","Message", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(form, e.getMessage(),"Errore durante l'emessione dell'ingiunzione", JOptionPane.ERROR_MESSAGE);
        } 
    }
    public void pushedIndietro() {
        form.dispose();
    }
    public ResultSet resetIngiunzioni() {
        ResultSet rst = ingiunzioneDao.getIngiunzioni();
        return rst;
    }
}
