package javaswinggui.projectpersonaldetails;
import enumerations.RecordStatus;
import sql.DataProcessing;
import javax.swing.*;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

/**
 * Class Definition: to Process the Contact Details of a general entry (person)
 * This class extends PersonalDetailsForm in a "has a" composite relationship
 * @author jmulhall
 */
public class ContactDetailsForm extends PersonalDetailsForm implements ActionListener {
    
    //declare  vars 
    private JTextField txtEmail;
    private JTextField txtContactNumber;
    private JTextField txtMobileNumber;
    private JTextField txtSkypeHandle;
    private JTextField txtTwitterHandle;
    private JComboBox cboRecordStatus;
    private JButton btnBack;
    private JButton btnExit;
    private JButton btnSave;
    public JFrame frameContactDetails;
    private DataProcessing dataProcessing;
    private boolean badRecord = false;
    
    //declare objs
    DataValidation validate = new DataValidation();
    /*
     * No Args Constructor - used to set up the form as a two stack layout  
     * First stack is details from prior form entered relating to the record
     * Second stack is the new contact details for entry
     */
    public ContactDetailsForm() {
        GridLayout contactStack = new GridLayout(0,2); //houses form panels
        JPanel pnlPersonalDetails = new JPanel();
        try
        {
            pnlPersonalDetails.setLayout(contactStack);
            pnlPersonalDetails.add(new JLabel("FirstName "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(0)));
            pnlPersonalDetails.add(new JLabel("Surname "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(1)));
            pnlPersonalDetails.add(new JLabel("Date of Birth "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(2)));
            pnlPersonalDetails.add(new JLabel("Street Address "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(3)));
            pnlPersonalDetails.add(new JLabel("Area "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(4)));
            pnlPersonalDetails.add(new JLabel("City "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(5)));
            pnlPersonalDetails.add(new JLabel("County "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(6)));
            pnlPersonalDetails.add(new JLabel("Post Code"));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(7)));
            pnlPersonalDetails.add(new JLabel("Country "));
            pnlPersonalDetails.add(new JLabel(super.formDetailsList.get(8)));     
        }
         catch(NullPointerException ne) {
                JOptionPane.showMessageDialog(null, "Error: No Data Inputted, Please Try Again"
                        + "", "System Notice", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        }
        //new fields for entry (modular form)
        txtEmail = new JTextField(20);
        txtContactNumber = new JTextField(20);
        txtMobileNumber = new JTextField(20);
        txtSkypeHandle = new JTextField(15);
        txtTwitterHandle = new JTextField(15);
        cboRecordStatus = new JComboBox(RecordStatus.values());      
        pnlPersonalDetails.add(new JLabel("Email "));
        pnlPersonalDetails.add(txtEmail);
        pnlPersonalDetails.add(new JLabel("Contact Number"));
        pnlPersonalDetails.add(txtContactNumber);
        pnlPersonalDetails.add(new JLabel("Mobile Number"));
        pnlPersonalDetails.add(txtMobileNumber);
        pnlPersonalDetails.add(new JLabel("Skype Handle"));
        pnlPersonalDetails.add(txtSkypeHandle);
        pnlPersonalDetails.add(new JLabel("Twitter Handle"));
        pnlPersonalDetails.add(txtTwitterHandle);
        pnlPersonalDetails.add(new JLabel("Record Status"));
        pnlPersonalDetails.add(cboRecordStatus);
        //buttons
        btnBack = new JButton("Back");
        btnBack.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnBack.addActionListener(this);
        btnSave = new JButton("Save Record");
        btnSave.setAlignmentY(Component.CENTER_ALIGNMENT);
        btnSave.addActionListener(this);
        btnExit = new JButton("Exit");
        btnExit.setAlignmentY(Component.RIGHT_ALIGNMENT);
        btnExit.addActionListener(this);      
        JPanel pnlContactButtons = new JPanel();
        pnlContactButtons.add(btnBack);
        pnlContactButtons.add(btnSave);
        pnlContactButtons.add(btnExit);
        //parent panel
        JPanel pnlParent = new JPanel();
        pnlParent.add(pnlPersonalDetails);
        pnlParent.add(pnlContactButtons);
        //Pass above built panels (pnlParent) to the frame and configure it
        frameContactDetails = new JFrame();
        frameContactDetails.add(pnlParent);
        frameContactDetails.setTitle("Complete Contact Record");
        frameContactDetails.setSize(660, 480);
        frameContactDetails.setLocationRelativeTo(null);
        frameContactDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameContactDetails.setVisible(true);
    }
    /*
     * Getters & Setters
     */
    //setters
    public void setBadRecord(boolean badRecord) {
        this.badRecord = badRecord;
    }
    //getters
    public boolean getBadRecord() {
        return badRecord;
    }
    /*
     * Method 1 - Action Event - action event listeners attached to the buttons on the forms
     */
    @Override
    public void actionPerformed(ActionEvent ap) {
        if(ap.getSource() == btnBack) {
            setBadRecord(false);
            JOptionPane.showMessageDialog(null, "Any data saved in memory for this record will "
                    + "be lost", "System Notice", JOptionPane.INFORMATION_MESSAGE);
            new PersonalDetailsForm();
            super.formDetailsList.clear();
            frameContactDetails.dispatchEvent(new WindowEvent(frameContactDetails, WindowEvent.WINDOW_LOST_FOCUS));
        }       
        else if(ap.getSource() == btnExit) {
            System.exit(0);
        }
        else if(ap.getSource() == btnSave) {
            setBadRecord(false);
            String email = txtEmail.getText();
            if(!validate.validateEmailAddress(email)) { 
                email = "Invalid Email Address"; 
                setBadRecord(true);
            }
            String contactNumber = txtContactNumber.getText();
            if(!validate.validatePhoneNumber(contactNumber)){
                contactNumber = "Invalid Contact Number";
                setBadRecord(true);
            }
            String mobileNumber = txtMobileNumber.getText();
            if(!validate.validatePhoneNumber(mobileNumber)){
                mobileNumber = "Invalid Mobile Number";
                setBadRecord(true);
            }
            String skypeHandle = txtSkypeHandle.getText();
            if(!validate.validateFieldLength(skypeHandle, 15)){
                skypeHandle = skypeHandle.substring(0,15);
                setBadRecord(true);
            }
            String twitterHandle = txtTwitterHandle.getText();
            if(!validate.validateFieldLength(twitterHandle, 15)){
                twitterHandle = twitterHandle.substring(0,15);
                setBadRecord(true);
            }
            String recordStatus = String.valueOf(cboRecordStatus.getSelectedItem());
            //append to static list for saving
            PersonalDetailsForm.formDetailsList.add(email);
            PersonalDetailsForm.formDetailsList.add(contactNumber);
            PersonalDetailsForm.formDetailsList.add(mobileNumber);
            PersonalDetailsForm.formDetailsList.add(skypeHandle);
            PersonalDetailsForm.formDetailsList.add(twitterHandle);
            PersonalDetailsForm.formDetailsList.add(recordStatus);
            //Dev Note: MySql implementation starts here!
            dataProcessing = new DataProcessing();
            if(getBadRecord() == false) {
                if(dataProcessing.insertRecordToDB(formDetailsList)) {
                    JOptionPane.showMessageDialog(null, "Record Saved to Database", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    //New Entry or Exit dialog to complete progam cycle
                    int option = JOptionPane.showConfirmDialog(null,"Transaction Completed - Do you want to "
                            + "enter another record?");
                    if(option == JOptionPane.YES_OPTION) {
                        new PersonalDetailsForm();
                    }
                    else {
                        System.exit(0);       
                    }
                }
            }
            else {
                int option = JOptionPane.showConfirmDialog(null,"Transaction Failed to Save to Database - "
                        + "Do you want to try again");
                if(option == JOptionPane.YES_OPTION) {
                    new PersonalDetailsForm();
                }
                else {
                    System.exit(0);       
                }   
            }
        }
    }
}