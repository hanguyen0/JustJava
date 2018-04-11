/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //Whipped Cream check box
        CheckBox isWhippedCream= findViewById(R.id.whippedcream_check_box);
        boolean hasWhippedCream = isWhippedCream.isChecked();
        //Log.v("MainActivity", "Has whipped cream? " + hasWhippedCream);

        //Chocolate check box
        CheckBox isChocolate = findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = isChocolate.isChecked();

        //Calculate price
        int price=calculatePrice(quantity, hasWhippedCream, hasChocolate);

        //display Order Summary Message
        String priceMessage=createOrderSummary(price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increase the number of coffee cup and display it.
     */
    public void increment(View view){
        //Add toast when customer choose less than 1 or greater than 100 cups of coffees

        Toast toast = Toast.makeText(this, "You can not order more than 100 cups of coffee at once!", Toast.LENGTH_SHORT);

        quantity+=1;
        if (quantity == 100){
            toast.show();
            return;
        }
        display(quantity);
    }

    /**
     * This method decrease the number of coffee cup and display it.
     */
    public void decrement(View view){
        //Add toast when customer choose less than 1 or greater than 100 cups of coffees

        Toast toast = Toast.makeText(this, "You can not have less than 1 cup of coffee!", Toast.LENGTH_SHORT);

        quantity-=1;
        if (quantity == 1){
            toast.show();
            return;
        }
        display(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     * @param hasChocolate whether the customer choose chocolate in their coffee
     * @param  hasWhippedCream wether the customer choose whipped cream in their coffee
     */
    private int calculatePrice(int quantity,boolean hasWhippedCream, boolean hasChocolate) {
        int coffeePrice= 5;

        //add $1 per order for customer wants whipped cream for their coffeee
        if (hasWhippedCream){
            coffeePrice+= 1;
        }

        //add $2 per order if chocolate is chosen
        if (hasChocolate){
            coffeePrice+=2;
        }

        return coffeePrice*quantity;
    }

    /**
     * This method return a summary of order.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean chocolate) {
        EditText editText = findViewById(R.id.name_input);
        String priceMessage="Name: " + editText.getText().toString();
        priceMessage += "\nHas whipped cream? " + hasWhippedCream;
        priceMessage += "\nHas chocolate? " + chocolate;
        priceMessage+="\nQuantity: " + quantity;
        priceMessage+="\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

}