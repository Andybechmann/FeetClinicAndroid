package com.example.bruger.feetclinic.DAL;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public interface IReadRepository<T> {
    ArrayList<T> getAll() throws Exception;
    T get(String id) throws Exception;
}
