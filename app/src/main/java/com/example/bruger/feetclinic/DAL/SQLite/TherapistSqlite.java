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
public class TherapistSqlite implements IUsyncRepository<TherapistORM,Therapist> {


    @Override
    public ArrayList<TherapistORM> getAllDeleted() throws Exception {
        return null;
    }

    @Override
    public ArrayList<TherapistORM> getAllUpdated() throws Exception {
        return null;
    }

    @Override
    public ArrayList<TherapistORM> getAllCreated() throws Exception {
        return null;
    }

    @Override
    public boolean approveCreate(TherapistORM therapistORM) {
        return false;
    }

    @Override
    public boolean approveUpdate(TherapistORM therapistORM) {
        return false;
    }

    @Override
    public boolean approveDelete(TherapistORM therapistORM) {
        return false;
    }

    @Override
    public boolean deleteAll(ArrayList<Therapist> list) {
        return false;
    }


    @Override
    public boolean createAll(ArrayList<Therapist> list) {
        return false;
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
    public ArrayList<Therapist> getAll() throws Exception {
        return null;
    }

    @Override
    public Therapist get(String id) throws Exception {
        return null;
    }
}
