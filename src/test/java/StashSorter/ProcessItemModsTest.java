/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StashSorter;

import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel Wade
 */
public class ProcessItemModsTest {
    
    public ProcessItemModsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of processMods method, of class ProcessItemMods.
     * Testing flat damage Abyss jewel mods.
     */
    /**
     * Tests calculating total added cold to spells for abyss jewel mod: 
     * "Adds 13 to 21 Cold Damage to Spells while holding a Shield"
     * Damage is in Tuple(Key, Value) form, and should equal
     * Tuple("Adds xx to xx Cold Damage to Spells while holding a Shield", 17)
     */
    @Test
    public void testProcessMods_FlatColdDamageWithShieldsKey() {
        System.out.println("testProcessMods_FlatColdDamageWithShieldsKey");
        String[] s = new String[1];
        s[0] = "Adds 13 to 21 Cold Damage to Spells while holding a Shield";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Adds ## to ## Cold Damage to Spells while holding a Shield", 17));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_FlatColdDamageWithShieldsValue() {
        System.out.println("testProcessMods_FlatColdDamageWithShieldsValue");
        String[] s = new String[1];
        s[0] = "Adds 13 to 21 Cold Damage to Spells while holding a Shield";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Adds ## to ## Cold Damage to Spells while holding a Shield", 17));
        ArrayList<Tuple> result = instance.processMods(s);
        
        double d = Double.parseDouble(result.get(0).getValue().toString());
        assertEquals(Double.parseDouble(expResult.get(0).getValue().toString()), d, 0);
    }
    
    @Test
    public void testProcessMods_FlatLightningDamageWithBowsKey() {
        System.out.println("testProcessMods_FlatLightningDamageWithBowsKey");
        String[] s = new String[1];
        s[0] = "Adds 5 to 50 Lightning Damage to Bow Attacks";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Adds ## to ## Lightning Damage to Bow Attacks", 27.5));
        ArrayList<Tuple> result = instance.processMods(s);
        
        //The tuple is stored as a object, so it must be converted to a float, and then to an int for comparison.
        assertEquals(expResult.get(0).getKey(),  result.get(0).getKey());
    }

    @Test
    public void testProcessMods_FlatLightningDamageWithBowsValue() {
        System.out.println("testProcessMods_FlatLightningDamageWithBowsValue");
        String[] s = new String[1];
        s[0] = "Adds 5 to 50 Lightning Damage to Bow Attacks";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Adds ## to ## Lightning Damage to Bow Attacks", 27.5));
        ArrayList<Tuple> result = instance.processMods(s);
        
        //Convert float to double
        double d = Double.parseDouble(result.get(0).getValue().toString());
        assertEquals(expResult.get(0).getValue(), d);
    }
    
    /**
     * Testing % and + mods.
     */
    
