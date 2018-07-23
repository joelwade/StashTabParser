/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StashSorter;

import CompositeModCalculations.CompCalcAlgorithm;
import CompositeModCalculations.Mod;
import CompositeModCalculations.ModUses;
import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import com.mycompany.poe.api.parser.ApiObjects.Stash;
import com.mycompany.poe.api.parser.ApiObjects.Item;
import com.mycompany.poe.api.parser.ApiObjects.Properties;
import com.mycompany.poe.api.parser.ApiObjects.Sockets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
/**
 *
 * @author Joel Wade
 */
public class StashSorter {
    
    private Stash stashIn;
    
    Map<String, Mod[]> compCalcs;
    Map<String, ModUses> modList;

    public StashSorter(Stash stashIn, Map<String, ModUses> modList, Map<String, Mod[]> compCalcs) {
        this.stashIn = stashIn;
        this.compCalcs = compCalcs;
        this.modList = modList;
    }
    
    public void setStash(Stash s){
        this.stashIn = s;
    }
    
    private Item processItem(Item i){
        i.stashId = stashIn.stashId;
        i.accountName = stashIn.accountName;
        i.lastCharacterName = stashIn.lastCharacterName;
        
        calculateNextLvlRequirements(i);
        calculateRequirements(i);
        //Calc alt art bool.
        
        if (i.name.contains("<<set:MS>>")){
            i.name = i.name.substring(28);
        }
        
        if (i.typeLine.contains("<<set:MS>>")){
            i.typeLine = i.typeLine.substring(28);
        }

        if (i.category.accessories != null){
            i = processItemMods(i);
            i.categoryType = "accessories";
            i.categorySpecific = i.category.accessories[0];
        } else if (i.category.armour != null){
            i = processItemMods(i);
            i.categoryType = "armour";
            if (i.category.armour[0] != null){
                i.categorySpecific = i.category.armour[0];
            }
            i = processArmour(i);
        } else if (i.category.jewels != null){
            i = processItemMods(i);
            i.categoryType = "jewels";
        } else if (i.category.weapons != null){
            i = processItemMods(i);
            i.categoryType = "weapons";
            i.categorySpecific = i.category.weapons[0];
            i = processWeapon(i);
        } else if (i.category.cards != null){
            i.categoryType = "cards";
        } else if (i.category.flasks != null){
            i.categoryType = "flasks";
        } else if (i.category.maps != null){
            i.categoryType = "maps";
        } else if (i.category.gems != null){
            i.categoryType = "gems";
            i.categorySpecific = i.category.gems[0];
            i = processGem(i);
        }
        
        if (i.sockets != null){
            i = processSockets(i);
        }
        
        return i;
    }
    
    private Item processItemMods(Item i){
        /**
         * Process mods for all 5 lists of mods:
         *  public String[] enchantMods;
         *  public String[] implicitMods;
         *  public String[] explicitMods;
         *  public String[] craftedMods;
         *  public String[] utilityMods;
         * 
         */
        //need to add the processed mods to i.implAndExplMods
        
        ProcessItemMods modProcessor = new ProcessItemMods();
        i.implAndExplMods = new ArrayList<>();
        i.implAndExplMods.addAll(modProcessor.processMods(i.enchantMods));
        i.implAndExplMods.addAll(modProcessor.processMods(i.implicitMods));
        i.implAndExplMods.addAll(modProcessor.processMods(i.explicitMods));
        i.implAndExplMods.addAll(modProcessor.processMods(i.craftedMods));
        i.implAndExplMods.addAll(modProcessor.processMods(i.utilityMods));
        
        CompCalcAlgorithm compCalc = new CompCalcAlgorithm(compCalcs, modList);
        if (i.calculatedTotalValues == null){
            i.calculatedTotalValues = compCalc.calcCompMods(i.implAndExplMods);
        } else {
            i.calculatedTotalValues.addAll(compCalc.calcCompMods(i.implAndExplMods));
        }
        
        
        return i;
    }
    
//    private enum itemType {
//        WEAPON, ARMOUR, JEWEL, ABYSS_JEWEL
//    }
    
