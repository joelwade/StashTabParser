/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.mycompany.poe.api.parser.ApiObjects.GGGFileData;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 *
 * @author Joel Wade
 */
public class Parser{
    
    private final String url = "https://pathofexile.com/api/public-stash-tabs?id=";
    private IDatabaseConnection dbConnection;
    private String nextChangeId;
    
    @Inject
    public Parser(IDatabaseConnection dbc) {
        dbConnection = dbc;
    }
    
    public GGGFileData getStashTabs() {
        try{
            URL gggUrl = new URL(url);
        
            return stringToGGGJsonFile(inputstreamToString(gggUrl.openStream()));
        } catch (Exception e){
            return null;
        }
    }
    
    private GGGFileData stringToGGGJsonFile(String s){
        Gson gson = new GsonBuilder().setLenient().create();
        
        return gson.fromJson(s, GGGFileData.class);
    }
    
    private String inputstreamToString(InputStream inputStream) throws UnsupportedEncodingException, IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
    
    public Boolean hasNextChangeId(){
        return nextChangeId != null;
    }
    
    public void getNextChangeId(){
        nextChangeId = dbConnection.getNextChangeId();
    }
    
    public Boolean establishConnection(){
        try {
            dbConnection.establishConnection();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean connectionEstablished(){
        if (dbConnection == null){
            return false;
        } else {
            return true;
        }
    }
    
}