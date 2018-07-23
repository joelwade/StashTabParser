/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StashSorter;

import DependencyInjector.ParserSetup;
import Parser.Parser;
import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.poe.api.parser.ApiObjects.GGGFileData;
import com.mycompany.poe.api.parser.ApiObjects.Item;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel Wade
 */

public class StashSorterTest {
    
    ArrayList<Item> items;
    public StashSorterTest() throws IOException {
        //Get stashSorterTestJson.json, convert to object.
        
        
        Parser instance = new Parser(null);
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString(fileToInputStream()));
        
        Injector injector = Guice.createInjector(new ParserSetup());
        
        StashSorter ss = new StashSorter(file.stashes[0],
                Utils.GetObjectsFromJson.getModList("src\\main\\java\\CompositeModCalculations\\ModList.json"),
                Utils.GetObjectsFromJson.getCompCalcsMap("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json"));
        items = ss.getProcessedItems();
    }
    
    /**
     * Test file has items for:
     * Weapons with both physical and multiple elemental damage rolls.
     * Armour, evasion and energy shield items.
     * Single currency items. 1/20 chance orb.
     * A full stack of currency. 20/20 jewellers orbs.
     * Amulet, Talisman t2, ring.
     * Unique items.
     * Stack of 3/5 div cards.
     * Stack of 5/5 div cards.
     * Stack of 1/5 div cards.
     * Stack of 25/5 div cards.
     * A t14 Crimson Temple map, 0% quality.
     * A t14 Crimson Temple map, 14% quality.
     * 6 Gems, one with 11% quality(Spark), one level 12(Leap slam), 
     * one level 16 and half leveled, and the rest are level 1 0% quality.
     * Helmet with 90% bladefall crit enchant.
     */
    public FileInputStream fileToInputStream() throws FileNotFoundException{
        return new FileInputStream("src\\test\\java\\StashSorter\\stashSorterTestJson.json");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testNothing(){
        assertNull(null);
    }
    
    public Tuple findItemStat(ArrayList<Tuple> stats, String key){
        for(Tuple t: stats){
            if (t.getKey().equals(key)){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Test of setStash method, of class StashSorter.
     */
    @Test
    public void testPainBaneElementalDamage(){
        System.out.println("testPainBaneElementalDamage");
        final float totalEleDamage = 12;
        //pain bane has 4 min and 12 max dmg, and 1.5 aps, which = 12 dps. 
        float totalEleDamageFromParser = 0;
        for (Item i: items){
            if (i.name.equals("Pain Bane")){
                for (Tuple p: i.calculatedTotalValues){
                    if (p.getKey().equals("Total Elemental Damage to Attacks")){
                        totalEleDamageFromParser = (Float)p.getValue();
                    }
                }
            }
        }
        
        assertEquals((int)totalEleDamage, (int)totalEleDamageFromParser);
    }
    
    @Test
    public void testPainBaneTotallDamage(){
        System.out.println("testPainBaneTotallDamage");
        final float totalDamage = 25;
        //pain bane has 9 min and 25 max dmg, and 1.5 aps, which = 25.5 dps. 
        float totalDamageFromParser = 0;
        for (Item i: items){
            if (i.name.equals("Pain Bane") || i.name.equals("Pain Bane")){
                for (Tuple p: i.calculatedTotalValues){
                    if (p.getKey().equals("Total Damage to Attacks")){
                        totalDamageFromParser = (Float)p.getValue();
                    }
                }
            }
        }
        
        assertEquals((int)totalDamage, (int)totalDamageFromParser);
    }
    
    @Test
    public void testMaliciousClawTotalDamage(){
        System.out.println("testMaliciousClawTotalDamage");
        final float totalDamage = 187;
        //pain bane has 74 min and 176 max dmg, and 1.5 aps, which = 187.5 dps. 
        float totalDamageFromParser = 0;
        for (Item i: items){
            if (i.typeLine.equals("Malicious Gemini Claw")){
                for (Tuple p: i.calculatedTotalValues){
                    if (p.getKey().equals("Total Damage to Attacks")){
                        totalDamageFromParser = (Float)p.getValue();
                    }
                }
            }
        }
        
        assertEquals((int)totalDamage, (int)totalDamageFromParser);
    }
    
    @Test
    public void testMaliciousClawChaosDamage(){
        System.out.println("testMaliciousClawChaosDamage");
        final float totalDamage = 119;
        //pain bane has 51 min and 108 max dmg, and 1.5 aps, which = 119.25 dps. 
        float totalDamageFromParser = 0;
        for (Item i: items){
            if (i.typeLine.equals("Malicious Gemini Claw") || i.typeLine.equals("Malicious Gemini Claw")){
                for (Tuple p: i.calculatedTotalValues){
                    if (p.getKey().equals("Total Chaos Damage to Attacks")){
                        totalDamageFromParser = (Float)p.getValue();
                    }
                }
            }
        }
        
        assertEquals((int)totalDamage, (int)totalDamageFromParser);
    }
    
    @Test
    public void testLeapSlamExp(){
        System.out.println("testLeapSlamExp");
        final float totalExp = 100;
        float expFromParser = 0;
        for (Item i: items){
            if (i.typeLine.equals("Leap Slam")){
                expFromParser = (int)findItemStat(i.itemStats, "currentLvlPercent").getValue();
            }
        }
        
        assertEquals((int)totalExp, (int)expFromParser);
    }
    
    @Test
    public void testPurityOfFireExp(){
        System.out.println("testPurityOfFireExp");
        final float totalExp = 50;
        float expFromParser = 0;
        for (Item i: items){
            if (i.typeLine.equals("Purity of Fire")){
                expFromParser = (int)findItemStat(i.itemStats, "currentLvlPercent").getValue();
            }
        }
        
        assertEquals((int)totalExp, (int)expFromParser);
    }
    
    @Test
    public void testPurtiyOfFireLevel(){
        System.out.println("testPurtiyOfFireLevel");
        final int expectedLevel = 16;
        int levelFromParser = 0;
        for (Item i : items){
            if (i.typeLine.equals("Purity of Fire")){
                levelFromParser = (int)findItemStat(i.itemStats, "currentLevel").getValue();
            }
        }
    }
    
    @Test
    public void testPurtiyOfFireLevelRequired(){
        System.out.println("testPurtiyOfFireLevel");
        final int expectedLevelRequired = 16;
        int levelRequiredFromParser = 0;
        for (Item i : items){
            if (i.typeLine.equals("Purity of Fire")){
                levelRequiredFromParser = (int)findItemStat(i.itemStats, "requiredLevel").getValue();
            }
        }
    }
    
    @Test
    public void testPurtiyOfFireStrengthRequired(){
        System.out.println("testPurtiyOfFireStrengthRequired");
        final int expectedStrRequired = 87;
        int strRequiredFromParser = 0;
        for (Item i : items){
            if (i.typeLine.equals("Purity of Fire")){
                strRequiredFromParser = (int)findItemStat(i.itemStats, "requiredStr").getValue();
            }
        }
    }
    
    @Test
    public void testPurtiyOfFireIntelligenceRequired(){
        System.out.println("testPurtiyOfFireIntelligenceRequired");
        final int expectedIntRequired = 60;
        int intRequiredFromParser = 0;
        for (Item i : items){
            if (i.typeLine.equals("Purity of Fire")){
                intRequiredFromParser = (int)findItemStat(i.itemStats, "requiredInt").getValue();
            }
        }
    }
    
    @Test
    public void testImmortalCallExp(){
        System.out.println("testImmortalCallExp");
        final float totalExp = 0;
        float expFromParser = 0;
        for (Item i: items){
            if (i.typeLine.equals("Immortal Call")){
                expFromParser = (int)findItemStat(i.itemStats, "currentLvlPercent").getValue();
            }
        }
        
        assertEquals((int)totalExp, (int)expFromParser);
    }
    
    @Test
    public void testSparkQuality(){
        System.out.println("testSparkQuality");
        final int totalQuality = 11;
        int qualityFromParser = 0;
        for (Item i: items){
            if (i.typeLine.equals("Spark")){
                qualityFromParser = i.quality;
            }
        }
        
        assertEquals(totalQuality, qualityFromParser);
    }
    
    @Test
    public void testRothsReachQuality(){
        System.out.println("testRothsReachQuality");
        final int totalQuality = 17;
        int qualityFromParser = 0;
        for (Item i: items){
            if (i.name.equals("Roth's Reach")){
                qualityFromParser = i.quality;
            }
        }
        
        assertEquals(totalQuality, qualityFromParser);
    }
    
    @Test
    public void testDarkRayQuality(){
        System.out.println("testDarkRayQuality");
        final int totalQuality = 0;
        int qualityFromParser = 15;
        for (Item i: items){
            if (i.name.equals("Darkray Vectors")){
                qualityFromParser = i.quality;
            }
        }
        
        assertEquals(totalQuality, qualityFromParser);
    }
    
    @Test
    public void testBroodWrapQuality(){
        System.out.println("testBroodWrapQuality");
        final int totalQuality = 20;
        int qualityFromParser = 0;
        for (Item i: items){
            if ( i.typeLine.equals("Cutthroat's Garb")){
                qualityFromParser = i.quality;
            }
        }
        
        assertEquals(totalQuality, qualityFromParser);
    }
    
    //Tests composite mod (Total) +##% to Resistances
    @Test
    public void testDoomDashTotalResistances(){
        System.out.println("testDoomDashTotalResistances");
        final int totalRes = 51;
        float resFromParser = 0;
        for (Item i: items){
            if ( i.name.equals("Doom Dash")){
                for (Tuple t : i.calculatedTotalValues){
                    if (t.getKey().equals("(Total) +##% to Resistances")){
                        resFromParser = (float) t.getValue();
                        break;
                    }
                }
            }
        }
        
        assertEquals(totalRes, resFromParser,0);
    }
    
    //Tests composite mod (Total) +##% to Resistances
    @Test
    public void testDoomDashTotalResistances2(){
        System.out.println("testDoomDashTotalResistances2");
        final int totalRes = 28;
        float resFromParser = 0;
        for (Item i: items){
            if ( i.name.equals("Doom Dash")){
                for (Tuple t : i.calculatedTotalValues){
                    if (t.getKey().equals("(Total) +##% to Elemental Resistances")){
                        resFromParser = (float) t.getValue();
                        break;
                    }
                }
            }
        }
        
        assertEquals(totalRes, resFromParser,0);
    }
    
    //Doom Dash
    @Test
    public void testDoomDashSocketCount(){
        System.out.println("testDoomDashSocketCount");
        final int socketCount = 4;
        float socketCountFromParser = 0;
        for (Item i: items){
            if ( i.name.equals("Doom Dash")){
                socketCountFromParser = i.socketCount;
            }
        }
        
        assertEquals(socketCount, socketCountFromParser,0);
    }
    
    //Doom Dash
    @Test
    public void testDoomDashSocketColours(){
        System.out.println("testDoomDashSocketColours");
        int[] socketColoursFromParser = {0,0,0,0};
        int[] socketColours = {3,1,0,0};
        for (Item i: items){
            if ( i.name.equals("Doom Dash")){
                socketColoursFromParser[0] = i.redSocketCount;
                socketColoursFromParser[1] = i.greenSocketCount;
                socketColoursFromParser[2] = i.blueSocketCount;
                socketColoursFromParser[3] = i.whiteSocketCount;
            }
        }
        
        assertArrayEquals(socketColours, socketColoursFromParser);
    }
    
    //Pandemonium Needle6tgg
    @Test
    public void testPandemoniumNeedleSocketColours(){
        System.out.println("testPandemoniumNeedleSocketColours");
        int[] socketColoursFromParser = {0,0,0,0};
        int[] socketColours = {1,2,0,0};
        for (Item i: items){
            if ( i.name.equals("Pandemonium Needle6tgg")){
                socketColoursFromParser[0] = i.redSocketCount;
                socketColoursFromParser[1] = i.greenSocketCount;
                socketColoursFromParser[2] = i.blueSocketCount;
                socketColoursFromParser[3] = i.whiteSocketCount;
            }
        }
        
        assertArrayEquals(socketColours, socketColoursFromParser);
    }
    
    //Pandemonium Needle6tgg
    @Test
    public void testPandemoniumNeedleSocketCount(){
        System.out.println("testPandemoniumNeedleSocketCount");
        int socketCountFromParser = 3;
        int socketCount = 0;
        for (Item i: items){
            if ( i.name.equals("Pandemonium Needle6tgg")){
                socketCount = i.socketCount;
            }
        }
        
        assertEquals(socketCount, socketCountFromParser);
    }
    
    //Pandemonium Needle6tgg
    @Test
    public void testPandemoniumNeedleMaxLinks(){
        System.out.println("testPandemoniumNeedleMaxLinks");
        int maxLinksFromParser = 2;
        int maxLinks = 0;
        for (Item i: items){
            if ( i.name.equals("Pandemonium Needle6tgg")){
                maxLinks = i.maxLinks;
            }
        }
        
        assertEquals(maxLinks, maxLinksFromParser);
    }
    
    @Test
    public void testTabulaRasaMaxLinks(){
        System.out.println("testTabulaRasaMaxLinks");
        int maxLinksFromParser = 6;
        int maxLinks = 0;
        for (Item i: items){
            if ( i.name.equals("Tabula Rasa")){
                maxLinks = i.maxLinks;
            }
        }
        
        assertEquals(maxLinks, maxLinksFromParser);
    }
    
    @Test
    public void testTabulaRasaMaxSockets(){
        System.out.println("testTabulaRasaMaxSockets");
        int socketCountFromParser = 6;
        int socketCount = 0;
        for (Item i: items){
            if ( i.name.equals("Tabula Rasa")){
                socketCount = i.socketCount;
            }
        }
        
        assertEquals(socketCount, socketCountFromParser);
    }
    
    @Test
    public void testTabulaRasaSocketColours(){
        System.out.println("testTabulaRasaSocketColours");
        int[] socketColoursFromParser = {0,0,0,0};
        int[] socketColours = {0,0,0,6};
        for (Item i: items){
            if ( i.name.equals("Tabula Rasa")){
                socketColoursFromParser[0] = i.redSocketCount;
                socketColoursFromParser[1] = i.greenSocketCount;
                socketColoursFromParser[2] = i.blueSocketCount;
                socketColoursFromParser[3] = i.whiteSocketCount;
            }
        }
        
        assertArrayEquals(socketColours, socketColoursFromParser);
    }
}