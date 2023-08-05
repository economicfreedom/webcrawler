package com.test2.webcrawler.crwaling.dao;

public interface DBInterface<T> {
     void insert(T t);

    Integer getMaxId();

}
