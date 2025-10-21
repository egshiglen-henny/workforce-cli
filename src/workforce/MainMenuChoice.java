package workforce;

/**
 * Enum 1 for handling main menu options in the CLI 
 * @author egshi
 */

// creating a menu choice enum to guide the user through the app options
public enum MainMenuChoice {
    SORT,     // allowing the user to sort employees
    SEARCH,   // letting the user search employees
    ADD,      // giving the option to add new employees
    DISPLAY,  // showing all current employee records
    REPORT_AND_BEHAVIOR, // showing employee type report and behaviors
    EXIT      // exiting the application gracefully
}
