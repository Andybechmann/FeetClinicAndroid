package com.example.bruger.feetclinic.DAL.REST;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.DAL.IRepository;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistRest implements IRepository<Therapist> {
    @Override
    public Therapist create(Therapist therapist) throws Exception {
        return null;
    }

    @Override
    public Therapist update(Therapist therapist) throws Exception {
        return null;
    }

    @Override
    public Therapist update(Therapist therapist, String id) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Therapist therapist) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        return null;
    }

    @Override
    public Therapist get(String id) throws Exception {
        return null;
    }
}
