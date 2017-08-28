package Dao;

import com.mongodb.MongoClient;

import java.io.Serializable;

public interface AbstractDao<T ,D> {
    void create(T entity);
}
