package com.example.bruger.feetclinic.UI.Treatment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.Manager.TreatmentManager;
import com.example.bruger.feetclinic.R;

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


        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        if(id != null )
        {
            treatment = getTreatment(id);
            setUpFields(treatment);
        }

    }

    private void setUpFields(Treatment t) {
        txtName.setText(t.getName());
        txtDescription.setText(t.getDescription());
        editPrice.setText(t.getPrice());
        editDuration.setText(t.getDuration());

    }

    private Treatment getTreatment(String id) {
        try {
            return manager.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

