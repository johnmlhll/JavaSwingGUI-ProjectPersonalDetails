package javaswinggui.projectpersonaldetails;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Class Purpose: Test Class for Unit Testing Main Method Class Details
 * @author jmulhall
 */
public class JavaSwingGUIProjectPersonalDetailsTest {
    
    public JavaSwingGUIProjectPersonalDetailsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println();
        System.out.println("* Test Start - MainClassJavaSwingGUIProjectPersonalDetails: @BeforeClass method");
    }
    /**
     * Test of main method, of class JavaSwingGUIProjectPersonalDetails.
     */
    @Test
    public void testMain() {
        System.out.println("Test 1 - Main Class Params");
        String[] args = null;
        String expectedArgs1 = "TestInput";
        String expectedArgs2 = "T1";
        JavaSwingGUIProjectPersonalDetails.main(args);
        args = new String[]{"TestInput", "T1"};
        assertEquals("args[0] not found on main method", args[0], expectedArgs1);
        assertEquals("args[1] not found on main method", args[1], expectedArgs2);
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* End Start - MainClassJavaSwingGUIProjectPersonalDetails: @AfterTest method");
    }
}
