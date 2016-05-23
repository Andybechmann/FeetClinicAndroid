package com.example.bruger.feetclinic.BLL.BE;

import com.orm.dsl.Table;

/**
 * Created by Bruger on 04-05-2016.
 */
@Table
public class TherapistORM extends Therapist implements IUsyncEntity {

    private Long id;

    public TherapistORM() {
    }
    public TherapistORM(String name, String description) {
        super(name, description);
    }
    public TherapistORM(Therapist therapist){
        super(therapist);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean isDeleted() {
        return false;
    }

    @Override
    public void setDeleted(boolean deleted) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void setModified(boolean modified) {

    }

    @Override
    public boolean isCreated() {
        return false;
    }

    @Override
    public void setCreated(boolean created) {

    }

    public void update(Therapist therapist) {
        this.name = therapist.getName();
        this.description = therapist.getDescription();
    }
}
