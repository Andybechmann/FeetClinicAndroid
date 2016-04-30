package com.example.bruger.feetclinic.DAL.Treatment;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.GsonService;
import com.example.bruger.feetclinic.Service.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class RestApiRepo implements IRepository<Treatment> {

    private String URL = "http://feetclinic-ievg0012.rhcloud.com/api/treatments";
    private HttpClient httpClient;
    private GsonService<Treatment> gsonService;

    public RestApiRepo(String url) {
        this();
        this.URL = url;
    }
    public RestApiRepo() {
        httpClient = new HttpClient(URL);
        gsonService = new GsonService<Treatment>(Treatment.class);
    }

    @Override
    public ArrayList<Treatment> getAll() throws IOException {
        String all = httpClient.doGet();
        return gsonService.fromJsonArray(all);
    }

    @Override
    public Treatment create(Treatment treatment) throws IOException {
        String treatmentAsJson = gsonService.toJson(treatment);
        String responseAsJson = httpClient.doPost(treatmentAsJson);
        return gsonService.fromJson(responseAsJson);
    }

    @Override
    public Treatment get(String id) throws IOException {
        String treatment = httpClient.doGet(appendId(URL, id));
        return gsonService.fromJson(treatment);
    }

    @Override
    public Treatment update(Treatment treatment) throws IOException {

        String treatmentToUpdate = gsonService.toJson(treatment);
        String updatedTreatment = httpClient
                .doUpdate(treatmentToUpdate, appendId(URL, treatment.getId()));
        return gsonService.fromJson(updatedTreatment);
    }

    @Override
    public Treatment update(Treatment treatment, String id) throws IOException {
        treatment.setId(id);
        return update(treatment);
    }

    @Override
    public boolean delete(Treatment treatment) throws IOException {
        return delete(treatment.getId());
    }

    @Override
    public boolean delete(String id) throws IOException {
        if (httpClient.delete(appendId(URL,id)).isSuccessful()){
            return true;
        }
        return false;
    }

    private String appendId(String url,String id){
        StringBuilder builder = new StringBuilder(url);
        builder.append("/");
        builder.append(id);
        return builder.toString();
    }
}
