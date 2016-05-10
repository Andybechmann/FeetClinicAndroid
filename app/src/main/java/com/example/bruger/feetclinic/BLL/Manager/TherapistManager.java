package com.example.bruger.feetclinic.BLL.Manager;

import android.content.Context;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.DAL.DALFactory;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.ConnectService;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistManager implements IManager<Therapist> {
    DALFactory dalFactory;
    IRepository<Therapist> workingRepository;
    ConnectService connectService;


    public TherapistManager()
     {
         connectService = new ConnectService();
         dalFactory = new DALFactory();
         workingRepository = dalFactory.getTherapistRepository(connectService.isOnline());
    }

    @Override
    public Therapist create(Therapist therapist) throws Exception {
        return workingRepository.create(therapist);
    }

    @Override
    public Therapist update(Therapist therapist) throws Exception {
        return workingRepository.update(therapist);
    }

    @Override
    public Therapist update(Therapist therapist, String id) throws Exception {
        return workingRepository.update(therapist,id);
    }

    @Override
    public boolean delete(Therapist therapist) throws Exception {
        return workingRepository.delete(therapist);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return workingRepository.delete(id);
    }

    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        return workingRepository.getAll();
    }

    @Override
    public Therapist get(String id) throws Exception {
        return workingRepository.get(id);
    }
}
