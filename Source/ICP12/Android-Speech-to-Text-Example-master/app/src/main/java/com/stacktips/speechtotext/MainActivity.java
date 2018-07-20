package com.stacktips.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
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
                    if(result.get(0).equals("my name is Billy")){
                        speaker.speak(getString(R.string.nice_to_meet_you));
                    }
                    if(result.get(0).equals("i'm not feeling good what should i do")){
                        speaker.speak(getString(R.string.system));
                    }
                    if(result.get(0).equals("thank you my medicine assistant")){
                        speaker.speak(getString(R.string.take_care));
                    }
                    if(result.get(0).equals("what medicine should i take")){
                        speaker.speak(getString(R.string.fever));
                    }
                    if(result.get(0).equals("what time is it")){
                        speaker.speak(getString(R.string.time));
                    }
                    if(result.get(0).equals("thank you")){
                        speaker.speak(getString(R.string.thank));


                    }



                }

            }

        }
    }

}
