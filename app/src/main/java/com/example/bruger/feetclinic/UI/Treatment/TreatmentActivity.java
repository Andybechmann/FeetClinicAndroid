package com.example.bruger.feetclinic.UI.Treatment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;

import com.example.bruger.feetclinic.BLL.BllFacade;
import com.example.bruger.feetclinic.BLL.Manager.Async.AsyncTaskResult;
import com.example.bruger.feetclinic.BLL.Manager.Async.DownloadTask;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnDownloadTaskCompleteListener;
import com.example.bruger.feetclinic.R;

import java.util.ArrayList;


/**
 * Created by Bruger on 25-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity implements OnDownloadTaskCompleteListener<Treatment> {

    private Button btnCreate;
    private TreatmentListViewAdapter treatmentListViewAdapter;
    private ListView listView;

    private boolean isReceiverRegistered = false;
    BllFacade bllFacade;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        bllFacade = new BllFacade();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sync();
    }

    @Override
    protected void onStart() {
        super.onStart();
        populateTreatments();
    }

    @Override
    public void onDownloadTaskComplete(AsyncTaskResult<Treatment> result) {
        if (result.isSuccessful()){
            update(result.getResults());
        }
        else {
            showDialog("Apps can not work normally, problems with Database " + result.getException().getMessage() + "  Try again later");
        }
    }

    private void populateTreatments(){
        DownloadTask<Treatment> downloadTask = new DownloadTask(this);
        downloadTask.execute( bllFacade.getTreatmentManager());
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
    private void setUpAdapter(final ArrayList<Treatment> listOfTreatments){
        listView = (ListView)findViewById(R.id.list);
        treatmentListViewAdapter = new TreatmentListViewAdapter(getApplicationContext(), listOfTreatments);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startDetailsActivity(listOfTreatments.get(position));
            }
        });
        listView.setAdapter(treatmentListViewAdapter);
    }

    private void showDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    
    private void sync() {
        Thread thread = new Thread(new Sync());
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    class Sync implements Runnable{

        @Override
        public void run() {
            bllFacade.getTreatmentManager().synchronize();
        }
    }
}
