package com.example.bruger.feetclinic.DAL.Treatment;

import android.util.Log;

import com.example.bruger.feetclinic.BE.Treatment;
import com.example.bruger.feetclinic.DAL.Treatment.ITreatmentDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class TreatmentDAO implements ITreatmentDAO {

	private final String URL = "http://feetclinic-ievg0012.rhcloud.com/api/treatments";

	private final String TAG = "TREATMENT";

	private ArrayList<Treatment> treatments;

	public TreatmentDAO(){ treatments = new ArrayList<Treatment>();
	}

    /**
     * Simple mockup function - can be used when testing offline.
     */
    public void loadFake()
    {
		treatments.add(new Treatment("søren"));
		treatments.add(new Treatment("peter"));
		treatments.add(new Treatment("søren25"));

    }

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
	{ return treatments; }


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
/*
	private String fromUrlToString(String urlParam)
	{
		BufferedReader reader=null;
		try
		{
			java.net.URL url = new URL(urlParam);
			URLConnection conn = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null)
				sb.append(line + "\n");
			return sb.toString();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			Log.d(TAG, "URL malformed", e);
		}
		catch (IOException e) {
			e.printStackTrace();
			Log.d(TAG, "IO error", e);
		}
		return null;

	}
*/

}
