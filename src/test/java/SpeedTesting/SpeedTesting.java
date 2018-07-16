/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.poe.api.parser.ApiObjects.GGGFileData;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
 *
 * @author Joel Wade
 */
public class SpeedTesting {
    
    private String poeUrl = "https://pathofexile.com/api/public-stash-tabs?id=2605-4457-4108-4817-1485";
    
    public FileInputStream fileToInputStream() throws FileNotFoundException{
        return new FileInputStream("C:\\Users\\hj\\Desktop\\Parser\\poe-api-parser\\src\\main\\java\\Testing\\newjson4.json");
    }
    
    public GGGFileData stringToGGGJsonFile(String s){
        Gson gson = new GsonBuilder().setLenient().create();
        
        return gson.fromJson(s, GGGFileData.class);
    }
    
    /**
        The following 10 methods were taken from: 
        * ttps://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string?page=1&tab=votes#tab-top
     */
    public String inputstreamToString1(FileInputStream inputStream) throws IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
    
    public String inputstreamToString2(FileInputStream inputStream){
        return new BufferedReader(new InputStreamReader(inputStream)).lines()
        .parallel().collect(Collectors.joining("\n"));
    }
    
    public String inputstreamToString3(FileInputStream inputStream) throws UnsupportedEncodingException, IOException{
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
    
    public String inputstreamToString4(FileInputStream inputStream) throws UnsupportedEncodingException, IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
    
    public String inputstreamToString5(FileInputStream inputStream) throws IOException{
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
    
    public String inputstreamToString6(FileInputStream inputStream) throws IOException{
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
    
    public String inputstreamToString7(FileInputStream inputStream) throws IOException{
        int ch;
        StringBuilder sb = new StringBuilder();
        while((ch = inputStream.read()) != -1)
            sb.append((char)ch);
        reset();
        return sb.toString();
    }
    
    public String inputstreamToString8(FileInputStream inputStream) throws IOException{
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }
    
    public String inputstreamToString9(FileInputStream inputStream) throws IOException{
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
    public String inputstreamToString10(FileInputStream inputStream) throws IOException{
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
    }
    
    public String inputstreamToString1(InputStream inputStream) throws IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
    
    public String inputstreamToString2(InputStream inputStream){
        return new BufferedReader(new InputStreamReader(inputStream)).lines()
        .parallel().collect(Collectors.joining("\n"));
    }
    
    public String inputstreamToString3(InputStream inputStream) throws UnsupportedEncodingException, IOException{
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
    
    public String inputstreamToString4(InputStream inputStream) throws UnsupportedEncodingException, IOException{
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
    
    public String inputstreamToString5(InputStream inputStream) throws IOException{
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
    
    public String inputstreamToString6(InputStream inputStream) throws IOException{
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
    
    public String inputstreamToString7(InputStream inputStream) throws IOException{
        int ch;
        StringBuilder sb = new StringBuilder();
        while((ch = inputStream.read()) != -1)
            sb.append((char)ch);
        reset();
        return sb.toString();
    }
    
    public String inputstreamToString8(InputStream inputStream) throws IOException{
        StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }
    
    public String inputstreamToString9(InputStream inputStream) throws IOException{
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
    public String inputstreamToString10(InputStream inputStream) throws IOException{
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
    }
    
    public static GGGFileData inputStreamToJackson(InputStream inputStream) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        GGGFileData f13 = mapper.readValue(inputStream, GGGFileData.class);
        return f13;
    }
    
    public static InputStream apiToInputStream() throws MalformedURLException, IOException, Exception{
        return retryConnection("https://pathofexile.com/api/public-stash-tabs?id=2605-4457-4108-4817-1485").openStream();
//        return new URL(poeUrl).openStream();
    }
    
    /**
     * Taken from 
     */
    
    public GGGFileData readFromUrl2() throws MalformedURLException, IOException, Exception{
        URL url = retryConnection(poeUrl);
//        URL url = new URL(poeUrl);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return new Gson().fromJson(reader, GGGFileData.class);
    }
    
    public GGGFileData readFromUrl2(InputStream u) throws MalformedURLException, IOException, Exception{
//        URL url = retryConnection(poeUrl);
//        URL url = new URL(poeUrl);
        InputStreamReader reader = new InputStreamReader(u);
        return new Gson().fromJson(reader, GGGFileData.class);
    }
    
    public static String fromStream(InputStream in) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
    
    
    public static URL retryConnection(String link) throws Exception{
        final int retryCount = 3;
        int currentRetry = 0;
        while (true){
            try {
                URL url = new URL(link);
                return url;
            } catch (Exception e) {
                System.out.println("Exception line 274");
                currentRetry++;
                
                if (currentRetry > retryCount){
                    throw new Exception("Went over retry count.");
                }
            }
            //Thread.sleep(500);
        }
    }
    
    public static void main(String[] args) throws IOException, ParseException, Exception {
        long startTime = System.nanoTime();
//        GGGFileData file = stringToGGGJsonFile(readUrl("http://www.pathofexile.com/api/public-stash-tabs"));
//        GGGFileData file = readFromUrl2();
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString1(fileToInputStream()));

//        GGGFileData file = stringToGGGJsonFile(inputstreamToString2(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString3(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString4(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString5(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString6(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString7(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString8(fileToInputStream()));
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString9(fileToInputStream()));
        
//        GGGFileData file = stringToGGGJsonFile(inputstreamToString10(fileToInputStream()));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        //Get file from api
        //get stashes from file
        //put accountName, lastCharacterName, id into item objects
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
        
        GGGFileData file = inputStreamToJackson(apiToInputStream());

//        Stash[] stashes = file.stashes;
//        System.out.println(stashes.length);
//        
//        Stash stash = stashes[42];
//        System.out.println(stash.items.length);
//        Item i = stash.items[0];
//        System.out.println(i.typeLine);
    }
}