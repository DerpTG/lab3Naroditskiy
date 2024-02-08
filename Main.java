import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect the database to work with:");
            System.out.println("1. MySQL");
            System.out.println("2. MongoDB");
            System.out.println("3. Redis");
            System.out.println("4. Blockchain");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Interacting with MySQL database.");
                System.out.println("1. Insert a new customer");
                System.out.println("2. List all customers");
                System.out.println("3. Update a customer");
                System.out.println("4. Delete a customer");
                System.out.print("Select an operation: ");
                int operation = scanner.nextInt();
                scanner.nextLine();

                MySQLCRUD mySQLCRUD = new MySQLCRUD("jdbc:mysql://localhost:3306/MySQLDB", "root", "IST888IST888");

                if (operation == 1) {
                    System.out.println("Please enter customer details.");
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    mySQLCRUD.insertCustomer(id, firstName, lastName, city, email);
                    System.out.println("Customer added successfully.");
                } else if (operation == 2) {
                    System.out.println("Fetching and printing all customers:");
                    mySQLCRUD.getAllCustomers();
                } else if (operation == 3) {
                    System.out.print("Enter Customer ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new First Name: ");
                    String newFirstName = scanner.nextLine();
                    mySQLCRUD.updateCustomer(updateId, newFirstName);
                    System.out.println("Customer updated successfully.");
                } else if (operation == 4) {
                    System.out.print("Enter Customer ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    mySQLCRUD.deleteCustomer(deleteId);
                    System.out.println("Customer deleted successfully.");
                } else {
                    System.out.println("Invalid operation.");
                }
            } else if (choice == 2) {
                MongoCRUD mongoDBCRUD = new MongoCRUD("MongoDB", "Customers");

                System.out.println("Interacting with MongoDB database.");
                System.out.println("1. Insert a new customer");
                System.out.println("2. List all customers");
                System.out.println("3. Update a customer");
                System.out.println("4. Delete a customer");
                System.out.print("Select an operation: ");
                int mongoOperation = scanner.nextInt();
                scanner.nextLine();

                if (mongoOperation == 1) {
                    // Insert a new customer
                    System.out.println("Please enter customer details.");
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    mongoDBCRUD.insertCustomer(firstName, lastName, city, email);
                    System.out.println("Inserted new customer into MongoDB.");
                } else if (mongoOperation == 2) {
                    // List all customers
                    System.out.println("Listing all customers from MongoDB:");
                    mongoDBCRUD.listCustomers();
                } else if (mongoOperation == 3) {
                    // Update a customer
                    System.out.println("Please enter the first name of the customer to update:");
                    String oldFirstName = scanner.nextLine();
                    System.out.println("Enter new first name:");
                    String newFirstName = scanner.nextLine();
                    mongoDBCRUD.updateCustomer(oldFirstName, newFirstName);
                    System.out.println("Customer updated successfully.");
                } else if (mongoOperation == 4) {
                    // Delete a customer
                    System.out.println("Please enter the first name of the customer to delete:");
                    String nameToDelete = scanner.nextLine();
                    mongoDBCRUD.deleteCustomer(nameToDelete);
                    System.out.println("Customer deleted successfully.");
                } else {
                    System.out.println("Invalid operation selected.");
                }

                // Close MongoDB connection
                mongoDBCRUD.close();
                System.out.println("MongoDB interaction completed.");
            } else if (choice == 3) {

                // Redis
            } else if (choice == 4) {

                // Blockchain
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

