package com.example.bruger.feetclinic.BLL.Manager;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.ISourceManager;
import com.example.bruger.feetclinic.BLL.TherapistSourceManager;
import com.example.bruger.feetclinic.DAL.IReadRepository;
import com.example.bruger.feetclinic.DAL.IRepository;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistManager implements IReadManager<Therapist> {
    ISourceManager<Therapist> sourceManager;
    IReadRepository<Therapist> workingRepository;


    public TherapistManager(Context context)
     {
         sourceManager = new TherapistSourceManager((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
         workingRepository = sourceManager.getResource();
    }


    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        return workingRepository.getAll();
    }

    @Override
    public Therapist get(String id) throws Exception {
       return  workingRepository.get(id);
    }
}
