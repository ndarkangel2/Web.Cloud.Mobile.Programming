package com.example.darkangel.newapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUpClick(View v) {

        if (v.getId() == R.id.signupbutton) {
            EditText name = findViewById(R.id.Sname);
            EditText password = findViewById(R.id.SPassword);


            String namestr = name.getText().toString();
            String passwordstr = password.getText().toString();


            Intent i = new Intent(SignUp.this, MainActivity.class);
            startActivity(i);

        }

    }

}