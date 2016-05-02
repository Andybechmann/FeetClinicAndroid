package com.example.bruger.feetclinic.BLL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.Treatment.RestApiRepo;
import com.example.bruger.feetclinic.DAL.Treatment.SqlRepo;
import com.example.bruger.feetclinic.Service.DBSynchronize.TreatmentSynchronizer;

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
            repository = new RestApiRepo();
        }
        else {
            repository = new SqlRepo();
        }

    }

    @Override
    public IRepository<Treatment> getResource() {
        return repository;
    }
}
