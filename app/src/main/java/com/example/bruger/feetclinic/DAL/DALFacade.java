package com.example.bruger.feetclinic.DAL;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.TherapistORM;
import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.BE.TreatmentORM;
import com.example.bruger.feetclinic.DAL.REST.TherapistRest;
import com.example.bruger.feetclinic.DAL.REST.TreatmentRest;
import com.example.bruger.feetclinic.DAL.SQLite.TherapistSqlite;
import com.example.bruger.feetclinic.DAL.SQLite.TreatmentSqlite;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public class DALFacade {

    private IRepository<Treatment> treatmentRepository;
    private IRepository<Therapist> therapistRepository;

    public IRepository<Treatment> getTreatmentRepository(boolean online) {
        if (online) {
            treatmentRepository = new TreatmentRest();
        } else {
            treatmentRepository = getTreatmentUsyncRepository();
        }
        return treatmentRepository;
    }

    public IUsyncRepository<TreatmentORM,Treatment> getTreatmentUsyncRepository(){
        return new TreatmentSqlite();
    }

    public IRepository<Therapist> getTherapistRepository(boolean online){
        if (online) {
            therapistRepository = new TherapistRest();
        } else {
            therapistRepository = getTherapistUsyncRepository();
        }
        return therapistRepository;
    }

    public IUsyncRepository<TherapistORM,Therapist> getTherapistUsyncRepository(){
        return new TherapistSqlite();
    }


}
