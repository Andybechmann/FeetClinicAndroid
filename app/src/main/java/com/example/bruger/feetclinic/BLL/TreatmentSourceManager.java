package com.example.bruger.feetclinic.BLL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;
import com.example.bruger.feetclinic.DAL.REST.TreatmentRest;
import com.example.bruger.feetclinic.DAL.SQLite.TreatmentSqlite;

/**
 * Created by Stepanenko on 29/04/2016.
 */
public class TreatmentSourceManager implements ISourceManager<Treatment> {

    IRepository<Treatment> activeRepository;
    boolean isConnected;

    public TreatmentSourceManager(ConnectivityManager cm) {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork.isConnectedOrConnecting();
        instanceRepository(isConnected);
    }

    private void instanceRepository(boolean isConnection){
        if (isConnection){
            activeRepository = new TreatmentRest();
        }
        else {
            activeRepository = new TreatmentSqlite();
        }

    }

    @Override
    public IRepository<Treatment> getResource() {
        return activeRepository;
    }

    @Override
    public boolean canSynchronize() {
        return isConnected;
    }

    @Override
    public IUsyncRepository<Treatment> getResourceToSynchronize() {
        return new TreatmentSqlite();
    }
}
