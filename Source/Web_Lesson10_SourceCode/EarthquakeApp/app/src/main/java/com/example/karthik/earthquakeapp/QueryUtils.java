package com.example.karthik.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthik on 11/3/17.
 */

public class QueryUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string


       // URL url = new URL("");
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        try {
            URL url= new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02");

                //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000/*miliseconds*/);
            urlConnection.setConnectTimeout(1500/* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

                //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)
            StringBuilder output = new StringBuilder();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null){
                output.append(line);
                line = reader.readLine();
            }
            jsonResponse = output.toString(); // store the url string

                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */
            JSONObject ResponseObject = new JSONObject(jsonResponse);
            JSONArray arraylist = ResponseObject.getJSONArray("features");
            // Return the list of earthquakes
            for(int i= 0; i< arraylist.length() ;  i++ ) {
                JSONObject jsonObj = arraylist.getJSONObject(i).getJSONObject("properties");
                double mag = jsonObj.getDouble("mag"); // pull data
                String location = jsonObj.getString("place"); //pull data
                long time = jsonObj.getLong("time");//pull time
                String newurl = jsonObj.getString("url"); // pull url
                earthquakes.add(new Earthquake(mag, location, time, newurl)); // send all this to main

            }


        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }



}
