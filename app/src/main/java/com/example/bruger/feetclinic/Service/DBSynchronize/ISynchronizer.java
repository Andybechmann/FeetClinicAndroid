package com.example.bruger.feetclinic.Service.DBSynchronize;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public interface ISynchronizer{
    <EntityORM extends Entity, Entity extends IEntity>
    boolean synchronize(IRepository<Entity> main, IUsyncRepository<EntityORM, Entity> local);
}
