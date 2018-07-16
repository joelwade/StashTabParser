/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poe.api.parser.ApiObjects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;

/**
 * @author Joel Wade
 */
public class Test {

    public static FileInputStream fileToInputStream() throws FileNotFoundException{
        return new FileInputStream("C:\\Users\\hj\\Desktop\\Parser\\poe-api-parser\\src\\main\\java\\Testing\\newjson3.json");
    }
    
    public static GGGFileData stringToGGGJsonFile(String s){
        Gson gson = new GsonBuilder().setLenient().create();
        
        return gson.fromJson(s, GGGFileData.class);
    }
    
    public static String inputstreamToString1(FileInputStream inputStream) throws IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
    
    public static String inputstreamToString2(FileInputStream inputStream){
        return new BufferedReader(new InputStreamReader(inputStream)).lines()
        .parallel().collect(Collectors.joining("\n"));
    }
    
    public static String inputstreamToString3(FileInputStream inputStream) throws UnsupportedEncodingException, IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
    
    public static String inputstreamToString4(FileInputStream inputStream) throws UnsupportedEncodingException, IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
    
    public static String inputstreamToString5(FileInputStream inputStream) throws IOException{
        String newLine = System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line; boolean flag = false;
        while ((line = reader.readLine()) != null) {
            result.append(flag? newLine: "").append(line);
            flag = true;
        }
        return result.toString();
    }
    
    public static String inputstreamToString6(FileInputStream inputStream) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while(result != -1) {
            buf.write((byte) result);
            result = bis.read();
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return buf.toString("UTF-8");
    }
    
    public static String inputstreamToString7(FileInputStream inputStream) throws IOException{
        int ch;
        StringBuilder sb = new StringBuilder();
        while((ch = inputStream.read()) != -1)
            sb.append((char)ch);
        reset();
        return sb.toString();
    }
    
    public static String inputstreamToString8(FileInputStream inputStream) throws IOException{
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }
    
    public static String inputstreamToString9(FileInputStream inputStream) throws IOException{
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
    public static String inputstreamToString10(FileInputStream inputStream) throws IOException{
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
    }
    
    /**
     * Taken from https://stackoverflow.com/questions/7467568/parsing-json-from-url
     */
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
    
    public static GGGFileData getApiData(String nextChangeId) throws IOException, Exception {
        String s = readUrl("http://www.pathofexile.com/api/public-stash-tabs");
        
        Gson gson = new GsonBuilder().setLenient().create();
        
        GGGFileData file = gson.fromJson(s, GGGFileData.class);
        
        return file;
    }
    
    public static InputStream apiToInputStream() throws MalformedURLException, IOException{
        return new URL("http://www.pathofexile.com/api/public-stash-tabs").openStream();
    }
    
    /**
     * Taken from 
     */
    
    public static GGGFileData readFromUrl2() throws MalformedURLException, IOException{
        URL url = new URL("http://www.pathofexile.com/api/public-stash-tabs");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return new Gson().fromJson(reader, GGGFileData.class);
    }
    
    public static void saveJsonToFIle() throws MalformedURLException, IOException{
        InputStream input = null;
        OutputStream output = null;
        PrintWriter writer = new PrintWriter("output.json", "UTF-8");
        try {
            input = new URL("http://www.pathofexile.com/api/public-stash-tabs").openStream();
            output = new FileOutputStream("output.json");
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
            // Here you could append further stuff to `output` if necessary.
        } finally {
            if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
            if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
        }
    }
    
    public static void main(String[] args) throws IOException, ParseException, Exception {
        long startTime = System.nanoTime();
//        GGGFileData file = stringToGGGJsonFile(readUrl("http://www.pathofexile.com/api/public-stash-tabs"));
        GGGFileData file = readFromUrl2();
//        GGGFileData file = getApiData("");
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString1(fileToInputStream()));

//        GGGFileData file = stringToGGGJsonFile(inputstreamToString2(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString3(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString4(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString5(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString6(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString7(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString8(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString9(fileToInputStream()));
        //GGGJsonFile file = stringToGGGJsonFile(inputstreamToString10(fileToInputStream()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        //Get file from api
        //get stashes from file
        //put accountName, lastCharacterName, stashId into item objects
        //put items into db
        
//        JSONParser parser = new JSONParser();
//        
//        Object obj = parser.parse(new FileReader("C:\\Users\\hj\\Desktop\\Parser\\poe-api-parser\\src\\main\\java\\Testing\\newjson3.json"));
//        JSONObject jsonObject = (JSONObject) obj;
//        String jsonO = jsonObject.toString();
//
//        Gson gson = new GsonBuilder().setLenient().create();
//       /// Item i = gson.fromJson(jsonO, Item.class);
//
//        GGGFileData file = gson.fromJson(jsonO, GGGFileData.class);
        
        Stash[] stashes = file.stashes;
        System.out.println(stashes.length);
        
        Stash stash = stashes[42];
        System.out.println(stash.items.length);
        Item i = stash.items[0];
        System.out.println(i.typeLine);
    }
}