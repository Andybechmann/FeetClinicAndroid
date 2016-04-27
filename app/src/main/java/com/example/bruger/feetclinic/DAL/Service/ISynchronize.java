package com.example.bruger.feetclinic.DAL.Service;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public interface ISynchronize<T>{
    boolean synchronize(IRepository<T> main, IRepository<T> slave);
}
