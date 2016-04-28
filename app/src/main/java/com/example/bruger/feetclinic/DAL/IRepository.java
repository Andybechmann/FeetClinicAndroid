package com.example.bruger.feetclinic.DAL;

import java.io.IOException;
import java.util.List;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public interface IRepository<T> {
    List<T> getAll() ;
    T create(T t);
    T get(String id);
    T update(T t);
    T update(T t,String id);
    T delete(T t);
    T delete(String id);
}
