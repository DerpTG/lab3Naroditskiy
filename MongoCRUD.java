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
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection(collectionName);
    }

    public void insertCustomer(String firstName, String lastName, String city, String email) {
        Document newCustomer = new Document("first_name", firstName)
                .append("last_name", lastName)
                .append("city", city)
                .append("email", email);
        collection.insertOne(newCustomer);
        System.out.println("Customer added successfully.");
    }

    public void listCustomers() {
        FindIterable<Document> customers = collection.find();
        for (Document customer : customers) {
            System.out.println(customer.toJson());
        }
    }

    public void updateCustomer(String oldFirstName, String newFirstName) {
        Document updatedCustomer = new Document("$set", new Document("first_name", newFirstName));
        collection.updateOne(new Document("first_name", oldFirstName), updatedCustomer);
        System.out.println("Customer with first name '" + oldFirstName + "' has been updated to new first name '" + newFirstName + "'.");
    }

    public void deleteCustomer(String name) {
        collection.deleteOne(new Document("first_name", name));
        System.out.println("Customer deleted successfully.");
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

    // Getters and Setters for databaseName and collectionName
    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        initializeConnection();
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
        initializeConnection();
    }
}

