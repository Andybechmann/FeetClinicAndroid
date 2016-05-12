package com.example.bruger.feetclinic.BLL;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.Manager.IManager;
import com.example.bruger.feetclinic.BLL.Manager.TherapistManager;
import com.example.bruger.feetclinic.BLL.Manager.TreatmentManager;

/**
 * Created by Stepanenko on 11/05/2016.
 */
public class BllFacade {
    IManager<Treatment> treatmentManager;
    IManager<Therapist> therapistManager;

    public IManager<Treatment> getTreatmentManager()
    {
        if (treatmentManager == null){
            treatmentManager = new TreatmentManager();
        }
        return treatmentManager;
    }
    public IManager<Therapist> getTherapistManager(){
        if (therapistManager == null){
            therapistManager = new TherapistManager();
        }
        return therapistManager;
    }
}
