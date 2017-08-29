package Dao;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.InsertOneOptions;
import org.bson.Document;

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
        Document entityToAdd = new Document(OBJECT_RECORD, gson.toJson(entity));
        if (collection.find(entityToAdd).first() == null) {
            collection.insertOne(entityToAdd);
        }
    }

    protected abstract String getRecordsName();
}
