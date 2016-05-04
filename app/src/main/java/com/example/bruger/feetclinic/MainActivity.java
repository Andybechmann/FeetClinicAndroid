package com.example.bruger.feetclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bruger.feetclinic.Service.DBSynchronize.ISynchronizer;
import com.example.bruger.feetclinic.UI.Booking.BookingActivity;
import com.example.bruger.feetclinic.UI.Therapist.TherapistActivity;
import com.example.bruger.feetclinic.UI.Treatment.TreatmentActivity;

public class MainActivity extends AppCompatActivity {



    private Button btnTreatment;
    private Button btnTherapist;
    private Button btnBooking;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTreatment = (Button)findViewById(R.id.btnTreatment);
        btnTherapist = (Button)findViewById(R.id.btnTherapist);
        btnBooking = (Button)findViewById(R.id.btnBooking);



        btnTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTreatment();
            }
        });
        btnTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTherapist();
            }
        });
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBooking();
            }
        });
    }

    private void onClickBooking() {
        Intent intent = new Intent(this,BookingActivity.class);
        startActivity(intent);
    }

    private void onClickTherapist() {
        Intent intent = new Intent(this,TherapistActivity.class);
        startActivity(intent);
    }

    private void onClickTreatment() {
        Intent intent = new Intent(this,TreatmentActivity.class);
        startActivity(intent);
    }



}
