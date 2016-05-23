package com.example.bruger.feetclinic.DAL.SQLite;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.TherapistORM;

import com.example.bruger.feetclinic.DAL.IReadRepository;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistSqlite implements IUsyncRepository<TherapistORM,Therapist> {


    @Override
    public ArrayList<TherapistORM> getAllDeleted() throws Exception {
       ArrayList<TherapistORM> therapists = new ArrayList<>();
        therapists.addAll(SugarRecord.find(TherapistORM.class, "deleted = ?", "1"));
        return therapists;
    }

    @Override
    public ArrayList<TherapistORM> getAllUpdated() throws Exception {
        ArrayList<TherapistORM> therapists = new ArrayList<>();
        therapists.addAll(SugarRecord.find(TherapistORM.class, "modified = ?", "1"));
        return therapists;
    }

    @Override
    public ArrayList<TherapistORM> getAllCreated() throws Exception {
        ArrayList<TherapistORM> therapists = new ArrayList<>();
        therapists.addAll(SugarRecord.find(TherapistORM.class, "created = ?", "1"));
        return therapists;
    }

    @Override
    public boolean approveCreate(TherapistORM therapist) {
        try {
            therapist.setCreated(false);
            SugarRecord.save(therapist);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean approveUpdate(TherapistORM therapist) {

        try {
            therapist.setModified(false);
            SugarRecord.save(therapist);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean approveDelete(TherapistORM therapist) {

        try {
            delete(therapist);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean deleteAll(ArrayList<Therapist> list) {

        try {
            for (Therapist therapist: list ) {
                delete(therapist);
            }
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }


    @Override
    public boolean createAll(ArrayList<Therapist> list) {

        for (Therapist therapist:list){
            try {
                TherapistORM therapistORM = new TherapistORM(therapist);
                therapistORM.setDeleted(false);
                therapistORM.setModified(false);
                therapistORM.setCreated(false);
                SugarRecord.save(therapistORM);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Therapist create(Therapist therapist) throws Exception {
        TherapistORM therapistORM = new TherapistORM(therapist);
        therapistORM.setDeleted(false);
        therapistORM.setModified(false);
        therapistORM.setCreated(true);
        SugarRecord.save(therapistORM);
        return therapistORM;
    }

    @Override
    public Therapist update(Therapist therapist) throws Exception {
        String _id = therapist.get_Id();
        TherapistORM therapistToUpdate = getTherapistOrm(_id);
        therapistToUpdate.update(therapist);
        therapistToUpdate.setModified(true);
        SugarRecord.save(therapistToUpdate);
        return therapistToUpdate;
    }


    @Override
    public Therapist update(Therapist therapist, String id) throws Exception {
        therapist.set_Id(id);
        return update(therapist);
    }

    @Override
    public boolean delete(Therapist therapist) throws Exception {
        return delete(therapist.get_Id());
    }

    @Override
    public boolean delete(String id) throws Exception {
        TherapistORM therapistOrm = getTherapistOrm(id);
        if (therapistOrm == null){
            throw new Exception("Treatment with given id not found");
        }
        therapistOrm.setModified(false);
        therapistOrm.setCreated(false);
        therapistOrm.setDeleted(true);
        SugarRecord.save(therapistOrm);
        return true;
    }

    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        ArrayList<Therapist> therapists = new ArrayList<>();
        for(TherapistORM therapistInDb : SugarRecord.listAll(TherapistORM.class)){
            if (!therapistInDb.isDeleted())
                therapists.add(therapistInDb);
        }
        return therapists;
    }

    @Override
    public Therapist get(String id) throws Exception {
        Therapist therapist = getTherapistOrm(id);
        if (therapist !=null){
            return therapist;
        }
        else throw new Exception("Treatment with given id not found");
    }

    private TherapistORM getTherapistOrm(String id) {
        List<TherapistORM> therapistInDb = SugarRecord.find(TherapistORM.class, "_id = ?", id);
        if (therapistInDb.size() == 0){
            try {
                therapistInDb.add(SugarRecord.findById(TherapistORM.class, Integer.parseInt(id)));
            }
            catch (NumberFormatException e) {
                return null;
            }
        }
        return therapistInDb.get(0);
    }
}
