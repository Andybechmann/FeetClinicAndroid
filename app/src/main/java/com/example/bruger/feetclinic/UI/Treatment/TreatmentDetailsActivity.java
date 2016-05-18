package com.example.bruger.feetclinic.UI.Treatment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.BllFacade;
import com.example.bruger.feetclinic.BLL.Manager.Async.AsyncTaskResult;
import com.example.bruger.feetclinic.BLL.Manager.Async.CreateTask;
import com.example.bruger.feetclinic.BLL.Manager.Async.DeleteTask;
import com.example.bruger.feetclinic.BLL.Manager.Async.DownloadTask;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnCreateTaskCompleteListener;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnDeleteTaskCompleteListener;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnDownloadTaskCompleteListener;
import com.example.bruger.feetclinic.BLL.Manager.Async.OnUpdateTaskCompleteListener;
import com.example.bruger.feetclinic.BLL.Manager.Async.UpdateTask;
import com.example.bruger.feetclinic.R;

/**
 * Created by Bruger on 27-04-2016.
 */
public class TreatmentDetailsActivity extends AppCompatActivity implements
        OnDownloadTaskCompleteListener<Treatment>, OnCreateTaskCompleteListener<Treatment>,
        OnUpdateTaskCompleteListener<Treatment>, OnDeleteTaskCompleteListener<Treatment> {

    private TextView txtName;
    private TextView txtDescription;
    private EditText editPrice;
    private EditText editDuration;
    private Treatment treatment;
    private String treatmentId;
    private Button btnCreate;
    private Button btnDelete;

    private AlertDialog alert;

    BllFacade bllFacade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatmentdetails);

        Intent intent = getIntent();
        treatmentId = intent.getStringExtra("id");

        // Get Views
        txtName = (TextView)findViewById(R.id.txtName);
        txtDescription = (TextView)findViewById(R.id.txtDescription);
        editPrice = (EditText)findViewById(R.id.editPrice);
        editDuration = (EditText)findViewById(R.id.editDuration);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        bllFacade = new BllFacade();
        if(treatmentId == null)
        {
            btnDelete.setVisibility(View.INVISIBLE);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(treatmentId == null){
                createNewTreatment();
                }else {
                    updateTreatment();  //to REST
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTreatment(treatment);
            }
        });


        if(treatmentId != null )
        {
            populateTreatment(treatmentId);
        }

    }

    private void deleteTreatment(Treatment treatment) {
        DeleteTask<Treatment> deleteTask = new DeleteTask(this,treatment);
        deleteTask.execute(bllFacade.getTreatmentManager());
    }

    private void updateTreatment() {
        treatment.setName(txtName.getText().toString());
        treatment.setDescription(txtDescription.getText().toString());
        treatment.setPrice(Integer.parseInt(editPrice.getText().toString()));
        treatment.setDuration(Integer.parseInt(editDuration.getText().toString()));

        UpdateTask<Treatment> updateTask = new UpdateTask<Treatment>(this,treatment);
        updateTask.execute(bllFacade.getTreatmentManager());
    }

    private void createNewTreatment() {
        Treatment treatment = new Treatment();
        treatment.setName(txtName.getText().toString());
        treatment.setDescription(txtDescription.getText().toString());
        treatment.setPrice(Integer.parseInt(editPrice.getText().toString()));
        treatment.setDuration(Integer.parseInt(editDuration.getText().toString()));

        CreateTask<Treatment> createTask = new CreateTask<>(this,treatment);
        createTask.execute(bllFacade.getTreatmentManager());
    }

    private void setUpFields(Treatment t) {
        txtName.setText(t.getName());
        txtDescription.setText(t.getDescription());
        editPrice.setText(t.getPrice()+"");
        editDuration.setText(t.getDuration()+"");

    }
    private void cleanUpFields()
    {
        txtName.setText("");
        txtDescription.setText("");
        editPrice.setText("");
        editDuration.setText("");
    }

    private void alertDialog(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private void populateTreatment(String id) {
        DownloadTask<Treatment> downloadTask = new DownloadTask<Treatment>(this,id);
        downloadTask.execute(bllFacade.getTreatmentManager());
    }

    @Override
    public void onDownloadTaskComplete(AsyncTaskResult<Treatment> result) {
        if (result.isSuccessful()){
                treatment = result.getResults().get(0);
                setUpFields(treatment);
        } else {
            alertDialog(result.getException().getMessage());
        }
    }

    @Override
    public void onCreateTaskComplete(AsyncTaskResult<Treatment> result) {
        if (result.isSuccessful()){
            alertDialog("Treatment has been created");
            cleanUpFields();
        } else {
            alertDialog(result.getException().getMessage());
        }
    }

    @Override
    public void onDeleteTaskComplete(AsyncTaskResult<Treatment> result) {
        if (result.isSuccessful()){
            finish();
        } else {
            alertDialog(result.getException().getMessage());
        }
    }

    @Override
    public void onUpdateTaskComplete(AsyncTaskResult<Treatment> result) {
        if (result.isSuccessful()){
            finish();
        } else {
            alertDialog(result.getException().getMessage());
        }
    }
}

