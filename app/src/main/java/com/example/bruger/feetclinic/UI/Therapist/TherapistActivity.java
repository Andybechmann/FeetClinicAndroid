package com.example.bruger.feetclinic.UI.Therapist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.BllFacade;
import com.example.bruger.feetclinic.BLL.Manager.Async.AsyncTaskResult;
import com.example.bruger.feetclinic.BLL.Manager.Async.DownloadTask;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnTaskCompleteListener;
import com.example.bruger.feetclinic.R;
import com.example.bruger.feetclinic.UI.Treatment.TreatmentListViewAdapter;

import java.util.ArrayList;

/**
 * Created by Bruger on 27-04-2016.
 */
public class TherapistActivity extends AppCompatActivity implements OnTaskCompleteListener<Therapist> {

    private TextView txtName;
    private TextView txtDescription;
    private AlertDialog alert;
    private TherapistListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Therapist> therapists;
    BllFacade bllFacade;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            NetworkInfo info = getNetworkInfo(context);
            if (info != null && info.isConnected()) {

            } else {
            }
        }
    };

    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connManager.getActiveNetworkInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist);
        bllFacade = new BllFacade();
        populateTreatments();
    }

    private void populateTreatments(){
        DownloadTask<Therapist> downloadTask = new DownloadTask(this);
        downloadTask.execute( bllFacade.getTherapistManager());
    }

    public void update(ArrayList<Therapist> arrTherapists) {
        setUpAdapter(arrTherapists);
    }
    private void setUpAdapter(final ArrayList<Therapist> listOfTherapists){
        listView = (ListView)findViewById(R.id.list);
        customListViewAdapter = new TherapistListViewAdapter(getApplicationContext(),listOfTherapists);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startDetailsActivity(listOfTherapists.get(position));
            }
        });
        listView.setAdapter(customListViewAdapter);
    }
    private void startDetailsActivity(Therapist therapist)
    {
        Intent intent = new Intent(this,TherapistDetailsActivity.class);
        String id = null;
        if (therapist != null) {
            id = therapist.get_Id();
        }
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onTaskComplete(AsyncTaskResult<Therapist> result) {

    }
}
