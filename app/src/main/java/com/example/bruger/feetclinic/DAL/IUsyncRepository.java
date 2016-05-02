package com.example.bruger.feetclinic.DAL;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 01/05/2016.
 */
public interface IUsyncRepository<T> extends IRepository<T> {
    ArrayList<T> getAllUsync() throws Exception;
    ArrayList<T> getAllToDelete() throws Exception;
    ArrayList<T> getAllToUpdate() throws Exception;
    ArrayList<T> getAllToCreate() throws Exception;
}
