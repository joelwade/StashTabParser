/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Parser.IDatabaseConnection;
import com.mycompany.poe.api.parser.ApiObjects.Item;
import com.mycompany.poe.api.parser.ApiObjects.Stash;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Joel Wade
 */
public class MockDatabaseConnection implements IDatabaseConnection{

    ArrayList<Item> items;
    
    @Override
    public void establishConnection() {
        items = new ArrayList<>();
    }
    
    @Override
    public Boolean isConnectionActive() {
        return items != null;
    }

    @Override
    public String getNextChangeId() {
        return new Random(15).toString();
    }

    @Override
    public void addItem(Stash s) {
        //Only add item to items if it isn't already there.
        ArrayList<String> ids = new ArrayList<>();
        for (Item i : items){
            ids.add(i.id);
        }
        
        for (Item i : s.items){
            if (!ids.contains(i.id)){
                items.add(i);
            }
        }
    }
    
}