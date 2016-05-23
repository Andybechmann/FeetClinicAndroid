package com.example.bruger.feetclinic.DAL.SQLite;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.BE.TreatmentORM;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentSqlite implements IUsyncRepository<TreatmentORM,Treatment> {

    @Override
    public ArrayList<TreatmentORM> getAllDeleted() throws Exception {
        ArrayList<TreatmentORM> treatments = new ArrayList<>();
        treatments.addAll( SugarRecord.find(TreatmentORM.class, "deleted = ?", "1") );
        return treatments;
    }

    @Override
    public ArrayList<TreatmentORM> getAllUpdated() throws Exception {
        ArrayList<TreatmentORM> treatments = new ArrayList<>();
        treatments.addAll( SugarRecord.find(TreatmentORM.class, "modified = ?", "1") );
        return treatments;
    }

    @Override
    public ArrayList<TreatmentORM> getAllCreated() throws Exception {
        ArrayList<TreatmentORM> treatments = new ArrayList<>();
        treatments.addAll(SugarRecord.find(TreatmentORM.class, "created = ?", "1"));
        return treatments;
    }

    @Override
    public boolean approveCreate(TreatmentORM treatment) {
        try {
            treatment.setCreated(false);
            SugarRecord.save(treatment);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean approveUpdate(TreatmentORM treatment) {
        try {
            treatment.setModified(false);
            SugarRecord.save(treatment);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean approveDelete(TreatmentORM treatment) {
        try {
            delete(treatment);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean deleteAll(ArrayList<Treatment> list)  {
        try {
            for (Treatment treatment: list ) {
                delete(treatment);
            }
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }


    @Override
    public boolean createAll(ArrayList<Treatment> list) {
        for (Treatment treatment:list){
            try {
                TreatmentORM treatmentORM = new TreatmentORM(treatment);
                treatmentORM.setDeleted(false);
                treatmentORM.setModified(false);
                treatmentORM.setCreated(false);
                SugarRecord.save(treatmentORM);
            } catch (Exception e) {
                return false;
            }
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
        return treatmentORM;
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

        String _id = treatment.get_Id();
        TreatmentORM treatmentToUpdate = getTreatmentOrm(_id);
        treatmentToUpdate.update(treatment);
        treatmentToUpdate.setModified(true);
        SugarRecord.save(treatmentToUpdate);
        return treatmentToUpdate;
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
        treatmentORM.setModified(false);
        treatmentORM.setCreated(false);
        treatmentORM.setDeleted(true);
        SugarRecord.save(treatmentORM);
        return true;
    }

    private TreatmentORM getTreatmentOrm(String id) throws Exception{
        List<TreatmentORM> treatmentsInDb = SugarRecord.find(TreatmentORM.class, "_id = ?", id);
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
