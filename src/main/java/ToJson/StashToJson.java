package ToJson;


import DependencyInjector.ParserSetup;
import Parser.Parser;
import StashSorter.StashSorter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycompany.poe.api.parser.ApiObjects.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Joel Wade
 */
public class StashToJson {
    
    public static class ItemSerialize implements JsonSerializer<Item> {
        
        /**
         * Tuple arraylists to be serialized.
         * itemStats
         * implAndExplMods
         * calculatedTotalValues
         */
        
        @Override
        public JsonElement serialize(Item i, Type type, JsonSerializationContext jsc) {
            
        JsonObject object = new JsonObject();
//        JsonElement ser = jsc.serialize(i, Item.class);
//        JsonObject object2 = ser.getAsJsonObject();
        
        //For this to work i need to have a seperate item object, which I then map the normal item object onto
        //The seperate item object must have no arraylists in.
        
        if (i.itemStats != null){
            for (Tuple tup: i.itemStats){
                object.addProperty(tup.getKey(), tup.getValue().toString() );
            }
        }
        
        if (i.implAndExplMods != null){
            for (Tuple tup: i.implAndExplMods){
                object.addProperty(tup.getKey(), tup.getValue().toString() );
            }
        }
        
        if (i.calculatedTotalValues != null){
            for (Tuple tup: i.calculatedTotalValues){
                object.addProperty(tup.getKey(), tup.getValue().toString() );
            }
        }
        return object;
        }
    }
    
    public static void main(String[] args) throws IOException{
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Item.class, new ItemSerialize())
                .setExclusionStrategies(new MyExclusionStrategy(ArrayList.class))
                .setPrettyPrinting()
                
                .create();
        
        Gson gsonOnlyArrayLists = new GsonBuilder()
                .setExclusionStrategies(new MyExclusionStrategy(ArrayList.class))
                .create();
        
        
//        
//        Injector injector = Guice.createInjector(new ParserSetup());
//        Parser instance = injector.getInstance(Parser.class);
//        GGGFileData file = instance.getStashTabs();
//        
//        Map<String, ModUses> modList = Utils.GetObjectsFromJson.getModList("src\\main\\java\\CompositeModCalculations\\ModList.json");
//        Map<String, Mod[]> compCalcs = Utils.GetObjectsFromJson.getCompCalcsMap("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json");
//        
//        StashSorter ss = new StashSorter(file.stashes[0],
//                modList,
//                compCalcs);
//        
//        ArrayList<Item> items = ss.getProcessedItems();
//        
//        System.out.println(items.size());

        Parser instance = new Parser(null);
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString(fileToInputStream()));
        
        Injector injector = Guice.createInjector(new ParserSetup());
        
        StashSorter ss = new StashSorter(file.stashes[0],
                Utils.GetObjectsFromJson.getModList("src\\main\\java\\CompositeModCalculations\\ModList.json"),
                Utils.GetObjectsFromJson.getCompCalcsMap("src\\main\\java\\CompositeModCalculations\\CompositeCalculations.json"));
        ArrayList<Item> items = ss.getProcessedItems();
        
        JsonObject ele = (JsonObject)gsonOnlyArrayLists.toJsonTree(items.get(1));
        JsonObject s = (JsonObject)gson.toJsonTree(items.get(1));
        
        for (Map.Entry entry: s.entrySet()){
            ele.add(entry.getKey().toString(), (JsonElement) entry.getValue());
        }
        
        System.out.println(ele);
        
        //ele is item i want to end up with.
        //Want to remove unwanted stuff.
    }
    
    public static FileInputStream fileToInputStream() throws FileNotFoundException{
        return new FileInputStream("src\\test\\java\\StashSorter\\stashSorterTestJson.json");
    }

    public static class MyExclusionStrategy implements ExclusionStrategy {
    private final Class<?> typeToSkip;

    private MyExclusionStrategy(Class<?> typeToSkip) {
      this.typeToSkip = typeToSkip;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
      return (clazz == typeToSkip);
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
      return f.getAnnotation(Foo.class) != null;
    }
  }
}