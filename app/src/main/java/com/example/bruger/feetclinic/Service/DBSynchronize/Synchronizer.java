package com.example.bruger.feetclinic.Service.DBSynchronize;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 10/05/2016.
 */
public class Synchronizer implements ISynchronizer{


    @Override
    public <T extends SuperT, SuperT extends IEntity> boolean synchronize(IRepository<SuperT> main, IUsyncRepository<T, SuperT> local)
     {
        ArrayList<T> objectsToSynchronize;

        //create to remote DB
        try {
            objectsToSynchronize = local.getAllCreated();
            for (T t: objectsToSynchronize) {
                t.set_Id(null);
                SuperT createdT = main.create(t);
                t.set_Id(createdT.get_Id());
                local.approveCreate(t);
            }
        } catch (Exception e) {
            return false;
        }
        //update to remote DB
        try {
            objectsToSynchronize = local.getAllUpdated();
            for (T t: objectsToSynchronize) {
                main.update(t);
                local.approveUpdate(t);
            }
        } catch (Exception e) {
            return false;
        }
        //delete to remote DB
        try {
            objectsToSynchronize = local.getAllDeleted();
            for (T t: objectsToSynchronize) {
                if(main.delete(t))
                    local.approveDelete(t);
            }
        } catch (Exception e) {
            return false;
        }


        try {

            ArrayList<SuperT> mainDbList = main.getAll();
            ArrayList<SuperT> localDbList  = local.getAll();

            localDbList.removeAll(mainDbList);
            local.deleteAll(localDbList);

            localDbList = local.getAll();
            mainDbList.removeAll(localDbList);

            local.createAll(mainDbList);
/*
            local.deleteAll();
            local.createAll(main.getAll());
            */
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
