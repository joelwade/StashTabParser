/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompositeModCalculations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
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
        
        Map<String, ModUses> mods2 = ModListCreator.getModMap("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json");
        
        CompositeCalculation compCalc = new CompositeCalculation("(Total) +## to maximum Life", mods);
        Map<String, Mod[]> compCalcMap = new HashMap<>();
        
        
        CompCalcAlgorithm instance = new CompCalcAlgorithm(compCalcMap, mods2);
        CompositeCalculation compp = new CompositeCalculation("(Total) +## to maximum Life", mods);
        
        float expResult = (float) 60.0;
        Tuple result = instance.calcIndividualCompMod(itemMods, compp);
        
        
        assertEquals(expResult, (float)result.getValue(),0);
    }
    
    @Test
    public void testCalcIndividualCompModMaxLife2() throws IOException {
        //Uses two rolls, 45 max life and 30 strength.
        System.out.println("testCalcIndividualCompModMaxLife2");
        ArrayList<Tuple> itemMods = new ArrayList<>();
        itemMods.add(new Tuple("+## to maximum Life", 45));
        itemMods.add(new Tuple("+## to Strength", 30));
        
        Map<String, ModUses> mods2 = ModListCreator.getModMap("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json");
        
        String ss = inputstreamToString(fileToInputStream("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json"));
        Map<String, Mod[]> compCalcMap = stringToMap(ss);
        
        CompCalcAlgorithm instance = new CompCalcAlgorithm(compCalcMap, mods2);
        
        float expResult = (float) 60.0;
        ArrayList<Tuple> result1 = instance.calcCompMods(itemMods);
        
        float result = (float)0.0;
        
        for (Tuple t: result1){
            if (t.getKey().equals("(Total) +## to maximum Life")){
                result = (float)t.getValue();
            }
        }
        assertEquals(expResult, result,0);
    }
    
    //Takes file path of comp calcs, normally compositeCalculationsFilePath, and returns input stream.
    private static FileInputStream fileToInputStream(String filePath) throws FileNotFoundException{
        String path = new File(filePath).getAbsolutePath();

        return new FileInputStream(path);
    }
    
    //takes input stream, typically from fileToInputStream, and returns a string.
    private static String inputstreamToString(FileInputStream inputStream) throws IOException{
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
    }
    
    //Takes string, typically from inputstreamToString, and returns a Map.
    private static Map<String, Mod[]> stringToMap(String s){
        Gson gson = new GsonBuilder().setLenient().create();
        
        Type stringStringMap = new TypeToken<Map<String, Mod[]>>(){}.getType();
        Map<String, Mod[]> map = gson.fromJson(s, stringStringMap);

        return map;
    }
    
}