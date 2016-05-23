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
    public <EntityORM extends Entity, Entity extends IEntity>
    boolean synchronize(IRepository<Entity> main, IUsyncRepository<EntityORM, Entity> local)
     {
        ArrayList<EntityORM> objectsToSynchronize;

        //create to remote DB
        try {
            objectsToSynchronize = local.getAllCreated();
            for (EntityORM entityORM : objectsToSynchronize) {
                entityORM.set_Id(null);
                Entity createdT = main.create(entityORM);
                entityORM.set_Id(createdT.get_Id());
                local.approveCreate(entityORM);
            }
        } catch (Exception e) {
            return false;
        }
        //update to remote DB
        try {
            objectsToSynchronize = local.getAllUpdated();
            for (EntityORM entityORM : objectsToSynchronize) {
                main.update(entityORM);
                local.approveUpdate(entityORM);
            }
        } catch (Exception e) {
            return false;
        }
        //delete to remote DB
        try {
            objectsToSynchronize = local.getAllDeleted();
            for (EntityORM entityORM : objectsToSynchronize) {
                if(main.delete(entityORM))
                    local.approveDelete(entityORM);
            }
        } catch (Exception e) {
            return false;
        }


        try {

            ArrayList<Entity> mainDbList = main.getAll();
            ArrayList<Entity> localDbList  = local.getAll();

            //was deleted in remote DB
            localDbList.removeAll(mainDbList);
            local.deleteAll(localDbList);

            //was created in remote DB
            localDbList = local.getAll();
            mainDbList.removeAll(localDbList);
            local.createAll(mainDbList);

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
