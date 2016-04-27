package com.example.bruger.feetclinic;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bruger.feetclinic.BE.Treatment;

import com.example.bruger.feetclinic.DAL.Treatment.TreatmentDAO;
import com.example.bruger.feetclinic.DAL.Treatment.TreatmentDAOTask;
import java.util.ArrayList;


/**
 * Created by Bruger on 25-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity {

    private CustomListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Treatment> treatments;
    TreatmentDAOTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

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
        task.execute(new TreatmentDAO());
        treatments.add(new Treatment("Fodbehandling1",170));
        treatments.add(new Treatment("Fodbehandling2",180));
        treatments.add(new Treatment("Fodbehandling3",190));
        treatments.add(new Treatment("Fodbehandling4", 200));
    }

    public void update(ArrayList<Treatment> arrTreatments) {
        arrTreatments.removeAll(treatments); //removing existing items
        treatments.addAll(arrTreatments);
        setUpAdapter();
    }
}
