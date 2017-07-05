package javaswinggui.projectpersonaldetails;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import sql.DataProcessing;

/**
 * Class Definition: to Process the Search Results by last name from Database
 * back to screen via results form
 * @author jmulhall
 */
public class RecordDisplay {
    /*
     * Declare Class Variables and Objects
     */
    DataProcessing dataProcessing = null;
    int rowCount = 0;
    int columnCount = 0;
    /*
     * No-args and Overloaded constructor initialising the class and read only results 
     */
    public RecordDisplay() {}
    public RecordDisplay(String lastName) {
        try {
            dataProcessing = new DataProcessing();
            ResultSet records = dataProcessing.getRecordFromDB(lastName);
            ResultSetMetaData metaData = records.getMetaData();
            //implementation ongoing
        }
        catch(SQLException sql) {
          JOptionPane.showMessageDialog(null, "Something went wrong retrieving records from the database."
                   + "\nDetails are: "+sql.getLocalizedMessage(), "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /*
     * Getters & Setters
     */
    //setters 
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
    //getters
    public int getRowCount() {
        return rowCount;
    }
    public int getColumnCount() {
        return columnCount;
    }
}