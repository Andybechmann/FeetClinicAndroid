package com.example.bruger.feetclinic;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bruger.feetclinic.BE.Treatment;

import java.util.ArrayList;


/**
 * Created by Bruger on 25-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity {

    private CustomListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Treatment> treatments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        treatments = new ArrayList<Treatment>();
        populateTreatments();

        listView = (ListView)findViewById(R.id.list);



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
        treatments.add(new Treatment("Fodbehandling1",170));
        treatments.add(new Treatment("Fodbehandling2",180));
        treatments.add(new Treatment("Fodbehandling3",190));
        treatments.add(new Treatment("Fodbehandling4",200));

    }

}
