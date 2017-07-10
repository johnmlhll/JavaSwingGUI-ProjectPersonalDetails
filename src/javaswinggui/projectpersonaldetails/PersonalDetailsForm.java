package javaswinggui.projectpersonaldetails;
/**
 * Personal Details Form Class Definition: To set up and process the personal details form of the application
 * @author jmulhall
 */
//import classes
import enumerations.County;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

public class PersonalDetailsForm extends JFrame implements ActionListener {
    /*
     * Declare instance objects and variables
     */
    DataValidation validate = new DataValidation();
    protected static List<String> formDetailsList = new ArrayList<>();
    //labels
    private JLabel lblFirstName;
    private JLabel lblSurname;
    private JLabel lblDateOfBirth;
    private JLabel lblStreetAddress;
    private JLabel lblArea;
    private JLabel lblCity;
    private JLabel lblCounty;
    private JLabel lblPostCode;
    private JLabel lblCountry;
    //textboxes
    private JTextField txtFirstName;
    private JTextField txtSurname;
    private JTextField txtDateOfBirth;
    private JTextField txtStreetAddress;
    private JTextField txtArea;
    private JTextField txtCity;
    private JComboBox cboCounty;
    private JTextField txtPostCode;
    private JTextField txtCountry;
    //buttons
    private JButton btnExit;
    private JButton btnSubmit;
    private JButton btnAddRecord;
    private JButton btnSearch;
    private JButton btnClear;
    //frame
    private JFrame framePersonalDetails;
    //input vars
    private String dbQueryVar;
    /*
     * Noargs constructor with GUI declaration and configuration
     */
    public PersonalDetailsForm() {}
    /**
     * method 1 -  Override actionListener method to handle events from buttons
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnSubmit) {
            String firstName = txtFirstName.getText();
            if(!validate.validateFieldLength(firstName, 20)) { firstName = firstName.substring(0, 20);}
            String surname = txtSurname.getText();
            if(!validate.validateFieldLength(surname, 20)) { surname = surname.substring(0, 20);}
            String dateOfBirth = txtDateOfBirth.getText();
            if(!validate.validateDate(dateOfBirth)) { dateOfBirth = "Undefined - Failed Validation"; }
            String streetAddress = txtStreetAddress.getText();
            if(!validate.validateFieldLength(streetAddress, 25)) { streetAddress = streetAddress.substring(0, 25);}
            String area = txtArea.getText();
            if(!validate.validateFieldLength(area, 20)) { area = area.substring(0, 20);}
            String city = txtCity.getText();
            if(!validate.validateFieldLength(city, 20)) { city = city.substring(0, 20);}
            String county = String.valueOf(cboCounty.getSelectedItem());
            if(cboCounty.getSelectedItem().toString().contains("Antrim")) { txtCountry.setText("Northern Ireland"); txtCountry.setEnabled(false);}
            else if(cboCounty.getSelectedItem().toString().contains("Armagh")) { txtCountry.setText("Northern Ireland"); txtCountry.setEnabled(false);}
            else if(cboCounty.getSelectedItem().toString().contains("Derry")) { txtCountry.setText("Northern Ireland"); txtCountry.setEnabled(false);}
            else if(cboCounty.getSelectedItem().toString().contains("Fermanagh")) { txtCountry.setText("Northern Ireland"); txtCountry.setEnabled(false);}
            else if(cboCounty.getSelectedItem().toString().contains("Tyrone")) { txtCountry.setText("Northern Ireland"); txtCountry.setEnabled(false);}
            else if(cboCounty.getSelectedItem().toString().contains("Down")) { txtCountry.setText("Northern Ireland"); txtCountry.setEnabled(false);}
            else if(!cboCounty.getSelectedItem().toString().contains("International")) { txtCountry.setText("Ireland"); txtCountry.setEnabled(false);}
            String postCode = txtPostCode.getText();
            if(!validate.validateFieldLength(postCode, 20)) { postCode = postCode.substring(0, 20);}
            String country = txtCountry.getText();
            if(!validate.validateFieldLength(country, 20)) { country = country.substring(0, 20);}
            formDetailsList.clear(); //clears for single record entry save.
            formDetailsList.add(firstName);
            formDetailsList.add(surname);
            formDetailsList.add(dateOfBirth);
            formDetailsList.add(streetAddress);
            formDetailsList.add(area);
            formDetailsList.add(city);
            formDetailsList.add(county);
            formDetailsList.add(postCode);
            formDetailsList.add(country);
            txtFirstName.setText("");
            txtSurname.setText("");
            txtDateOfBirth.setText("");
            txtStreetAddress.setText("");
            txtArea.setText("");
            txtCity.setText("");
            txtPostCode.setText("");
            txtCountry.setText("");
            if((PersonalDetailsForm.formDetailsList.get(1).equals("") || 
                    PersonalDetailsForm.formDetailsList.get(3).equals("")) ||
                    PersonalDetailsForm.formDetailsList.get(7).equals("")) { 
                btnAddRecord.setEnabled(false);
            }
            else {
                btnAddRecord.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Record for "+firstName+" "
                        +surname+" \nWe have saved your entry In-Memory. "
                        + "Please press 'Add Record' to complete the entry!",
                        "System Notice", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(ae.getSource() == btnAddRecord) {
            new ContactDetailsForm().runContactForm();
            framePersonalDetails.dispatchEvent(new WindowEvent(framePersonalDetails, WindowEvent.WINDOW_LOST_FOCUS));
        }
        else if(ae.getSource() == btnExit) {
            int option = JOptionPane.showConfirmDialog(null, "Do you wish to exit?");
            if(option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            else if(option == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "No problem, you can work away on the application again", 
                        "System Notice", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(option == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "Exit request cancelled"
                        + "", "System Notice", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if(ae.getSource() == btnSearch) {
            dbQueryVar = JOptionPane.showInputDialog(null, "Please Enter the Last/Family Name you wish to search for");
            new RecordDisplay(dbQueryVar);
            framePersonalDetails.dispatchEvent(new WindowEvent(framePersonalDetails, WindowEvent.WINDOW_LOST_FOCUS));
        }
        else if(ae.getSource() == btnClear) {
            txtFirstName.setText("");
            txtSurname.setText("");
            txtDateOfBirth.setText("");
            txtStreetAddress.setText("");
            txtArea.setText("");
            txtCity.setText("");
            txtPostCode.setText("");
            txtCountry.setText("");
            cboCounty.setSelectedIndex(0);
        }
    }
    /**
     * method 2 - Run personal (main) form set up and process form operations
     */
    public void runPersonalDetailsForm() {
     //declare user entry panel (1 of 3)
        JPanel pnlUserEntry = new JPanel();
        pnlUserEntry.setLayout(new BoxLayout(pnlUserEntry, BoxLayout.PAGE_AXIS)); //sets up a vertical stack
        //initialise form text field objs
        txtFirstName = new JTextField(20);
        txtSurname = new JTextField(20);
        txtDateOfBirth = new JTextField(20);
        txtStreetAddress = new JTextField(25);
        txtArea = new JTextField(20);
        txtCity = new JTextField(20);
        cboCounty = new JComboBox(County.values());
        txtPostCode = new JTextField(20);
        txtCountry = new JTextField(20);
        
        lblFirstName = new JLabel("First Name " );
        lblFirstName.setAlignmentX(SwingConstants.LEFT);
        lblSurname = new JLabel("Surname " );
        lblSurname.setAlignmentX(SwingConstants.LEFT);
        lblDateOfBirth = new JLabel("Date of Birth ");
        lblDateOfBirth.setAlignmentX(SwingConstants.LEFT);
        lblStreetAddress = new JLabel("Street Address ");
        lblStreetAddress.setAlignmentX(SwingConstants.LEFT);
        lblArea = new JLabel("Area ");
        lblArea.setAlignmentX(SwingConstants.LEFT);
        lblCity = new JLabel("City ");
        lblCity.setAlignmentX(SwingConstants.LEFT);
        lblCounty = new JLabel("County ");
        lblCounty.setAlignmentX(SwingConstants.LEFT);
        lblPostCode = new JLabel("Post Code ");
        lblPostCode.setAlignmentX(SwingConstants.LEFT);
        lblCountry = new JLabel("Country");
        lblCountry.setAlignmentX(SwingConstants.LEFT);
        //add entries to entry panal
        pnlUserEntry.add(lblFirstName);
        pnlUserEntry.add(txtFirstName);
        pnlUserEntry.add(lblSurname);
        pnlUserEntry.add(txtSurname);
        pnlUserEntry.add(lblDateOfBirth);
        pnlUserEntry.add(txtDateOfBirth);
        pnlUserEntry.add(lblStreetAddress);
        pnlUserEntry.add(txtStreetAddress);
        pnlUserEntry.add(lblArea);
        pnlUserEntry.add(txtArea);
        pnlUserEntry.add(lblCity);
        pnlUserEntry.add(txtCity);
        pnlUserEntry.add(lblCounty);
        pnlUserEntry.add(cboCounty);
        pnlUserEntry.add(lblPostCode);
        pnlUserEntry.add(txtPostCode);
        pnlUserEntry.add(lblCountry);
        pnlUserEntry.add(txtCountry);
        pnlUserEntry.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedSoftBevelBorder(), "Data Entry"));
        //declare user entry panel (2 of 3) and set up action listeners
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.Y_AXIS));
        btnExit = new JButton("Exit");
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(this);
        btnSubmit = new JButton("Submit");
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSubmit.addActionListener(this);
        btnAddRecord = new JButton("Add Record");
        btnAddRecord.setEnabled(false);
        btnAddRecord.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddRecord.addActionListener(this);
        btnSearch = new JButton("Search Records");
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearch.addActionListener(this);
        btnClear = new JButton("Clear");
        btnClear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnClear.addActionListener(this);
        //add buttons to panel
        pnlButtons.add(btnExit);
        pnlButtons.add(btnSubmit);
        pnlButtons.add(btnAddRecord);
        pnlButtons.add(btnSearch);
        pnlButtons.add(btnClear);
        //parent panel to tie entry and button panels together
        //declare user entry panel (3 of 3)
        JPanel pnlParent = new JPanel();
        pnlParent.add(pnlUserEntry);
        pnlParent.add(pnlButtons);
        //declare Main GUI Frame for Form
        framePersonalDetails = new JFrame();
        //add to frame
        framePersonalDetails.add(pnlParent);
        //configure frame look
        framePersonalDetails.setTitle("Personal Details Form");
        framePersonalDetails.setSize(330, 580); //size dimension of frame
        framePersonalDetails.setLocationRelativeTo(null); //center form on screen
        framePersonalDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePersonalDetails.setVisible(true);
    }
}
