package workforce;

/**
 * Subclass 2 for Employee parent class - creating a Developer
 * @author egshi
 */

// representing developers responsible for building and maintaining applications
public class Developer extends Employee {
    public Developer(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                     Department department, PositionType position, String jobTitle) {
        // calling the parent constructor with provided values
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
    }
    
    public void writeCode() {
        // simulating a Developer-specific action
        System.out.println(getFullName() + " is writing Java code.");
    }
}
