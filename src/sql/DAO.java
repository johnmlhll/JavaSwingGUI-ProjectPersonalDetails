package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Definition: to handle the data connection with the database 
 * @author jmulhall
 */
public class DAO {
    /*
     * Declare Objects/Vars/Constructors
     */
    Connection dbConn = null;
    String driverUrl = "jdbc:mysql://localhost:3306/records_db?autoReconnect=true&useSSL=false";
    String id = "root";
    String password = new Credentials().getDBPassword();
    //default constructor
    public DAO() {}
    /*
     * Method 1 - Open a Database Connection
     */
    public Connection openConnection() {
        try {
            if(dbConn == null || dbConn.isClosed()) {
               dbConn = DriverManager.getConnection(driverUrl,id, password);
            }
            else {
                System.out.println("The database connection for records_db is already open...");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in establishing database connection to records_db - DAO Class Issue");
        }
        return dbConn;
    }
    /*
     * Method 2 - Close a Database Connection
     */
    public void closeConnection() {
        try {
            if(dbConn.isValid(0)) {
                dbConn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in closing the database connection to records_db - DAO Class Issue");
        }
    }
}

