package workforce;

/**
 * Subclass 2 for Manager parent class - representing assistant managers 
 * who help lead specific areas under supervision
 * @author egshi
 */

// creating an Assistant Manager class
public class AssistantManager extends Manager {
    public AssistantManager(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                            Department department, PositionType position, String jobTitle) {
        // passing appropriate manager level to parent constructor
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle, "Assistant Manager");
    }
}