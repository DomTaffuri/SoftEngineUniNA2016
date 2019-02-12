/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;


/**
 *
 * @author Dario
 */
public class Database {
   private static final String connectionString = "jdbc:mysql://localhost:3306/db_ingsw?";
   
    private static Connection connection = null;
    public Database() {
        /* Caricamento della libreria mysql.jdbc */
        try {
             Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean createConnection(String username, String password)throws Exception {
        boolean connect = true;
        try {
            connection = DriverManager.getConnection(connectionString,username,password);
           
        } 
        catch (SQLException e) {
            connect= false;
            e.printStackTrace();
            throw new Exception("Username e/o password errati");
            
         }
        return connect;
    }
    
    public static Connection getConnection() {
        return connection;
    }
}
