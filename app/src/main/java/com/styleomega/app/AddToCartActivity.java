package com.styleomega.app; // Changed package

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// Assuming Cart, DataBaseHelper, CartAdapter will also be moved/refactored to com.styleomega.app package
// If DataBaseHelper refers to com.styleomega.app.db.DatabaseHelper, specific import needed later.

public class AddToCartActivity extends AppCompatActivity {
    private List<Cart> checkoutsList;
    private ListView listcheckouts;
    private Button checkout;
    private TextView totalPrice;

    int totalPriceCounter;
    //Type baseType = new TypeToken<ArrayList<checkout>>() {}.getType();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        try {
            SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            String userName = logindetails.getString("username", null);
            checkoutsList = new ArrayList<Cart>();
            // This DataBaseHelper is from the old package.
            // It will need to be updated to use the new com.styleomega.app.db.DatabaseHelper
            // or the old DataBaseHelper class needs to be refactored and moved.
            DataBaseHelper db = new DataBaseHelper(getBaseContext());
            checkoutsList = db.cartlist(userName); // Assumes Cart will be in com.styleomega.app
            checkout=(Button)findViewById(R.id.btncheck);

            if (checkoutsList.size() == 0) {
                this.listcheckouts = (ListView) findViewById(R.id.listView);
            } else {

                this.listcheckouts = (ListView) findViewById(R.id.listView);
                // Assumes CartAdapter will be in com.styleomega.app
                this.listcheckouts.setAdapter(new CartAdapter(this, checkoutsList));
                this.totalPrice = (TextView) findViewById(R.id.textView12);

                int count = db.cartcounttotal(userName);
                this.totalPrice.setText(Integer.toString(count));


            }

            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(AddToCartActivity.this);
                    builder.setTitle("Information");
                    builder.setMessage("You have succesfully checked out of the system ");
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            //Finishing the dialog and removing Activity from stack.
                            dialogInterface.dismiss();
                            finish();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    checkoutsList .clear();
                    listcheckouts.setAdapter(null);

                    Intent registerIntent = new Intent(AddToCartActivity.this, Catergorys.class);
                    AddToCartActivity.this.startActivity(registerIntent);

                }
            });

        }
        catch (Exception ex)
        {

            Toast.makeText(AddToCartActivity.this,"Error :" +  ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }



    }
}
