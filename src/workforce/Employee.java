package workforce;

/**
 * Parent class for Manager - representing any person in the company, whether a developer, manager or other staff
 * @author egshi
 */

// defining an abstract class to store common attributes for all employees
public abstract class Employee implements Comparable<Employee> {
    /**
    * Enum 5 for PositionType - listing possible employee ranks or contract status
    * @author egshi
    */

    // defining position levels to classify employee experience or contract type
    public enum PositionType {
       INTERN,     // representing entry-level intern
       JUNIOR,     // representing junior-level staff
       MIDDLE,     // representing mid-level staff
       SENIOR,     // representing senior-level staff
       CONTRACT    // representing fixed-term contract staff
    }
    protected String firstName, lastName, genderIdentity, emailAddress, jobTitle;
    protected double monthlySalary;
    protected Department department;
    protected PositionType position;

    // setting up employee with full constructor info
    public Employee(String firstName, String lastName, String genderIdentity, String emailAddress,
                    double monthlySalary, Department department, PositionType position, String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genderIdentity = genderIdentity;
        this.emailAddress = emailAddress;
        this.monthlySalary = monthlySalary;
        this.department = department;
        this.position = position;
        this.jobTitle = jobTitle;
    }

    // getting the full name by combining first and last
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // returning the employee’s job title
    public String getRole() {
        return jobTitle;
    }

    // returning the department this employee belongs to
    public Department getDepartment() {
        return department;
    }

    // returning the employee’s job position level
    public PositionType getPosition() {
        return position;
    }

    // returning the salary of the employee
    public double getSalary() {
        return monthlySalary;
    }

    // comparing employee names for sorting
    @Override
    public int compareTo(Employee other) {
        return this.getFullName().compareToIgnoreCase(other.getFullName());
    }

    // displaying full employee information nicely
    @Override
    public String toString() {
        return String.format("| %-20s | %-8s | %-30s | €%-7.0f | %-18s | %-10s | %-18s |",
                getFullName(), genderIdentity, emailAddress, monthlySalary,
                department.getName(), position, jobTitle);
    }

}