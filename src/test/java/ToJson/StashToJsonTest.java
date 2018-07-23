/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToJson;

import java.io.FileInputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel Wade
 */
public class StashToJsonTest {
    
    public StashToJsonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of main method, of class StashToJson.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        StashToJson.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fileToInputStream method, of class StashToJson.
     */
    @Test
    public void testFileToInputStream() throws Exception {
        System.out.println("fileToInputStream");
        FileInputStream expResult = null;
        FileInputStream result = StashToJson.fileToInputStream();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
