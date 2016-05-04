package com.example.bruger.feetclinic.Service.DBSynchronize;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.ISourceManager;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentSynchronizer implements ISynchronizer<Treatment> {

    ISourceManager<Treatment> sourceManager;
    IRepository<Treatment> main;
    IUsyncRepository<Treatment> slave;
    public TreatmentSynchronizer(ISourceManager<Treatment> manager) {
        sourceManager = manager;
        main = sourceManager.getResource();
        slave = sourceManager.getResourceToSynchronize();
    }

    @Override
    public boolean synchronize() {
        if (!sourceManager.canSynchronize()){
            return false;
        }
        ArrayList<Treatment> treatments;
        //create to remote DB
        try {
            treatments = slave.getAllCreated();
            for (Treatment treatment: treatments) {
                main.create(treatment);
                slave.approveCreate(treatment);
            }
        } catch (Exception e) {
            return false;
        }
        //update to remote DB
        try {
            treatments = slave.getAllUpdated();
            for (Treatment treatment: treatments) {
                main.update(treatment);
                slave.approveUpdate(treatment);
            }
        } catch (Exception e) {
            return false;
        }
        //delete to remote DB
        try {
            treatments = slave.getAllDeleted();
            for (Treatment treatment: treatments) {
                if(main.delete(treatment))
                slave.approveDelete(treatment);
            }
        } catch (Exception e) {
            return false;
        }
        try {
            slave.deleteAll();
            slave.createAll(main.getAll());
        } catch (Exception e) {
            return false;
        }
        return true;

    }


}
