package com.example.bruger.feetclinic.DAL.SQLite;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.TherapistORM;

import com.example.bruger.feetclinic.DAL.IReadRepository;
import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistSqlite implements IReadRepository<Therapist> {
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
}
