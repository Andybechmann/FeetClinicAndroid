package com.example.bruger.feetclinic.BLL.Manager.Async;

import java.util.ArrayList;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public class AsyncTaskResult<T> {
    private boolean isSuccessful;
    private ArrayList<T> results;
    private Exception exception;

    public AsyncTaskResult(Exception exception) {
        this.exception = exception;
        isSuccessful = false;
    }

    public AsyncTaskResult(ArrayList<T> results) {
        this.results = results;
        if (results != null){
            isSuccessful = true;
        }
        else {
            isSuccessful = false;
        }
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public Exception getException() {
        return exception;
    }
}
