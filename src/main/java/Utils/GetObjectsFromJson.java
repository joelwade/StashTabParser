/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import CompositeModCalculations.Mod;
import CompositeModCalculations.ModUses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Joel Wade
 */
public class GetObjectsFromJson {
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
    private static Map<String, Mod[]> stringToModMap(String s){
        Gson gson = new GsonBuilder().setLenient().create();
        
        Type stringStringMap = new TypeToken<Map<String, Mod[]>>(){}.getType();
        Map<String, Mod[]> map = gson.fromJson(s, stringStringMap);

        return map;
    }
    
    //Takes string, typically from inputstreamToString, and returns a Map.
    private static Map<String, ModUses> stringToModUsesMap(String s){
        Gson gson = new GsonBuilder().setLenient().create();
        
        Type stringStringMap = new TypeToken<Map<String, ModUses>>(){}.getType();
        Map<String, ModUses> map = gson.fromJson(s, stringStringMap);

        return map;
    }
    
    public static Map<String, Mod[]> getCompCalcsMap(String filePath) throws IOException{
        return stringToModMap(inputstreamToString(fileToInputStream(filePath)));
    }
    
    public static Map<String, ModUses> getModList(String filePath) throws IOException{
        return stringToModUsesMap(inputstreamToString(fileToInputStream(filePath)));
    }
}
