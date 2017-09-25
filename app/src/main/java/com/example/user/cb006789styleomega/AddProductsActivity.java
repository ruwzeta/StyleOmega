package com.example.user.cb006789styleomega;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                        DataBaseHelper dbs = new DataBaseHelper(getBaseContext());
                        Items items = new Items();
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
