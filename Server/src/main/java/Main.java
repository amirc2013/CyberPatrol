/**
 * Created by User on 8/28/2017.
 */

import static spark.Spark.*;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

public class Main {

    public  static void main(String args[]){

        get("/hello", (req, res) -> "Hello World");

        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("myDb");
        System.out.println("Credentials ::"+ credential);
    }

}
