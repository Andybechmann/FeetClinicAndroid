package com.example.bruger.feetclinic.BLL.Manager.Async;

import com.example.bruger.feetclinic.BLL.BE.IEntity;

/**
 * Created by Stepanenko on 18/05/2016.
 */

public interface OnDeleteTaskCompleteListener<T extends IEntity> {
    void onDeleteTaskComplete(AsyncTaskResult<T> result);
}