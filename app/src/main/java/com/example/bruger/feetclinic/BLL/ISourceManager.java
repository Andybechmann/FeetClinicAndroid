package com.example.bruger.feetclinic.BLL;

import com.example.bruger.feetclinic.DAL.IRepository;

/**
 * Created by Stepanenko on 29/04/2016.
 */
public interface ISourceManager<T> {
    IRepository<T> getResource();
}
