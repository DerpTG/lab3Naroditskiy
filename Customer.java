/**
 * Project: Lab3 Database Assignment
 * Purpose Details: Navigate and perform database operations
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/7/2024
 * Last Date Changed: 2/23/2024
 * Rev: 1.1
 */

/**
 * Represents a customer entity.
 */
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String city;
    private String email;

    /**
     * Constructs a new Customer object with the specified attributes.
     * @param id The ID of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param city The city of the customer.
     * @param email The email of the customer.
     */
    public Customer(String id, String firstName, String lastName, String city, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.email = email;
    }

    /**
     * Returns a string representation of the customer.
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}