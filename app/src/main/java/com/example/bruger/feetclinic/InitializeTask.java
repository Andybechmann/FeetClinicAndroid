package com.example.bruger.feetclinic;

/* Uses AsyncTask to create a task away from the main UI thread. This task takes a
    URL string and uses it to create an HttpUrlConnection. Once the connection
    has been established, the AsyncTask downloads the contents of the webpage as
    an InputStream. Finally, the InputStream is converted into a string, which is
    displayed in the UI by the AsyncTask's onPostExecute method.*/

import android.os.AsyncTask;

import com.easv.oe.policedistrict.DALC.BEPoliceDistrict;
import com.easv.oe.policedistrict.DALC.PoliceDistricts;
import com.example.bruger.feetclinic.BE.Treatment;
import com.example.bruger.feetclinic.DAL.TreatmentDAO;

import java.util.ArrayList;


public class InitializeTask extends AsyncTask<
        TreatmentDAO, // collection of PoliceDistricts to execute
        Void, // to type of progress info
        ArrayList<Treatment>> // output of doInBackground
{

    MainActivity m_context;

    public InitializeTask(MainActivity context)
    {
        m_context = context;
    }

    // onPostExecute displays the results of the AsyncTask.doInBackground().
    // this method is invoked by the GUI thread
    @Override
    protected void onPostExecute(final ArrayList<Treatment> treatments) {
        m_context.initializeData(treatments);

    }

    @Override
    protected ArrayList<Treatment> doInBackground(TreatmentDAO... ms) {
        ms[0].loadAll();

        return ms[0].getAll();
    }
}
