package com.example.bruger.feetclinic.Service;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Stepanenko on 28/04/2016.
 */
public class HttpClient {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    private String url;

    public HttpClient(String url) {
        client = new OkHttpClient();
        this.url = url;
    }
//========================GET====================================
    public String doGet() throws IOException {
        return doGet(url);
    }
    public String doGet(String url) throws IOException {
        return get(url).body().string();
    }
    public Response get() throws IOException {
        return get(url);
    }
    public Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
//===============DELETE================================================

    public String doDelete() throws IOException{
        return doDelete(url);
    }
    public String doDelete(String url ) throws IOException{
        return delete(url).body().string();
    }
    public Response delete() throws IOException {
        return delete(url);
    }
    public Response delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
//===================POST==============================================
    public String doPost(String json) throws IOException {
       return doPost(json, url);
    }
    public String doPost(String json,String url) throws IOException {
        return post(json).body().string();
    }
    public Response post(String json) throws IOException {
       return post(json, url);
    }
    public Response post(String json,String url) throws IOException {
        RequestBody body = RequestBody.create  (JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
//====================UPDATE==================================================

    public String doUpdate(String json) throws IOException {
        return  doUpdate(json,url);
    }
    public String doUpdate(String json,String url) throws IOException {
        return update(json,url).body().string();
    }
    public Response update(String json) throws IOException {
        return update(json,url);
    }
    public Response update(String json,String url) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }




}
