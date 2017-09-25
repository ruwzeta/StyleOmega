package com.example.user.cb006789styleomega;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 9/18/2017.
 */

public class MainMenuAdapter extends ArrayAdapter<Items> {
    private Context context;
    private List<Items> itemsList;

    public MainMenuAdapter(Context context,List<Items> itemsList1)
    {
        super(context, R.layout.gridlayoutstyle,itemsList1);
        this.context = context;
        this.itemsList = itemsList1;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.gridlayoutstyle,null);
        }
        ImageView imageload = (ImageView) v.findViewById(R.id.image1);
        try {
            Picasso.with(context).load(itemsList.get(position).getPicURL())
                    .resize(100, 150) // here you resize your image to whatever width and height you like
                    .into(imageload);
        } catch (Exception ex) {}

        TextView textViewname = (TextView) v.findViewById(R.id.proName);
        textViewname.setText(String.valueOf(itemsList.get(position).getItemname()));

        return v;

    }
}
