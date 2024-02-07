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
                // MySQL interaction
                System.out.println("Interacting with MySQL database.");
                System.out.println("Please enter customer details.");
                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();
                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();
                System.out.print("Enter City: ");
                String city = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();

                MySQLCRUD mySQLCRUD = new MySQLCRUD("jdbc:mysql://localhost:3306/your_database", "root", "IST888IST888");
                mySQLCRUD.insertCustomer(id, firstName, lastName, city, email);
                System.out.println("Customer added successfully.");
            } else if (choice == 2) {

                // MongoDB interaction would go here
            } else if (choice == 3) {

                // Redis interaction would go here
            } else if (choice == 4) {

                // Blockchain  interaction would go here
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

