package com.example.bruger.feetclinic.BLL.BE;

import com.orm.dsl.Table;

/**
 * Created by Bruger on 04-05-2016.
 */
@Table
public class TherapistORM extends Therapist {

    private Long id;

    public TherapistORM(String name, String description) {
        super(name, description);
    }

    public TherapistORM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
