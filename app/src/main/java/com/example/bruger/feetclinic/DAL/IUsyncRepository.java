package com.example.bruger.feetclinic.DAL;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 01/05/2016.
 */
public interface IUsyncRepository<T>  {
    ArrayList<T> getAllUsync() throws Exception;
    ArrayList<T> getAllDeleted() throws Exception;
    ArrayList<T> getAllUpdated() throws Exception;
    ArrayList<T> getAllCreated() throws Exception;

    boolean approveCreate(T t) throws Exception;
    boolean approveUpdate(T t) throws Exception;
    boolean approveDelete(T t) throws Exception;
    boolean deleteAll() throws Exception;
    boolean createAll(ArrayList<T> list) throws Exception;
}
