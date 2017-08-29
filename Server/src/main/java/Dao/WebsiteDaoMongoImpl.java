package Dao;

import Entities.Website;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class WebsiteDaoMongoImpl extends AbstractMongoDao<Website> implements WebsitesDao<MongoClient> {
    private static final String WEBSITES_RECORDS_NAME = "Website";

    public WebsiteDaoMongoImpl(MongoClient dataSource) {
        super(dataSource);
    }

    @Override
    public Collection<Website> getWebsites() {
        Collection<Website> websites = new ArrayList<Website>();
        MongoCollection<Document> collection = database.getCollection(getRecordsName());

        FindIterable<Document> iterDoc = collection.find();
        Iterator webIterator = iterDoc.iterator();

        while (webIterator.hasNext()) {
            Document webDoc = (Document) webIterator.next();
            Website web = gson.fromJson((String) webDoc.get(OBJECT_RECORD), Website.class);
            websites.add(web);
        }

        return websites;
    }

    @Override
    protected String getRecordsName() {
        return WEBSITES_RECORDS_NAME;
    }
}
