package com.example.bruger.feetclinic.DAL;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Stepanenko on 27/04/2016.
 */
public interface IRepository<T> {
    ArrayList<T> getAll() throws IOException;
    T create(T t) throws IOException;
    T get(String id) throws IOException;
    T update(T t) throws IOException;
    T update(T t,String id) throws IOException;
    boolean delete(T t) throws IOException;
    boolean delete(String id) throws IOException;
}
