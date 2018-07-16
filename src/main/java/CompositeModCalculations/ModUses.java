/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompositeModCalculations;

import java.util.ArrayList;

/**
 *
 * @author Joel Wade
 */
public class ModUses {
    public String name;
    public ArrayList<String> uses;

    public ModUses(String name) {
        this.name = name;
        this.uses = new ArrayList<String>();
    }
    
    public ModUses(String name, ArrayList<String> uses){
        this.name = name;
        this.uses = uses;
    }
    
    public ModUses addUse(String use){
        uses.add(use);
        return this;
    }
}