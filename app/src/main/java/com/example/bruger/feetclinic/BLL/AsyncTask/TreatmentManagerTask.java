package com.example.bruger.feetclinic.BLL.AsyncTask;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.TreatmentManager;
import com.example.bruger.feetclinic.DAL.Treatment.ApiRepo;
import com.example.bruger.feetclinic.UI.Treatment.TreatmentActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stepanenko on 25/04/2016.
 */
public class TreatmentManagerTask extends AsyncTask<TreatmentManager,Void,List<Treatment>>
{
    private TreatmentActivity context;

    public TreatmentManagerTask(TreatmentActivity context) {
        this.context = context;
    }



    @Override
    protected List<Treatment> doInBackground(TreatmentManager... params) {

        try {
            return params[0].getAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<Treatment> treatments) {
        context.update(treatments);
    }
}
