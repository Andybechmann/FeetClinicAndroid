package com.example.bruger.feetclinic.BLL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.example.bruger.feetclinic.DAL.REST.TherapistRest;
import com.example.bruger.feetclinic.DAL.SQLite.TherapistSqlite;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistSourceManager implements ISourceManager<Therapist> {
    IRepository<Therapist> activeRepository;
    boolean isConnected;

    public TherapistSourceManager(ConnectivityManager cm) {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork.isConnectedOrConnecting();
        instanceRepository(isConnected);
    }

    private void instanceRepository(boolean isConnection){
        if (isConnection){
            activeRepository = new TherapistRest();
        }
        else {
            activeRepository = new TherapistSqlite();
        }

    }

    @Override
    public IRepository<Therapist> getResource() {
        return activeRepository;
    }

    @Override
    public boolean canSynchronize() {
        return isConnected;
    }

    @Override
    public IUsyncRepository<Therapist> getResourceToSynchronize() {
        return new TherapistSqlite();
    }
}
