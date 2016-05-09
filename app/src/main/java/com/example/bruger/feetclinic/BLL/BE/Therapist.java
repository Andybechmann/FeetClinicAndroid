package com.example.bruger.feetclinic.BLL.BE;

/**
 * Created by Bruger on 04-05-2016.
 */
public class Therapist implements IEntity{

    protected String name;
    protected String _id;
    protected String description;

    public Therapist(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Therapist() {
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String get_Id() {
        return _id;
    }
}
