/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StashSorter;

import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Joel Wade
 */
public class ProcessItemMods {

    public ArrayList<Tuple> processMods(String[] s) {
        if (s==null || s.length<=0){
            return new ArrayList<>();
        }
        ArrayList<Tuple> mods = new ArrayList<>();
        for (int x = 0; x <= s.length-1; x++){
            mods.add(processMod(s[x]));
        }
        return mods;
    }
    
    private Tuple processMod(String s){
        //Get numbers, ints and floats, from string.
        ArrayList<String> numbs = parseIntsAndFloats(s);
        //If string starts with "Adds" then it has two numbers, and the average is needed.
        if (s.substring(0, 4).equals("Adds")){
            //Replace both the first and second number with ##.
            s = s.replaceFirst(numbs.get(0), "##");
            //Return the string with the numbers removed/replaced, and the average of the numbers.
            return new Tuple(s.replaceFirst(numbs.get(1), "##"), averageOfTwoStrings(numbs.get(0), numbs.get(1)));
        } else {
            //Replace the number with ##.
            s = s.replaceFirst(numbs.get(0), "##");
            return new Tuple(s, numbs.get(0));
        }
    }
    
    private float averageOfTwoStrings(String s1, String s2){
        return ( Float.parseFloat(s1) + Float.parseFloat(s2) ) /2;
    }
    
    //https://stackoverflow.com/questions/12234963/java-searching-float-number-in-string
    private ArrayList<String> parseIntsAndFloats(String raw) {
        ArrayList<String> listBuffer = new ArrayList<String>();

        Pattern p = Pattern.compile("[-]?[0-9]*\\.?[0-9]+");

        Matcher m = p.matcher(raw);

        while (m.find()) {
            listBuffer.add(m.group());
        }

        return listBuffer;
    }
}
