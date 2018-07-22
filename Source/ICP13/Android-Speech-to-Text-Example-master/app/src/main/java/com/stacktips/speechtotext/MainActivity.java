package com.stacktips.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private Speaker speaker;
    private SpeechRecognizer sr;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speaker = new Speaker(this);
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);

        sr = SpeechRecognizer.createSpeechRecognizer(this);

        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
                speaker.allow(true);
                //speaker.speak(getString(R.string.Hello));
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        } catch (ActivityNotFoundException a) {

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                    if(result.get(0).equals("hello")){

                        speaker.speak(getString(R.string.what_is_your_name));
                    }
                    if(result.get(0).contains("name")){
                        String resname = result.get(0);
                        String[] words = resname.split(" ");
                        String name = words[words.length -1];
                       speaker.speak(getString(R.string.nice_to_meet_you) + name);
                    }
                    if(result.get(0).contains("feeling")){
                        speaker.speak(getString(R.string.system));
                    }
                    if(result.get(0).contains("assistant")){
                        speaker.speak(getString(R.string.take_care));
                    }
                    if(result.get(0).contains("take")){
                        speaker.speak(getString(R.string.fever));
                    }
                    if(result.get(0).contains("time")){

                        SimpleDateFormat sdfDate =new SimpleDateFormat("HH:mm");//dd/MM/yyyy
                        Date now = new Date();
                        String[] strDate = sdfDate.format(now).split(":");
                        if(strDate[1].contains("00")) {
                            strDate[1] = "o'clock";
                        }
                        speaker.speak(getString(R.string.time)+sdfDate.format(now));
                    }
                    if(result.get(0).contains("thank")){
                        speaker.speak(getString(R.string.thank));


                    }



                }

            }

        }
    }

}
