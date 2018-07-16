/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompositeModCalculations;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel Wade
 */
public class ValueMultiplierPairTest {
    
    public ValueMultiplierPairTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getValue method, of class ValueMultiplierPair.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        ValueMultiplierPair instance = null;
        float expResult = 0.0F;
        float result = instance.getValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValue method, of class ValueMultiplierPair.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        float value = 0.0F;
        ValueMultiplierPair instance = null;
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMultiplier method, of class ValueMultiplierPair.
     */
    @Test
    public void testGetMultiplier() {
        System.out.println("getMultiplier");
        ValueMultiplierPair instance = null;
        float expResult = 0.0F;
        float result = instance.getMultiplier();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMultiplier method, of class ValueMultiplierPair.
     */
    @Test
    public void testSetMultiplier() {
        System.out.println("setMultiplier");
        float multiplier = 0.0F;
        ValueMultiplierPair instance = null;
        instance.setMultiplier(multiplier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
