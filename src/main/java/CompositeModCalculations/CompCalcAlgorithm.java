package CompositeModCalculations;

import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Joel Wade
 */
public class CompCalcAlgorithm {
    Map<String, Mod[]> compCalcs;
    Map<String, ModUses> modList;
    
    public CompCalcAlgorithm(Map<String, Mod[]> compCalcs,  Map<String, ModUses> modList){
        this.compCalcs = compCalcs;
        this.modList = modList;
    }

    /**
     * Pseudo code for calcCompMods:
     * Create set of composite mods to be calculated.
     * For each mod in implicit and explicit mods.
     *      if mod is in mod list.
     *          get uses, which are composite calculations names, from mod list, add to set
     *          
     * end for
     * Create list of compositeCalculations objects.
     * for each composite calc in compositeCalculations list
     *      calcIndividualCompMod
     * 
     * @param itemMods
     * @return ArrayList<Tuple> of calculated composite mods.
     */
    
    public ArrayList<Tuple> calcCompMods(ArrayList<Tuple> itemMods) {
        //Set is used to remove duplicates.
        Set<String> toBeCalced = new HashSet<>();
        
        for (Tuple itemMod: itemMods){//For each mod in implicit and explicit mods.
            if (modList.containsKey(itemMod.getKey())){//if mod is in mod list.
                //Get uses from mod list and add to uses.
                ArrayList<String> modUses = modList.get(itemMod.getKey()).uses;
                for (String s : modUses){
                    toBeCalced.add(s);
                }
            }
        }
        ArrayList<Tuple> calcedMods = new ArrayList<>();
        
        for (String s: toBeCalced){
            if (compCalcs.containsKey(s)){
                CompositeCalculation temp = new CompositeCalculation(s, compCalcs.get(s));
                calcedMods.add( calcIndividualCompMod(itemMods, temp) );
            }
        }
        
        return calcedMods;
    }
    
    //Take list of item mods and calculate the specified composite value.
    public Tuple calcIndividualCompMod(ArrayList<Tuple> itemMods, CompositeCalculation calc){
        float total = 0;
        
        for (Mod mod: calc.getMods()){
            //if Array of itemMods contains a mod in calc.mod[]
            if (isStringInModArrayList(itemMods, mod.getName())){
                //Get value and multiplier from the item mod and calc, and add to total.
                ValueMultiplierPair v = getFromArrayListUsingString(itemMods, calc.getMods(), mod.getName());
                total += (v.getValue()*v.getMultiplier());
            }
        }
        //return final value in tuple form.
        return new Tuple(calc.getName(), total);
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
        //Search compCalcs for itemMod, get uses array from item mod.
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