package General; /**
 * Created by User on 8/28/2017.
 */

import Dao.WebsiteDaoMongoImpl;
import Entities.CriminalClassification;
import Entities.Website;
import Exceptions.CyberPatrolException;
import Service.WebsiteService;
import Service.WebsiteServiceImpl;
import com.google.gson.Gson;
import com.mongodb.*;

import static spark.Spark.*;

public class Bootstrap {
    private static WebsiteService webService;
    private static Gson gson;

    public static void main(String args[]) {
        try {
            Configuration conf = new Configuration();
            MongoClient mc = new MongoClient(conf.getMongoHost(), conf.getMongoPort());

            WebsiteDaoMongoImpl dao = new WebsiteDaoMongoImpl(mc);
            WebsiteService webService = new WebsiteServiceImpl(dao);

            gson = new Gson();
            get("/getWebsites", (req, res) -> gson.toJson(webService.getWebsites()));

            get("/addWebsite/:name/:address/:classification", (req, res) -> {
                String name = req.params(":name");
                String address = req.params(":address");
                CriminalClassification classification = CriminalClassification.valueOf(req.params(":classification"));
                webService.create(new Website(name, address, classification));
                return true;
            });

        } catch (CyberPatrolException e) {
            e.printStackTrace();
        }
    }

    private static void clearDB(MongoClient mc) {
        for (String name : mc.listDatabaseNames()) {
            mc.dropDatabase(name);
        }
    }
}
