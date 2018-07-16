package CompositeModCalculations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/**
 * @author Joel Wade
 */
public class ModListCreator {
    
    /**
     * Takes the file, CompositeCalculations.json, and creates a new file, ModList.json, containing
     * a list of all mods with their corresponding 'uses'. A 'use' is a calculation that is a composite
 of many different item mods.
 A composite calculation is made up of a Key of type String, and a Mod array (Mod[]]).
 The outcome, the Mod List, is a Map of ModUses objects.
     */
    
    private static final String compositeCalculationsFilePath = 
            "src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json";
    
    private static final String modListFilePath = "src\\main\\java\\CompositeModCalculations\\ModList.json";
    
    //Takes file path and returns a fully populated map of all ModUses and their uses.
    public static Map<String, ModUses> getModMap(String ccFilePath) throws FileNotFoundException, IOException{
        //From CompositeCalculations.json
        Map<String, Mod[]> calcsFromFile = stringToMap(inputstreamToString(fileToInputStream(ccFilePath)));
        
        //Map of ModUses to be added to a new file.
        Map<String, ModUses> mods = new HashMap<>();

        //For each calc in CompositeCalculations.json.
        for (Map.Entry<String, Mod[]> entry : calcsFromFile.entrySet()){
            //For each mod in each calc in CompositeCalculations.json.
            for (Mod m: entry.getValue()){
                //If map has not got the mod in alread, add it.
                if (!mods.containsKey(m.getName())) {
                    ModUses toAdd = new ModUses(m.getName());
                    toAdd.addUse(entry.getKey());
                    mods.put(m.getName(), toAdd);
                } else {//else, add use to mod in map.
                    ModUses toAdd = new ModUses(m.getName(), mods.get(m.getName()).uses);
                    toAdd.addUse(entry.getKey());
                    mods.put(m.getName(), toAdd);
                }
            }
        }
        
        return mods;
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
    
    public static String getModListAsJson(String filePath) throws IOException {
        Gson gson = new Gson();
        return gson.toJson(getModMap(filePath));
    }
    
    public static boolean saveJsonToFile(String compCalcFilePath, String modListFilePath) {
        String f = new File(modListFilePath).getAbsolutePath();
        FileWriter writer;
        try{
            String json = getModListAsJson(compCalcFilePath);
            
            writer = new FileWriter(f);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args){
        saveJsonToFile(compositeCalculationsFilePath, modListFilePath);
    }
}