    private Item processWeapon(Item i){
        if (i.properties == null){
            return i;
        }
        //calc different dps
        float totalPhysDmg = 0;
        float totalEleDmg = 0;
        float totalFireDmg = 0;
        float totalColdDmg = 0;
        float totalLightningDmg = 0;
        float totalChaosDmg = 0;
        for (Properties a : i.properties){
            switch (a.name) {
                case "Critical Strike Chance":
                    String tempCrit = a.values[0][0].toString();
                    i.critChance = Float.parseFloat(tempCrit.substring(0, tempCrit.length()-1));
                    break;
                case "Attacks per Second":
                    String tempAps = a.values[0][0].toString();
                    i.aps = Float.parseFloat(tempAps);
                    break;
                case "Physical Damage":
                    totalPhysDmg = getTotalDamage(a.values[0][0].toString());
                    break;
                case "Elemental Damage":
                    //Need to add up all ele dmg
                    for(int j = 0; j < a.values.length; j++){
                        int dmg = getTotalDamage(a.values[j][0].toString());
                        //Calc fire, cold and lightning dmg.
                        if (a.values[j][1].toString().equals("4.0")) { // fire dmg
                            totalFireDmg = dmg;
                        } else if (a.values[j][1].toString().equals("5.0")) { // cold dmg
                            totalColdDmg = dmg;
                        } else if (a.values[j][1].toString().equals("6.0")) { // lightning dmg
                            totalLightningDmg = dmg;
                        }
                    }   
                    break;
                case "Chaos Damage":
                    totalChaosDmg = getTotalDamage(a.values[0][0].toString());
                    break;
                case "Weapon Range":
                    i.weaponRange = Integer.parseInt(a.values[0][0].toString());
                    break;
                case "Quality":
                    i.quality = getQuality(a.values[0][0]);
                    break;
                default:
                    break;
            }
        }
        if (i.calculatedTotalValues == null){
            i.calculatedTotalValues = new ArrayList<>();
        }
        
        totalPhysDmg = totalPhysDmg/2 * i.aps;
        totalEleDmg = totalFireDmg + totalColdDmg + totalLightningDmg;
        totalEleDmg = totalEleDmg/2 * i.aps;
        totalFireDmg = totalFireDmg/2 * i.aps;
        totalColdDmg = totalColdDmg/2 * i.aps;
        totalLightningDmg = totalLightningDmg/2 * i.aps;
        totalChaosDmg = totalChaosDmg/2 * i.aps;
        
        if (totalEleDmg != 0){
            i.calculatedTotalValues.add(new Tuple("Total Elemental Damage to Attacks", totalEleDmg));
            i.calculatedTotalValues.add(new Tuple("Total Elemental Damage to Attacks", totalEleDmg));
            if (totalFireDmg != 0){
                i.calculatedTotalValues.add(new Tuple("Total Fire Damage to Attacks", totalFireDmg));
            } else if (totalColdDmg != 0){
                i.calculatedTotalValues.add(new Tuple("Total Cold Damage to Attacks", totalColdDmg));
            } else if (totalLightningDmg != 0){
                i.calculatedTotalValues.add(new Tuple("Total Lightning Damage to Attacks", totalLightningDmg));
            }
        }
        
        if (totalPhysDmg != 0){
            i.calculatedTotalValues.add(new Tuple("Total Physical Damage to Attacks", totalPhysDmg));
        }
        
        if (totalChaosDmg != 0){
            i.calculatedTotalValues.add(new Tuple("Total Chaos Damage to Attacks", totalChaosDmg));
        }
        
        i.calculatedTotalValues.add(new Tuple("Total Damage to Attacks", totalEleDmg + totalPhysDmg + totalChaosDmg));
        
        //Still need to calc with 20q if non corrupted.
        return i;
    }
    
    private Item processArmour(Item i){
        if (i.properties == null){
            return i;
        }
        for (Properties a: i.properties){
            switch (a.name) {
                case "Armour":
                    i.armour = Integer.parseInt(a.values[0][0].toString());
                    break;
                case "Evasion Rating":
                    i.evasion = Integer.parseInt(a.values[0][0].toString());
                    break;
                case "Energy Shield":
                    i.energyShield = Integer.parseInt(a.values[0][0].toString());
                    break;
                case "Quality":
                    i.quality = getQuality(a.values[0][0]);
                    break;
                default:
                    break;
            }
        }
        //Calculate with 20 quality.
        
        //Enchanted mod.
        //append to file if not already there.
        //add to item.
//        if (i.enchantMods != null){
//            addEnchantToFile(i);
//            //If enchant is "adds xx-yy" then work out avg.
//            i.enchantedMod = new Tuple("0", i.enchantMods[0]);
//        }
        
        return i;
    }
    
//    private void addEnchantToFile(Item i){
//        File file = new File("src\\main\\java\\Testing\\enchants.txt");
//        try {
//            Scanner scanner = new Scanner(file);
//            //now read the file line by line...
//            int lineNum = 0;
//            boolean add = true;
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                lineNum++;
//                if(line.equals( i.enchantMods[0] )) { 
//                    add = false;
//                }
//            }
//            if (add){
//                try {
//                    Files.write(Paths.get("src\\main\\java\\Testing\\enchants.txt"), "\n".getBytes(), StandardOpenOption.APPEND);
//                    Files.write(Paths.get("src\\main\\java\\Testing\\enchants.txt"), i.enchantMods[0].getBytes(), StandardOpenOption.APPEND);
//                }catch (IOException e) {
//                    System.out.println("Error 1 in stashSorter, method addEnchantToFile.");
//                }
//            }
//        } catch(FileNotFoundException e) { 
//            System.out.println("Error 2 in stashSorter, method addEnchantToFile.");
//        }
//
//    }
    
