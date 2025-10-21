package workforce;

/**
 * Subclass 1 for Manager parent class - representing senior-level managers who 
 * oversee multiple departments or teams
 * @author egshi
 */

// creating a Senior Manager class
public class SeniorManager extends Manager {
    public SeniorManager(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                         Department department, PositionType position, String jobTitle) {
        // calling Manager constructor with role title
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle, "Senior Manager");
    }
}

