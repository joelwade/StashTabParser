/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poe.api.parser.ApiObjects;

import ToJson.Foo;
import java.util.ArrayList;

/**
 *
 * @author Joel Wade
 */
public class Item {
    
    //Unprocessed variables, these come from the api data.
    public String name;
    public String typeLine;
    
    public boolean corrupted;
    public boolean abyssJewel;
    public boolean identified;
    public boolean support;
    public boolean shaper;
    public boolean elder;
    public boolean lockedToCharacter;
    public boolean raceReward;
    public boolean isRelic;

    public String[] enchantMods;
    public String[] implicitMods;
    public String[] explicitMods;
    public String[] craftedMods;
    public String[] utilityMods;

    public Properties[] requirements;
    public Properties[] nextLevelRequirements;
    public Properties[] properties;
    public Properties[] additionalProperties;
    
    public Category category;
    @Foo
    public Sockets[] sockets;
    
    public String artFilename;
    public String icon;
    public String id;
    public String inventoryId;
    public String league;
    public String secDescrText;
    public String prophecyDiffText, prophecyText;
    public String note; //Price of item. If this is empty, and stash has a title starting ~b/o or ~price, 
                        //change this to stash name.
    
    public int ilvl;
    public int frameType;
    public int stackSize;
    public int talismanTier;
    public int w;
    public int x;
    public int y;
    public int h;

    //To disregard
    public transient String descrText;
    public transient Item[] socketedItems;
    public transient String[] cosmeticMods;
    public transient String[] flavourText;
    
    //To add from stash data.
    public String stashId;
    public String accountName;
    public String lastCharacterName;
    
    //Weapon stats
    public float aps;
    public float critChance;
    public int weaponRange;
    //Armour stats
    public int energyShield;
    public int armour;
    public int evasion;
    //Gem stats
    //Socket info
    public int socketCount;
    public int maxLinks;
    public int redSocketCount;
    public int greenSocketCount;
    public int blueSocketCount;
    public int whiteSocketCount;
    //Misc
    public int quality;
    public int craftedModsCount;
    public Boolean altArt;//Calc
    public String itemType; //Amulet, boots, belt, etc.
    public String categoryType; //twosword, bow, dagger, staff, claw etc.
    public String categorySpecific; //armour, weapon, jewel etc.
    public float adjustedPrice;
    //Tuples.
    @Foo
    public Tuple enchantedMod;
    @Foo
    public ArrayList<Tuple> itemStats;
    @Foo
    public ArrayList<Tuple> implAndExplMods;
    @Foo
    public ArrayList<Tuple> calculatedTotalValues;
}