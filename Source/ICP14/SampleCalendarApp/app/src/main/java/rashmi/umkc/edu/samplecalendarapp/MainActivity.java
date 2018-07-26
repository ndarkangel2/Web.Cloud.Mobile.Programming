package rashmi.umkc.edu.samplecalendarapp;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.displaycalendareventintent.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void openCalender(View v){
        Intent intent = new Intent(MainActivity.this, Calender.class);
        startActivity(intent);

    }


}