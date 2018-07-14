package com.example.darkangel.newapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class NewActivity  extends AppCompatActivity {


    protected void onCreate(Bundle newInstanceState) {
        super.onCreate(newInstanceState);
        setContentView(R.layout.activity2);
    }


    public void goback(View v) {
        Intent intent = new Intent(NewActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onPhotoClick(View v){
        Intent intent = new Intent(NewActivity.this, Camera.class);
        startActivity(intent);

    }
    public void onLocationClick(View v) {
        Intent intent = new Intent(NewActivity.this, mapGoogle.class);
        startActivity(intent);
    }
    public void onVideoClick(View v) {
        Intent intent = new Intent(NewActivity.this,PlayVideo.class);
        startActivity(intent);
    }
    public void scan(View v){
        Intent intent = new Intent(NewActivity.this, BarScan.class);
        startActivity(intent);
    }
    public void redirectToNews(View v) {
        Intent redirect = new Intent(NewActivity.this, NewsActivity.class);
        startActivity(redirect);
    }
    public void textScan(View v) {
        Intent redirect = new Intent(NewActivity.this, TextRec.class);
        startActivity(redirect);
    }


}

