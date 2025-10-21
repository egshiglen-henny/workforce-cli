package workforce;

import java.util.*;

/**
 * DreamCompanyApp main class - CLI Main application methods with enums and switch-case
 * @author egshi
 */

public class DreamCompanyApp {

    // starting the main CLI-based application loop
    public static void main(String[] args) {
        // setting up scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // creating an instance of the DreamCompany manager
        DreamCompany dreamCompany = new DreamCompany();
        System.out.println("--------------------------------------------------------------------");
        // asking for and validating the filename
        String dataFileName;
        while (true) {
            System.out.print("Please enter the file name to read: \n");
            dataFileName = userInputScanner.nextLine();

            // validating filename ends with .csv
            if (!DreamCompany.isFileNameValid(dataFileName)) {
                System.out.println("ERROR: Invalid filename! Please enter a valid .csv file name.");
                continue;
            }

            try {
                dreamCompany.loadFromFile(dataFileName);
                System.out.println("File read successfully!!!");
                break;
            } catch (Exception exception) {
                System.out.println("ERROR: Could not read the file: " + exception.getMessage() + ". Please try again.");
            }
        }

        System.out.println("--------------------------------------------------------------------");

        // flag to control when to exit the main loop
        boolean shouldExit = false;
        // main application loop that keeps running until user chooses to exit
        while (!shouldExit) {
            System.out.println("""
                               Do you wish to Sort or Search? Please choose an option from the following:
                               1. SORT
                               2. SEARCH
                               3. ADD
                               4. DISPLAY
                               5. REPORT & BEHAVIORS
                               6. EXIT""");

            String mainChoiceInput;
            do {
                System.out.print("Enter choice (1–6): ");
                mainChoiceInput = userInputScanner.nextLine();
                // validating the main menu input choice (must be 1–5)
                if (!DreamCompany.isMenuChoiceValid(mainChoiceInput, 1, 6)) {
                    System.out.println("ERROR: Invalid option! Please enter a number between 1 and 6.");
                }
            } while (!DreamCompany.isMenuChoiceValid(mainChoiceInput, 1, 6));

            int mainChoice = Integer.parseInt(mainChoiceInput);

            // converting numeric menu input into enum choice for easier handling
            MainMenuChoice option = switch (mainChoice) {
                case 1 -> MainMenuChoice.SORT;
                case 2 -> MainMenuChoice.SEARCH;
                case 3 -> MainMenuChoice.ADD;
                case 4 -> MainMenuChoice.DISPLAY;
                case 5 -> MainMenuChoice.REPORT_AND_BEHAVIOR;
                case 6 -> MainMenuChoice.EXIT;
                default -> throw new IllegalArgumentException("ERROR: Invalid main menu option!");
            };

            // handling menu logic based on user choice (sort, search, add, display, exit)
            switch (option) {
                                // handling sorting options
                case SORT -> {
                    System.out.println("--------------------------------------------------------------------");
                    // showing submenu for sort options
                    System.out.println("""
                                       Please select sort option from the following: 
                                       1. NAME
                                       2. SALARY
                                       3. DEPARTMENT""");
                    String sortChoiceInput;
                    do {
                        System.out.print("Enter choice (1–3): ");
                        sortChoiceInput = userInputScanner.nextLine();
                        // validating the sort option input (must be 1–3)
                        if (!DreamCompany.isMenuChoiceValid(sortChoiceInput, 1, 3)) {
                            System.out.println("ERROR: Invalid choice! Please enter a number between 1 and 3.");
                        }
                    } while (!DreamCompany.isMenuChoiceValid(sortChoiceInput, 1, 3));

                    int sortChoice = Integer.parseInt(sortChoiceInput);
                    SortingChoice sortOption = switch (sortChoice) {
                        case 1 -> SortingChoice.NAME;
                        case 2 -> SortingChoice.SALARY;
                        case 3 -> SortingChoice.DEPARTMENT;
                        default -> throw new IllegalArgumentException("ERROR: Invalid sort option! ");
                    };

                    switch (sortOption) {
                        case NAME -> {
                            System.out.println("--------------------------------------------------------------------");
                            dreamCompany.sortByNameRecursive();
                            System.out.println("Sorted by name (Alphabetically): ");
                            dreamCompany.displayAll();
                        }
                        case SALARY -> {
                            System.out.println("--------------------------------------------------------------------");
                            dreamCompany.sortBySalaryDesc();
                            System.out.println("Sorted by Salary (High to Low):");
                            dreamCompany.displayAll();
                        }
                        case DEPARTMENT -> {
                            System.out.println("--------------------------------------------------------------------");
                            dreamCompany.sortByDepartmentThenName();
                            System.out.println("Sorted by department and name (Alphabetically):");
                            dreamCompany.displayAll();
                        }
                    }
                    System.out.println("--------------------------------------------------------------------");
                }
                // handling all search functionality
                case SEARCH -> {
                    System.out.println("--------------------------------------------------------------------");
                    // showing submenu for search choices
                    System.out.println("""
                                       Please select search option from the following: 
                                       1. FULL_NAME 
                                       2. MULTIPLE_NAMES 
                                       3. ROLE""");
                    // 
                    String searchChoiceInput;
                    do {
                        System.out.print("Enter choice (1–3): ");
                        searchChoiceInput = userInputScanner.nextLine();
                        // validating the search menu choice (must be 1–3)
                        if (!DreamCompany.isMenuChoiceValid(searchChoiceInput, 1, 3)) {
                            System.out.println("ERROR: Invalid choice! Please enter a number between 1 and 3.");
                        }
                    } while (!DreamCompany.isMenuChoiceValid(searchChoiceInput, 1, 3));

                    int searchChoice = Integer.parseInt(searchChoiceInput);
                    SearchingChoice searchOption = switch (searchChoice) {
                        case 1 -> SearchingChoice.FULL_NAME;
                        case 2 -> SearchingChoice.MULTIPLE_NAMES;
                        case 3 -> SearchingChoice.ROLE;
                        default -> throw new IllegalArgumentException("ERROR: Invalid search option! Please enter a number between 1 and 3.");
                    };

                    switch (searchOption) {
                        case FULL_NAME -> {
                            System.out.println("--------------------------------------------------------------------");
                            String firstName;
                            do {
                                System.out.print("Please enter First Name: ");
                                firstName = userInputScanner.nextLine();
                                if (!DreamCompany.isFirstNameValid(firstName)) {
                                    System.out.println("ERROR: Invalid first name! Please use only letters.");
                                }
                            } while (!DreamCompany.isFirstNameValid(firstName));

                            String lastName;
                            do {
                                System.out.print("Please enter Last Name: ");
                                lastName = userInputScanner.nextLine();
                                if (!DreamCompany.isLastNameValid(lastName)) {
                                    System.out.println("ERROR: Invalid last name! Please use only letters.");
                                }
                            } while (!DreamCompany.isLastNameValid(lastName));

                            String fullName = firstName + " " + lastName;
                            Employee result = dreamCompany.binarySearchByName(fullName);
                            System.out.println(result != null ? "Found employee: " + result : "ERROR: Could not find employee with the name: " + fullName + ".");
                            System.out.println("--------------------------------------------------------------------");
                        }
                        
                        // handling multiple full name searches with input validation and summary
                        case MULTIPLE_NAMES -> {
                            System.out.println("--------------------------------------------------------------------");
                            // tracking valid and invalid entries
                            int validCount = 0;
                            int invalidCount = 0;
                            
                            System.out.println("Please enter multiple names one by one (First name Last name), or press enter to skip:");
                            for (int i = 1; i <= 5; i++) {
                                
                                // ask user for a full name and allow skipping
                                System.out.print("Enter name №" + i + ": ");
                                String name = userInputScanner.nextLine().trim();
                                // press enter to skip or quit
                                if (name.isEmpty()) break;
                                
                                // split name and validate parts using validators
                                String[] parts = name.split(" ");
                                if (parts.length != 2 ||
                                    !DreamCompany.isFirstNameValid(parts[0]) ||
                                    !DreamCompany.isLastNameValid(parts[1])) {
                                    System.out.println("ERROR: Invalid name format or values: " + name);
                                    invalidCount++;
                                    continue;
                                }
                                
                                // perform binary search and report result
                                Employee result = dreamCompany.binarySearchByName(name);
                                if (result != null) {
                                    System.out.println("Found employee: " + result);
                                    validCount++;
                                } else {
                                    System.out.println("ERROR: Could not find employee with the name: " + name + ".");
                                    validCount++;
                                }
                            }
                            
                            if (validCount == 0 && invalidCount == 5) {
                                System.out.println("All 5 attempts were invalid. No names were searched.");
                            } else {
                                System.out.println("🔍 Search complete. " + validCount + " names searched, " + invalidCount + " invalid entries skipped.");
                            }

                            System.out.println("--------------------------------------------------------------------");
                            
                        }
                        case ROLE -> {
                            System.out.println("--------------------------------------------------------------------");
                            // listing roles to choose from for search
                            System.out.println("""
                                               Please select a role(Employee/Manager type) to search from the following:
                                               1. Developer 
                                               2. QA Engineer
                                               3. Clerk 
                                               4. Team Lead 
                                               5. Assistant Manager 
                                               6. Senior Manager""");

                            String roleChoice;
                            do {
                                System.out.print("Enter choice (1–6): ");
                                roleChoice = userInputScanner.nextLine();
                                // validating role choice input (must be 1–6)
                                if (!DreamCompany.isMenuChoiceValid(roleChoice, 1, 6)) {
                                    System.out.println("ERROR: Invalid option! Please enter a number between 1 and 6.");
                                }
                            } while (!DreamCompany.isMenuChoiceValid(roleChoice, 1, 6));

                            String jobRoleName = switch (Integer.parseInt(roleChoice)) {
                                case 1 -> "Developer";
                                case 2 -> "QA Engineer";
                                case 3 -> "Clerk";
                                case 4 -> "Team Lead";
                                case 5 -> "Assistant Manager";
                                case 6 -> "Senior Manager";
                                default -> "";
                            };
                            System.out.println("--------------------------------------------------------------------");
                            System.out.println("Employee list with the role of " + jobRoleName + ":");
                            dreamCompany.searchByRole(jobRoleName);
                            System.out.println("--------------------------------------------------------------------");
                        }
                    }
                }
                
                // allowing user to add employees (manually or randomly)
                case ADD -> {
                    System.out.println("--------------------------------------------------------------------");
                    // asking the user whether to manually enter data or randomly generate
                    System.out.println("""
                                       Please select add option from the following:
                                       1. Add new employee 
                                       2. Add random employee""");
                    String addChoiceInput;
                    do {
                        System.out.print("Enter choice (1–2): ");
                        addChoiceInput = userInputScanner.nextLine();
                        // validating add option choice (manual or random)
                        if (!DreamCompany.isMenuChoiceValid(addChoiceInput, 1, 2)) {
                            System.out.println("ERROR: Invalid choice! Please enter 1 or 2.");
                        }
                    } while (!DreamCompany.isMenuChoiceValid(addChoiceInput, 1, 2));

                    int addChoice = Integer.parseInt(addChoiceInput);
                    AddingChoice addOption = switch (addChoice) {
                        case 1 -> AddingChoice.MANUAL;
                        case 2 -> AddingChoice.RANDOM;
                        default -> throw new IllegalArgumentException("ERROR: Invalid add option! Please select 1 (Manual) or 2 (Random).");
                    };

                    switch (addOption) {
                        case MANUAL -> {
                            System.out.println("--------------------------------------------------------------------");
                            // collecting user input for first name
                            String firstName;
                            do {
                                System.out.print("Enter first name: ");
                                firstName = userInputScanner.nextLine();
                                if (!DreamCompany.isFirstNameValid(firstName)) {
                                    System.out.println("ERROR: Invalid first name! Please use only letters.");
                                }
                            } while (!DreamCompany.isFirstNameValid(firstName));
                            
                            System.out.println("--------------------------------------------------------------------");
                            // collecting user input for last name
                            String lastName;
                            do {
                                System.out.print("Enter last name: ");
                                lastName = userInputScanner.nextLine();
                                if (!DreamCompany.isLastNameValid(lastName)) {
                                    System.out.println("ERROR: Invalid last name! Please use only letters.");
                                }
                            } while (!DreamCompany.isLastNameValid(lastName));
                            
                            System.out.println("--------------------------------------------------------------------");
                            // gender selection (1 = Female, 2 = Male)
                            System.out.println("""
                                               Please select Gender from the following: 
                                               1. Female
                                               2. Male""");
                            String genderChoice;
                            do {
                                System.out.print("Enter choice (1–2): ");
                                genderChoice = userInputScanner.nextLine();
                                // validating gender choice (1 = Female, 2 = Male)
                                if (!DreamCompany.isMenuChoiceValid(genderChoice, 1, 2)) {
                                    System.out.println("ERROR: Invalid choice! Please enter 1 or 2.");
                                }
                            } while (!DreamCompany.isMenuChoiceValid(genderChoice, 1, 2));

                            String gender = genderChoice.equals("1") ? "Female" : "Male";
                            
                            System.out.println("--------------------------------------------------------------------");
                            // asking for email address
                            String email;
                            do {
                                System.out.print("Enter email address: ");
                                email = userInputScanner.nextLine();
                                if (!DreamCompany.isEmailValid(email)) {
                                    System.out.println("ERROR: Invalid email! Please enter a valid format like company@dream.com.");
                                }
                            } while (!DreamCompany.isEmailValid(email));
                            
                            System.out.println("--------------------------------------------------------------------");
                            // asking for monthly salary input
                            String salaryInput;
                            do {
                                System.out.print("Enter salary amount (1000-10000): ");
                                salaryInput = userInputScanner.nextLine();
                                if (!DreamCompany.isSalaryValid(salaryInput)) {
                                    System.out.println("ERROR: Invalid input! Salary must be a number between 1000 and 10000.");
                                }
                            } while (!DreamCompany.isSalaryValid(salaryInput));
                            
                            // converting string to double after validation
                            double salary = Double.parseDouble(salaryInput);
                            
                            System.out.println("--------------------------------------------------------------------");
                            // asking the user to select from available departments
                            System.out.println("""
                                               Please select Department from the following: 
                                               1. IT Development 
                                               2. HR 
                                               3. Finance""");
                            String departmentChoice;
                            do {
                                System.out.print("Enter choice (1–3): ");
                                departmentChoice = userInputScanner.nextLine();
                                // validating department choice 
                                if (!DreamCompany.isMenuChoiceValid(departmentChoice, 1, 3)) {
                                    System.out.println("ERROR: Invalid choice! Please enter a number between 1 and 3.");
                                }
                            } while (!DreamCompany.isMenuChoiceValid(departmentChoice, 1, 3));
                            
                            String departmentName = switch (departmentChoice) {
                                case "1" -> "IT Development";
                                case "2" -> "HR";
                                case "3" -> "Finance";
                                default -> "IT Development";
                            };
                            Department department = Department.getDepartment(departmentName);
                            
                            System.out.println("--------------------------------------------------------------------");
                            // asking to choose from manager type
                            System.out.println("""
                                               Please select Manager type from the following:
                                               1. Senior Manager
                                               2. Assistant Manager
                                               3. Team Lead""");
                            String jobChoice;
                            do {
                                System.out.print("Enter choice (1–3): ");
                                jobChoice = userInputScanner.nextLine();
                                // validating job choice 
                                if (!DreamCompany.isMenuChoiceValid(jobChoice, 1, 3)) {
                                    System.out.println("ERROR: Invalid choice! Please enter a number between 1 and 3.");
                                }
                            } while (!DreamCompany.isMenuChoiceValid(jobChoice, 1, 3));
                            
                            String job = switch (jobChoice) {
                                case "1" -> "Senior Manager";
                                case "2" -> "Assistant Manager";
                                case "3" -> "Team Lead";
                                default -> "Clerk";
                            };

                            Employee.PositionType position = Employee.PositionType.MIDDLE; // default

                            // using the builder to create an appropriate Employee subclass based on job title
                            Employee newEmployee = DreamCompany.createEmployee(job, firstName, lastName, gender, email, salary, department, position, job);
                            // adding the new employee to the company list
                            dreamCompany.addEmployee(newEmployee);
                            System.out.println("Successfully added employee: " + newEmployee);
                            // writing this employee's info to the output CSV file
                            DreamCompany.appendEmployeeToFile(newEmployee, "applicant_data.csv");
                            
                            // displaying the current employee list
                            System.out.println("--------------------------------------------------------------------");
                            System.out.println("Current employee list: ");
                            dreamCompany.displayAll();
                            System.out.println("--------------------------------------------------------------------");
                        }
                        case RANDOM -> {
                            System.out.println("--------------------------------------------------------------------");
                            // asking how many employees to auto-generate
                            String count;
                            do {
                                System.out.print("Number of random employees to generate: ");
                                count = userInputScanner.nextLine();
                                // validating department choice 
                                if (!DreamCompany.isMenuChoiceValid(count, 1, 10)) {
                                    System.out.println("ERROR: Invalid choice! Please enter a number between 1 and 10.");
                                }
                            } while (!DreamCompany.isMenuChoiceValid(count, 1, 10));
                            // generating the list using the random generator class
                            int countInt = Integer.parseInt(count);
                            ArrayList<Employee> randomEmployeeList = RandomEmployeeDataGenerator.generateRandomEmployee(countInt);
                            for (Employee e : randomEmployeeList) dreamCompany.addEmployee(e);
                            System.out.println("List of added random employees:");
                            for (Employee e : randomEmployeeList) {
                                System.out.println(e);
                            }
                            DreamCompany.appendEmployeesToFile(randomEmployeeList, "applicant_data.csv");
                            // displaying the current employee list
                            System.out.println("--------------------------------------------------------------------");
                            System.out.println("Current employee list: ");
                            dreamCompany.displayAll();
                            System.out.println("--------------------------------------------------------------------");
                        }
                    }
                }
                
                // displaying the first 20 employees
                case DISPLAY -> {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("List of all employees:");
                    dreamCompany.displayAll();
                    System.out.println("--------------------------------------------------------------------");
                }
                
                case REPORT_AND_BEHAVIOR -> {
                    ArrayList<Employee> allEmployees = dreamCompany.getAllEmployees();
                    
                    System.out.println("--------------------------------------------------------------------");
                    // showing report of subclasses
                    DreamCompany.reportBySubclass(allEmployees);
                    System.out.println("--------------------------------------------------------------------");
                    
                    // demonstrating behavior
                    for (Employee employee : allEmployees) {
                        if (employee instanceof Developer developer) developer.writeCode();
                        else if (employee instanceof QAEngineer qa) qa.runTests();
                        else if (employee instanceof Clerk clerk) clerk.manageFiles();
                    }
                    System.out.println("--------------------------------------------------------------------");
                }

                
                // ending the application
                case EXIT -> {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Exiting application. . .");
                    shouldExit = true;
                }
            }
        }
        userInputScanner.close();
    }
}

