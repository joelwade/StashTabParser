/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DependencyInjector;

import com.mycompany.poe.api.parser.ApiObjects.Tuple;
import Parser.DatabaseConnection;
import Parser.IDatabaseConnection;
import Parser.MockDatabaseConnection;
import com.google.inject.AbstractModule;

/**
 *
 * @author Joel Wade
 */
public class ParserSetup extends AbstractModule {

    @Override
    protected void configure() {
        bind(IDatabaseConnection.class).to(MockDatabaseConnection.class);
//        bind(IDatabaseConnection.class).to(DatabaseConnection.class);
    }
    
}