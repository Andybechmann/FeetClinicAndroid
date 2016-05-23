package com.example.bruger.feetclinic.BLL.Manager;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.DAL.DALFacade;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.ConnectService;
import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronizer;
import com.example.bruger.feetclinic.Service.DBSynchronize.Synchronizer;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistManager implements IManager<Therapist> {
    DALFacade dalFacade;
    IRepository<Therapist> workingRepository;
    ConnectService connectService;
    ISynchronizer synchronizer;


    public TherapistManager()
     {
         connectService = new ConnectService();
         dalFacade = new DALFacade();
         workingRepository = dalFacade.getTherapistRepository(connectService.isOnline());
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
    public boolean synchronize() {
        boolean isOk = false;
        if (synchronizer == null){
            synchronizer = new Synchronizer();
        }
        if (connectService.isOnline()){
            isOk = synchronizer.synchronize(dalFacade.getTherapistRepository(true), dalFacade.getTherapistUsyncRepository());
        }
        return isOk;
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
