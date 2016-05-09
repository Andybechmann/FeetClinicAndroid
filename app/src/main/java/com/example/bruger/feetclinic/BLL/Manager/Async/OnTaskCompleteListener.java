package com.example.bruger.feetclinic.BLL.Manager.Async;

import com.example.bruger.feetclinic.BLL.BE.IEntity;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public interface OnTaskCompleteListener<T extends IEntity> {
    void onTaskComplete(AsyncTaskResult<T> result);
}
