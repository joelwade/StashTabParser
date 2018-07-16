package CompositeModCalculations;

import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joel Wade
 */
public class CompCalcAlgorithm {
    /**
     * Have loaded already - 
     * CompCalculations - HashMap.
     * ModList -  Map<String, Mod[]>.
     * 
     * Create - 
     * HashMap of values to calc.
     * 
     * For each mod in implicit and explicit mods.
     *      Check what comp mods are associated with that mod.
     *      For each associated comp mod
     *          if those calcs are not in the HashMap.
     *              Use all mods to calc the full composite mod, add to HashMap. calcIndividualCompMod().
     * end for
     * 
     */
    
    CompositeCalculations calcs;
    Map<String, ModUses> modList;
    
    
    public CompCalcAlgorithm(CompositeCalculations calcs,  Map<String, ModUses> modList){
        this.calcs = calcs;
        this.modList = modList;
    }

    public void calcCompMods(ArrayList<Tuple> itemMods) {
        HashMap<String, Tuple> calcedMods = new HashMap<>();
        
        for (Tuple itemMod: itemMods){//For each mod in implicit and explicit mods.
            ArrayList<String> uses;
            if (isStringInModArrayList(itemMods, itemMod.getKey())){//if mod is in mod list.
                uses = getUsesFromModList(itemMod.getKey());
            }
            
            //Get uses from mod list.
            //For each use, get full CompositeCalculation from compCalcs.
            //calc tuple (using calcIndividualCompMod()) and add to calcedMods.
        }
    }
    
    //Take list of item mods and calculate the specified composite value.
    public Tuple calcIndividualCompMod(ArrayList<Tuple> itemMods, Mod[] calc, String calcName){
        float total = 0;
        
        ArrayList<ValueMultiplierPair> toUse = new ArrayList<>();
        
        //For each mod in calc
        for (Mod u: calc){
            if (isStringInModArrayList(itemMods, u.getName())){//If mod is in itemMods
                toUse.add(getFromArrayListUsingString(itemMods, calc, u.getName()));
            }
        }
        
        //use multiplier to add to total.
        for (ValueMultiplierPair t: toUse){
            total += t.getValue() * t.getMultiplier();
        }
        //cake
        //return final value in tuple form.
        return new Tuple(calcName, total);
    }
    
    //If provided string is a key in a tuple in provided array list, return true;
    private boolean isStringInModArrayList(ArrayList<Tuple> itemMods, String s){
        return itemMods.stream().anyMatch((t) -> (t.getKey().equals(s)));
    }
    
    //Takes arraylist of tuples(String, mod), and returns a ValueMultiplierPair.
    private ValueMultiplierPair getFromArrayListUsingString(ArrayList<Tuple> itemMods, Mod[] calc,
            String s){
        String ss = "";
        //Get multiplier.
        for (Mod mod : calc) {
            if (mod.getName().equals(s)){
                ss = Float.toString(mod.getMultiplier());
            }
        }
        //Get value.
        for (Tuple t: itemMods){
            if (t.getKey().equals(s)){
                //Return multipler and value.
                return new ValueMultiplierPair(Float.parseFloat(t.getValue().toString()), Float.parseFloat(ss));
            }
        }
        
        return null;
    }
    
    private ArrayList<String> getUsesFromModList(String itemMod){
        //Search calcs for itemMod, get uses array from item mod.
        return null;
    }
}

class ValueMultiplierPair {
    private float value;
    private float multiplier;

    public ValueMultiplierPair(float value, float multiplier) {
        this.value = value;
        this.multiplier = multiplier;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}