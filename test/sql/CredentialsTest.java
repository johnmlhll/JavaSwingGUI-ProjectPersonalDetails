/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jmulhall
 */
public class CredentialsTest {
    
    public CredentialsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("* Test Start - Credentials Class: @BeforeClass method");
    }
    /**
     * Test 1 - Test of setDBPassword method, of class Credentials.
     * @throws java.sql.SQLException
     */
    @Test (expected=MySQLNonTransientConnectionException.class)
    public void testSetDBPassword() throws SQLException {
        System.out.println("Test 1 - setDBPassword");
        String dbPassword = "bad_pwd";
        Credentials instance = new Credentials();
        DAO dao = new DAO();
        instance.setDBPassword(dbPassword);
        Connection conn = DriverManager.getConnection(instance.getUrl(), instance.getUserId(), instance.getDBPassword());
        assertFalse("Fail due to set method failing to set bad password into DB string", conn.isValid(0));
        //confirms that the DAO class is also affected by the bad password
        assertFalse("Fail due to set method failing to stop DAO class connection string", dao.openConnection().isValid(0));
    }

    /**
     * Test 2 - Test of setUrl method, of class Credentials.
     * @throws java.sql.SQLException
     */
    @Test (expected=SQLException.class)
    public void testSetUrl() throws SQLException {
        System.out.println("Test 2 - setUrl");
        String url = "bad_url_supplied"; /* bad url will throw SQLException */
        Credentials instance = new Credentials();
        instance.setUrl(url);
        Connection conn = DriverManager.getConnection(instance.getUrl(), instance.getUserId(), instance.getDBPassword());
        assertFalse("Failed due to bad url credentials class making a successful connection", conn.isValid(0));
        conn.close();
        instance = null;
    }

    /**
     * Test 3 - Test of setUserId method, of class Credentials.
     * @throws java.sql.SQLException
     */
    @Test(expected=SQLException.class)
    public void testSetUserId() throws SQLException {
        System.out.println("Test 3 - setUserId");
        String userId = "bad_id";
        Credentials instance = new Credentials();
        instance.setUserId(userId);
        Connection conn = DriverManager.getConnection(instance.getUrl(), instance.getUserId(), instance.getDBPassword());
        assertFalse("Failed due to a bad user id insert still creating a successful connection", conn.isValid(0));
        conn.close();
        instance = null;
    }

    /**
     * Test 4 - Test of getDBPassword method, of class Credentials.
     * Tests the getUrl, getUseId and getPassword Methods
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetDBPassword() throws SQLException {
        System.out.println("Test 4 - getDBPassword");
        Credentials instance = new Credentials();
        Connection conn = DriverManager.getConnection(instance.getUrl(), instance.getUserId(), instance.getDBPassword());
        assertTrue("Failed due to get methods failing to establish a valid DB Connection", conn.isValid(0));
    }
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* Test End - Credentials Class: @AfterClass method");
    }
}
