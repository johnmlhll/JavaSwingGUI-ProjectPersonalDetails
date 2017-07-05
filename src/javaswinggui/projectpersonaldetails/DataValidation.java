package javaswinggui.projectpersonaldetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Definition; to validate loosely structured data fields in the application
 * to defined data format standards
 * @author jmulhall
 */
public class DataValidation {
    /*
     * Method 1 - Validate date field to dd/MM/yyyy format
     */
    public boolean validateDate(String dateField) {
        boolean isValidDate = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(String.valueOf(dateField));
            String dateStr = dateFormat.format(date);            
            if(dateField.equals(dateStr)) {
                isValidDate = true;
            }
            else {
                JOptionPane.showMessageDialog(null, "Warning: Invalid Data Entered for Date. "
                    + "\nPlease revise using dd/MM/yyyy format!","System Notice", JOptionPane.ERROR_MESSAGE);
            }
                
        }
        catch(ParseException pe) {
            JOptionPane.showMessageDialog(null, "Error: Something went wrong parsing date value. "
                    + "\nPlease revise using dd/MM/yyyy format!","System Notice", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error Parsing Date: Details are:"+pe.getMessage());
        }
        return isValidDate;
    }
    
    /*
     * Method 2 - Validate text field length to specified length (capacity 60 chars)
     */
    public boolean validateFieldLength(String fieldInput, int length) {
            boolean isValidLength = false;
            if(fieldInput.length() <= length) {
                isValidLength = true;
                if(fieldInput.length() > 60) {
                    JOptionPane.showMessageDialog(null, "Warning - Ref:"+fieldInput+": \nInvalid Field Length of "+fieldInput.length()+" "
                    + "\nPlease keep below 60 characters in length","System Notice", JOptionPane.ERROR_MESSAGE);
                    isValidLength = false;
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Warning - Ref:"+fieldInput+": \nInvalid Field Length of "+fieldInput.length()+" "
                    + "\nPlease keep below "+length+" characters in length","System Notice", JOptionPane.ERROR_MESSAGE);
            }
        return isValidLength;
    }
    /*
     * Method 3 - Validate email field length to specified regex email pattern 
     */
    public boolean validateEmailAddress(String emailField) {
        boolean isValidEmail = false;
        String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(emailField.matches(emailRegex) && emailField.length() < 23) {
            isValidEmail = true;
        }
        return isValidEmail;
    }
    /*
     * Method 4 - Validate phone number field field length to specified regex email pattern 
     */
    public boolean validatePhoneNumber(String phoneField) {
        boolean isValidPhoneNumber  = false;
        Pattern mobilePhoneNumberRegex = Pattern.compile("\\d{3}-\\d{7}");
        Pattern landLineRegex = Pattern.compile("\\d{2}-\\d{7}");
        Pattern longAreaCodeRegex = Pattern.compile("\\d{4}-\\d{6}");
        Matcher matchMobileNumber = mobilePhoneNumberRegex.matcher(phoneField);
        Matcher landLineMatcher = landLineRegex.matcher(phoneField);
        Matcher longAreaCodeMatcher = longAreaCodeRegex.matcher(phoneField);
        long phoneNumber = 0;
        try {
            phoneNumber = Long.parseLong(phoneField);
        } 
        catch(NumberFormatException im) {
            System.out.println("Error: Phone Number Failed Validation. Details: "+im.getLocalizedMessage());
            phoneNumber = 0;
        }
        if(matchMobileNumber.matches() || landLineMatcher.matches() || longAreaCodeMatcher.matches() || 
                (phoneNumber != 0 & phoneField.length() < 16)) {
            isValidPhoneNumber = true;
        }
        return isValidPhoneNumber;
    }
    //abcdefghijklmnopqrstuvwxyz
}