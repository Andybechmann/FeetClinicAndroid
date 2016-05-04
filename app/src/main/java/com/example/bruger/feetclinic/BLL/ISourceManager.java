package com.example.bruger.feetclinic.BLL;

import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.DAL.IUsyncRepository;

/**
 * Created by Stepanenko on 29/04/2016.
 */
public interface ISourceManager<T> {
    IRepository<T> getResource();
    boolean canSynchronize();
    IUsyncRepository<T> getResourceToSynchronize();

}
