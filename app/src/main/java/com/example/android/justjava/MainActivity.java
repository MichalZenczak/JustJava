/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

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
       String order = createOrderSummary(userName, price, hasWhippedCream, hasChocolate);

       Intent intent = new Intent(Intent.ACTION_SENDTO);
       intent.setData(Uri.parse("mailto:")); // only email apps should handle this
       intent.putExtra(Intent.EXTRA_EMAIL, "zenczak.michal@gmail.com");
       intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order summary");
       intent.putExtra(Intent.EXTRA_TEXT, order);

       if (intent.resolveActivity(getPackageManager()) != null) {
           startActivity(intent);
       }
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
        if (quantity == 100 ) {
            Toast.makeText(this, "Dude! Take it easy! 100 is enough!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        if (quantity == 1){
            Toast.makeText(this, "You cannot order less than one coffee!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }
}