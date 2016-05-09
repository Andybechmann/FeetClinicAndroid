package com.example.bruger.feetclinic.BLL.Manager.Async;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.BLL.Manager.IManager;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public class DownloadTask<T extends IEntity> extends AsyncTask<IManager<T>,Void,AsyncTaskResult<T>> {

    private final OnTaskCompleteListener listener;
    private  String id = null;
    public DownloadTask(OnTaskCompleteListener listener) {
        this.listener = listener;
}

    public DownloadTask(OnTaskCompleteListener listener, String id) {
        this.listener = listener;
        this.id = id;
    }

    @Override
    protected AsyncTaskResult<T> doInBackground(IManager<T>... params) {
        ArrayList<T> resultList = new ArrayList<>();
        try {
            if (id == null){
                resultList.addAll(params[0].getAll());
            }
            else {
                resultList.add(params[0].get(id));
            }
            AsyncTaskResult<T> result = new AsyncTaskResult<T>(resultList);
            return result;
        } catch (Exception e) {
            AsyncTaskResult<T> result = new AsyncTaskResult<T>(e);
            return result;
        }
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<T> asyncTaskResult) {
        if (listener !=null) {
            listener.onTaskComplete(asyncTaskResult);
        }
    }
}
