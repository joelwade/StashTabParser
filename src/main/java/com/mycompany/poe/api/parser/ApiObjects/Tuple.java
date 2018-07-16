/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poe.api.parser.ApiObjects;

import javafx.util.Pair;

/**
 *
 * @author Joel Wade
 */
public class Tuple{

    Pair p;

    public Tuple(String key, Object value) {
        p = new Pair(key, value);
    }

    public String getKey() {
        return p.getKey().toString();
    }

    public Object getValue() {
        return p.getValue();
    }

    public boolean set(String k, Object v) {
        p = new Pair(k, v);
        return true;
    }
    
}
