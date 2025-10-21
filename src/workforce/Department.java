package workforce;

/**
 * Parent class for ITDevelopmentDepartment, HRDepartment, and FinanceDepartment - shared logic for departments
 * @author egshi
 */

// creating a base class to represent any department in the company
public abstract class Department {
    protected String name;

    // setting the department name
    public Department(String name) {
        this.name = name;
    }
    // getting the department name
    public String getName() {
        return name;
    }

    // forcing subclasses to describe themselves
    public abstract String getDescription();
    
    /**
    * Utility class that returns a Department subclass object based on the input string
    * @author egshi
    */
    
    // Moved here from DepartmentSelector
    public static Department getDepartment(String name) {
        return switch (name.toLowerCase()) {
            case "hr" -> new HRDepartment();
            case "finance" -> new FinanceDepartment();
            case "it development" -> new ITDevelopmentDepartment();
            default -> new ITDevelopmentDepartment(); // falling back to IT if the name doesn't match known types
        };
    }
}

