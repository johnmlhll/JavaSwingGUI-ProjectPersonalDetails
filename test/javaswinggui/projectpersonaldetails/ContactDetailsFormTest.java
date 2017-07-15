package javaswinggui.projectpersonaldetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaswinggui.projectpersonaldetails.PersonalDetailsForm.formDetailsList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sql.DataProcessing;


/**
 * Test Class Purpose: Test Class for Unit Testing Contact Details
 * @author jmulhall
 */
public class ContactDetailsFormTest {
    
    public ContactDetailsFormTest() {
    }
    /*
     * Before Class Test Method
     */
    @BeforeClass
    public static void setUpClass() {
         System.out.println("* Test Start - ContactDetailsForm: @BeforeClass method");
    }

    /**
     * Test 1 - Test of setBadRecord method, of class ContactDetailsForm.
     */
    @Test
    public void testSetBadRecord() {
        System.out.println("Test 1 - setBadRecord");
        boolean badRecord = false;
        ContactDetailsForm instance = new ContactDetailsForm();
        instance.setBadRecord(badRecord);
        assertEquals(instance.getBadRecord(), badRecord);
    }

    /**
     * Test 2 - Test of getBadRecord method, of class ContactDetailsForm.
     */
    @Test
    public void testGetBadRecord() {
        System.out.println("Test 2 - getBadRecord");
        ContactDetailsForm instance = new ContactDetailsForm();
        boolean result = instance.getBadRecord();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test 3 - Test of actionPerformed method, of class ContactDetailsForm.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("Test 3 - actionPerformed");
        //declare vars
        boolean actualResult = false;
        ResultSet result = null;
        int recordId = 0;
        
        //part 1 - test entry state of frame and in mem list 
        ContactDetailsForm instance = new ContactDetailsForm();
        //ensure class frame is null pre event firing for contacts
        assertNull("Contact Form Frame is not null pre event", instance.frameContactDetails);
        //ensure form list is populated carrying data into the class process method for the event 
        assertNotNull("formDetailsList List is null", PersonalDetailsForm.formDetailsList);
        
        //part 2 test erroneous data additions to the in memory List used for saving records
        DataProcessing dataProcessing = new DataProcessing();
        formDetailsList.add(0, "Test");
        formDetailsList.add(1, "Case");
        formDetailsList.add(2, "01/01/1900");
        formDetailsList.add(3, "4 More Quality Street");
        formDetailsList.add(4, "Quality");
        formDetailsList.add(5, "Dublin City");
        formDetailsList.add(6, "Dublin");
        formDetailsList.add(7, "DB9999");
        formDetailsList.add(8, "Ireland");
        formDetailsList.add(9, "test@case.com");
        formDetailsList.add(10, "019999999");
        formDetailsList.add(11, "0869999999");
        formDetailsList.add(12, "@testcase");
        formDetailsList.add(13, "@twitter");
        formDetailsList.add(14, "Pending");
        instance.runContactForm();
        result = dataProcessing.getRecordFromDB("Case");
        try {
            //run loaded list through method to DB
            assertNotNull("formDetailsList List is null", PersonalDetailsForm.formDetailsList);
            assertFalse(result.isFirst()); //tests if the result has a first column return (recordid)
            if(result.isFirst()) { //if test case made it to db - if statement deletes the entry
                actualResult = true;
                result.close();
                result = dataProcessing.getRecordID("Test", "Case", "Dublin");
                recordId = result.getInt(1);
                boolean deletedRecord = dataProcessing.deleteRecord(recordId);
                System.out.println("Test Record Success Outcome (insert/delete from DB): "+deletedRecord);
            }
        } catch (SQLException ex) {
            System.out.println("DB Error retrieving record (testRunContactForm()): \n"+ex.getLocalizedMessage());
            Logger.getLogger(ContactDetailsFormTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Test 4 - Test of runContactForm method, of class ContactDetailsForm. 
     * NullPointerException handled in the live method with try-catch.
     */
    @Test (expected = IndexOutOfBoundsException.class) 
    public void testRunContactForm() {
        String message = "formDetailsList List is null";
        //routine
        System.out.println("Test 4 - runContactForm");
        ContactDetailsForm instance = new ContactDetailsForm();
        instance.runContactForm();
        //check exception handling - run it again
        formDetailsList.clear();
        instance.runContactForm();
        assertNotNull(message, formDetailsList); //checks arraylist is not null when method is run
        assertNotNull(this); 
    }
    /*
     * After Class Test Method
     */
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* Test End - ContactDetailsForm: @AfterClass method");
    }
}
