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
public class UtenzaDao implements DataAccessObject {
    private Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rst=null;
    public UtenzaDao() {
        cn=Database.getConnection();
    }
   
    public ResultSet doQuery(String query, Object dato) throws SQLException {
          try {
             
        
         pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         for(int i = 1; i<=12; i++ )
            pst.setObject(i, dato);
         
         rst=pst.executeQuery();
        }
        catch(SQLException e){
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
        return rst;
    }

    @Override
    public void doUpdate(Object dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
      public ResultSet getLitaEvasori(Object dato) throws SQLException{
         ResultSet rst=null;
      
      String query = ("SELECT DISTINCT(utenza.idUtenza),nome,cognome, citta, indirizzo, cf FROM utenza JOIN lettura ON utenza.idUtenza = lettura.idUtenza WHERE lettura.idIngiunzione IS NULL AND statoBolletta = 'NP' AND (instr(utenza.idUtenza,?)>0 OR instr(?,utenza.idUtenza)>0 OR instr(cognome,?)>0 OR instr(?,cognome)>0 OR instr(nome,?)>0 OR instr(?,nome)>0 OR instr(citta,?)>0 OR instr(?,citta)>0 OR instr(indirizzo,?)>0 OR instr(?,indirizzo)>0 OR instr(cF,?)>0 OR instr(?,cF)>0)");
       try {
        rst = doQuery(query,dato);
       }
       catch (SQLException e) {
            throw new SQLException();
       }
       return rst;
    }
    
    // Metodo che viene invocato quando l'operatore digita avanti nel form "Seleziona Evasore"
    // istanzia evasore attraverso il metodo get evasore del dao
    // segnala eventuali problemi
      
    public ResultSet getEvasore(String id) throws SQLException{
       
        
       try {
       
        String query = ("SELECT * FROM utenza WHERE idUtenza=?");
        pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
       
        pst.setString(1,id);
        rst=pst.executeQuery();

        
       }
       catch (SQLException e){
             throw new QueryExcep("Eorre: problema nel prelevare i dati dell'evasore\n");     
       }
     return rst; 
    }

    @Override
    public ResultSet doQuery(String query) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
