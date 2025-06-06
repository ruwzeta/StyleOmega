package com.styleomega.app; // Changed package

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog; // Changed to AndroidX
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 9/18/2017.
 */

// Assuming Cart, DataBaseHelper will also be moved/refactored to com.styleomega.app package
// If DataBaseHelper refers to com.styleomega.app.db.DatabaseHelper, specific import needed later.
public class CartAdapter extends ArrayAdapter<Cart> {
    private Context context;
    private List<Cart> checkoutList;



    public CartAdapter(Context context, List<Cart> resource) {

        super(context, R.layout.checkoutitem, resource);
        this.context = context;
        this.checkoutList = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.checkoutitem, parent, false);

        TextView checkoutsItemName = (TextView) view.findViewById(R.id.ItemNameText);
        checkoutsItemName.setText(String.valueOf(checkoutList.get(position).getItemname())); // Assumes Cart is in com.styleomega.app

        int qty ;




        TextView checkoutsItemPrice = (TextView) view.findViewById(R.id.ItemPriceText);
        checkoutsItemPrice.setText(String.valueOf(checkoutList.get(position).getItemprice())); // Assumes Cart is in com.styleomega.app



        Button deleteButton = (Button) view.findViewById(R.id.ItemDelButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = checkoutList.get(position).getCheckId(); // Assumes Cart is in com.styleomega.app
                // This DataBaseHelper is from the old package.
                DataBaseHelper db = new DataBaseHelper(context);
                try {
                    if (db.cartDel(s)) {
                        Intent intent2;
                        intent2 = new Intent(context,AddToCartActivity.class); // Assumes AddToCartActivity is in com.styleomega.app
                        context.startActivity(intent2);

                        Toast.makeText(context,"Success :" +  "Checkout Item has been  removed!",
                                Toast.LENGTH_SHORT).show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Your advertisment has not deleted!");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                    }
                }catch (Exception ex)
                {}

            }
        });

        return view;


    }
}
