/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import com.mycompany.poe.api.parser.ApiObjects.Stash;

/**
 *
 * @author Joel Wade
 */
public interface IDatabaseConnection {
    public void establishConnection();
    public Boolean isConnectionActive();
    public String getNextChangeId();
    public void addItem(Stash s);
}