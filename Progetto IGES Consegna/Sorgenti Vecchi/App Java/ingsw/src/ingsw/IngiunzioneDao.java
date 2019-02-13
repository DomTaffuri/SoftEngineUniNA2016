/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
/**
 *
 * @author Dario
 */
public class IngiunzioneDao implements DataAccessObject  {
    
    private Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rst=null;
    public IngiunzioneDao(){
        cn=Database.getConnection();
        
    }


    public void deleteIngiunzione(int idUtenza) throws SQLException {
         try {
        String query = ("DELETE FROM ingiunzione WHERE idUtenza=?");
    
         pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         pst.setInt(1,idUtenza);
         pst.executeUpdate();
         }
        catch(SQLException e){
            System.out.println("Error");
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
    }
   
    public void create(double importo,String stato, String nProtocollo, double mora, double mcTotale, String dataUltimaModifica,int idUtenza)throws SQLException {       
       try {
        deleteIngiunzione(idUtenza);
        String query = ("INSERT INTO ingiunzione (importo,stato,nProtocollo,mora,mcTotale,dataUltimaModifica,idUtenza) VALUES(?,?,?,?,?,?,?)");
               
         pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         pst.setDouble(1,importo);
         pst.setString(2,stato);
         pst.setString(3, nProtocollo);
         pst.setDouble(4, mora);
         pst.setDouble(5,mcTotale);
         java.util.Date date = Calendar.getInstance().getTime();
         DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         String today = formatter.format(date);
         pst.setString(6,today );
         pst.setInt(7,idUtenza);
   
         pst.executeUpdate();
         
        }
        catch(SQLException e){
            
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
      
    }
    @Override
    public void doUpdate(Object i) {
        
    }
    
    private void setPstQueryGetIngiunzioni(String query,String dato, int indice) throws QueryExcep {
        try {
            
             if(!dato.isEmpty()) 
                pst.setString(indice,dato);
            else 
                pst.setString(indice, ".*");

        }
        catch(SQLException e) {
            throw new QueryExcep("Errore ricerca\n");
        }
    }
    
    public ResultSet getIngiunzioni(String nome,String cognome,String idUtente,String idIngiunzione,String indirizzo,String citta,String inizio,String fine) throws SQLException {
        ResultSet res = null;
        String query = "SELECT ingiunzione.idIngiunzione, utenza.nome, utenza.cognome, stato FROM utenza JOIN ingiunzione ON utenza.idIngiunzione=ingiunzione.idIngiunzione WHERE nome REGEXP ? AND cognome REGEXP ? AND utenza.idUtenza REGEXP ? AND ingiunzione.idIngiunzione REGEXP ? AND indirizzo REGEXP ? AND citta REGEXP ? AND ingiunzione.dataUltimaModifica>= ? AND ingiunzione.dataUltimaModifica<= ?";
        try {
            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);    
            setPstQueryGetIngiunzioni(query,nome,1);
            setPstQueryGetIngiunzioni(query,cognome,2);
            setPstQueryGetIngiunzioni(query,idUtente,3);
            setPstQueryGetIngiunzioni(query,idIngiunzione,4);
            setPstQueryGetIngiunzioni(query,indirizzo,5);
            setPstQueryGetIngiunzioni(query,citta,6);
            if(!inizio.isEmpty()) 
               pst.setString(7,inizio);
            else
                pst.setString(7, "1990-01-01");      
             if(!fine.isEmpty())
                pst.setString(8,fine);
            else
                pst.setString(8, "2100-01-01");       
            res=pst.executeQuery();        
        }
        catch(SQLException e) {
            throw new QueryExcep("Errore ricerca\n");
        }  
        return res;
    }
    public ResultSet getIngiunzioni() {
        ResultSet res = null;
        String query = ("SELECT idIngiunzione,idUtenza,importo FROM ingiunzione WHERE stato='NE'");
        try {
            res=doQuery(query);
        }
        catch (SQLException e) {
            
        }
        return res;
    }
    public ResultSet getIngiunzione(String idIngiunzione) throws SQLException {
        ResultSet res=null;
       
        String query = "SELECT utenza.nome,utenza.cognome,ingiunzione.importo,ingiunzione.mcTotale,ingiunzione.mora,utenza.citta,utenza.cF,utenza.idUtenza,utenza.indirizzo,ingiunzione.nProtocollo FROM ingiunzione JOIN utenza ON ingiunzione.idUtenza=utenza.idUtenza WHERE ingiunzione.idIngiunzione=?";
        
        try {
     
            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, idIngiunzione);
            res=pst.executeQuery();
        
        }
        catch (SQLException e) {
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
        return res;
    }
    @Override
    public ResultSet doQuery(String query) throws SQLException {
        try { 

         pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
         
         rst=pst.executeQuery();
        }
        catch(SQLException e){
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
        return rst;
    }
    
    public void setNumeroProtocollo(String idIngiunzione,String nProtocollo) throws SQLException {
        String query = "UPDATE ingiunzione SET nProtocollo = ? WHERE idIngiunzione = ?";

        try {

            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1,nProtocollo);
            pst.setString(2,idIngiunzione);
            pst.executeUpdate();
        }
        catch(SQLException e) {
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
    }
    
    public void cambiaStato(String idIngiunzione,String stato) throws SQLException {
        String query = "UPDATE ingiunzione SET stato = ? WHERE idIngiunzione = ?";
        try {
 
            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1,stato);
            pst.setString(2,idIngiunzione);
            pst.executeUpdate();
        }
        catch(SQLException e) {
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
    }
    
    public void cambiaLettura(String idUtenza,String lettura) throws SQLException {
        String query = "UPDATE ingiunzione SET mcTotale = ? WHERE idUtenza = ?";
        try {
 
            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1,lettura);
            pst.setString(2,idUtenza);
            pst.executeUpdate();
        }
        catch(SQLException e) {
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
    }
    
    public void cambiaMora(String idUtenza,String mora) throws SQLException {
        String query = "UPDATE ingiunzione SET mora = ? WHERE idUtenza = ?";
        try {
     
            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1,mora);
            pst.setString(2,idUtenza);
            pst.executeUpdate();
        }
        catch(SQLException e) {
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
    }
    
    public void cambiaImporto(String idUtenza,Double importo) throws SQLException {
        String query = "UPDATE ingiunzione SET importo = ? WHERE idUtenza = ?";
        try {
      
            pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setDouble(1,importo);
            pst.setString(2,idUtenza);
            pst.executeUpdate();
        }
        catch(SQLException e) {
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
    }
}
