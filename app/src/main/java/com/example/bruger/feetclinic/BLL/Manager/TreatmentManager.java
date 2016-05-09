package com.example.bruger.feetclinic.BLL.Manager;

import android.content.Context;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.DALFactory;
import com.example.bruger.feetclinic.DAL.IRepository;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentManager implements IManager<Treatment> {
    DALFactory dalFactory;
    IRepository<Treatment> workingRepository;

    public TreatmentManager(Context context) {
        dalFactory = new DALFactory();
        workingRepository = dalFactory.getTreatmentRepository();
    }

    @Override
    public ArrayList<Treatment> getAll() throws Exception {
        return workingRepository.getAll();
    }

    @Override
    public Treatment create(Treatment treatment) throws Exception {
        return workingRepository.create(treatment);
    }

    @Override
    public Treatment get(String id) throws Exception {
        return workingRepository.get(id);
    }

    @Override
    public Treatment update(Treatment treatment) throws Exception {
        return workingRepository.update(treatment);
    }

    @Override
    public Treatment update(Treatment treatment, String id) throws Exception {
        treatment.set_Id(id);
        return workingRepository.update(treatment);
    }

    @Override
    public boolean delete(Treatment treatment) throws Exception {
        return workingRepository.delete(treatment);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return workingRepository.delete(id);
    }
}
