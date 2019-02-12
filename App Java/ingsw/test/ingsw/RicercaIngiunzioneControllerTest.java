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
 * @author dario
 */
public class RicercaIngiunzioneControllerTest {
    
    public RicercaIngiunzioneControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Database.createConnection("root", "");
        } catch (Exception ex) {
            Logger.getLogger(RicercaIngiunzioneControllerTest.class.getName()).log(Level.SEVERE, null, ex);
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
    private void testCaseEmpty(String nome, String idUtente, RicercaIngiunzioneController instance) throws Exception {
        final String cognome = "";
        final String idIngiunzione = "";
        final String indirizzo = "";
        final String citta = "";
        final String inizio = "1990-01-01";
        final String fine = "2020-12-12";
        ResultSet result = null;
        result = instance.pushedRicerca(nome, cognome, idUtente, idIngiunzione, indirizzo, citta, inizio, fine);
        try {
            assertEquals(result.next(),false);             
        }
        catch(Exception e) {throw new Exception();}
    }
    private void testCaseGianlucaMatch(String nome, String idUtente, RicercaIngiunzioneController instance) throws Exception {
        final String cognome = "";
        final String idIngiunzione = "";
        final String indirizzo = "";
        final String citta = "";
        final String inizio = "1990-01-01";
        final String fine = "2100-12-12";
        ResultSet result = null;
        result = instance.pushedRicerca(nome, cognome, idUtente, idIngiunzione, indirizzo, citta, inizio, fine);
        try {
            
            if(result.next()) {
          
                assertEquals(result.getString("idIngiunzione"),"140"); 
                assertEquals(result.getString("nome"),"Gianluca"); 
                assertEquals(result.getString("cognome"),"Sabella"); 
                assertEquals(result.getString("stato"),"E");
                
            }
            else throw new Exception();
        }
        catch(Exception e) {throw new Exception();}
    }
    /**
     * Test of pushedRicerca method, of class RicercaIngiunzioneController.
     */
    @Test
    public void testPushedRicerca() {
        System.out.println("Test pushedRicerca");
        RicercaIngiunzioneController instance = new RicercaIngiunzioneController(new MenuForm());
        ResultSet result = null;
        ResultSet expResult = null;
  
        
        // Batteria di test 
        try {
            testCaseGianlucaMatch("Gianluca","5",instance);
            testCaseEmpty("Gianluca","NN",instance);
            testCaseGianlucaMatch("Gianluca","",instance);
            testCaseEmpty("Gianluca","-1",instance);
            testCaseEmpty("7","5",instance);
            testCaseEmpty("7","NN",instance);
            testCaseEmpty("7","-1",instance);
            testCaseEmpty("7","",instance);
            testCaseGianlucaMatch("","5",instance);
            testCaseEmpty("","NN",instance);
            testCaseEmpty("","-1",instance);
            //testCaseEmpty("","",instance); // il test case non è possibile concretamente, si deve necessarimente inserire qualcosa
           
        }
        catch(Exception e) { 
            e.printStackTrace();
            fail();
        }
        System.out.println("Fine test pushedRicerca");
    }
   
  
}
