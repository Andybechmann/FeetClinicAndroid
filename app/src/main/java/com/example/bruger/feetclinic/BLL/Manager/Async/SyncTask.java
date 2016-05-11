package com.example.bruger.feetclinic.BLL.Manager.Async;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.BLL.Manager.IManager;

/**
 * Created by Stepanenko on 11/05/2016.
 */
public class SyncTask<T extends IEntity> extends AsyncTask<IManager<T>,Void,Boolean> {

    @Override
    protected Boolean doInBackground(IManager<T>... params) {
       return params[0].synchronize();
    }
}
