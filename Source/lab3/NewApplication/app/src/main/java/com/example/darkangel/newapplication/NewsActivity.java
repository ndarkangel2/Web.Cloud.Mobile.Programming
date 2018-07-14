package com.example.darkangel.newapplication;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by gangi on 7/13/2018.
 */

public class NewsActivity extends AppCompatActivity{

    EditText searchText;
    ListView listView;
    Button searchGuardianButton;

    private static final String GUARDIAN_API_URL= "https://content.guardianapis.com/search?api-key=918be697-d3b5-4008-a8a2-8ce583a40f27&q=";
    Hashtable<String, String> hashtable = new Hashtable<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        searchText = findViewById(R.id.guardianSearch);
        listView = findViewById(R.id.listView);
        searchGuardianButton = findViewById(R.id.searchGuardian);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String picked = String.valueOf(adapterView.getItemAtPosition(i));

                Uri webURI = Uri.parse(hashtable.get(picked));

                Intent eqIntent = new Intent(Intent.ACTION_VIEW, webURI);
                startActivity(eqIntent);
            }
        });
    }

    // On Click of Search Guardian API
    public void searchGuardianAPI(View view) {
        if(searchText != null){

            fetchDetailsAndSet(searchText.getText().toString());
        }else{

            Toast.makeText(NewsActivity.this, "Input text should not be Empty !", Toast.LENGTH_LONG).show();
        }
    }

    private void fetchDetailsAndSet(String searchString) {

        URL url = null;

        String jsonResponse = "";
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        URLConnection urlConnection;
        String apiURL = GUARDIAN_API_URL+searchString;
        System.out.println(apiURL);
        try {

            url = new URL(apiURL);

            urlConnection = url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) !=  null) {

                stringBuilder.append(line);
            }

            if(bufferedReader != null){
                bufferedReader.close();
            }

            jsonResponse = stringBuilder.toString();


            JSONObject jsonObject = new JSONObject(jsonResponse).getJSONObject("response");

            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            List<String> stringList = new ArrayList<>();

            for(int i =0;i < jsonArray.length(); i++){

                JSONObject json = jsonArray.getJSONObject(i);

                String webTitle = json.getString("webTitle");
                String webURL = json.getString("webUrl");

                stringList.add(webTitle);

                hashtable.put(webTitle, webURL);
            }

            ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, stringList);

            listView.setAdapter(listAdapter);

            searchText.setVisibility(View.INVISIBLE);
            searchGuardianButton.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            Log.e(NewsActivity.class.getSimpleName(), "Exception:  ", e);
        }
    }
}