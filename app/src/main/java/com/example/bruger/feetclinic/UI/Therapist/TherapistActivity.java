package com.example.bruger.feetclinic.UI.Therapist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.BLL.Manager.Async.AsyncTaskResult;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnDownloadTaskCompleteListener;
import com.example.bruger.feetclinic.R;
import com.example.bruger.feetclinic.BLL.BllFacade;
import com.example.bruger.feetclinic.BLL.Manager.Async.DownloadTask;


import java.util.ArrayList;

/**
 * Created by Bruger on 27-04-2016.
 */
public class TherapistActivity extends AppCompatActivity implements OnDownloadTaskCompleteListener<Therapist> {

    private TextView txtName;
    private TextView txtDescription;
    private AlertDialog alert;
    private TherapistListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Therapist> therapists;
    BllFacade bllFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist);
        bllFacade = new BllFacade();
        populateTherapist();
    }

    private void populateTherapist(){
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
                startDetailsActivity(listOfTherapists.get(position).get_Id());
            }
        });
        listView.setAdapter(customListViewAdapter);


    }
    private void startDetailsActivity(String id)
    {
        /*
        Intent intent = new Intent(this,TherapistDetailsActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        */
        //skal videre udikles
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

    @Override
    public void onDownloadTaskComplete(AsyncTaskResult<Therapist> result) {
        if (result.isSuccessful()){
            update(result.getResults());
        }
        else {
            showDialog("Apps can not work normally, problems with Database " + result.getException().getMessage() + "  Try again later");
            finish();
        }
    }
}
