package com.example.bruger.feetclinic.Service.DBSynchronize;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronize;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentSynchronizer implements ISynchronize<Treatment> {
    @Override
    public boolean synchronize(IRepository<Treatment> main, IRepository<Treatment> slave) {
        return false;
    }
}
