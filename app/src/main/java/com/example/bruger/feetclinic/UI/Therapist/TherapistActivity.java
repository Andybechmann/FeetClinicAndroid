package com.example.bruger.feetclinic.UI.Therapist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.R;

import java.util.ArrayList;

/**
 * Created by Bruger on 27-04-2016.
 */
public class TherapistActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtDescription;
    private AlertDialog alert;
    private TherapistListViewAdapter customListViewAdapter;
    private ListView listView;
    ArrayList<Therapist> therapists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist);
        therapists = new ArrayList<>();

        txtName = (TextView)findViewById(R.id.txtName);
        txtDescription = (TextView)findViewById(R.id.txtDescription);


    }


    private void setUpAdapter(final ArrayList<Therapist> listOfTherapists){
        listView = (ListView)findViewById(R.id.list);
        customListViewAdapter = new TherapistListViewAdapter(getApplicationContext(), listOfTherapists);
        listView.setAdapter(customListViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                startDetailsActivity(listOfTherapists.get(position).get_Id());
            }
        });
    }
    private void startDetailsActivity(String id)
    {
        Intent intent = new Intent(this,TherapistDetailsActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
