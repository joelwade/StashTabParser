/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import DependencyInjector.ParserSetup;
import com.google.inject.Guice;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.google.inject.Injector;
import com.mycompany.poe.api.parser.ApiObjects.GGGFileData;

/**
 * @author Joel Wade
 */
public class ParserTest {
    
    Injector injector;
    public ParserTest() {
        injector = Guice.createInjector(new ParserSetup());
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of getStashTabs method, of class Parser.
     */
    @Test
    public void testGetStashTabs() {
        System.out.println("getStashTabs");
        Parser instance = injector.getInstance(Parser.class);
        
        GGGFileData file = instance.getStashTabs();
        int expResult = 630;
        int result = file.stashes.length;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hasNextChangeId method, of class Parser.
     */
    @Test
    public void testHasNextChangeIdTrue() {
        System.out.println("hasNextChangeId");
        Parser instance = injector.getInstance(Parser.class);
        
        instance.getNextChangeId();
        Boolean expResult = true;
        Boolean result = instance.hasNextChangeId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of hasNextChangeId method, of class Parser.
     */
    @Test
    public void testHasNextChangeIdFalse() {
        System.out.println("hasNextChangeId");
        Parser instance = injector.getInstance(Parser.class);
        
        Boolean expResult = false;
        Boolean result = instance.hasNextChangeId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of establishConnection method, of class Parser.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        Parser instance = injector.getInstance(Parser.class);
        Boolean expResult = true;
        Boolean result = instance.establishConnection();
        assertEquals(expResult, result);
    }
    
}