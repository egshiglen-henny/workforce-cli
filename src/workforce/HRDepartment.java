package workforce;

/**
 * Subclass 2 for Department parent class - representing the HR department
 * @author egshi
 */

// extending Department to represent the Human Resources team
public class HRDepartment extends Department {
    public HRDepartment() {
        super("HR"); // using super to pass the department name to the parent class constructor
    }

    // giving an explanation of the HR department's responsibilities
    public String getDescription() {
        return "managing employee relations, recruiting new talent, and supporting staff well-being and performance.";
    }
}
