package com.example.bruger.feetclinic.BLL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.Treatment.RestApiClient;
import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronize;
import com.example.bruger.feetclinic.Service.DBSynchronize.TreatmentSynchronizer;
import com.example.bruger.feetclinic.DAL.Treatment.ApiRepo;
import com.example.bruger.feetclinic.DAL.Treatment.SqlRepo;

import java.io.IOException;
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
            restRepository = new RestApiClient();
            synchronizer.synchronize(restRepository,sqlRepository);
            workingRepository = restRepository;
        }
        else {
            workingRepository = sqlRepository;
        }
    }


    public List<Treatment> getAll() throws IOException {
        return workingRepository.getAll();
    }

    public Treatment create(Treatment t) throws IOException {
        return workingRepository.create(t);
    }


    public Treatment get(String id)  {
        try {
            return workingRepository.get(id);
        } catch (IOException e) {
            return new Treatment();
        }
    }

    private void changeRepository(){
        if(workingRepository instanceof ApiRepo )
             workingRepository = sqlRepository;
        else
            workingRepository = restRepository;


    }
}
