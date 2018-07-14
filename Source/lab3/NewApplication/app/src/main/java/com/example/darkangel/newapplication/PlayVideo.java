package com.example.darkangel.newapplication;

import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayVideo extends AppCompatActivity {

    private static final int TAKE_VIDEO_CODE = 1;
    VideoView userVideo;

    private static final int MY_VIDEO_REQUEST_CODE = 100;



    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        userVideo = (VideoView) findViewById(R.id.viewVideo);
        //Button click eventlistener. Initializes the camera.
        Toast.makeText(this,"In Video activity",Toast.LENGTH_SHORT).show();
        if (checkSelfPermission(Manifest.permission.CAPTURE_VIDEO_OUTPUT) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAPTURE_VIDEO_OUTPUT}, MY_VIDEO_REQUEST_CODE);
        }

    }

    public void callVideo(View v) {
        //getting the image
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (videoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(videoIntent, TAKE_VIDEO_CODE);
        }
    }

    //If the video is captured then set the image video to the video captured.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == TAKE_VIDEO_CODE && resultCode == RESULT_OK) {
          Uri videoUri = intent.getData();
          userVideo.setVideoURI(videoUri);


            Log.d("VideoDemo", "Video saved");
        }
    }

    public void redirectToHome(View v) {
        Intent redirect = new Intent(PlayVideo.this, NewActivity.class);
        startActivity(redirect);
    }
}
