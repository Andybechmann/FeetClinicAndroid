package com.example.bruger.feetclinic.DAL.Treatment;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.HttpClient;

import java.util.List;



/**
 * Created by Stepanenko on 27/04/2016.
 */
public class RestApiClient implements IRepository<Treatment> {

    private String URL = "http://feetclinic-ievg0012.rhcloud.com/api/treatments";
    private HttpClient httpClient;

    public RestApiClient() {
        httpClient = new HttpClient(URL);
    }

    @Override
    public List<Treatment> getAll() {
        return null;
    }

    @Override
    public Treatment create(Treatment treatment) {
        return null;
    }

    @Override
    public Treatment get(String id) {
        return null;
    }

    @Override
    public Treatment update(Treatment treatment) {
        return null;
    }

    @Override
    public Treatment update(Treatment treatment, String id) {
        return null;
    }

    @Override
    public Treatment delete(Treatment treatment) {
        return null;
    }

    @Override
    public Treatment delete(String id) {
        return null;
    }
}
