package com.styleomega.app; // Changed package

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Assuming Items and DataBaseHelper will also be moved to com.styleomega.app package
// If DataBaseHelper refers to com.styleomega.app.db.DatabaseHelper, specific import needed later.

public class AddProductsActivity extends AppCompatActivity {

    private EditText itemname;
    private EditText itemType;
    private EditText itemPrice;
    private EditText picURL;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        this.itemname = (EditText) findViewById(R.id.itmname);
        this.itemType =  (EditText)findViewById(R.id.txtItype);
        this.itemPrice = (EditText) findViewById(R.id.itmprice);
        this.picURL = (EditText) findViewById(R.id.picurl);
        this.submit = (Button) findViewById(R.id.btnadd);

        try {

            submit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    try{
                        // This DataBaseHelper is from the old package.
                        // It will need to be updated to use the new com.styleomega.app.db.DatabaseHelper
                        // or the old DataBaseHelper class needs to be refactored and moved.
                        // For now, class resolution will assume it's in the same 'com.styleomega.app' package.
                        DataBaseHelper dbs = new DataBaseHelper(getBaseContext());
                        Items items = new Items(); // Assuming Items will be in com.styleomega.app
                        items.setItemname(itemname.getText().toString());
                        items.setItemType(itemType.getText().toString());
                        items.setItemPrice(itemPrice.getText().toString());
                        items.setPicURL(picURL.getText().toString());
                        if(dbs.CreateItemFROMITEMOBJECT(items))
                        {

                            Toast.makeText(AddProductsActivity.this, "Your  Prodcut has been added !",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(AddProductsActivity.this,AdminHome.class);
                            startActivity(intent2);

                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setMessage("FAIL");
                            builder.setCancelable(false);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder.create().show();
                        }

                    }catch (Exception ex)
                    {
                        Toast.makeText(AddProductsActivity.this, ex.getMessage().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }catch (Exception ex)
        {
            Toast.makeText(AddProductsActivity.this, ex.getMessage().toString(),
                    Toast.LENGTH_SHORT).show();
        }



    }
}
