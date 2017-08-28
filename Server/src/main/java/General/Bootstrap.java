package General;
/**
 * Created by User on 8/28/2017.
 */

import Dao.WebsiteDaoMongoImpl;
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

            enableCORS("*", "*", "*");

            gson = new Gson();

            post("/addWebsite", (request, response) -> {
                webService.create(gson.fromJson(request.body(), Website.class));
                return true;
            });

            get("/getWebsites", (req, res) -> gson.toJson(webService.getWebsites()));

        } catch (CyberPatrolException e) {
            e.printStackTrace();
        }
    }

    private static void clearDB(MongoClient mc) {
        for (String name : mc.listDatabaseNames()) {
            mc.dropDatabase(name);
        }
    }

    // Enables CORS on requests. This method is an initialization method and should be called once.
    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
