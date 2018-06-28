package com.example.darkangel.newapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class NewActivity  extends AppCompatActivity {
    Button goBack;
    protected void onCreate(Bundle newInstanceState) {
        super.onCreate(newInstanceState);
        setContentView(R.layout.activity2);
    }
        public void goback(View v){
                Intent intent = new Intent(NewActivity.this, MainActivity.class);
                startActivity(intent);

            }
    }

