package com.example.bruger.feetclinic.UI.Treatment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.TreatmentManager;
import com.example.bruger.feetclinic.R;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by Bruger on 27-04-2016.
 */
public class TreatmentDetailsActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtDescription;
    private EditText editPrice;
    private EditText editDuration;
    Treatment treatment;
    TreatmentManager manager;
    Button btnCreate;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatmentdetails);
        manager = new TreatmentManager(this);
        treatment = new Treatment();

        // Get Views
        txtName = (TextView)findViewById(R.id.txtName);
        txtDescription = (TextView)findViewById(R.id.txtDescription);
        editPrice = (EditText)findViewById(R.id.editPrice);
        editDuration = (EditText)findViewById(R.id.editDuration);
        btnCreate = (Button)findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTreatmentDetails();
            }
        });


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        if(id != null )
        {
            treatment = getTreatment(id);
            setUpFields(treatment);
        }

    }

    private void saveTreatmentDetails() {
        Treatment treatment = new Treatment();

        treatment.setName(txtName.getText().toString());
        treatment.setDescription(txtDescription.getText().toString());
        treatment.setPrice(Integer.parseInt(editPrice.getText().toString()));
        treatment.setDuration(Integer.parseInt(editDuration.getText().toString()));

        try {
            manager.create(treatment);
        } catch (Exception e) {
            AlertDialog alert = new AlertDialog.Builder(getApplicationContext()).create();
            alert.setMessage("TEST");
            alert.show();
        }
    }

    private void setUpFields(Treatment t) {
        txtName.setText(t.getName());
        txtDescription.setText(t.getDescription());
        editPrice.setText(t.getPrice());
        editDuration.setText(t.getDuration());

    }

    private Treatment getTreatment(String id) {
        return manager.get(id);
    }
}

