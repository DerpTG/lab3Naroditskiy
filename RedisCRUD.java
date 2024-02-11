import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisCRUD {
    private Jedis jedis;

    // Simplified constructor to establish a connection to Redis on localhost
    public RedisCRUD() {
        try {
            this.jedis = new Jedis(); // Defaults to "localhost" and port 6379
            System.out.println("Connected to Redis on localhost");
        } catch (JedisConnectionException e) {
            System.err.println("Failed to connect to Redis: " + e.getMessage());
            e.printStackTrace();
        }
    }

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

    // Retrieve a customer by ID
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

    // Update a customer
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

    // Delete a customer
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

    // Close the connection to Redis
    public void closeConnection() {
        if (this.jedis != null) {
            jedis.close();
            System.out.println("Connection to Redis closed");
        }
    }
}