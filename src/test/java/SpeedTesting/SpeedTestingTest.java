/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpeedTesting;

import SpeedTesting.SpeedTesting;
import com.mycompany.poe.api.parser.ApiObjects.GGGFileData;
import com.mycompany.poe.api.parser.ApiObjects.Stash;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Joel Wade
 */
public class SpeedTestingTest {
    InputStream in;
    public SpeedTestingTest() throws IOException, Exception {
        SpeedTesting instance = new SpeedTesting();
        in = instance.apiToInputStream();
        URL url1 = new URL("https://pathofexile.com/api/public-stash-tabs?id=2605-4457-4108-4817-1485");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    public int stashCount = 520;
    
    /**
     * Test of readFromUrl2 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testReadFromUrl2() throws Exception {
        System.out.println("readFromUrl2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.readFromUrl2();
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        assertEquals(stashCount, file.stashes.length);
    }
    
    @org.junit.Test
    public void testReadFromUrl22() throws Exception {
        System.out.println("readFromUrl2 - but using pre-downloaded input stream");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.readFromUrl2(in);
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        assertEquals(stashCount, file.stashes.length);
    }
    
    @org.junit.Test
    public void testfromStream_v2() throws IOException{
        System.out.println("testfromStream_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.fromStream(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }
    
    @org.junit.Test
    public void testInputstreamToString1_v2() throws Exception {
        System.out.println("inputstreamToString1_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString1(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString2 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString2_v2() throws FileNotFoundException {
        System.out.println("inputstreamToString2_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString2(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString3 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString3_v2() throws Exception {
        System.out.println("inputstreamToString3_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString3(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString4 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString4_v2() throws Exception {
        System.out.println("inputstreamToString4_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString4(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString5 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString5_v2() throws Exception {
        System.out.println("inputstreamToString5_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString5(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString6 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString6_v2() throws Exception {
        System.out.println("inputstreamToString6_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString6(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString7 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString7_v2() throws Exception {
        System.out.println("inputstreamToString7_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString7(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString8 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString8_v2() throws Exception {
        System.out.println("inputstreamToString8_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString8(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString9 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString9_v2() throws Exception {
        System.out.println("inputstreamToString9_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString9(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString10 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString10_v2() throws Exception {
        System.out.println("inputstreamToString10_v2");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString10(in));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }
    
    @org.junit.Test
    public void testfromStream() throws IOException{
        System.out.println("fromStream");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.fromStream(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }
    
    /**
     * Test of inputstreamToString1 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString1() throws Exception {
        System.out.println("inputstreamToString1 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString1(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString2 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString2() throws FileNotFoundException {
        System.out.println("inputstreamToString2 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString2(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString3 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString3() throws Exception {
        System.out.println("inputstreamToString3 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString3(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString4 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString4() throws Exception {
        System.out.println("inputstreamToString4 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString4(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString5 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString5() throws Exception {
        System.out.println("inputstreamToString5 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString5(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString6 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString6() throws Exception {
        System.out.println("inputstreamToString6 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString6(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString7 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString7() throws Exception {
        System.out.println("inputstreamToString7 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString7(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString8 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString8() throws Exception {
        System.out.println("inputstreamToString8 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString8(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString9 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString9() throws Exception {
        System.out.println("inputstreamToString9 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString9(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }

    /**
     * Test of inputstreamToString10 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputstreamToString10() throws Exception {
        System.out.println("inputstreamToString10 - From file");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.stringToGGGJsonFile(instance.inputstreamToString10(instance.fileToInputStream()));
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        assertEquals(stashCount, file.stashes.length);
    }
    
    /**
     * Test of inputstreamToString9 method, of class SpeedTesting.
     */
    @org.junit.Test
    public void testInputStreamToJackson() throws Exception {
        System.out.println("testInputStreamToJackson");
        SpeedTesting instance = new SpeedTesting();
        long startTime = System.nanoTime();
        GGGFileData file = instance.inputStreamToJackson(in);
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(duration/1000000);
        
        Stash[] stashes = file.stashes;
        assertEquals(stashCount, stashes.length);
    }
    
    /**
     * Test of fileToInputStream method, of class SpeedTesting.
     */
//    @org.junit.Test
//    public void testFileToInputStream() throws Exception {
//        System.out.println("fileToInputStream");
//        SpeedTesting instance = new SpeedTesting();
//        FileInputStream expResult = null;
//        FileInputStream result = instance.fileToInputStream();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of stringToGGGJsonFile method, of class SpeedTesting.
//     */
//    @org.junit.Test
//    public void testStringToGGGJsonFile() {
//        
//        System.out.println("stringToGGGJsonFile");
//        String s = "";
//        SpeedTesting instance = new SpeedTesting();
//        GGGFileData expResult = null;
//        GGGFileData result = instance.stringToGGGJsonFile(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getApiData method, of class SpeedTesting.
     */
//    @org.junit.Test
//    public void testGetApiData() throws Exception {
//        System.out.println("getApiData");
//        String nextChangeId = "";
//        SpeedTesting instance = new SpeedTesting();
//        GGGFileData expResult = null;
//        GGGFileData result = instance.getApiData(nextChangeId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of apiToInputStream method, of class SpeedTesting.
//     */
//    @org.junit.Test
//    public void testApiToInputStream() throws Exception {
//        System.out.println("apiToInputStream");
//        SpeedTesting instance = new SpeedTesting();
//        InputStream expResult = null;
//        InputStream result = instance.apiToInputStream();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//

//
//    /**
//     * Test of main method, of class SpeedTesting.
//     */
//    @org.junit.Test
//    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] args = null;
//        SpeedTesting.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}