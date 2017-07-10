package javaswinggui.projectpersonaldetails;
/**
 * Main Class Program Definition: GUI Form in Java using Swing to create a simple personal
 * details form. This application will use GitHub for version control.
 * @author jmulhall@yahoo.com twitter: @johnmlhll
 */
public class JavaSwingGUIProjectPersonalDetails {

    /**
     * Main Class definition: main method to manage the program starting with the
     * GUI form to input personal details. Future builds include extra form
     * and MySQL DB for Personal and Contact(fk personalId) tables 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //instantiate PersonalDetailsForm and call the constructor
        new PersonalDetailsForm().runPersonalDetailsForm();
    }
    
}
