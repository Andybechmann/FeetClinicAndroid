package com.example.bruger.feetclinic.DAL.Treatment;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BE.Treatment;
import com.example.bruger.feetclinic.TreatmentActivity;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 25/04/2016.
 */
public class TreatmentDAOTask extends AsyncTask<TreatmentDAO,Void,ArrayList<Treatment>>
{
    private TreatmentActivity context;

    public TreatmentDAOTask(TreatmentActivity context) {
        this.context = context;
    }

    @Override
    protected ArrayList<Treatment> doInBackground(TreatmentDAO... params) {
        params[0].loadAll();
        return params[0].getAll();
    }

    @Override
    protected void onPostExecute(ArrayList<Treatment> treatments) {
        context.update(treatments);
    }
}
