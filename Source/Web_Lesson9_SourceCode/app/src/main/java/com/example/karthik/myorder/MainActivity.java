package com.example.karthik.myorder;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Intent;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 10;
    final int CHICKEN_PRICE = 3;
    final int ONION_PRICE = 1;
    String orderSummaryMessage="";
    int quantity = 2;


// Specify the layout to use when the list of choices appears

// Apply the adapter to the spinner


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //Spinner spinner = (Spinner) findViewById(R.id.spinner_list);
        // Create an ArrayAdapter using the string array and a default spinner layout
       // ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
         //      R.array.spinner_list_items, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        //arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
       // spinner.setAdapter(arrayAdapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//        check if whipped cream is selected
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_checked);
        boolean hasChicken = chicken.isChecked();
        //        check if chocolate is selected
        CheckBox onion = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnion = onion.isChecked();
//        calculate and store the total price
        float totalPrice = calculatePrice(hasChicken, hasOnion);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasChicken, hasOnion, totalPrice);
// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents
        Intent order = new Intent(MainActivity.this, newlistOrder.class);
        order.putExtra("Order_List", orderSummaryMessage);
        startActivity(order);

    }
    public void sendOrder(View view) {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//        check if whipped cream is selected
        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_checked);
        boolean hasChicken = chicken.isChecked();
        //        check if chocolate is selected
        CheckBox onion = (CheckBox) findViewById(R.id.onion_checked);
        boolean hasOnion = onion.isChecked();
//        calculate and store the total price
        float totalPrice = calculatePrice(hasChicken, hasOnion);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasChicken, hasOnion, totalPrice);
// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Pizza");
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);
        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));

    }
    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));

    }

    private String createOrderSummary(String userInputName, boolean hasChicken, boolean hasOnion, float price) {
         orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n"+
                getString(R.string.order_summary_chicken,boolToString(hasChicken))+"\n"+
                getString(R.string.order_summary_onion,boolToString(hasOnion)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,price) +"\n"+
                getString(R.string.thank_you);
        return orderSummaryMessage;

    }


    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasChicken, boolean hasOnion) {
        int basePrice = PIZZA_PRICE;
        if (hasChicken) {
            basePrice += CHICKEN_PRICE;
        }
        if (hasOnion) {
            basePrice += ONION_PRICE;
        }

        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select less than one pizza");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;

        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select atleast one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;


        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?

       boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
       switch(view.getId()) {
          case R.id.radio_cola:
              if (checked)
                    // User check cola
                  break;
         case R.id.radio_fanta:
                if (checked)
                    //User check fanta
                    break;
        }
    }

}
