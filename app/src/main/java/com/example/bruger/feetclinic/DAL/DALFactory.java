package com.example.bruger.feetclinic.DAL;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.REST.TherapistRest;
import com.example.bruger.feetclinic.DAL.REST.TreatmentRest;
import com.example.bruger.feetclinic.DAL.SQLite.TherapistSqlite;
import com.example.bruger.feetclinic.DAL.SQLite.TreatmentSqlite;
import com.example.bruger.feetclinic.Service.ConnectService;

import java.io.IOException;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public class DALFactory {
    ConnectService connectService;
    IRepository<Treatment> treatmentRepository;
    IRepository<Therapist> therapistRepository;

    public DALFactory() {
        ConnectService connectService = new ConnectService();
    }

    public IRepository<Treatment> getTreatmentRepository() {
        if (connectService.isOnline()) {
            treatmentRepository = new TreatmentRest();
        } else {
            treatmentRepository = new TreatmentSqlite();
        }
        return treatmentRepository;
    }

    public IRepository<Therapist> getTherapistRepository(){
        if (connectService.isOnline()) {
            therapistRepository = new TherapistRest();
        } else {
            therapistRepository = new TherapistSqlite();
        }
        return therapistRepository;
    }
}
