package com.example.bruger.feetclinic.BLL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.Service.ISynchronize;
import com.example.bruger.feetclinic.DAL.Service.TreatmentSynchronizer;
import com.example.bruger.feetclinic.DAL.Treatment.ApiRepo;
import com.example.bruger.feetclinic.DAL.Treatment.SqlRepo;

import java.util.List;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentManager {

    ConnectivityManager cm;
    private boolean isConnected;
    ISynchronize synchronizer;
    IRepository<Treatment> workingRepository;
    IRepository<Treatment> sqlRepository;
    IRepository<Treatment> restRepository;


    public TreatmentManager(Context context) {
        cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork.isConnectedOrConnecting();
        synchronizer = new TreatmentSynchronizer();
        sqlRepository = new SqlRepo();
        setUpRepository();

       
    }

    private void setUpRepository(){
        if (isConnected) {
            restRepository = new ApiRepo();
            synchronizer.synchronize(restRepository,sqlRepository);
            workingRepository = restRepository;
        }
        else {
            workingRepository = sqlRepository;
        }
    }


    public List<Treatment> getAll(){
        return workingRepository.getAll();
    }

    public Treatment create(Treatment t){
        return workingRepository.create(t);
    }


}
