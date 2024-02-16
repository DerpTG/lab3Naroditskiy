/** Project: Lab3 Database Assignment
 * Purpose Details: Navigate and perform database operations
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/7/2024
 * Last Date Changed: 2/15/2024
 * Rev: 1.0
 */

/**
 * Import statement for utilizing the Scanner class from the java.util package.
 */
import java.util.Scanner;

/**
 * Main application class that provides a menu-driven interface for database and blockchain interactions.
 */
public class Main {

    /**
     * Entry point to the application. It displays a selection menu to the user for various database operations.
     * @param args Command line arguments passed to the program. These are not used in this application.
     */
    public static void main(String[] args) {
        // Initializes the scanner to capture user input from the console.
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect the database to work with:");
            System.out.println("1. MySQL");
            System.out.println("2. MongoDB");
            System.out.println("3. Redis");
            System.out.println("4. Blockchain");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            /**
             * The user's choice from the menu which determines the next steps in the application flow.
             * This choice will direct the application to interact with different databases or the blockchain, or to exit.
             */
            int choice = scanner.nextInt();
            scanner.nextLine(); // Properly handle the next input.

            if (choice == 1) {
                System.out.println("Interacting with MySQL database.");
                System.out.println("1. Insert a new customer");
                System.out.println("2. List all customers");
                System.out.println("3. Update a customer");
                System.out.println("4. Delete a customer");
                System.out.print("Select an operation: ");

                /**
                 * @param operation The operation number selected by the user to perform specific CRUD action.
                 */
                int operation = scanner.nextInt();
                scanner.nextLine(); // Clears the scanner buffer.

                MySQLCRUD mySQLCRUD = new MySQLCRUD("jdbc:mysql://localhost:3306/MySQLDB", "root", "IST888IST888");

                if (operation == 1) {
                    System.out.println("Please enter customer details.");
                    /**
                     * @param id Unique identifier for the customer.
                     */
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    /**
                     * @param firstName Customer's first name.
                     */
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    /**
                     * @param lastName Customer's last name.
                     */
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    /**
                     * @param city Customer's city.
                     */
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    /**
                     * @param email Customer's email address.
                     */
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    // Invokes the method to insert a new customer record with provided details.
                    mySQLCRUD.insertCustomer(id, firstName, lastName, city, email);
                    System.out.println("Customer added successfully.");
                } else if (operation == 2) {
                    // Lists all customer records from the MySQL database.
                    mySQLCRUD.getAllCustomers();
                    System.out.println("Fetching and printing all customers:");
                }
                else if (operation == 3) {
                    /**
                     * @param updateId The customer's ID for which the record is to be updated.
                     */
                    System.out.print("Enter Customer ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    /**
                     * @param newFirstName New first name to update the customer's record.
                     */
                    System.out.print("Enter new First Name: ");
                    String newFirstName = scanner.nextLine();
                    // Updates the specified customer's first name in the database.
                    mySQLCRUD.updateCustomer(updateId, newFirstName);
                    System.out.println("Customer updated successfully.");
                } else if (operation == 4) {
                    /**
                     * @param deleteId The customer's ID for the record to be deleted.
                     */
                    System.out.print("Enter Customer ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    // Deletes the specified customer's record from the database.
                    mySQLCRUD.deleteCustomer(deleteId);
                    System.out.println("Customer deleted successfully.");
                } else {
                    // Handles invalid operation selection.
                    System.out.println("Invalid operation.");
                }
            } else if (choice == 2) {
                /**
                 * Initializes the MongoDB CRUD operations handler with database and collection names.
                 */
                MongoCRUD mongoDBCRUD = new MongoCRUD("MongoDB", "Customers");

                System.out.println("Interacting with MongoDB database.");
                System.out.println("1. Insert a new customer");
                System.out.println("2. List all customers");
                System.out.println("3. Update a customer");
                System.out.println("4. Delete a customer");
                System.out.print("Select an operation: ");

                /**
                 * @param operation The operation number selected by the user to perform specific CRUD action.
                 */
                int operation = scanner.nextInt();
                scanner.nextLine(); // Clears the scanner buffer.

                if (operation == 1) {
                    System.out.println("Please enter customer details.");
                    /**
                     * @param firstName Customer's first name.
                     */
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    /**
                     * @param lastName Customer's last name.
                     */
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    /**
                     * @param city Customer's city.
                     */
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    /**
                     * @param email Customer's email address.
                     */
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    // Inserts a new customer into MongoDB.
                    mongoDBCRUD.insertCustomer(firstName, lastName, city, email);
                    System.out.println("Inserted new customer into MongoDB.");
                } else if (operation == 2) {
                    // Lists all customer records from MongoDB.
                    mongoDBCRUD.listCustomers();
                    System.out.println("Listing all customers from MongoDB:");
                } else if (operation == 3) {
                    /**
                     * @param oldFirstName The current first name of the customer before update.
                     */
                    System.out.println("Please enter the first name of the customer to update:");
                    String oldFirstName = scanner.nextLine();
                    /**
                     * @param newFirstName The new first name to update the customer's record.
                     */
                    System.out.println("Enter new first name:");
                    String newFirstName = scanner.nextLine();
                    // Updates the customer's first name in MongoDB.
                    mongoDBCRUD.updateCustomer(oldFirstName, newFirstName);
                    System.out.println("Customer updated successfully.");
                } else if (operation == 4) {
                    /**
                     * @param nameToDelete The first name of the customer whose record is to be deleted.
                     */
                    System.out.println("Please enter the first name of the customer to delete:");
                    String nameToDelete = scanner.nextLine();
                    // Deletes the specified customer's record from MongoDB.
                    mongoDBCRUD.deleteCustomer(nameToDelete);
                    System.out.println("Customer deleted successfully.");
                } else {
                    // Handles invalid operation selection.
                    System.out.println("Invalid operation selected.");
                }

                // Closes the MongoDB connection.
                mongoDBCRUD.close();
                System.out.println("MongoDB interaction completed.");
            } else if (choice == 3) {
                RedisCRUD redisCRUD = new RedisCRUD(); // Initialize Redis CRUD operations
                System.out.println("Interacting with Redis database.");
                System.out.println("1. Insert a new customer");
                System.out.println("2. Get a customer's details");
                System.out.println("3. Update a customer's details");
                System.out.println("4. Delete a customer");
                System.out.print("Select an operation: ");

                int operation = scanner.nextInt();
                scanner.nextLine(); // Clears the scanner buffer for next input.

                if (operation == 1) {
                    System.out.println("Inserting a new customer. Please enter the following details:");
                    /**
                     * @param id The unique identifier for the customer.
                     */
                    System.out.print("Customer ID: ");
                    String id = scanner.nextLine();
                    /**
                     * @param firstName The first name of the customer.
                     */
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    /**
                     * @param lastName The last name of the customer.
                     */
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    /**
                     * @param city The city of the customer.
                     */
                    System.out.print("City: ");
                    String city = scanner.nextLine();
                    /**
                     * @param email The email address of the customer.
                     */
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    redisCRUD.insertCustomer(id, firstName, lastName, city, email);
                } else if (operation == 2) {
                    /**
                     * @param id The customer ID to retrieve details for.
                     */
                    System.out.print("Enter Customer ID to retrieve: ");
                    String id = scanner.nextLine();
                    redisCRUD.getCustomer(id);
                } else if (operation == 3) {
                    System.out.println("Updating a customer. Please enter the following details:");
                    /**
                     * @param id The customer ID for the record to update.
                     */
                    System.out.print("Customer ID: ");
                    String id = scanner.nextLine();
                    /**
                     * @param firstName The new first name for the customer.
                     */
                    System.out.print("First Name: ");
                    String firstName = scanner.nextLine();
                    /**
                     * @param lastName The new last name for the customer.
                     */
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    /**
                     * @param city The new city for the customer.
                     */
                    System.out.print("City: ");
                    String city = scanner.nextLine();
                    /**
                     * @param email The new email for the customer.
                     */
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    redisCRUD.updateCustomer(id, firstName, lastName, city, email);
                } else if (operation == 4) {
                    /**
                     * @param id The customer ID to delete.
                     */
                    System.out.print("Enter Customer ID to delete: ");
                    String id = scanner.nextLine();
                    redisCRUD.deleteCustomer(id);
                } else {
                    System.out.println("Invalid operation selected.");
                }

                // Close the connection to Redis
                redisCRUD.closeConnection();
            } else if (choice == 4) {
                BlockchainARM blockchain = new BlockchainARM(4); // Initialize the blockchain with a difficulty of 4

                while (true) {
                    System.out.println("\nBlockchain Operations Menu:");
                    System.out.println("1. Add Customer Data Block");
                    System.out.println("2. Read Blockchain (List All Blocks)");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");

                    int operation = scanner.nextInt();
                    scanner.nextLine(); // Consumes the newline character after input

                    if (operation == 1) {
                        // Adds a customer data block and mines it for Proof of Work
                        System.out.print("Enter customer data to add to the blockchain: ");
                        /**
                         * @param customerData The data of the customer to be added to the blockchain.
                         */
                        String customerData = scanner.nextLine();
                        blockchain.addBlock(customerData);
                        System.out.println("Customer data block added and mined successfully.");
                    } else if (operation == 2) {
                        // Prints the current blockchain
                        System.out.println("Current Blockchain:");
                        blockchain.printBlockchain();
                    } else if (operation == 3) {
                        // Exits the blockchain application
                        System.out.println("Exiting the blockchain application...");
                        break;
                    } else {
                        // Handles invalid operation selection
                        System.out.println("Invalid operation. Please select a valid option.");
                    }
                }
            } else if (choice == 5) {
                    System.out.println("Exiting the program...");
                    break;
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }
}

