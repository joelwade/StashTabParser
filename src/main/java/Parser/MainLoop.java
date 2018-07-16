/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import DependencyInjector.ParserSetup;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 * @author Joel Wade
 */

public class MainLoop {
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new ParserSetup());
        Parser parser = injector.getInstance(Parser.class);
        
        //All errors should be passed to and handled here, where the results can be logged.
        
        //If next_change_id == null, establish connection to db and get.
        
        //Download data using next_change_id. Logging issues.
        
        //Convert data to GGGFileData object.
        
        //Process each stash. Calc dps, pdps, edps. Total res, total ele res.
        
        //Establish db connection if needed. Add items to db.
        
        //Send items to live search service.
//        Parser p = new Parser();
//        System.out.println(p.hasNextChangeId());
    }
}