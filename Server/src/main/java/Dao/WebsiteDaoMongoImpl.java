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

/**
 * Created by User on 8/28/2017.
 */
public class WebsiteDaoMongoImpl extends AbstractMongoDao<Website> implements WebsitesDao<MongoClient> {
    private static final String WEBSITES_RECORDS_NAME = "Website";

    public WebsiteDaoMongoImpl(MongoClient dataSource) {
        super(dataSource);
    }

    @Override
    public Collection<Website> getWebsites() {
        Collection<Website> c = new ArrayList<Website>();
        MongoCollection<Document> collection = database.getCollection(getRecordsName());

        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            Document d = (Document) it.next();
            Website web = gson.fromJson((String) d.get(OBJECT_RECORD), Website.class);
            c.add(web);
        }

        return c;
    }

    @Override
    protected String getRecordsName() {
        return WEBSITES_RECORDS_NAME;
    }
}
