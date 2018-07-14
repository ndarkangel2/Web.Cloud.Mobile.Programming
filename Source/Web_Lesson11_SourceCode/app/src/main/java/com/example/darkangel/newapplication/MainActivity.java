package com.example.darkangel.newapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity  extends AppCompatActivity {


    protected void onCreate(Bundle newInstanceState) {
        super.onCreate(newInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onPhotoClick(View v){
        Intent intent = new Intent(MainActivity.this, Camera.class);
        startActivity(intent);

    }
    public void onLocationClick(View v) {
        Intent intent = new Intent(MainActivity.this, mapGoogle.class);
        startActivity(intent);
    }
    public void onVideoClick(View v) {
        Intent intent = new Intent(MainActivity.this,PlayVideo.class);
        startActivity(intent);
    }

}

