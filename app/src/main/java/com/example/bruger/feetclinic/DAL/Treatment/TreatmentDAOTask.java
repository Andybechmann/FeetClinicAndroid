package com.example.bruger.feetclinic.DAL.Treatment;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.UI.Treatment.TreatmentActivity;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 25/04/2016.
 */
public class TreatmentDAOTask extends AsyncTask<ApiRepo,Void,ArrayList<Treatment>>
{
    private TreatmentActivity context;

    public TreatmentDAOTask(TreatmentActivity context) {
        this.context = context;
    }

    @Override
    protected ArrayList<Treatment> doInBackground(ApiRepo... params) {
        params[0].loadAll();
        return params[0].getAll();
    }

    @Override
    protected void onPostExecute(ArrayList<Treatment> treatments) {
        context.update(treatments);
    }
}
