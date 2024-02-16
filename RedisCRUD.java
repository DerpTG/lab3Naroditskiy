/**
 * Project: Lab3 Database Assignment
 * Purpose Details: Navigate and perform database operations
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/7/2024
 * Last Date Changed: 2/15/2024
 * Rev: 1.0
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * This class provides methods to perform CRUD operations on a Redis database.
 */
public class RedisCRUD {
    private Jedis jedis;

    /**
     * Initializes a connection to the Redis database running on localhost.
     */
    public RedisCRUD() {
        try {
            this.jedis = new Jedis(); // Defaults to "localhost" and port 6379
            System.out.println("Connected to Redis on localhost");
        } catch (JedisConnectionException e) {
            System.err.println("Failed to connect to Redis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inserts a new customer into the Redis database.
     * @param id The ID of the customer.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param city The city of the customer.
     * @param email The email address of the customer.
     */
    public void insertCustomer(String id, String firstName, String lastName, String city, String email) {
        try {
            jedis.hset("customer:" + id, "firstName", firstName);
            jedis.hset("customer:" + id, "lastName", lastName);
            jedis.hset("customer:" + id, "city", city);
            jedis.hset("customer:" + id, "email", email);
            System.out.println("Customer added successfully with ID: " + id);
        } catch (JedisConnectionException e) {
            System.err.println("Error inserting customer: " + e.getMessage());
        }
    }

    /**
     * Retrieves and prints customer details from Redis based on the provided ID.
     * @param id The ID of the customer to retrieve.
     */
    public void getCustomer(String id) {
        try {
            if (jedis.exists("customer:" + id)) {
                System.out.println("Customer found:");
                System.out.println("First Name: " + jedis.hget("customer:" + id, "firstName"));
                System.out.println("Last Name: " + jedis.hget("customer:" + id, "lastName"));
                System.out.println("City: " + jedis.hget("customer:" + id, "city"));
                System.out.println("Email: " + jedis.hget("customer:" + id, "email"));
            } else {
                System.out.println("Customer not found with ID: " + id);
            }
        } catch (JedisConnectionException e) {
            System.err.println("Error retrieving customer: " + e.getMessage());
        }
    }

    /**
     * Updates the details of an existing customer in Redis.
     * @param id The ID of the customer to update.
     * @param firstName The new first name of the customer.
     * @param lastName The new last name of the customer.
     * @param city The new city of the customer.
     * @param email The new email address of the customer.
     */
    public void updateCustomer(String id, String firstName, String lastName, String city, String email) {
        try {
            if (jedis.exists("customer:" + id)) {
                jedis.hset("customer:" + id, "firstName", firstName);
                jedis.hset("customer:" + id, "lastName", lastName);
                jedis.hset("customer:" + id, "city", city);
                jedis.hset("customer:" + id, "email", email);
                System.out.println("Customer updated successfully with ID: " + id);
            } else {
                System.out.println("Customer not found with ID: " + id + " for update.");
            }
        } catch (JedisConnectionException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    /**
     * Deletes a customer from Redis based on the provided ID.
     * @param id The ID of the customer to delete.
     */
    public void deleteCustomer(String id) {
        try {
            if (jedis.exists("customer:" + id)) {
                jedis.del("customer:" + id);
                System.out.println("Customer deleted successfully with ID: " + id);
            } else {
                System.out.println("Customer not found with ID: " + id + " for deletion.");
            }
        } catch (JedisConnectionException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }

    /**
     * Closes the connection to the Redis database.
     */
    public void closeConnection() {
        if (this.jedis != null) {
            jedis.close();
            System.out.println("Connection to Redis closed");
        }
    }
}