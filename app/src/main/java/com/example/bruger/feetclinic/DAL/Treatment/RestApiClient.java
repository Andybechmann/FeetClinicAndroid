package com.example.bruger.feetclinic.DAL.Treatment;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;



/**
 * Created by Stepanenko on 27/04/2016.
 */
public class RestApiClient implements IRepository<Treatment> {

    private String URL = "http://feetclinic-ievg0012.rhcloud.com/api/treatments";

    public RestApiClient() {


    }


    @Override
    public List<Treatment> getAll() {
        try {
            StringBuilder sb = new StringBuilder();
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            if (connection.getResponseCode() !=200 ){
                throw new RuntimeException("Failed : HTTP Error code : " + connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));


            while ((br.readLine() ) != null) {

            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
