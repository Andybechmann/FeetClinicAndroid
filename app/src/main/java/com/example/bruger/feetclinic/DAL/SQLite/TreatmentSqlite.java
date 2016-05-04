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
public class TreatmentSqlite implements IUsyncRepository<Treatment>,IRepository<Treatment> {
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
    public ArrayList<Treatment> getAllDeleted() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isDeleted())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public ArrayList<Treatment> getAllUpdated() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isModified())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public ArrayList<Treatment> getAllCreated() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (treatmentInDb.isCreated())
                treatments.add(treatmentInDb);
        }
        return treatments;
    }

    @Override
    public boolean approveCreate(Treatment treatment) throws Exception {
        TreatmentORM treatmentORM = (TreatmentORM) treatment;
        treatmentORM.setCreated(false);
        SugarRecord.save(treatmentORM);
        return true;
    }

    @Override
    public boolean approveUpdate(Treatment treatment) throws Exception {
        TreatmentORM treatmentORM = (TreatmentORM) treatment;
        treatmentORM.setModified(false);
        SugarRecord.save(treatmentORM);
        return true;
    }

    @Override
    public boolean approveDelete(Treatment treatment) throws Exception {
        TreatmentORM treatmentORM = (TreatmentORM) treatment;
        SugarRecord.delete(treatmentORM);
        return true;
    }

    @Override
    public boolean deleteAll() throws Exception {
        SugarRecord.deleteAll(TreatmentORM.class);
        return true;
    }

    @Override
    public boolean createAll(ArrayList<Treatment> list) {
        for (Treatment treatment:list) {
            TreatmentORM treatmentORM = new TreatmentORM(treatment);
            treatmentORM.setCreated(false);
            treatmentORM.setModified(false);
            treatmentORM.setDeleted(false);
            SugarRecord.save(treatmentORM);
        }
        return true;
    }

    @Override
    public ArrayList<Treatment> getAll() throws Exception {
        ArrayList<Treatment> treatments = new ArrayList<>();
        for(TreatmentORM treatmentInDb : SugarRecord.listAll(TreatmentORM.class)){
            if (!treatmentInDb.isDeleted())
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

    //can find by int id or string id
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
        String _id = treatment.get_Id(); //synchronized with remote DB
        TreatmentORM treatmentORM;
        if (_id == null){
            treatmentORM = (TreatmentORM) treatment;
            long id = treatmentORM.getId();
            treatmentORM = getTreatmentOrm(String.valueOf(id));
            if (treatmentORM ==null){
                throw new Exception("Treatment with given id not found");
            }
            treatmentORM.setModified(true);
            treatmentORM.setDeleted(false);
            treatmentORM.setCreated(false);
            SugarRecord.save(treatmentORM);
        }
        else {
            treatmentORM = getTreatmentOrm(_id);
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
        }
        return treatment;
    }

    @Override
    public Treatment update(Treatment treatment, String id) throws Exception{
        treatment.set_Id(id);
        return update(treatment);
    }

    @Override
    public boolean delete(Treatment treatment) throws Exception{
        String _id = treatment.get_Id();
        if (_id != null){
            return delete(treatment.get_Id());
        }
        else {
            TreatmentORM treatmentORM = (TreatmentORM) treatment;
            long id = treatmentORM.getId();
            return delete(String.valueOf(id));
        }

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
        if (treatmentsInDb.size() == 0){
            try {
                treatmentsInDb.add(SugarRecord.findById(TreatmentORM.class, Integer.parseInt(id)));
            }
            catch (NumberFormatException e) {
                return null;
        }
    }
        return treatmentsInDb.get(0);
    }




}
