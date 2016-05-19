package com.example.bruger.feetclinic.Service;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stepanenko on 28/04/2016.
 */
public class JsonService<T> {

    private Gson gson;
    private final Class<T> type;

    public JsonService(Class<T> type) {
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

    public ArrayList<T> fromJsonArray(String json){
        Type collectionType =new ListParameterizedType(type);
        ArrayList<T> list = gson.fromJson(json,collectionType);
        return list;
    }

    private class ListParameterizedType implements ParameterizedType {

        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[] {type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

}
