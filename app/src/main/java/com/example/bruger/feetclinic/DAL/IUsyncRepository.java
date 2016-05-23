package com.example.bruger.feetclinic.DAL;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 01/05/2016.
 */
public interface IUsyncRepository<EntityORM extends Entity, Entity> extends IRepository<Entity>  {
    ArrayList<EntityORM> getAllDeleted() throws Exception;
    ArrayList<EntityORM> getAllUpdated() throws Exception;
    ArrayList<EntityORM> getAllCreated() throws Exception;

    boolean approveCreate(EntityORM entityORM) ;
    boolean approveUpdate(EntityORM entityORM) ;
    boolean approveDelete(EntityORM entityORM) ;
    boolean deleteAll(ArrayList<Entity> list) ;
    boolean createAll(ArrayList<Entity> list) ;
}
