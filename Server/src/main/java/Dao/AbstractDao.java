package Dao;

import com.mongodb.MongoClient;

import java.io.Serializable;

/**
 * Created by User on 8/28/2017.
 */
public interface AbstractDao<T ,D> {
    void create(T entity);
}
