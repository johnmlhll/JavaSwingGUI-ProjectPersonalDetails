package javaswinggui.projectpersonaldetails;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sql.DataProcessing;

/**
 * Record Display Class Definition: to Process the Search Results by last name from Database
 * btnBack to screen via results form
 * @author jmulhall
 */
public class RecordDisplay extends JFrame implements ActionListener {
    /*
     * Declare Class Variables and Objects
     */
    DataProcessing dataProcessing = null;
    int rowCount = 0;
    int columnCount = 0;
    JButton btnBack;
    JFrame displayFrame;
    /*
     * No-args constructor initialising the class and processing
     * the display of read only results 
     */
    public RecordDisplay() {}
    
    /*
     * Method 1 - Displays record display/results
     */
    public void runRecordDisplay(String lastName) {
            //declare local process objects
        JTable recordTable;
        JPanel pnlTable;
        JPanel pnlParent;
        JScrollPane tableContainer;
        ResultSet records;
        //routine
        try {
            dataProcessing = new DataProcessing();
            if(lastName.equals("")) {
                 records = dataProcessing.getAllRecordsFromDB();
            }
            else{
                 records = dataProcessing.getRecordFromDB(lastName);
            }
            ResultSetMetaData metaData = records.getMetaData();    
            columnCount = metaData.getColumnCount();
            DefaultTableModel tableModel = new DefaultTableModel();
            //get column names (dev note on process: think long winded SQLAdaptor from ADO.net)
            for(int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }
            //Object Array size of ColumnCount to hold data for table
            Object[] tableData = new Object[columnCount];
            //get row data from results set (db return)
            while (records.next()) {
                for(int i = 0; i < columnCount; i++) {
                    tableData[i] = records.getObject(i+1);
                }
                tableModel.addRow(tableData);
            }      
            //table creation
            recordTable = new JTable();
            recordTable.setModel(tableModel);
            //frame creation
            displayFrame = new JFrame();
            displayFrame.setSize(1100, 400); //size dimension of frame
            //panels creation
            pnlTable = new JPanel();
            pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.Y_AXIS)); //sets up a vertical record display
            tableContainer = new JScrollPane(recordTable);;
            pnlTable.add(tableContainer, BorderLayout.CENTER);
            //button creation
            btnBack = new JButton("Back");
            btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnBack.addActionListener(this);
            //wrap in parent panel
            pnlParent = new JPanel();
            pnlParent.add(pnlTable);
            pnlParent.add(btnBack);
            pnlParent.setLayout(new BoxLayout(pnlParent, BoxLayout.Y_AXIS)); //sets up vertical screen stack
            //add to frame
            //configure frame look
            displayFrame.setTitle("Records Display");
            displayFrame.setLocationRelativeTo(null); //center form on screen
            displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            displayFrame.getContentPane().add(pnlParent);
            displayFrame.setVisible(true);  
        }
        catch(SQLException sql) {
          JOptionPane.showMessageDialog(null, "Something went wrong displaying records from the database."
                   + "\nDetails are: "+sql.getLocalizedMessage(), "System Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /*
     * Method 2 - Event Listener for btnBack button
     */
    @Override
    public void actionPerformed(ActionEvent ba) {
        if(ba.getSource() == btnBack) {
            new PersonalDetailsForm();
            displayFrame.dispose();
        }
    }
}