    @Test
    public void testProcessMods_PercentIASKey() {
        System.out.println("testProcessMods_PercentIASKey");
        String[] s = new String[1];
        s[0] = "7% increased Attack Speed if you've dealt a Critical Strike Recently";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("##% increased Attack Speed if you've dealt a Critical Strike Recently", 7));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_PercentIASValue() {
        System.out.println("testProcessMods_PercentIASValue");
        String[] s = new String[1];
        s[0] = "7% increased Attack Speed if you've dealt a Critical Strike Recently";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("##% increased Attack Speed if you've dealt a Critical Strike Recently", 7));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue().toString(), result.get(0).getValue());
    }
    
    @Test
    public void testProcessMods_HeadhunterDamageKey() {
        System.out.println("testProcessMods_HeadhunterDamageKey");
        String[] s = new String[1];
        s[0] = "30% increased Damage with Hits against Rare monsters";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("##% increased Damage with Hits against Rare monsters", 30));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_HeadhunterDamageValue() {
        System.out.println("testProcessMods_HeadhunterDamageValue");
        String[] s = new String[1];
        s[0] = "30% increased Damage with Hits against Rare monsters";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("##% increased Damage with Hits against Rare monsters", 30));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue().toString(), result.get(0).getValue());
    }
    
    @Test
    public void testProcessMods_ColdResistanceKey() {
        System.out.println("testProcessMods_ColdResistanceKey");
        String[] s = new String[1];
        s[0] = "+28% to Cold Resistance";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("+##% to Cold Resistance", 28));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_ColdResistanceValue() {
        System.out.println("testProcessMods_ColdResistanceValue");
        String[] s = new String[1];
        s[0] = "+28% to Cold Resistance";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("+##% to Cold Resistance", 28));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue().toString(), result.get(0).getValue());
    }
    
    @Test
    public void testProcessMods_MaximumLifeKey() {
        System.out.println("testProcessMods_MaximumLifeKey");
        String[] s = new String[1];
        s[0] = "+62 to maximum Life";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("+## to maximum Life", 62));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_MaximumLifeValue() {
        System.out.println("testProcessMods_MaximumLifeValue");
        String[] s = new String[1];
        s[0] = "+62 to maximum Life";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("+## to maximum Life", 62));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue(),Integer.parseInt(result.get(0).getValue().toString()) );
    }
    
    @Test
    public void testProcessMods_SocketedSpellsPlusCritKey() {
        System.out.println("testProcessMods_SocketedSpellsPlusCritKey");
        String[] s = new String[1];
        s[0] = "Socketed Spells have +2% to Critical Strike Chance";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Socketed Spells have +##% to Critical Strike Chance", 2));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(),result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_SocketedSpellsPlusCritValue() {
        System.out.println("testProcessMods_SocketedSpellsPlusCritValue");
        String[] s = new String[1];
        s[0] = "Socketed Spells have +2% to Critical Strike Chance";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Socketed Spells have +##% to Critical Strike Chance", 2));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(),result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_SpellsPlusCritKey() {
        System.out.println("testProcessMods_SpellsPlusCritKey");
        String[] s = new String[1];
        s[0] = "Spells have +0.79% to Critical Strike Chance";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Spells have +##% to Critical Strike Chance", 0.79));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(),result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_SpellsPlusCritValue() {
        System.out.println("testProcessMods_SpellsPlusCritValue");
        String[] s = new String[1];
        s[0] = "Spells have +0.79% to Critical Strike Chance";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Spells have +##% to Critical Strike Chance", 0.79));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue().toString(), result.get(0).getValue());
    }
    
    /**
     * Testing mods that aren't flat, % or +.
     */
    
    @Test
    public void testProcessMods_DyingSunFlatProjectileKey() {
        System.out.println("testProcessMods_DyingSunFlatProjectileKey");
        String[] s = new String[1];
        s[0] = "Skills fire 2 additional Projectiles during Flask Effect";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Skills fire ## additional Projectiles during Flask Effect", 2));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_DyingSunFlatProjectileValue() {
        System.out.println("testProcessMods_DyingSunFlatProjectileValue");
        String[] s = new String[1];
        s[0] = "Skills fire 2 additional Projectiles during Flask Effect";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Skills fire ## additional Projectiles during Flask Effect", 2));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue().toString(), result.get(0).getValue());
    }
    
    @Test
    public void testProcessMods_TornadoShotHelmProjectilesKey() {
        System.out.println("testProcessMods_TornadoShotHelmProjectilesKey");
        String[] s = new String[1];
        s[0] = "Tornado Shot fires 2 additional secondary Projectiles";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Tornado Shot fires ## additional secondary Projectiles", 2));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_TornadoShotHelmProjectilesValue() {
        System.out.println("testProcessMods_TornadoShotHelmProjectilesValue");
        String[] s = new String[1];
        s[0] = "Tornado Shot fires 2 additional secondary Projectiles";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Tornado Shot fires ## additional secondary Projectiles", 2));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_SocketedGemsKey() {
        System.out.println("testProcessMods_SocketedGemsKey");
        String[] s = new String[1];
        s[0] = "Socketed Gems are Supported by Level 20 Remote Mine";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Socketed Gems are Supported by Level ## Remote Mine", 20));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getKey(), result.get(0).getKey());
    }
    
    @Test
    public void testProcessMods_SocketedGemsValue() {
        System.out.println("testProcessMods_SocketedGemsKey");
        String[] s = new String[1];
        s[0] = "Socketed Gems are Supported by Level 20 Remote Mine";
        
        ProcessItemMods instance = new ProcessItemMods();
        ArrayList<Tuple> expResult = new ArrayList<>();
        expResult.add(new Tuple("Socketed Gems are Supported by Level ## Remote Mine", 20));
        ArrayList<Tuple> result = instance.processMods(s);
        
        assertEquals(expResult.get(0).getValue().toString(), result.get(0).getValue());
    }
    
}