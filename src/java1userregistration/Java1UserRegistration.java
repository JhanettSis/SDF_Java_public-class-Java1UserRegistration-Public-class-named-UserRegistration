/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java1userregistration;

import java.io.BufferedWriter; // Import the BufferedWriter class for writing text to a character output stream
import java.io.FileWriter; // Import the FileWriter class for writing characters to a file
import java.io.IOException; // Import the IOException class for handling input/output exceptions
import java.time.LocalDate; // Import the LocalDate class to represent dates
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class for formatting dates
import java.time.format.DateTimeParseException; // Import for handling parsing exceptions related to dates
import java.util.ArrayList; // Import ArrayList class for using resizable arrays
import java.util.List; // Import List interface for creating a list of registered emails
import java.util.Scanner; // Import Scanner class for reading input from the user
import java.util.regex.Pattern; // Import Pattern class for working with regular expressions

/**
 *
 * @author jhane
 * Declaring a public class named UserRegistration. 
 * Public means it's accessible from any other class.
 */
public class Java1UserRegistration { // Public class named UserRegistration

    // Static list to store registered emails to check for uniqueness
    private static final List<String> registeredEmails = new ArrayList<>(); // Initialized as an empty list
    private static final String FILE_NAME = "user_registration.txt"; // Constant filename to save user data

    /**
     * Main method, the entry point of the program. 
     * Public allows it to be called by the Java runtime.
     * @param args the command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to read input from the console
        
        System.out.println("=== User Registration ==="); // Print welcome message
        
        // Collect full name from the user
        String fullName = getFullName(scanner); 
        
        // Collect birth date from the user
        String birthDate = getBirthDate(scanner); 
        
        // Collect gender from the user
        String gender = getGender(scanner); 
        
        // Collect email from the user
        String email = getEmail(scanner); 
        
        // Collect password from the user
        String password = getPassword(scanner); 
        
        // Save user data to a file
        saveUserData(fullName, birthDate, gender, email, password); 
        
        System.out.println("Registration successful! User data has been saved."); // Inform user of success
        scanner.close(); // Close the scanner to prevent resource leaks
    }

    // Method to get and validate full name
    private static String getFullName(Scanner scanner) {
        String fullName; // Declare variable to hold full name
        while (true) { // Loop until valid input is received
            System.out.print("Enter your full name (First Last): "); // Prompt user for full name
            fullName = scanner.nextLine().trim(); // Read input and remove leading/trailing spaces

            // Validate full name
            if (isValidFullName(fullName)) { // Check if the name is valid
                return fullName; // Return valid full name
            } else {
                System.out.println("Invalid name. Please enter a valid full name."); // Prompt to re-enter name
            }
        }
    }

    // Validation for full name
    private static boolean isValidFullName(String fullName) {
        String[] parts = fullName.split(" "); // Split full name into parts
        return parts.length == 2 && // Check if there are exactly two parts
               parts[0].length() > 3 && // Ensure first name has more than 3 letters
               parts[1].length() > 3 && // Ensure last name has more than 3 letters
               !fullName.matches(".*\\d.*") && // Check for numbers
               !fullName.matches(".*[^a-zA-Z\\s].*") && // Check for special characters
               !fullName.startsWith(" ") && // Ensure no leading space
               !fullName.endsWith(" "); // Ensure no trailing space
    }

    // Method to get and validate birth date
    private static String getBirthDate(Scanner scanner) {
        String birthDate; // Declare variable to hold birth date
        while (true) { // Loop until valid input is received
            System.out.print("Enter your birth date (YYYY-MM-DD): "); // Prompt user for birth date
            birthDate = scanner.nextLine().trim(); // Read input and remove leading/trailing spaces

            // Validate birth date
            if (isValidDate(birthDate)) { // Check if date is valid
                return birthDate; // Return valid birth date
            } else {
                System.out.println("Invalid date. Please enter a valid birth date."); // Prompt to re-enter date
            }
        }
    }

    // Validation for date
    private static boolean isValidDate(String date) {
        
        try {
            // Create a DateTimeFormatter for the new format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy"); // Updated pattern
            LocalDate parsedDate = LocalDate.parse(date, formatter); // Parse the date
            return !parsedDate.isAfter(LocalDate.now()); // Check if the date is not in the future
        } catch (DateTimeParseException e) { // Handle parsing exception
            return false; // Return false if date is invalid
        }
    }

    // Method to get and validate gender
    private static String getGender(Scanner scanner) {
        String gender; // Declare variable to hold gender
        while (true) { // Loop until valid input is received
            System.out.print("Select your gender (Male/Female): "); // Prompt user for gender
            gender = scanner.nextLine().trim().toLowerCase(); // Read input, trim spaces, and convert to lowercase
            if (gender.equals("male") || gender.equals("female")) { // Check for valid gender
                return gender.substring(0, 1).toUpperCase() + gender.substring(1); // Return gender with capitalized first letter
            } else {
                System.out.println("Invalid gender. Please select 'Male' or 'Female'."); // Prompt to re-enter gender
            }
        }
    }

    // Method to get and validate email address
    private static String getEmail(Scanner scanner) {
        String email; // Declare variable to hold email
        while (true) { // Loop until valid input is received
            System.out.print("Enter your email address: "); // Prompt user for email
            email = scanner.nextLine().trim(); // Read input and remove leading/trailing spaces

            // Validate email
            if (isValidEmail(email)) { // Check if email is valid
                return email; // Return valid email
            } else {
                System.out.println("Invalid email or email already registered. Please enter a unique email."); // Prompt to re-enter email
            }
        }
    }

    // Email validation
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Regular expression for email validation
        boolean isUnique = !registeredEmails.contains(email); // Check if the email is unique
        return Pattern.matches(emailRegex, email) && isUnique; // Return true if valid and unique
    }

    // Method to get and validate password
    private static String getPassword(Scanner scanner) {
        String password; // Declare variable to hold password
        while (true) { // Loop until valid input is received
            System.out.print("Create a password (minimum 8 characters): "); // Prompt user for password
            password = scanner.nextLine().trim(); // Read input and remove leading/trailing spaces
            if (isValidPassword(password)) { // Check if password is valid
                return password; // Return valid password
            } else {
                System.out.println("Invalid password. Please ensure it meets the criteria."); // Prompt to re-enter password
            }
        }
    }

    // Password validation
    private static boolean isValidPassword(String password) {
        return password.length() >= 8; // Return true if password is at least 8 characters long
    }

    // Method to save user data to a file
    private static void saveUserData(String fullName, String birthDate, String gender, String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) { // Open file in append mode
            writer.write("Full Name: " + fullName); // Write full name to file
            writer.newLine(); // Write a new line
            writer.write("Birth Date: " + birthDate); // Write birth date to file
            writer.newLine(); // Write a new line
            writer.write("Gender: " + gender); // Write gender to file
            writer.newLine(); // Write a new line
            writer.write("Email: " + email); // Write email to file
            writer.newLine(); // Write a new line
            writer.write("Password: " + password); // Write password to file
            writer.newLine(); // Write a new line
            writer.write("-----------------------------------------"); // Separator for different users
            writer.newLine(); // Write a new line
            registeredEmails.add(email); // Add email to the registered list
        } catch (IOException e) { // Handle any IO exceptions
            System.out.println("Error saving user data: " + e.getMessage()); // Print error message
        }
    }
}
