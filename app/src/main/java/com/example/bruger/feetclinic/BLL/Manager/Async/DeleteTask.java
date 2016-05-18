package com.example.bruger.feetclinic.BLL.Manager.Async;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.BLL.Manager.IManager;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public class DeleteTask<T extends IEntity> extends AsyncTask<IManager<T>,Void,AsyncTaskResult<T>> {

    private final OnDeleteTaskCompleteListener listener;
    private  String id = null;
    private T entity = null;

    public DeleteTask(OnDeleteTaskCompleteListener listener, String id) {
        this.listener = listener;
        this.id = id;
    }

    public DeleteTask(OnDeleteTaskCompleteListener listener, T entity) {
        this.listener = listener;
        this.entity = entity;
    }

    @Override
    protected AsyncTaskResult<T> doInBackground(IManager<T>... params) {
        ArrayList<T> resultList = new ArrayList<>();
        try {
            if (id == null){
                params[0].delete(entity);
            }
            else {
                params[0].delete(id);
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
            listener.onDeleteTaskComplete(asyncTaskResult);
        }
    }
}
