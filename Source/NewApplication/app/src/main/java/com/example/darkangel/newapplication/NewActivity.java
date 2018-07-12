package com.example.darkangel.newapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class NewActivity  extends AppCompatActivity {
    Button goBack;

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
        Intent intent = new Intent(NewActivity.this, myGoogleMap.class);
        startActivity(intent);
    }
    public void onVideoClick(View v) {
        Intent intent = new Intent(NewActivity.this,PlayVideo.class);
        startActivity(intent);
    }
    public void onTelephoneClick(View v) {
        Intent intent = new Intent(NewActivity.this, Telephone.class);
        startActivity(intent);
    }


}

