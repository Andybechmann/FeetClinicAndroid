package com.example.bruger.feetclinic.BLL.Manager.Async;

import android.os.AsyncTask;

import com.example.bruger.feetclinic.BLL.BE.IEntity;
import com.example.bruger.feetclinic.BLL.Manager.IManager;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public class CreateTask<T extends IEntity> extends AsyncTask<IManager<T>,Void,AsyncTaskResult<T>> {

    OnTaskCompleteListener listener;
    T entity;

    public CreateTask(OnTaskCompleteListener listener, T entity) {
        this.listener = listener;
        this.entity = entity;
    }

    @Override
    protected AsyncTaskResult<T> doInBackground(IManager<T>... params) {
        ArrayList<T> resultList = new ArrayList<>();
        try {
            resultList.add(params[0].create(entity));
            AsyncTaskResult<T> result = new AsyncTaskResult<T>(resultList);
            return result;
        } catch (Exception e) {
            AsyncTaskResult<T> result = new AsyncTaskResult<T>(e);
            return result;
        }
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<T> asyncTaskResult) {
        listener.onTaskComplete(asyncTaskResult);
    }
}
