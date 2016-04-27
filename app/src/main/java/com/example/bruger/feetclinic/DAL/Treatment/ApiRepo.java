package com.example.bruger.feetclinic.DAL.Treatment;

import android.util.Log;

import com.example.bruger.feetclinic.BLL.BE.Treatment;
import com.example.bruger.feetclinic.DAL.IRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiRepo implements IRepository<Treatment> {

	private final String URL = "http://feetclinic-ievg0012.rhcloud.com/api/treatments";

	private final String TAG = "TREATMENT";

	private ArrayList<Treatment> treatments;

	public ApiRepo(){ treatments = new ArrayList<Treatment>();
	}

    /**
     * Simple mockup function - can be used when testing offline.
     */

	public void loadAll()
	{
        try {
		String result = getContent(URL);

        if (result == null) return;
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject treatment = array.getJSONObject(i);

				Treatment t = new Treatment( treatment.getString("name"),treatment.getInt("price"));
				treatments.add(t);
			}

		} catch (JSONException e) {
			Log.e(TAG,
					"There was an error parsing the JSON", e);
		} catch (Exception e)
        {  Log.d(TAG, "General exception in loadAll " + e.getMessage());
        }
	}
	
	public ArrayList<Treatment> getAll()
	{
		loadAll();
		return treatments; }

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


	/**
	 * Get the content of the url as a string. Based on using a scanner.
	 * @param urlString - the url must return data typical in either json, xml, csv etc..
	 * @return the content as a string. Null is something goes wrong.
	 */
    private String getContent(String urlString)
    {
        StringBuilder sb = new StringBuilder();
        try {
            java.net.URL url = new URL(urlString);
            Scanner s = new Scanner(url.openStream());

            while (s.hasNextLine()) {
                String line = s.nextLine();
                sb.append(line);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
