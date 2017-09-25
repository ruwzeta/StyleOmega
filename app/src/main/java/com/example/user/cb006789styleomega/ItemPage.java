package com.example.user.cb006789styleomega;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ItemPage extends AppCompatActivity {

    private Context context;
    private TextView itmname, itmprice, itemtype;
    private ImageView picture;

    Button AddToCheckouts;
    private Items itm;

    private EditText itemqty;

    Button share;
    Button inq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);



        final Intent intent = getIntent();
        itm = (Items) intent.getSerializableExtra("it");
        this.itmname = (TextView) findViewById(R.id.itemname);
        this.itmname.setText(itm.getItemname().toString());

        this.itmprice = (TextView) findViewById(R.id.itemprice);
        this.itmprice.setText(itm.getItemPrice().toString());

        this.itemtype = (TextView) findViewById(R.id.itemtype);
        this.itemtype.setText(itm.getItemType().toString());


        this.picture = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(context).load(String.valueOf(itm.getPicURL())).resize(150, 150).into(picture);


        this.share=(Button)findViewById(R.id.btnshare) ;

        this.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plan");
                share.putExtra(Intent.EXTRA_SUBJECT,"FahionHouse StyleOmega - ");
                String applink = "Item Name :" + itmname.getText().toString().trim() + "|  Item Price : " +itmprice.getText().toString().trim() + "c#0004:  " +  "Omega Fashion House";
                share.putExtra(Intent.EXTRA_TEXT,"StyleOmega" + applink);
                startActivity(Intent.createChooser(share, "Share Via"));



            }
        });

        this.inq=(Button)findViewById(R.id.inqq);
        this.inq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ItemPage.this, InquiryActivity.class);
                startActivity(intent);




            }
        });



        this.AddToCheckouts = (Button) findViewById(R.id.addcart);
        this.AddToCheckouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                    String userName = logindetails.getString("username", null);
                    DataBaseHelper das = new DataBaseHelper(getBaseContext());

                    Cart cartobject = new Cart();
                    cartobject.setItemname(itmname.getText().toString().trim());
                    cartobject.setUsername(userName.toString().trim());
                    cartobject.setItemprice(Integer.parseInt(itmprice.getText().toString().trim()));

                    if (das.createCart(cartobject)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setMessage("Your item has successfully added to the cart! .");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                        builder.create().show();
                    } else {
                        Toast.makeText(ItemPage.this, "Error :" + "Something went wrong! It is not added to the cart!",
                                Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception ex) {
                    Toast.makeText(ItemPage.this, "Error :" + ex.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }); }
}
