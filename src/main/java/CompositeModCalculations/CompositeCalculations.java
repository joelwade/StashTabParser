package CompositeModCalculations;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Joel Wade
 */
public class CompositeCalculations {
    //Could have composite_Calculations as a hashmap, makes life easier.
    //https://stackoverflow.com/questions/14944419/gson-to-hashmap
    private HashMap<String, Mod[]> composite_Calculations;

    public CompositeCalculations() {
        this.composite_Calculations = new HashMap<String, Mod[]>() {};
    }
    
    public Map<String, Mod[]> getCalcs(){
        return composite_Calculations;
    }
    
    public CompositeCalculations addCalc(String key, Mod[] mods){
        composite_Calculations.put(key, mods);
        return this;
    }
}
