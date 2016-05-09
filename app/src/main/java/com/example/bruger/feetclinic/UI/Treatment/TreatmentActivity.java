package com.example.bruger.feetclinic.UI.Treatment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;

import com.example.bruger.feetclinic.BLL.BE.TreatmentORM;
import com.example.bruger.feetclinic.BLL.ISourceManager;
import com.example.bruger.feetclinic.BLL.Manager.Async.AsyncTaskResult;
import com.example.bruger.feetclinic.BLL.Manager.Async.DownloadTask;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnTaskCompleteListener;
import com.example.bruger.feetclinic.BLL.Manager.IManager;
import com.example.bruger.feetclinic.BLL.Manager.TreatmentManager;
import com.example.bruger.feetclinic.BLL.TreatmentSourceManager;
import com.example.bruger.feetclinic.R;
import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronizer;
import com.example.bruger.feetclinic.Service.DBSynchronize.TreatmentSynchronizer;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Bruger on 25-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity implements OnTaskCompleteListener<Treatment> {

    ISynchronizer<Treatment> synchronizer;
    private Button btnCreate;
    private CustomListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Treatment> treatments;

    private boolean isReceiverRegistered = false;
    private boolean isConnected = false;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            NetworkInfo info = getNetworkInfo(context);
            if (info != null && info.isConnected()) {
                isConnected = true;
                sync();
            } else {
                isConnected = false;
            }
        }
    };

    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connManager.getActiveNetworkInfo();
    }
    public boolean isOnline() {

        Runtime runtime = Runtime.getRuntime();
        try {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        treatments = new ArrayList<>();
        populateTreatments();
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailsActivity(null);
            }
        });
        sync();
    }

    private void sync() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ISourceManager<Treatment> sourceManager = new TreatmentSourceManager(cm);
        synchronizer = new TreatmentSynchronizer(sourceManager);

        SynchronizeTask synchronizeTask = new SynchronizeTask(synchronizer);
        Thread thread = new Thread(synchronizeTask);
        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isReceiverRegistered) {
            isReceiverRegistered = true;
            registerReceiver(receiver, new IntentFilter("android.net.wifi.STATE_CHANGE")); // IntentFilter to wifi state change is "android.net.wifi.STATE_CHANGE"
        }
        populateTreatments();
    }

    @Override
    protected void onPause() {
        super.onPause();
            if (isReceiverRegistered) {
                isReceiverRegistered = false;
                unregisterReceiver(receiver);
            }
    }

    private void setUpAdapter(final ArrayList<Treatment> listOfTreatments){
        listView = (ListView)findViewById(R.id.list);
        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(), listOfTreatments);
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startDetailsActivity(listOfTreatments.get(position));
            }
        });
    }

    private void populateTreatments(){
        DownloadTask<Treatment> downloadTask = new DownloadTask(this);
        downloadTask.execute(new TreatmentManager(this));
    }

    private void startDetailsActivity(Treatment treatment)
    {

        Intent intent = new Intent(this,TreatmentDetailsActivity.class);
        String id = null;
        if (treatment != null) {
            id = treatment.get_Id();
        }
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void update(ArrayList<Treatment> arrTreatments) {
        setUpAdapter(arrTreatments);
    }

    @Override
    public void onTaskComplete(AsyncTaskResult<Treatment> result) {
        if (result.isSuccessful()){
            update(result.getResults());
        }
    }


    class SynchronizeTask implements Runnable{
        ISynchronizer<Treatment> synchronizer;
        public SynchronizeTask(ISynchronizer<Treatment> synchronizer) {
            this.synchronizer = synchronizer;
        }

        @Override
        public void run() {
            if (synchronizer.synchronize()){
                TreatmentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        populateTreatments();
                    }
                });
            }

        }
    }

}
