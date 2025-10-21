package workforce;

/**
 * Subclass 3 for Employee parent class - creating a QA Engineer
 * @author egshi
 */

// representing QA engineers who test and verify software quality
public class QAEngineer extends Employee {
    public QAEngineer(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                      Department department, PositionType position, String jobTitle) {
        // initializing the parent class
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
    }
    
    public void runTests() {
        // simulating a Quality Assurance Engineer action
        System.out.println(getFullName() + " is running test cases.");
    }
}
