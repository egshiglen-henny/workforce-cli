package workforce;

import java.util.*;

/**
 * Random employee generator class is responsible for generating a list of randomized Employee objects
 * and optionally saving them to a CSV file. It is designed to assist in testing,
 * populating the system with initial data.
 * @author egshi
 */

// generating a list of randomized employee objects and optionally saving to a CSV file
public class RandomEmployeeDataGenerator {

    // arrays of values for randomized employee generation (based on disney characters)
    // declaring possible first names for random generation
    private static final String[] FIRST_NAMES = {
        "Ariel", "Belle", "Elsa", "Anna", "Moana", "Jasmine", "Mulan", "Rapunzel",
        "Tiana", "Merida", "Snow", "Aurora", "Cinderella", "Pocahontas", "Vanellope",
        "Esmeralda", "Wendy", "Megara", "Alice", "Lilo"
    };
    
    // declaring possible last names for combining with first names
    private static final String[] LAST_NAMES = {
        "Mouse", "Duck", "Pan", "Darling", "White", "Beast", "Rider", "Dragon",
        "Triton", "Naveen", "Smith", "Flynn", "Shang", "Li", "Potts", "Fitzherbert",
        "Banks", "March", "LeFou", "Stitch"
    };
    
    // declaring gender options for random assignment
    private static final String[] GENDERS = {"Female", "Male"};
    
    // declaring job titles representing employee roles in the organization
    private static final String[] JOB_TITLES = {
        "Developer", "QA Engineer", "Clerk", "Team Lead", "Assistant Manager", "Senior Manager"
    };
    
    // declaring department names for use in department generation, departments employees can belong to
    private static final String[] DEPARTMENTS = {
        "IT Development", "HR", "Finance"
    };

    // creating shared random instance for consistent value generation
    private static final Random random = new Random();

    /**
     * generating a list of random Employee objects.
     *
     * @param numberOfEntriesToGenerate Number of employees to generate
     * @return A list containing randomized Employee objects
     */
    public static ArrayList<Employee> generateRandomEmployee(int numberOfEntriesToGenerate) {
        // creating a dynamic list to store employees
        ArrayList<Employee> employeeList = new ArrayList<>();
        // repeating the generation process for the requested number of entries
        for (int i = 0; i < numberOfEntriesToGenerate; i++) {
            // selecting a random first name from the list
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            // selecting a random last name from the list
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            // selecting a random gender from available options
            String gender = GENDERS[random.nextInt(GENDERS.length)];
            // formatting email by combining names and converting to lowercase
            String email = (firstName + "." + lastName + "@dream.com").toLowerCase();
            double salary = 1000 + random.nextInt(9001); // gives values from 1000 to 10000 inclusive
            // selecting a random job title from the available list
            String jobTitle = JOB_TITLES[random.nextInt(JOB_TITLES.length)];
            // selecting a random department name, getting the actual Department subclass from Selector class
            Department department = Department.getDepartment(DEPARTMENTS[random.nextInt(DEPARTMENTS.length)]);
            // selecting a random position type from enum
            Employee.PositionType position = Employee.PositionType.values()[random.nextInt(Employee.PositionType.values().length)];

            // dynamically selecting the correct employee subclass using the builder
            Employee newEmployee = DreamCompany.createEmployee(
                jobTitle, firstName, lastName, gender, email, salary, department, position, jobTitle
            );
            
            // adding the new employee to the list
            employeeList.add(newEmployee);
        }
        // returning the filled list of employees
        return employeeList;
    }

    // saving list of employees to a CSV file using the existing file writer utility
    public static void saveToCSV(ArrayList<Employee> employeeList, String filename) {
        // appending csv writing to the EmployeeFileWriter utility
        DreamCompany.appendEmployeesToFile(employeeList, filename);
    }
}
