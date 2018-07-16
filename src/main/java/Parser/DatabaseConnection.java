/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import com.mycompany.poe.api.parser.ApiObjects.Item;
import com.mycompany.poe.api.parser.ApiObjects.Stash;
import java.util.ArrayList;

/**
 *
 * @author Joel Wade
 */
public class DatabaseConnection implements IDatabaseConnection{
    
    @Override
    public void establishConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addItem(Stash s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Throw error if connection isn't active/established.
    @Override
    public String getNextChangeId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isConnectionActive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}