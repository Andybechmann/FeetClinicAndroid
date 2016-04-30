package com.example.bruger.feetclinic.DAL.Treatment;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class SqlRepo implements IRepository<Treatment> {
    @Override
    public ArrayList<Treatment> getAll() {
        return null;
    }

    @Override
    public Treatment create(Treatment treatment) {
        return null;
    }

    @Override
    public Treatment get(String id) {
        return null;
    }

    @Override
    public Treatment update(Treatment treatment) {
        return null;
    }

    @Override
    public Treatment update(Treatment treatment, String id) {
        return null;
    }

    @Override
    public boolean delete(Treatment treatment) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
