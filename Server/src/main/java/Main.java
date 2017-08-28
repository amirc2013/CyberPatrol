/**
 * Created by User on 8/28/2017.
 */

import static spark.Spark.*;

import Dao.AbstractDao;
import Dao.WebsiteDaoMongoImpl;
import Entities.CriminalClassification;
import Entities.Website;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.Iterator;
import java.util.Properties;

public class Main {

    private static final String HOST = "host";
    private static final String PORT = "port";
    private static final String CONFIGURATION_FILE_NAME = "config.properties";

    public static void main(String args[]) {
        Properties prop = loadProperties(CONFIGURATION_FILE_NAME);
        MongoClient mc = new MongoClient(prop.getProperty(HOST), Integer.parseInt(prop.getProperty(PORT)));

        // Clear the Database
        clearDB(mc);

        Website web = new Website("fff", "27", CriminalClassification.PEDOPHILE);
        WebsiteDaoMongoImpl dao = new WebsiteDaoMongoImpl(mc);
        dao.create(web);
        for (Website website : dao.getWebsites()) {
            System.out.println(website.getName());
        }
    }

    private static Properties loadProperties(String name) {
        Properties prop = null;
        try (InputStream input = new FileInputStream(name);) {
            prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            prop = null;
        } catch (IOException e) {
            e.printStackTrace();
            prop = null;
        }
        return prop;
    }

    private static void clearDB(MongoClient mc) {
        for (String name : mc.listDatabaseNames()) {
            mc.dropDatabase(name);
        }
    }
}
