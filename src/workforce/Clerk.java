package workforce;

/**
 * Subclass 4 for Employee parent class - creating a Clerk
 * @author egshi
 */

// representing clerical staff who handle administrative and support tasks
public class Clerk extends Employee {
    public Clerk(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                 Department department, PositionType position, String jobTitle) {
        // setting up base class attributes
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
    }
    

    
    public void manageFiles() {
        // simulating Clerk responsibility
        System.out.println(getFullName() + " is managing office files.");
    }
}
