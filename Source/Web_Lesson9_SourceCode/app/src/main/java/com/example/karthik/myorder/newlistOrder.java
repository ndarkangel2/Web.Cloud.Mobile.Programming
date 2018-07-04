package com.example.karthik.myorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class newlistOrder extends AppCompatActivity {
TextView neworder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlist_order);
        neworder = (TextView) findViewById(R.id.addtolist);


        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("Order_List");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("Order_List");

        }
        neworder.setText(newString);
    }
}
