package com.example.bruger.feetclinic.DAL.REST;

import com.example.bruger.feetclinic.BLL.BE.Therapist;
import com.example.bruger.feetclinic.DAL.IRepository;
import com.example.bruger.feetclinic.Service.HttpClient;
import com.example.bruger.feetclinic.Service.JsonService;

import java.util.ArrayList;

/**
 * Created by Bruger on 04-05-2016.
 */
public class TherapistRest implements IRepository<Therapist> {

    private String URL = "http://feetclinic-ievg0012.rhcloud.com/api/therapist";
    private HttpClient httpClient;
    private JsonService<Therapist> jsonService;



    public TherapistRest(String url) {
        this.URL = url;
        httpClient = new HttpClient(URL);
        jsonService = new JsonService(Therapist.class);
    }
    public TherapistRest() {
        httpClient = new HttpClient(URL);
        jsonService = new JsonService(Therapist.class);
    }



    @Override
    public Therapist create(Therapist therapist) throws Exception {
        String therapistAsJson = jsonService.toJson(therapist);
        String responseAsJson = httpClient.doPost(therapistAsJson);
        return jsonService.fromJson(responseAsJson);
    }

    @Override
    public Therapist update(Therapist therapist) throws Exception {
        String therapistToUpdate = jsonService.toJson(therapist);
        String updatedTherapist = httpClient
                .doUpdate(therapistToUpdate, appendId(URL, therapist.get_Id()));
        return jsonService.fromJson(updatedTherapist);
    }

    @Override
    public Therapist update(Therapist therapist, String id) throws Exception {
        therapist.set_Id(id);
        return update(therapist);
    }

    @Override
    public boolean delete(Therapist therapist) throws Exception {
        return delete(therapist.get_Id());
    }

    @Override
    public boolean delete(String id) throws Exception {
        if (httpClient.delete(appendId(URL,id)).isSuccessful()){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Therapist> getAll() throws Exception {
        String all = httpClient.doGet();
        return jsonService.fromJsonArray(all);
    }

    @Override
    public Therapist get(String id) throws Exception {
        String therapist = httpClient.doGet(appendId(URL, id));
        return jsonService.fromJson(therapist);
    }

    private String appendId(String url,String id){
        StringBuilder builder = new StringBuilder(url);
        builder.append("/");
        builder.append(id);
        return builder.toString();
    }
}
