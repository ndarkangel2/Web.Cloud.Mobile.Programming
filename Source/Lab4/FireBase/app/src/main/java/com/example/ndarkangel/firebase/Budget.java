package com.example.ndarkangel.firebase;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Budget extends AppCompatActivity {
    private EditText amound;
    private Button enter;
    private Button viewButton;
    private Button viewTotal;
    private Button delete;
    Button SignOut;
    DataBaseHelper myDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        amound = (EditText) findViewById(R.id.userAmount);
        enter = (Button) findViewById(R.id.enter);
        viewButton = (Button) findViewById(R.id.view_all);
        viewTotal = (Button) findViewById(R.id.view_Total);
        delete = (Button) findViewById(R.id.delete);
        SignOut = (Button) findViewById(R.id.signOut);
        myDatabase = new DataBaseHelper(this);
        final Spinner myspinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spend_summary, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        myspinner.setAdapter(adapter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted =  myDatabase.insertCategory( myspinner.getSelectedItem().toString(), amound.getText().toString() );
                if(isInserted = true)
                    Toast.makeText(Budget.this, "Data Inserted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Budget.this, "Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Cursor res = myDatabase.getData();
                assert(res.getCount() < 0 ):"Count is " +res.getCount();
                if(res.getCount() == 0){
                    //getting items
                    showMyMessage("error", " noting found" );
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append( res.getString(1)+ "\n");
                    buffer.append( res.getString(2)+ "\n"+ "\n");
                }
                showMyMessage("Data", buffer.toString());
            }
        });
        viewTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor myres = myDatabase.getSum();
                assert(myres.getCount() < 0 ):"Count is " +myres.getCount();
                if(myres.getCount() == 0){
                    //getting items
                    showMyMessage("error", " noting found" );
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (myres.moveToNext()){
                    buffer.append( myres.getString(0)+ "\t"+ "\t"+ "\t"+ "\t"+ "\t");
                    buffer.append( myres.getString(1)+ "\n");
                }
                showMyMessage("Total", buffer.toString());
            }

        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor_DT = myDatabase.Cleardb();
                if(cursor_DT.getCount() == 0){
                    //this is the message pop up if there is nothing in the base
                    showMyMessage("Database now Cleared", "Enter your new Data" );
                    return;
                }
            }
        });
        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Budget.this, MainActivity.class));


            }
        });

    }
    public void showMyMessage( String title, String myMessage ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(myMessage);
        builder.show();
    }

    public void onPhotoClick(View v){
        Intent intent = new Intent(Budget.this, Camera.class);
        startActivity(intent);

    }
    public void recognizeItemInScreen(View v){
       Intent intent = new Intent(Budget.this, Recognize.class);
        startActivity(intent);

    }
    public void openCalender(View v){
        Intent intent = new Intent(Budget.this, Calender.class);
        startActivity(intent);

    }
}