    private Item processGem(Item i){
        //Work out % way thru level
        if (i.itemStats == null){
            i.itemStats = new ArrayList<>();
        }
        float t = i.additionalProperties[0].progress;
        for (Properties p : i.additionalProperties){
            if (p.name.equals("Experience")){
                i.itemStats.add(new Tuple("currentLvlPercent", (int) calculatePercent(p.values[0][0].toString())));
            }
        }
        
        //Work out current level.
        for (Properties a : i.properties){
            if (a.name.equals("Level")){
                i.itemStats.add(new Tuple("currentLevel", Integer.parseInt(a.values[0][0].toString())));
            } else if (a.name.equals("Quality")) {
                i.quality = getQuality(a.values[0][0]);
            }
        }
        
        return i;
    }
    
    /**
     * Used to populate the following variables in item:
     * socketCount
     * maxLinks
     * redSocketCount;
     * greenSocketCount;
     * blueSocketCount;
     * whiteSocketCount
     * 
     * @param i
     * @return i
     */
    private Item processSockets(Item i){
        i.socketCount = i.sockets.length;
        
        int[] socketGroup = new int[6];
        i.redSocketCount = 0;
        i.greenSocketCount = 0;
        i.blueSocketCount = 0;
        i.whiteSocketCount = 0;
        i.socketCount = 0;
        
        for (Sockets s: i.sockets){
            if (s.sColour.equals("R")){
                i.redSocketCount++;
            } else if (s.sColour.equals("G")){
                i.greenSocketCount++;
            } else if (s.sColour.equals("B")){
                i.blueSocketCount++;
            } else if (s.sColour.equals("W")){
                i.whiteSocketCount++;
            }
            i.socketCount++;
            socketGroup[s.group] ++;
        }
        
        i.maxLinks = Arrays.stream(socketGroup).max().getAsInt();
        
        return i;
    }
    
    //Total = min + max roll. E.g. total of 13-55 = 68.
    private int getTotalDamage(String rolls){
        String[] parts = rolls.split("-");
        return (Integer.parseInt(parts[0])) + (Integer.parseInt(parts[1]));
    }
    
    private int getQuality(Object s){
        return Integer.parseInt(s.toString().substring(1, 3));
    }
    
    //Input will be in the form current/total required e.g. 7680380/15206031 or 9569/9569 or 1/285815.
    private float calculatePercent(String s){
        String numbers[] = s.split("/");
        return Float.parseFloat(numbers[0]) / Float.parseFloat(numbers[1]) *100;
    }
    
    private Item calculateRequirements(Item i){
        if (i.requirements == null) {
            return i;
        }
        if (i.itemStats == null){
            i.itemStats = new ArrayList<>();
        }
        for (Properties a: i.requirements){
            switch (a.name) {
                case "Level":
                    i.itemStats.add(new Tuple("requiredLevel", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                case "Str":
                    i.itemStats.add(new Tuple("requiredStr", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                case "Dex":
                    i.itemStats.add(new Tuple("requiredDex", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                case "Int":
                    i.itemStats.add(new Tuple("requiredInt", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                default:
                    break;
            }
        }
//        i.requirements = null;
        return i;
    }
    
    private Item calculateNextLvlRequirements(Item i){
        if (i.nextLevelRequirements == null) {
            return i;
        }
        if (i.itemStats == null){
            i.itemStats = new ArrayList<>();
        }
        for (Properties a : i.nextLevelRequirements){
            switch (a.name) {
                case "Level":
                    i.itemStats.add(new Tuple("nextLvlRequired", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                case "Str":
                    i.itemStats.add(new Tuple("nextLvlRequiredStr", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                case "Dex":
                    i.itemStats.add(new Tuple("nextLvlRequiredDex", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                case "Int":
                    i.itemStats.add(new Tuple("nextLvlRequiredInt", Integer.parseInt(a.values[0][0].toString()) ));
                    break;
                default:
                    break;
            }
        }
        return i;
    }
    
    public ArrayList<Item> getProcessedItems(){
        if (stashIn == null){
            throw new Error("stashIn Not populated.");
        }
        ArrayList<Item> items = new ArrayList<>();
        
        for (Item i: stashIn.items){
            items.add(processItem(i));
        }
        
        return items;
    }
}