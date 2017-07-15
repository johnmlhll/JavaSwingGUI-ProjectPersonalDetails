package javaswinggui.projectpersonaldetails;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Class Purpose: Test Class for Unit Testing Data Validation Class Details
 * @author jmulhall
 */
public class DataValidationTest {
    
    public DataValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println();
        System.out.println("* Test Start - DataValidation: @BeforeClass method");
    }
    /**
     * Test 1 - Test of validateDate method, of class DataValidation.
     */
    @Test
    public void testValidateDate() {
        System.out.println("Test 1 - validateDate");
        //test 1.1 - validate correct entry
        String dateField = "12/03/1988";
        DataValidation instance = new DataValidation();
        boolean expResult = true;
        boolean result = instance.validateDate(dateField);
        assertEquals(expResult, result);
        instance = null;
        //test 1.2 - handle invalid entry
        dateField = "02031988";
        instance  = new DataValidation();
        expResult = false;
        result = instance.validateDate(dateField); 
        assertEquals(expResult, result);
    }
    /**
     * Test 2 - Test of validateFieldLength method, of class DataValidation.
     */
    @Test
    public void testValidateFieldLength() {
        System.out.println("Test 2 - validateFieldLength");
        String goodFieldInput = "This is a test and This";
        String badFieldInput = "This is a test and This and Oops";
        int setLength = 30;
        DataValidation instance = new DataValidation();
        boolean expResult = true;
        //test 2.1 - test valid input < 30 chars
        boolean result = instance.validateFieldLength(goodFieldInput, setLength);
        assertEquals(expResult, result);
        //test 2.2 - test invalid input > 30 chars <  60 chars
        expResult = false;
        result  = instance.validateFieldLength(badFieldInput, setLength);
        assertEquals(expResult, result);
    }

    /**
     * Test 3 - Test of validateEmailAddress method, of class DataValidation.
     */
    @Test
    public void testValidateEmailAddress() {
        System.out.println("Test 3 - validateEmailAddress");
        String goodEmail = "johnmlhll@yahoo.com";
        String badEmail = "thisemailstringisjusttoolongATreallylongdomainnameDOTcom";
        DataValidation instance = new DataValidation();
        boolean expResult = true;
        //test 3.1 - test good email input
        boolean result = instance.validateEmailAddress(goodEmail);
        assertEquals(expResult, result);
        //tests 3.2 bad email input
        result = instance.validateEmailAddress(badEmail);
        assertNotEquals(expResult, result);
    }
    /**
     * Test 4 - Test of validatePhoneNumber method, of class DataValidation.
     */
    @Test
    public void testValidatePhoneNumber() {
        System.out.println("Test 4 - validatePhoneNumber");
        String goodLocalMobilePhonePattern = "0869999999";
        String goodLocalLandPhonePattern = "019999999";
        String goodinternationalPhonePattern = "9999999999";
        String badNumberInputPattern = "00353WHOSCALLING?";
        DataValidation instance = new DataValidation();
        boolean expResult = true;
        //test 4.1 - test valid inputs against patterns
        boolean result = instance.validatePhoneNumber(goodLocalMobilePhonePattern);
        assertEquals("Fail in Local Mobile Number Pattern", expResult, result);
        result = instance.validatePhoneNumber(goodLocalLandPhonePattern);
        assertEquals("Fail in Local Land Number Pattern", expResult, result);
        result = instance.validatePhoneNumber(goodinternationalPhonePattern);
        assertEquals("Fail in Local International Number Pattern", expResult, result);
        //test 4.2 - test bad inputs against patterns
        result = instance.validatePhoneNumber(badNumberInputPattern);
        assertNotEquals("Fail in General Bad Number Inpt", expResult, result);
    }
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* Test End - DataValidation: @AfterClass method");
    }
    
}
