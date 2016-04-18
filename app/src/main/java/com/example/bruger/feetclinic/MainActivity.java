package com.example.bruger.feetclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bruger.feetclinic.BE.Treatment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnTreatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTreatment = (Button)findViewById(R.id.btnTreatment);


        btnTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTreatment();

            }
        });
    }

    private void onClickTreatment() {
        Intent intent = new Intent(this,TreatmentActivity.class);
        startActivity(intent);
    }

}
