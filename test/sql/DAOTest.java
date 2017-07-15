/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jmulhall
 */
public class DAOTest {
    
    public DAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println();
        System.out.println("* Test Start - DAO Class: @BeforeClass method");
    }
    /**
     * Test 1 - Test of openConnection method, of class DAO.
     * @throws java.sql.SQLException
     */
    @Test(expected = MySQLNonTransientConnectionException.class)
    public void testOpenConnection() throws SQLException {
        System.out.println("Test 1 - openConnection");
        DAO instance = new DAO();
        Credentials creds = new Credentials();
        //test 1.1 - checks connection is valid upon openConnection()
        Connection result = instance.openConnection();
        assertTrue(result.isValid(0));
        //test 1.2 - checks for valid connection after one is closed and opened 3 times
        result.close();
        Connection existingConn = instance.openConnection();
        result = instance.openConnection();
        result = instance.openConnection();
        assertTrue("Database connection broken by repeated connection attempts", result.isValid(0));
        result.close();
        existingConn.close();
        //test 1.3 - checks for connection error upon bad credentials supplied to connection string
        Connection badConnection = DriverManager.getConnection(creds.getUrl(), 
                "bad_id", "bad_pwd");
        assertFalse("Database Connection made with false credentials", badConnection.isValid(0));
    }
    /**
     * Test of closeConnection method, of class DAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnection() throws SQLException {
        System.out.println("Test 2 - closeConnection");
        DAO instance = new DAO();
        Connection conn = instance.openConnection();
        assertNotNull("Fail due to connection not been initialised", instance.dbConn);
        assertTrue("Fail due to internal DAO Connection not been valid", instance.dbConn.isValid(0));
        conn.close();
        assertFalse("Fail due to database connection remaining open", instance.dbConn.isValid(0));
    }
    @AfterClass
    public static void tearDownClass() {
        System.out.println("* Test End - DAO Class: @AfterClass method");
    }
    
}
