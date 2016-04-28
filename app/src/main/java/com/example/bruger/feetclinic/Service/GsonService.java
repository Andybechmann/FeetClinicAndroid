package com.example.bruger.feetclinic.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Stepanenko on 28/04/2016.
 */
public class GsonService<T> {

    private Gson gson;
    private final Class<T> type;

    public GsonService(Class<T> type) {
        this.type = type;
        gson = new Gson();
    }
    public T fromJson(String json){
        T t = gson.fromJson(json,type);
        return t;
    }
    public String toJson(T t){
        return gson.toJson(t);
    }
    public String toJson(List<T> listOfT){
        return gson.toJson(listOfT);
    }
    public List<T> fromJsonArray(String json){
        Type collectionType = new TypeToken<List<T>>() {}.getType();
        return gson.fromJson(json,collectionType);
    }



}
