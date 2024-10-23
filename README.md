The program imports necessary classes for handling file I/O, date management, regex for email validation, and user input.

Main Method:

Initializes a Scanner to read input from the user.
Prompts the user for their full name, validating it to ensure it meets all criteria.
Prompts for the birth date and validates it to ensure the format is correct and not a future date.
Prompts for gender and validates it.
Prompts for an email address, checks its format, and ensures uniqueness.
Prompts for a password and validates its length.
If all validations are successful, the program writes the user's information to a .txt file.
Validation Methods:

isValidFullName: Checks if the full name contains exactly two parts, each longer than 3 letters and free of numbers and special characters.
isValidDate: Validates the birth date format and checks that it is not in the future.
isValidGender: Checks if the gender input is either "Male" or "Female."
isValidEmail: Uses a regex pattern to validate the email format.
isUniqueEmail: Checks if the email already exists in a predefined list (in a real application, this would typically query a database).
isValidPassword: Validates that the password is at least 8 characters long.
File Writing: The program uses a BufferedWriter to create a text file named after the user's email, writing the user's information into it. If an error occurs during this process, it catches the exception and displays an error message.

Completion Message: After successfully writing to the file, it notifies the user that the registration was successful and closes the scanner to free up resources.
