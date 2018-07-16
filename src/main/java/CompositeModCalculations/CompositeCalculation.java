/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompositeModCalculations;

/**
 *
 * @author Joel Wade
 */
public class CompositeCalculation {
    private String name;
    private Mod[] mods;

    public CompositeCalculation(String name, Mod[] mods) {
        this.name = name;
        this.mods = mods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mod[] getMods() {
        return mods;
    }

    public void setMods(Mod[] mods) {
        this.mods = mods;
    }
}