/**
 * Project: Lab3 Database Assignment
 * Purpose Details: Navigate and perform database operations
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/7/2024
 * Last Date Changed: 2/23/2024
 * Rev: 1.1
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * This class provides methods to perform CRUD operations on a Redis database using JSON serialization.
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
     * @param gsonData The Gson serialized data of the customer.
     */
    public void insertCustomer(String id, String gsonData) {
        try {
            jedis.set(id, gsonData);
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
            String json = jedis.get(id);
            if (json != null) {
                System.out.println("Customer found:");
                System.out.println(json);
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
     * @param gsonData The Gson serialized data of the customer.
     */
    public void updateCustomer(String id, String gsonData) {
        try {
            if (jedis.exists(id)) {
                jedis.set(id, gsonData);
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
            if (jedis.exists(id)) {
                jedis.del(id);
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
