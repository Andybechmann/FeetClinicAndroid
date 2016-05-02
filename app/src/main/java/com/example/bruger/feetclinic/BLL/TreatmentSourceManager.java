package com.example.bruger.feetclinic.BLL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.REST.TreatmentRest;
import com.example.bruger.feetclinic.DAL.SQLite.TreatmentSqlite;

/**
 * Created by Stepanenko on 29/04/2016.
 */
public class TreatmentSourceManager implements ISourceManager<Treatment> {

    IRepository<Treatment> repository;
    public TreatmentSourceManager(ConnectivityManager cm) {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        instanceRepository(activeNetwork.isConnectedOrConnecting());
    }

    private void instanceRepository(boolean isConnection){
        if (isConnection){
            repository = new TreatmentRest();
        }
        else {
            repository = new TreatmentSqlite();
        }

    }

    @Override
    public IRepository<Treatment> getResource() {
        return repository;
    }
}
