/** Project: Lab3 Database Assignment
 * Purpose Details: Navigate and perform database operations
 * Course: IST 242
 * Author: Felix Naroditskiy
 * Date Developed: 2/7/2024
 * Last Date Changed: 2/14/2024
 * Rev: 1.0

 */

import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class MongoCRUD {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private String databaseName;
    private String collectionName;

    public MongoCRUD(String databaseName, String collectionName) {
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        initializeConnection();
    }

    private void initializeConnection() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            database = mongoClient.getDatabase(databaseName);
            collection = database.getCollection(collectionName);
        } catch (MongoException e) {
            System.err.println("An error occurred while establishing a connection to MongoDB: " + e.getMessage());
        }
    }

    public void insertCustomer(String firstName, String lastName, String city, String email) {
        try {
            Document newCustomer = new Document("first_name", firstName)
                    .append("last_name", lastName)
                    .append("city", city)
                    .append("email", email);
            collection.insertOne(newCustomer);
            System.out.println("Customer added successfully.");
        } catch (MongoException e) {
            System.err.println("An error occurred while inserting a customer: " + e.getMessage());
        }
    }

    public void listCustomers() {
        try {
            FindIterable<Document> customers = collection.find();
            for (Document customer : customers) {
                System.out.println(customer.toJson());
            }
        } catch (MongoException e) {
            System.err.println("An error occurred while listing customers: " + e.getMessage());
        }
    }

    public void updateCustomer(String oldFirstName, String newFirstName) {
        try {
            Document updatedCustomer = new Document("$set", new Document("first_name", newFirstName));
            collection.updateOne(new Document("first_name", oldFirstName), updatedCustomer);
            System.out.println("Customer with first name '" + oldFirstName + "' has been updated to new first name '" + newFirstName + "'.");
        } catch (MongoException e) {
            System.err.println("An error occurred while updating a customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(String name) {
        try {
            collection.deleteOne(new Document("first_name", name));
            System.out.println("Customer deleted successfully.");
        } catch (MongoException e) {
            System.err.println("An error occurred while deleting a customer: " + e.getMessage());
        }
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    // Getters and Setters
    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        // Reinitialize connection only if mongoClient is already initialized
        if (mongoClient != null) {
            initializeConnection();
        }
    }

    public String getCollectionName() { return collectionName; }
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
        // Reinitialize connection only if mongoClient is already initialized
        if (mongoClient != null) {
            initializeConnection();
        }
    }
}

