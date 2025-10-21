package workforce;

import java.util.*;
import java.io.*;

/**
 * Company manager class that includes file loading and 
 * structure for recursive sorting and searching, etc
 * @author egshi
 */

public class DreamCompany {
    private ArrayList<Employee> employeeList = new ArrayList<>();

    /* adding a new employee to the employee list
     * since only 20 employees are supposed to be listed in the terminal, 
     * it will delete from top (FIFO) order if it exceeds more than 20 employees
     */
    public void addEmployee(Employee newEmployee) {
        if (employeeList.size() >= 20) {
            employeeList.remove(0); // remove the oldest - first employee
        }
        employeeList.add(newEmployee);//add new employee at the end
    }
    
        /**
    * Utility class for dynamically creating the correct Employee subclass based on job title
    * @author egshi
    */

    // generating employees based on job title input
    public static Employee createEmployee(String jobRoleName, String firstName, String lastName, String genderIdentity,
                                          String emailAddress, double monthlySalary, Department department,
                                          Employee.PositionType position, String jobTitle) {

        jobRoleName = jobRoleName.toLowerCase();

        // deciding which type of employee to make based on the job title
        if (jobRoleName.contains("developer")) 
            return new Developer(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
        if (jobRoleName.contains("qa")) 
            return new QAEngineer(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
        if (jobRoleName.contains("clerk")) 
            return new Clerk(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
        if (jobRoleName.contains("team lead")) 
            return new TeamLead(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
        if (jobRoleName.contains("assistant manager")) 
            return new AssistantManager(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
        if (jobRoleName.contains("senior manager")) 
            return new SeniorManager(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);

        // falling back to developer if nothing matches
        return new Developer(firstName, lastName, genderIdentity, emailAddress, monthlySalary, department, position, jobTitle);
    }

    // loading employee data from a CSV file
    public void loadFromFile(String dataFileName) {
        // got this method from class material
        try (Scanner userInputScanner = new Scanner(new File(dataFileName))) {
             // skipping header line
            if (userInputScanner.hasNextLine()) userInputScanner.nextLine();
            while (userInputScanner.hasNextLine()) {
                String[] parts = userInputScanner.nextLine().split(",");
                if (parts.length >= 8) {
                    String firstName = parts[0], lastName = parts[1], genderIdentity = parts[2], emailAddress = parts[3];
                    double monthlySalary = Double.parseDouble(parts[4]);
                    Department assignedDepartment = Department.getDepartment(parts[5]);
                    Employee.PositionType position;
                    // changing position type to middle by default if the value is empty
                    if (parts[6] == null || parts[6].isBlank()) {
                        position = Employee.PositionType.MIDDLE;
                    } else {
                        try {
                            position = Employee.PositionType.valueOf(parts[6].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            position = Employee.PositionType.MIDDLE;
                        }
                    }

                    String jobTitle = parts[7];
                    Employee newEmployee = createEmployee(jobTitle, firstName, lastName, genderIdentity, emailAddress, monthlySalary, assignedDepartment, position, jobTitle);
                    addEmployee(newEmployee);
                }
            }
        } catch (Exception exception) {
            System.out.println("FILE ERROR: " + exception.getMessage());
        }
    }
    
    /**
    * File writer class for new employee (adding manually or randomly)
    * @author egshi
    */
    private static final String HEADER_LINE = "First Name,Last Name,Gender,Email,Salary,Department,Position,Job Title\n";

    // appending a single employee's data to the specified file
    public static void appendEmployeeToFile(Employee employee, String fileName) {
        try {
            File outputFile = new File(fileName);
            boolean fileAlreadyExists = outputFile.exists();

            try (FileWriter fileWriter = new FileWriter(outputFile, true)) {
                if (!fileAlreadyExists) {
                    fileWriter.write(HEADER_LINE); // writing header only once if file is new
                }
                fileWriter.write(convertToCSV(employee));
                System.out.println("Employee saved to file: " + fileName);
            }

        } catch (IOException exception) {
            System.out.println("Error saving to file: " + exception.getMessage());
        }
    }

    // appending multiple employees' data to the specified file
    public static void appendEmployeesToFile(List<Employee> employeeList, String fileName) {
        try {
            File outputFile = new File(fileName);
            boolean fileAlreadyExists = outputFile.exists();

            try (FileWriter fileWriter = new FileWriter(outputFile, true)) {
                if (!fileAlreadyExists) {
                    fileWriter.write(HEADER_LINE);
                }
                for (Employee employee : employeeList) {
                    fileWriter.write(convertToCSV(employee));
                }
                System.out.println("Result: " + employeeList.size() + " employees saved to " + fileName);
            }

        } catch (IOException exception) {
            System.out.println("Error saving to file: " + exception.getMessage());
        }
    }

    // converting an Employee object into a comma-separated CSV line
    private static String convertToCSV(Employee employee) {
        return employee.firstName + "," +
               employee.lastName + "," +
               employee.genderIdentity + "," +
               employee.emailAddress + "," +
               employee.getSalary() + "," +
               employee.getDepartment().getName() + "," +
               employee.getPosition() + "," +
               employee.getRole() + "\n";
    }
    
    /**
    * Validator class for the user's input in the CLI
    * @author egshi
    */
    // checking if user input is a number within a valid menu range
    public static boolean isMenuChoiceValid(String input, int min, int max) {
            try {
                int value = Integer.parseInt(input);
                return value >= min && value <= max;
            } catch (NumberFormatException exception) {
                return false; // input was not a valid number
            }
        }

        private static String capitalize(String name) {
            if (name == null || name.isBlank()) return "";
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }

        // validating first name: must start with a capital letter and contain only letters, hyphens, or spaces
        public static boolean isFirstNameValid(String firstName) {
            firstName = capitalize(firstName);
            return firstName != null && firstName.matches("[A-Z][a-zA-Z\\- ]{1,}");
        }

        // validating last name with same rules as first name
        public static boolean isLastNameValid(String lastName) {
            lastName = capitalize(lastName);
            return lastName != null && lastName.matches("[A-Z][a-zA-Z\\- ]{1,}");
        }

        // basic pattern matching to ensure email structure is valid
        public static boolean isEmailValid(String email) {
            return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
        }

        // checking if salary input is a number between 1000 and 10000
        public static boolean isSalaryValid(String salaryInput) {
            try {
                double salary = Double.parseDouble(salaryInput);
                return salary >= 1000 && salary <= 10000;
            } catch (NumberFormatException exception) {
                return false;
            }
        }

        // checks if filename ends with .csv and file actually exists
        public static boolean isFileNameValid(String fileName) {
            return fileName != null &&
                   fileName.toLowerCase().endsWith(".csv") &&
                   new File(fileName).exists();
        }
    
    // displaying the first 20 employees in the list only
    public void displayAll() {
        int displayLimit = Math.min(20, employeeList.size());
        for (int i = 0; i < displayLimit; i++) {
            System.out.println(employeeList.get(i));
        }
    }
    
    // starting the recursive insertion sort by name
    public void sortByNameRecursive() {
        // triggering recursive sort by passing in the size of the list
        recursiveSort(employeeList.size());
    }

    // performing recursive sort by name using insertion logic
    private void recursiveSort(int numberOfEmployees) {
        if (numberOfEmployees <= 1) return; // base case to stop recursion

        // calling sort on smaller portion of list
        recursiveSort(numberOfEmployees - 1);

        // taking the last employee in the unsorted portion
        Employee comparisonMember = employeeList.get(numberOfEmployees - 1);

        // inserting the last employee into correct position within the sorted portion
        recursiveInsert(numberOfEmployees - 2, comparisonMember);
    }

    // inserting the comparison employee into the sorted section at the correct index
    private void recursiveInsert(int index, Employee comparisonMember) {
        
        // placing the item if we're at the beginning or correct sorted spot
        if (index < 0 || employeeList.get(index).compareTo(comparisonMember) <= 0) {
            employeeList.set(index + 1, comparisonMember);
            return;
        }

        // shifting item one position to the right
        employeeList.set(index + 1, employeeList.get(index));

        // repeating until correct spot is found
        recursiveInsert(index - 1, comparisonMember);
    }

    // bubble Sort for sorting employees by salary in descending order.
    // after each pass, the highest remaining salary is bubbled to the rightmost unsorted position
    public void sortBySalaryDesc() {
        for (int i = 0; i < employeeList.size(); i++) {
            for (int j = 0; j < employeeList.size() - 1 - i; j++) {
                Employee employee1 = employeeList.get(j);
                Employee employee2 = employeeList.get(j + 1);

                if (employee1.getSalary() < employee2.getSalary()) {
                    // swapping elements if they are out of descending order
                    employeeList.set(j, employee2);
                    employeeList.set(j + 1, employee1);
                }
            }
        }
    }


    // bubble Sort for sorting employees by department, then by full name alphabetically.
    // primary sort by department name, secondary sort by employee's full name when departments match.
    public void sortByDepartmentThenName() {
        //
        for (int i = 0; i < employeeList.size(); i++) {
            for (int j = 0; j < employeeList.size() - 1 - i; j++) {
                Employee employee1 = employeeList.get(j);
                Employee employee2 = employeeList.get(j + 1);

                String department1 = employee1.getDepartment().getName();
                String department2 = employee2.getDepartment().getName();
                String name1 = employee1.getFullName();
                String name2 = employee2.getFullName();

                if (department1.compareToIgnoreCase(department2) > 0 ||
                   (department1.equalsIgnoreCase(department2) && name1.compareToIgnoreCase(name2) > 0)) {
                    // swapping employees when department order is incorrect,
                    // or names are out of order within the same department
                    employeeList.set(j, employee2);
                    employeeList.set(j + 1, employee1);
                }
            }
        }
    }


    // performing binary search to find employee by full name
    public Employee binarySearchByName(String fullNameInput) {
        // making sure list is sorted by name before searching
        sortByNameRecursive();

        // splitting user input to create a dummy comparison object
        String[] fullNameParts = fullNameInput.split(" ");
        String targetFirstName = fullNameParts[0];
        String targetLastName = fullNameParts[1];

        // creating temporary employee to compare against
        Employee comparisonEmployee = new Developer(targetFirstName, targetLastName, "", "", 0.0, null, null, "");

        int searchStartIndex = 0;
        int searchEndIndex = employeeList.size() - 1;

        // performing binary search loop
        while (searchStartIndex <= searchEndIndex) {
            int middleIndex = (searchStartIndex + searchEndIndex) / 2;
            int comparisonResult = employeeList.get(middleIndex).compareTo(comparisonEmployee);

            if (comparisonResult == 0) {
                return employeeList.get(middleIndex); // exact match found
            } else if (comparisonResult < 0) {
                searchStartIndex = middleIndex + 1; // search in upper half
            } else {
                searchEndIndex = middleIndex - 1; // search in lower half
            }
        }
        return null; // no match found
    }
    
    // searching for multiple names and printing any matches found
    public void searchMultipleNames(ArrayList<String> fullNames) {
        // sorting the list once to prepare for binary search
        sortByNameRecursive(); 
        for (String name : fullNames) {
            Employee found = binarySearchByName(name);
            if (found != null) {
                System.out.println(found);
            } else {
                System.out.println(name + " not found.");
            }
        }
    }
    
    // searching employees by job role with exact match on title (ignoring upper/lower case)
    public void searchByRole(String jobRoleName) {
        // linear search
        for (Employee employee : employeeList) {
            // looping through all employees to find job title matches
            if (employee.getRole().equalsIgnoreCase(jobRoleName)) {
                System.out.println(employee);
            }
        }
    }
    
    // generating a report showing counts of each employee subclass
    public static void reportBySubclass(ArrayList<Employee> employees) {
        int developerCount = 0, qualityAssuranceCount = 0, clerkCount = 0, teamLeadCount = 0, assistantManagerCount = 0, seniorManagerCount = 0;

        for (Employee employee : employees) {
            if (employee instanceof Developer) developerCount++;
            else if (employee instanceof QAEngineer) qualityAssuranceCount++;
            else if (employee instanceof Clerk) clerkCount++;
            else if (employee instanceof TeamLead) teamLeadCount++;
            else if (employee instanceof AssistantManager) assistantManagerCount++;
            else if (employee instanceof SeniorManager) seniorManagerCount++;
        }

        System.out.println("Employee role report (by count):");
        System.out.println("Developers: " + developerCount);
        System.out.println("QA Engineers: " + qualityAssuranceCount);
        System.out.println("Clerks: " + clerkCount);
        System.out.println("Team Leads: " + teamLeadCount);
        System.out.println("Assistant Managers: " + assistantManagerCount);
        System.out.println("Senior Managers: " + seniorManagerCount);
    }
    
    public ArrayList<Employee> getAllEmployees() {
        return employeeList;
    }
    
}
