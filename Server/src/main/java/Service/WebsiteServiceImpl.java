package Service;

import Dao.WebsitesDao;
import Entities.Website;

import java.util.Collection;

/**
 * Created by User on 8/28/2017.
 */
public class WebsiteServiceImpl implements WebsiteService {
    private WebsitesDao dao;

    public WebsiteServiceImpl(WebsitesDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(Website entity) {
        dao.create(entity);
    }

    @Override
    public Collection<Website> getWebsites() {
        return dao.getWebsites();
    }
}
