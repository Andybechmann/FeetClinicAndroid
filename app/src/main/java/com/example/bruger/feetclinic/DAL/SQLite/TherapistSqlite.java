package com.example.bruger.feetclinic.DAL.SQLite;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.TherapistORM;

import com.example.bruger.feetclinic.DAL.IReadRepository;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistSqlite implements IRepository<Therapist>,IUsyncRepository<Therapist> {
    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        ArrayList<Therapist> therapists = new ArrayList<>();
        for(TherapistORM therapistInDb : SugarRecord.listAll(TherapistORM.class)){
                therapists.add(therapistInDb);
        }
        return therapists;
    }

    @Override
    public Therapist get(String id) throws Exception {
        return null;
    }

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
    public ArrayList<Therapist> getAllDeleted() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Therapist> getAllUpdated() throws Exception {
        return null;
    }

    @Override
    public ArrayList<Therapist> getAllCreated() throws Exception {
        return null;
    }

    @Override
    public boolean approveCreate(Therapist therapist) throws Exception {
        return false;
    }

    @Override
    public boolean approveUpdate(Therapist therapist) throws Exception {
        return false;
    }

    @Override
    public boolean approveDelete(Therapist therapist) throws Exception {
        return false;
    }

    @Override
    public boolean deleteAll() throws Exception {
        return false;
    }

    @Override
    public boolean createAll(ArrayList<Therapist> list) throws Exception {
        return false;
    }
}
