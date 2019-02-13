/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Serena
 */
public class IngiunzioneDaoTest {
     private final String idIngiunzioneVal = "140";
     private final String nomeVal = "Gianluca";
     private final String cognomeVal = "Sabella";
     private final String statoVal = "E";
    public IngiunzioneDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Database.createConnection("root", "");
        } catch (Exception ex) {
            Logger.getLogger(IngiunzioneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

      private void testCaseEmpty(String nome, String idUtente, IngiunzioneDao instance) throws Exception {
        final String cognome = "";
        final String idIngiunzione = "";
        final String indirizzo = "";
        final String citta = "";
        final String inizio = "1990-01-01";
        final String fine = "2020-12-12";
        ResultSet result = null;
        result = instance.getIngiunzioni(nome, cognome, idUtente, idIngiunzione, indirizzo, citta, inizio, fine);
        try {
            assertEquals(result.next(),false);             
        }
        catch(Exception e) {throw new Exception();}
    }
    private void testCaseMatch(String nome, String idUtente, IngiunzioneDao instance) throws Exception {
        final String cognome = "";
        final String idIngiunzione = "";
        final String indirizzo = "";
        final String citta = "";
        final String inizio = "1990-01-01";
        final String fine = "2100-12-12";
       
        ResultSet result = null;
        result = instance.getIngiunzioni(nome, cognome, idUtente, idIngiunzione, indirizzo, citta, inizio, fine);
        try {
            
            if(result.next()) {
          
                assertEquals(result.getString("idIngiunzione"),idIngiunzioneVal); 
                assertEquals(result.getString( "nome"),nomeVal); 
                assertEquals(result.getString("cognome"),cognomeVal); 
                assertEquals(result.getString("stato"),statoVal);
                
            }
            else throw new Exception();
        }
        catch(Exception e) {throw new Exception();}
    }
    
    

    /**
     * Test of getIngiunzioni method, of class IngiunzioneDao.
     */
    @Test
    public void testGetIngiunzioni_8args() throws Exception {
       System.out.println("Test getIngiunzioni");
        IngiunzioneDao instance = new IngiunzioneDao();
       
        // Batteria di test 
        try {
            testCaseMatch("Gianluca","5",instance);
            testCaseEmpty("Gianluca","NN",instance);
            testCaseMatch("Gianluca","",instance);
            testCaseEmpty("Gianluca","-1",instance);
            testCaseEmpty("7","5",instance);
            testCaseEmpty("7","NN",instance);
            testCaseEmpty("7","-1",instance);
            testCaseEmpty("7","",instance);
            testCaseEmpty("NN","5",instance);
            testCaseEmpty("NN","NN",instance);
            testCaseEmpty("NN","",instance);
            testCaseEmpty("NN","-1",instance);
            testCaseMatch("","5",instance);
            testCaseEmpty("","NN",instance);
            testCaseEmpty("","-1",instance);
//            testCaseEmpty("","",instance); // il test case non è possibile concretamente, si deve necessarimente inserire qualcosa
           
        }
        catch(Exception e) { 
            e.printStackTrace();
            fail();
        }
        System.out.println("Fine test getIngiunzioni");
    
    }

}