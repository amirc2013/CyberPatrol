package Service;

import Entities.Website;

import java.util.Collection;

/**
 * Created by User on 8/28/2017.
 */
public interface WebsiteService extends AbstractService<Website> {
    Collection<Website> getWebsites();
}
