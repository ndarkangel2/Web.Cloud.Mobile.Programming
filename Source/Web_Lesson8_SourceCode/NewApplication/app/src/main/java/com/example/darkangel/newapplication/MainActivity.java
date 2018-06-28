package com.example.darkangel.newapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.String;
public class MainActivity extends AppCompatActivity {
    Button logIn;
    EditText username,password;
    TextView errorview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void checkCredentials(View v) {
        username = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        errorview = (TextView)findViewById(R.id.error);
        String userName = username.getText().toString();
        String pas =  password.getText().toString();

        boolean validationFlag = false;
        if(!userName.isEmpty() && !pas.isEmpty()){
            if(userName.equals("Admin") && pas.equals("Admin")){
                validationFlag = true;
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        }
        if(!validationFlag){

            errorview.setVisibility(View.VISIBLE);

        }

    }
}
