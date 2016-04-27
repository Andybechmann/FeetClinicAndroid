package com.example.bruger.feetclinic.UI.Treatment;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;

import com.example.bruger.feetclinic.BLL.TreatmentManager;
import com.example.bruger.feetclinic.DAL.Treatment.ApiRepo;
import com.example.bruger.feetclinic.DAL.Treatment.TreatmentDAOTask;
import com.example.bruger.feetclinic.R;

import java.util.ArrayList;


/**
 * Created by Bruger on 25-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity {

    private CustomListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Treatment> treatments;
    TreatmentManager manager;
    TreatmentDAOTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        manager = new TreatmentManager(this);
        treatments = new ArrayList<Treatment>();
        populateTreatments();
        listView = (ListView)findViewById(R.id.list);
        setUpAdapter();

    }


    private void setUpAdapter(){
        //setup adapter
        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(), treatments);
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }


    private void populateTreatments(){

        task = new TreatmentDAOTask(this);
        task.execute(new ApiRepo());


    }

    public void update(ArrayList<Treatment> arrTreatments) {
        arrTreatments.removeAll(treatments); //removing existing items
        treatments.addAll(arrTreatments);
        setUpAdapter();
    }
}
