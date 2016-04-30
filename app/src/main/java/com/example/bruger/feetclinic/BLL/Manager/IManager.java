package com.example.bruger.feetclinic.BLL.Manager;

import com.example.bruger.feetclinic.BLL.BE.IEntity;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 29/04/2016.
 */
public interface IManager<T extends IEntity> {
    ArrayList<T> getAll() throws Exception;
    T create(T t) throws Exception;
    T get(String id) throws Exception;
    T update(T t) throws Exception;
    T update(T t,String id) throws Exception;
    boolean delete(T t) throws Exception;
    boolean delete(String id) throws Exception;
}
