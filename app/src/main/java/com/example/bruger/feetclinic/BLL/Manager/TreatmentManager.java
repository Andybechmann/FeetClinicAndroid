package com.example.bruger.feetclinic.BLL.Manager;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.DALFacade;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.ConnectService;
import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronizer;
import com.example.bruger.feetclinic.Service.DBSynchronize.Synchronizer;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentManager implements IManager<Treatment> {
    DALFacade dalFacade;
    IRepository<Treatment> workingRepository;
    ConnectService connectService;
    ISynchronizer synchronizer;

    public TreatmentManager() {
        connectService = new ConnectService();
        dalFacade = new DALFacade();
        workingRepository = dalFacade.getTreatmentRepository(connectService.isOnline());
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

    @Override
    public boolean synchronize() {
        boolean isOk = false;
        if (synchronizer == null){
            synchronizer = new Synchronizer();
        }
        if (connectService.isOnline()){
            isOk = synchronizer.synchronize(dalFacade.getTreatmentRepository(true), dalFacade.getTreatmentUsyncRepository());
        }

        return isOk;
    }
}
