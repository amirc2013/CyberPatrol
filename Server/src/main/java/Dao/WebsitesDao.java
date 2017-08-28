package Dao;

import Entities.Website;

import java.util.Collection;

/**
 * Created by User on 8/28/2017.
 */
public interface WebsitesDao<D> extends AbstractDao<Website,D> {
    Collection<Website> getWebsites();
}
