package com.example.bruger.feetclinic.DAL.SQLite;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.BE.TreatmentORM;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentSqlite implements IUsyncRepository<Treatment> {
    @Override
    public ArrayList<Treatment> getAllUsync() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (!treatmentInDb.isSynchronized())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public ArrayList<Treatment> getAllToDelete() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isDeleted())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public ArrayList<Treatment> getAllToUpdate() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isModified())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public ArrayList<Treatment> getAllToCreate() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isCreated())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public ArrayList<Treatment> getAll() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isSynchronized())
            treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public Treatment create(Treatment treatment) throws Exception {
        TreatmentORM treatmentORM = new TreatmentORM(treatment);
        treatmentORM.setDeleted(false);
        treatmentORM.setModified(false);
        treatmentORM.setCreated(true);
        SugarRecord.save(treatmentORM);
        return treatment;
    }

    @Override
    public Treatment get(String id) throws Exception {
        Treatment treatment = getTreatmentOrm(id);
        if (treatment !=null){
            return treatment;
        }
        else throw new Exception("Treatment with given id not found");
    }

    @Override
    public Treatment update(Treatment treatment) throws Exception {
        TreatmentORM treatmentORM = getTreatmentOrm(treatment.get_Id());
        if (treatmentORM ==null){
            throw new Exception("Treatment with given id not found");
        }
        long id = treatmentORM.getId();
        treatmentORM = new TreatmentORM(treatment);
        treatmentORM.setId(id);
        treatmentORM.setModified(true);
        treatmentORM.setDeleted(false);
        treatmentORM.setCreated(false);
        SugarRecord.save(treatmentORM);
        return treatment;
    }

    @Override
    public Treatment update(Treatment treatment, String id) throws Exception{
        treatment.set_Id(id);
        return update(treatment);
    }

    @Override
    public boolean delete(Treatment treatment) throws Exception{
        return delete(treatment.get_Id());
    }

    @Override
    public boolean delete(String id) throws Exception{
        TreatmentORM treatmentORM = getTreatmentOrm(id);
        if (treatmentORM == null){
            throw new Exception("Treatment with given id not found");
        }
        treatmentORM.setDeleted(true);
        SugarRecord.save(treatmentORM);
        return true;
    }

    private TreatmentORM getTreatmentOrm(String id) throws Exception{
        List<TreatmentORM> treatmentsInDb = SugarRecord.find(TreatmentORM.class,"_id = ?",id);
        return treatmentsInDb.get(0);
    }


}
