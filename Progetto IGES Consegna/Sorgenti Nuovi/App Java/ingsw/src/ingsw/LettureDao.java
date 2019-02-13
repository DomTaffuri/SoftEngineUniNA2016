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
public class LettureDao implements DataAccessObject{
    private Connection cn;
    private static PreparedStatement pst;
    private static ResultSet rst=null;
    public LettureDao() {
        cn=Database.getConnection();
    }
    public ResultSet doQuery(String query, Object dato) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void doUpdate(Object dato) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
     public ResultSet getLetture(int codUtenza) throws SQLException{
        try {
        String codUtenzas= String.valueOf(codUtenza);
        String query = ("SELECT mc FROM lettura WHERE idUtenza = ?");
        
         pst=cn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         pst.setString(1, codUtenzas);
         
         rst=pst.executeQuery();
        }
        catch(SQLException e){
            throw new QueryExcep("Eorre durante l'interrogazione del database\n");
        }
        return rst;
    }

    @Override
    public ResultSet doQuery(String query) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
