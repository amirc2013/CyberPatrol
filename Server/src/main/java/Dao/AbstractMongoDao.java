package Dao;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

/**
 * Created by User on 8/28/2017.
 */
public abstract class AbstractMongoDao<T> implements AbstractDao<T, MongoClient> {
    protected static final String OBJECT_RECORD = "object";
    protected MongoClient client;
    protected MongoDatabase database;
    protected Gson gson;

    public AbstractMongoDao(MongoClient dataSource) {
        gson = new Gson();
        client = dataSource;
        database = client.getDatabase(getRecordsName());
    }

    @Override
    public void create(T entity) {
        MongoCollection<Document> collection = database.getCollection(getRecordsName());
        collection.insertOne(new Document(OBJECT_RECORD, gson.toJson(entity)));
    }

    protected abstract String getRecordsName();
}
