package com.example.bruger.feetclinic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Bruger on 18-04-2016.
 */
public class TreatmentActivity extends AppCompatActivity {

    Button btnBack;
    ListView listTreatments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        btnBack = (Button)findViewById(R.id.btnBack);
        listTreatments = (ListView)findViewById(R.id.listTreatments);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}