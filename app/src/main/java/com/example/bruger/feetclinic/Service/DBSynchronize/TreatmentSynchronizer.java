package com.example.bruger.feetclinic.Service.DBSynchronize;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronize;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentSynchronizer implements ISynchronize<Treatment> {


    @Override
    public boolean synchronize(IRepository<Treatment> main, IUsyncRepository<Treatment> slave) {
        try {
            for (Treatment treatment : slave.getAllToDelete()){
                if (!syncDeleted(treatment,main))
                    return false;
            }

            for (Treatment treatment : slave.getAllToCreate()){
                if (!syncCreated(treatment, main))
                    return false;
            }

            for (Treatment treatment : slave.getAllToUpdate()){
                if (!syncUpdated(treatment, main))
                    return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean syncDeleted(Treatment treatment,IRepository<Treatment> main) throws Exception {
        if (!main.delete(treatment.get_Id())){
            return false;
        }
        return true;
    }

    private boolean syncCreated(Treatment treatment, IRepository<Treatment> main){
        try {
            return main.create(treatment).get_Id() != null;
        } catch (Exception e) {
            return false;
        }
    }
    private boolean syncUpdated(Treatment treatment, IRepository<Treatment> main){
        try {
            main.update(treatment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
