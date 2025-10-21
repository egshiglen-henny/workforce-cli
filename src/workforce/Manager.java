package workforce;

/**
 * Subclass 1 for Employee parent class - representing people who manage others, 
 * holding leadership responsibilities
 * @author egshi
 */

// creating the abstract Manager class to extend Employee and add managerLevel
public abstract class Manager extends Employee {
    protected String managerLevel;

    // constructing a Manager with additional level info
    public Manager(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                   Department department, PositionType position, String jobTitle, String ml) {
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
        this.managerLevel = ml;
    }
}
