package workforce;

/**
 * Subclass 3 for Manager parent class - representing team leads who coordinate 
 * and guide smaller development teams
 * @author egshi
 */

// creating a Team Lead class
public class TeamLead extends Manager {
    public TeamLead(String firstName, String lastName, String genderIdentity, String emailAddress, double monthlySalary,
                    Department department, PositionType position, String jobTitle) {
        // passing Team Lead level to Manager constructor
        super(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle, "Team Lead");
    }
}

