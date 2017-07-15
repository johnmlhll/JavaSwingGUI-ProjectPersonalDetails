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
 * Test Class Purpose: Test Class for Unit Testing Data Display Class Details
 * @author jmulhall
 */
public class RecordDisplayTest {
    
    public RecordDisplayTest() {
    }
    //test routine
    @BeforeClass
    public static void setUpClass() {
        System.out.println();
        System.out.println("* Test Start - RecordDisplay: @BeforeClass method");
    }
    /**
     * Test 1 Test of runRecordDisplay method, of class RecordDisplay.
     */
    @Test 
    public void testRunRecordDisplay() {
        System.out.println("Test 1 - runRecordDisplay");
        String lastName = "Skywalker";
        int initialisedColumnCount  = 0;
        RecordDisplay instance = new RecordDisplay();
        instance.runRecordDisplay(lastName);
        //test 1.1 - check display record frame is not null
        assertNotNull("Fail due Display Record Frame is null", instance.displayFrame);
        //test 1.2 - check column count is greater then 0 indicating record display 
        assertNotEquals(initialisedColumnCount, instance.columnCount);
        //test 1.3 - check frame is focusable and is button owner of back button
        assertTrue("Window of Display Frame does not meet Focus Requirements",
                instance.getFocusableWindowState());
        assertTrue("Fail due to back button not been enabled", instance.btnBack.isEnabled());
    }
    /**
     * Test 2 - Test of actionPerformed method, of class RecordDisplay.
     */
    @Test (expected = NullPointerException.class)
    public void testActionPerformed() {
        System.out.println("Test 2 - actionPerformed");
        ActionEvent ba = null;
        RecordDisplay instance = new RecordDisplay();
        instance.actionPerformed(ba);
        //test routine
        assertTrue("Fail due to frame not been active", instance.displayFrame.isActive());
        assertTrue("Fail due to frame not showing on screen ", instance.displayFrame.isShowing());
        assertTrue("Fail due to frame or sub components not been valid", instance.displayFrame.isValid());
        assertNotNull(ba);
    }
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* Test End - RecordDisplay: @AfterClass method");
    }
}
