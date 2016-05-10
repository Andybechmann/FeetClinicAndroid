package com.example.bruger.feetclinic.DAL;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 01/05/2016.
 */
public interface IUsyncRepository<T extends SuperT,SuperT> extends IRepository<SuperT>  {
    ArrayList<T> getAllDeleted() throws Exception;
    ArrayList<T> getAllUpdated() throws Exception;
    ArrayList<T> getAllCreated() throws Exception;

    boolean approveCreate(T t) ;
    boolean approveUpdate(T t) ;
    boolean approveDelete(T t) ;
    boolean deleteAll(ArrayList<SuperT> list) ;
    boolean createAll(ArrayList<SuperT> list) ;
}
