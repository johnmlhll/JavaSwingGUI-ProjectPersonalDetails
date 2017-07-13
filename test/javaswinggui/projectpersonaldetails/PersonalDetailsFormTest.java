/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswinggui.projectpersonaldetails;

import java.awt.event.ActionEvent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jmulhall
 */
public class PersonalDetailsFormTest {
    
    public PersonalDetailsFormTest() {
    }
    //Start - build test object
    @BeforeClass
    public static void setUpClass() {
        System.out.println();
        System.out.println("* Test Start - PersonalDetailsForm: @BeforeClass method");
    }
    /**
     * Test of runPersonalDetailsForm method, of class PersonalDetailsForm.
     */
    @Test
    public void testRunPersonalDetailsForm() {
        int expectedCloseOperation = 3; //3 is "hide on close"
        String title = "Personal Details Form";
        System.out.println("Test 1 - runPersonalDetailsForm");
        PersonalDetailsForm instance = new PersonalDetailsForm();
        instance.runPersonalDetailsForm();
        assertNotNull(instance.framePersonalDetails); //is frame artifact set up
        int actualCloseOperation = instance.framePersonalDetails.getDefaultCloseOperation();
        assertTrue(expectedCloseOperation == actualCloseOperation); //is close operation set up properly
        assertEquals(instance.framePersonalDetails.getTitle(), title); //checks title is not changed
        assertTrue(instance.framePersonalDetails.isEnabled()); 
    }

    /**
     * Test of actionPerformed method, of class PersonalDetailsForm.
     */
    @Test(expected = NullPointerException.class)
    public void testActionPerformed() {
        System.out.println("Test 2 - actionPerformed");
        ActionEvent ae = null;
        PersonalDetailsForm instance = new PersonalDetailsForm();
        instance.actionPerformed(ae);
        assertNotNull(instance.getBtnSubmit()); //ensures button is initiallised
        assertTrue(instance.framePersonalDetails.hasFocus()); //ensures underlying frame has focus
        assertTrue(instance.framePersonalDetails.isShowing()); //is on screen
        assertTrue(instance.framePersonalDetails.isActive()); 
        assertNotNull(instance.framePersonalDetails);       
    }
    //End - dispoose of test object
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* Test End - PersonalDetailsForm: @AfterClass method");
    }

}
