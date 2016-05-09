package com.example.bruger.feetclinic.BLL.Manager;

import com.example.bruger.feetclinic.BLL.BE.IEntity;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public interface IReadManager <T extends IEntity> {
    ArrayList<T> getAll() throws Exception;
    T get(String id) throws Exception;
}
