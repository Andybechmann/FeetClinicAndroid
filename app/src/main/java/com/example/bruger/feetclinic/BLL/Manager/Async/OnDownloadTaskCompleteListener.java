package com.example.bruger.feetclinic.BLL.Manager.Async;

import com.example.bruger.feetclinic.BLL.BE.IEntity;

/**
 * Created by Stepanenko on 09/05/2016.
 */
public interface OnDownloadTaskCompleteListener<T extends IEntity> {
    void onDownloadTaskComplete(AsyncTaskResult<T> result);
}

