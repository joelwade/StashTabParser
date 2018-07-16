/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompositeModCalculations;

import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel Wade
 */
public class CompCalcAlgorithmTest {
    
    public CompCalcAlgorithmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of calcCompMods method, of class CompCalcAlgorithm.
     */
//    @Test
//    public void testCalcCompMods() {
//        System.out.println("calcCompMods");
//        ArrayList<Tuple> itemMods = null;
//        CompCalcAlgorithm instance = null;
//        instance.calcCompMods(itemMods);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of calcIndividualCompMod method, of class CompCalcAlgorithm.
     * Calculates "(total) +## to maximum Life" from two item mods:
     * 45 max life and 30 strength
     */
    @Test
    public void testCalcIndividualCompModMaxLife() throws IOException {
        //Uses two rolls, 45 max life and 30 strength.
        System.out.println("testCalcIndividualCompModMaxLife");
        ArrayList<Tuple> itemMods = new ArrayList<>();
        itemMods.add(new Tuple("+## to maximum Life", 45));
        itemMods.add(new Tuple("+## to Strength", 30));
        
        Mod[] mods = {new Mod("+## to maximum Life", 1), 
            new Mod("+## to Strength", (float) .5),
            new Mod("+## to all Attributes", (float) .5),
            new Mod("+## to Strength and Dexterity", (float) .5),
            new Mod("+## to Strength and Intelligence", (float) .5)};
        CompositeCalculations calcs = new CompositeCalculations().addCalc("(Total) +## to maximum Life", mods);
        
        CompCalcAlgorithm instance = new CompCalcAlgorithm(calcs, null);
        
        float expResult = (float) 60.0;
        Tuple result = instance.calcIndividualCompMod(itemMods, mods, "(Total) +## to maximum Life");
        
        assertEquals(expResult, (float)result.getValue(),0);
    }
    
    /**
     * Test of calcIndividualCompMod method, of class CompCalcAlgorithm.
     * Calculates "(total) +## to maximum Life" from two item mods:
     * 45 max life and 30 strength
     */
    @Test
    public void testCalcIndividualCompModElementalDamage() throws IOException {
        //Uses two rolls, 45 max life and 30 strength.
        System.out.println("testCalcIndividualCompModElementalDamage");
        ArrayList<Tuple> itemMods = new ArrayList<>();
        itemMods.add(new Tuple("Adds ## to ## Fire Damage to Attacks", 45));
        itemMods.add(new Tuple("Adds ## to ## Cold Damage to Attacks", 45));
        itemMods.add(new Tuple("Adds ## to ## Lightning Damage to Attacks", 30));
        
        Map.Entry<String, ModUses> mapEntry = null;
        Map<String, ModUses> mods2 = ModListCreator.getModMap("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json");
        for (Map.Entry<String, ModUses> entry : mods2.entrySet()){
            if (entry.getKey().equals("(Total) +## to maximum Life")){
                mapEntry = entry;
                break;
            }
        }
        
        CompositeCalculations calcs = new CompositeCalculations().addCalc("(Total) +## to maximum Life", mapEntry);
        
        CompCalcAlgorithm instance = new CompCalcAlgorithm(calcs, null);
        
        float expResult = (float) 60.0;
        Tuple result = instance.calcIndividualCompMod(itemMods, mapEntry, "(Total) +## to maximum Life");
        
        assertEquals(expResult, (float)result.getValue(),0);
    }
    
    
    
}