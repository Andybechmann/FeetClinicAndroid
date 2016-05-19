package com.example.bruger.feetclinic.BLL.BE;

/**
 * Created by Stepanenko on 19/05/2016.
 */
public interface IUsyncEntity extends IEntity {

    boolean isDeleted();

    void setDeleted(boolean deleted);

    boolean isModified();

    void setModified(boolean modified);

    boolean isCreated();

    void setCreated(boolean created);
}
