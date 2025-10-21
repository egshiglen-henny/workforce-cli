package workforce;

/**
 * Subclass 1 for Department parent class - representing the IT department
 * @author egshi
 */

// extending Department to represent the IT department
// declaring ITDevelopmentDepartment subclass and overriding methods
public class ITDevelopmentDepartment extends Department {
    public ITDevelopmentDepartment() {
        // using super to pass the department name to the parent class constructor
        super("IT Development");
    }

    // giving an explanation of this department’s purpose
    public String getDescription() {
        return "leading software design, application development, and technical innovation for internal and client systems.";
    }
}