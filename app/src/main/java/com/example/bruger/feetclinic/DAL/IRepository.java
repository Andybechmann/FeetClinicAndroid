package com.example.bruger.feetclinic.DAL;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.orm.SugarRecord;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Stepanenko on 27/04/2016.
 */
public interface IRepository<T> extends IReadRepository<T> {
    T create(T t) throws Exception;
    T update(T t) throws Exception;
    T update(T t,String id) throws Exception;
    boolean delete(T t) throws Exception;
    boolean delete(String id) throws Exception;
}
