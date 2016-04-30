package com.example.bruger.feetclinic.UI.Treatment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;

import com.example.bruger.feetclinic.BLL.Manager.IManager;
import com.example.bruger.feetclinic.BLL.Manager.TreatmentManager;
import com.example.bruger.feetclinic.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bruger on 25-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity {

    private Button btnCreate;
    private CustomListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Treatment> treatments;
    IManager<Treatment> manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);
        treatments = new ArrayList<>();
        populateTreatments();
        listView = (ListView)findViewById(R.id.list);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreate();
            }
        });


    }

    private void onClickCreate() {
        Intent intent = new Intent(this,TreatmentDetailsActivity.class);
        startActivity(intent);
    }

    private void setUpAdapter(){
        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(), treatments);
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Treatment t = treatments.get(position);
                String treatmentId = t.getId();
                goTreatmentDetailAc(treatmentId);
            }
        });
    }

    private void populateTreatments(){
        DowloadTask task = new DowloadTask();
        Thread thread = new Thread(task);
        thread.start();

    }

    private void goTreatmentDetailAc(String id)
    {
        Intent intent = new Intent(this,TreatmentDetailsActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void update(List<Treatment> arrTreatments) {
        treatments.addAll(arrTreatments);
        setUpAdapter();
    }

    class DowloadTask implements Runnable{

        @Override
        public void run() {
            manager = new TreatmentManager(TreatmentActivity.this);
            try {
                final ArrayList<Treatment> treatmentList = manager.getAll();
                TreatmentActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update(treatmentList);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
