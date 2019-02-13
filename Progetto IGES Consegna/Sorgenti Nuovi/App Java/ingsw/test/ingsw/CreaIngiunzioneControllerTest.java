/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;

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
public class CreaIngiunzioneControllerTest {
    
    public CreaIngiunzioneControllerTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Database.createConnection("Operatore", "0000");
        } catch (Exception ex) {
            Logger.getLogger(CreaIngiunzioneControllerTest.class.getName()).log(Level.SEVERE, null, ex);
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

   

 

    /**
     * Test of submit method, of class CreaIngiunzioneController.
     */
    @Test
    public void testSubmit() {
        System.out.println("TEST OF THE METHOD submit");
        String idEvasore = "";
        CreaIngiunzioneController instance = new CreaIngiunzioneController();
      
        System.out.println("TC1: POSITIVE CONCRETE VALUE ");
           try { 
                idEvasore = "9";
                instance.submit(idEvasore);
              
           }
           catch(Exception e) {
               fail("TEST CASE FAIL"+e.getMessage());
           }
        System.out.println("TC2: NEGATIVE VALUE");
            try{
                idEvasore = "-1";
                instance.submit(idEvasore);
            }
           catch(Exception e) {
               fail("TEST CASE FAIL"+e.getMessage());
           }
        System.out.println("TC3: NULL VALUE");
           try {
                idEvasore = null;
                instance.submit(idEvasore);
            }
           catch(Exception e) {
               fail("TEST CASE FAIL"+e.getMessage());
           }
        System.out.println("TC4: POSITIVE IMPOSSIBLE VALUE");
           try {
            idEvasore = "1985";
            instance.submit(idEvasore);
            }
           catch(Exception e) {
               fail("TEST CASE FAIL"+e.getMessage());
           }
       System.out.println("TC5: ANOTHER POSITIVE CONCRETE VALUE");
           try {
            idEvasore = "8";
            instance.submit(idEvasore);
            }
           catch(Exception e) {
               fail("TEST CASE FAIL"+e.getMessage());
           }
    }

   
    
}
