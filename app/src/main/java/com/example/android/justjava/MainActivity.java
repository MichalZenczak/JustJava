
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *

 *
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       EditText nameEditText = findViewById(R.id.name_view);
       String userName = nameEditText.getText().toString();

       CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCreamCheckBox);
       Boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

       CheckBox chocolateCheckBox = findViewById(R.id.chocolateCreamCheckBox);
       Boolean hasChocolate = chocolateCheckBox.isChecked();

       int price = calculatePrice(hasWhippedCream, hasChocolate);
       displayMessage(createOrderSummary(userName, price, hasWhippedCream, hasChocolate));
    }

    /**
     * Calculates the price of the order.
     *@return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate){
            basePrice += 2;
        }
        return basePrice * quantity;
    }

    private String createOrderSummary(String userName, int price, boolean hasWhippedCream, boolean hasChocolate){
        String orderSummary = "Name: " + userName;
        orderSummary += "\nadd Whipped Cream? " + hasWhippedCream;
        orderSummary += "\nadd Chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank You!";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment (View view){
        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}