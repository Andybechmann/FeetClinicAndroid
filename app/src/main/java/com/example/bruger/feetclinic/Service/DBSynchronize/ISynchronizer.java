package com.example.bruger.feetclinic.Service.DBSynchronize;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public interface ISynchronizer<T>{
    boolean synchronize();
}
