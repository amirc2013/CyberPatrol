package Dao;

import Entities.Website;

import java.util.Collection;

public interface WebsitesDao<D> extends AbstractDao<Website,D> {
    Collection<Website> getWebsites();
}
