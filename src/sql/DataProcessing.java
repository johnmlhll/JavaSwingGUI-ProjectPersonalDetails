package sql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Class Definition: to insert data to the database
 * @author jmulhall
 */
public class DataProcessing extends DAO {
    /*
     * Method 1 - Insert Data Record to DB
     */
    public boolean insertRecordToDB(List record) {
        //local var declaration
        boolean validInsert = false;
        String uspInsertRecord = "{CALL usp_insert_record(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
   
        String firstName = record.get(0).toString();
        String lastName = record.get(1).toString();
        String dateOfBirth = record.get(2).toString();
        String streetAddress = record.get(3).toString();
        String area = record.get(4).toString();
        String city = record.get(5).toString();
        String county = record.get(6).toString();
        String postCode = record.get(7).toString();
        String country = record.get(8).toString();
        String email  = record.get(9).toString();
        String contactNumber = record.get(10).toString();
        String mobileNumber = record.get(11).toString();
        String skypeHandle = record.get(12).toString();
        String twitterHandle =  record.get(13).toString();
        String recordStatus = record.get(14).toString();

        try {
           CallableStatement insertRecord = openConnection().prepareCall(uspInsertRecord);
           insertRecord.setString(1, firstName);
           insertRecord.setString(2, lastName);
           insertRecord.setString(3, dateOfBirth);
           insertRecord.setString(4, streetAddress);
           insertRecord.setString(5, area);
           insertRecord.setString(6, city);
           insertRecord.setString(7, county);
           insertRecord.setString(8, postCode);
           insertRecord.setString(9, country);
           insertRecord.setString(10, email);
           insertRecord.setString(11, contactNumber);
           insertRecord.setString(12, mobileNumber);
           insertRecord.setString(13, skypeHandle);
           insertRecord.setString(14, twitterHandle);
           insertRecord.setString(15, recordStatus);
           ResultSet result = insertRecord.executeQuery();
           if(result != null) {
               validInsert = true;
           }
           closeConnection();
        }
        catch(SQLException sql) {
           JOptionPane.showMessageDialog(null, "Something went wrong with the save to the database."
                   + "\nDetails are: "+sql.getLocalizedMessage(), "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
        return validInsert;
    }
    /*
     * Method 2 - search method - return a specific record(s) from DB
     */
    public ResultSet getRecordFromDB(String lastName) {
        ResultSet result = null;
        String uspGetResults = "{ CALL usp_find_recordby_lastname(?) }";
        try {
            CallableStatement getRecords = openConnection().prepareCall(uspGetResults);
            getRecords.setString(1, lastName);
            result = getRecords.executeQuery();
        }
        catch(SQLException sql) {
            JOptionPane.showMessageDialog(null, "Something went wrong with data retrieval from the database."
                   + "\nDetails are: "+sql.getLocalizedMessage(), "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
        return result;
    }
    /*
     * Method 3 - search method - return all records from DB (limit 500 descending by last_name)
     */
    public ResultSet getAllRecordsFromDB() {
        ResultSet result = null;
        String uspRetrieveAllRecords = "{ CALL usp_retrieve_all_records() }";
        try {
            CallableStatement getAllRecords = openConnection().prepareCall(uspRetrieveAllRecords);
            result = getAllRecords.executeQuery();
        }
        catch(SQLException sql) {
            JOptionPane.showMessageDialog(null, "Something went wrong with data retrieval from the database."
                   + "\nDetails are: "+sql.getLocalizedMessage(), "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
        return result;
    }
}
