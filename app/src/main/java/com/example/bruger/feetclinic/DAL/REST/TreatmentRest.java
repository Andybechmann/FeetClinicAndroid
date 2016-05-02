package com.example.bruger.feetclinic.DAL.REST;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.GsonService;
import com.example.bruger.feetclinic.Service.HttpClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Stepanenko on 27/04/2016.
 */
public class TreatmentRest implements IRepository<Treatment> {

    private String URL = "http://feetclinic-ievg0012.rhcloud.com/api/treatments";
    private HttpClient httpClient;
    private GsonService<Treatment> gsonService;

    public TreatmentRest(String url) {
        this();
        this.URL = url;
    }
    public TreatmentRest() {
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
                .doUpdate(treatmentToUpdate, appendId(URL, treatment.get_Id()));
        return gsonService.fromJson(updatedTreatment);
    }

    @Override
    public Treatment update(Treatment treatment, String id) throws IOException {
        treatment.set_Id(id);
        return update(treatment);
    }

    @Override
    public boolean delete(Treatment treatment) throws IOException {
        return delete(treatment.get_Id());
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
