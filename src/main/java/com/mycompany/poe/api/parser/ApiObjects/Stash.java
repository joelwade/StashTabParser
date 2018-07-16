/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poe.api.parser.ApiObjects;

/**
 *
 * @author Joel Wade
 */
public class Stash {
    public String accountName;
    public String lastCharacterName;
    public String stashId;
    public String stash;
    
    public String stashType;
    public boolean Public;
    
    public Item[] items;
}