/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;

/**
 *
 * @author dario
 */
public interface DataAccessObject {
  
    public ResultSet doQuery(String query) throws SQLException;
    public void doUpdate(Object dato);
}
