/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Dario
 */
public class MenuController {

    private String username = "";
    private String password = "";
    private static Database db = new Database();
    private static Connection cn;
    private LoginForm login=new LoginForm(this);
    private boolean connect;
   
  

    public void startLogin() {
        login.setVisible(true);
    }
    public void setUsername(String user) {
        this.username=user;
    }
    public void setPassword(String pass) {
        this.password=pass;
    }
    public void doLogin() {
        try {
        connect = db.createConnection(username,password);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(login, e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
        }
        cn = db.getConnection();
        if(connect){
            MenuForm menuForm = new MenuForm();
            login.setVisible(false);
            menuForm.setVisible(true);
        }
    }
}
