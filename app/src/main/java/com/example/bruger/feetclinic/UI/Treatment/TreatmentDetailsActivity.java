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
import android.widget.Toast;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.BLL.Manager.IManager;
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
    String treatmentId;
    Button btnCreate;
    Button btnDelete;

    AlertDialog alert;

    IManager<Treatment> manager;

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
                deleteTreatment();
            }
        });


        if(treatmentId != null )
        {
            populateTreatment(treatmentId);
        }

    }

    private void deleteTreatment() {
        DeleteOne delete = new DeleteOne(treatment);
        Thread thread = new Thread(delete);
        thread.start();
    }

    private void updateTreatment() {



        treatment.setName(txtName.getText().toString());
        treatment.setDescription(txtDescription.getText().toString());
        treatment.setPrice(Integer.parseInt(editPrice.getText().toString()));
        treatment.setDuration(Integer.parseInt(editDuration.getText().toString()));

        UpdateOne updateOne = new UpdateOne(treatment);
        Thread thread = new Thread(updateOne);
        thread.start();
    }

    private void updateTreatmentUI(Treatment t) {
        treatment = t;
        setUpFields(t);
    }

    private void createNewTreatment() {
        Treatment treatment = new Treatment();


        treatment.setName(txtName.getText().toString());
        treatment.setDescription(txtDescription.getText().toString());
        treatment.setPrice(Integer.parseInt(editPrice.getText().toString()));
        treatment.setDuration(Integer.parseInt(editDuration.getText().toString()));

        CreateOne createOne = new CreateOne(treatment);
        Thread thread = new Thread(createOne);
        thread.start();
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

    private void okToast(String msg)
    {
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        cleanUpFields();
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
        DownloadOne task = new DownloadOne(id);
        Thread thread = new Thread(task);
        thread.start();
    }


    class DownloadOne implements Runnable{
        String _id;
        public DownloadOne(String id) {
            _id = id;
        }

        @Override
        public void run() {
            manager = new TreatmentManager(TreatmentDetailsActivity.this);
            try {
                final Treatment t = manager.get(_id);
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateTreatmentUI(t);
                    }
                });

            } catch (final Exception e) {
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog("found none ! " + e.toString());
                    }
                });

            }
        }
    }



    class CreateOne implements Runnable{
        Treatment treatment;
        public CreateOne(Treatment treatment) {
            this.treatment = treatment;
        }

        @Override
        public void run() {
            manager = new TreatmentManager(TreatmentDetailsActivity.this);
            try {
                treatment = manager.create(treatment);
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        okToast("Treatment has been saved! ");
                        updateTreatmentUI(treatment);
                    }
                });
            } catch (final Exception e) {
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog("Has not been created!! " + e.toString());
                    }
                });
            }
        }
    }
    class UpdateOne implements Runnable{
        Treatment treatment;
        public UpdateOne(Treatment treatment) {
            this.treatment = treatment;
        }

        @Override
        public void run() {
            manager = new TreatmentManager(TreatmentDetailsActivity.this);
            try {
                treatment = manager.update(treatment);
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        okToast("Treatment has been saved! ");
                        updateTreatmentUI(treatment);
                    }
                });
            } catch (final Exception e) {
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog("Has not been Changed!! " + e.toString());
                    }
                });
            }
        }
    }

    class DeleteOne implements Runnable{
        Treatment treatment;
        boolean succes;
        public DeleteOne(Treatment treatment) {
            this.treatment = treatment;
        }

        @Override
        public void run() {
            manager = new TreatmentManager(TreatmentDetailsActivity.this);
            try {
                succes = manager.delete(treatment);
                treatment = new Treatment();
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        okToast("Treatment has been deletet");
                        updateTreatmentUI(treatment);
                        finish();
                    }
                });
            } catch (final Exception e) {
                TreatmentDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog("Has not been Deletet!! " + e.toString());
                    }
                });
            }
        }
    }

}

