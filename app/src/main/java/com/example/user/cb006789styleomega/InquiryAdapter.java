package com.example.user.cb006789styleomega;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 9/18/2017.
 */

public class InquiryAdapter extends ArrayAdapter<Inquiry> {
    private Context context1;
    private List<Inquiry> inquiriesList1;


    public InquiryAdapter(Context context,List<Inquiry> inquiriesList) {
        super(context, R.layout.activity_inquiry,inquiriesList);
        this.context1 = context;
        this.inquiriesList1 = inquiriesList;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context1.getSystemService(context1.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.inquirygrid,parent,false);


        TextView inquiryusername=(TextView)view.findViewById(R.id.tvusernameprod);
        inquiryusername.setText(String.valueOf(inquiriesList1.get(position).getUsername()));

        TextView inquiryprodname =(TextView)view.findViewById(R.id.tvprodnameinq);
        inquiryprodname.setText(String.valueOf(inquiriesList1.get(position).getProdName()));



        TextView InquiryDetails = (TextView) view.findViewById(R.id.tvdecinq);
        InquiryDetails.setText(String.valueOf(inquiriesList1.get(position).getInqDetails()));

        return view;
    }
}

