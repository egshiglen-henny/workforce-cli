package workforce;

/**
 * Subclass 3 for Department parent class - representing the Finance department
 * @author egshi
 */

// extending Department to represent the Finance team
public class FinanceDepartment extends Department {
    public FinanceDepartment() {
        super("Finance"); // using super to pass the department name to the parent class constructor
    }

    // giving an explanation of the Finance department’s responsibilities
    public String getDescription() {
        return "overseeing budgeting, financial planning, payroll, and ensuring overall fiscal stability for the company.";
    }
}