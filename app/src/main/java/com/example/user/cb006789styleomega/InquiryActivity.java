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
import android.widget.TextView;
import android.widget.Toast;

public class InquiryActivity extends AppCompatActivity {

    TextView inqprodname;
    TextView inqid;
    TextView inqDetails;
    Button submit;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiry);
        try {
            final Intent intent = getIntent();
            final Inquiry inq = (Inquiry) intent.getSerializableExtra("inquiry");

            SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            userName = logindetails.getString("username", null);

            inqprodname = (TextView) findViewById(R.id.Tfuser);
            inqid = (TextView) findViewById(R.id.tfinq);
            inqDetails = (TextView) findViewById(R.id.Tdesc);
            submit = (Button) findViewById(R.id.Baddinq);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create the database inquiry
                    DataBaseHelper db = new DataBaseHelper(getBaseContext());
                    Inquiry inq = new Inquiry();
                    inq.setUsername(userName);
                    inq.setProdName(inqprodname.getText().toString());
                    inq.setInqDetails(inqDetails.getText().toString());
                    if (db.AdInquiryCreate(inq)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Your Inquiry has already send to the database! Please visit our contact cloth store page in order to view your inquiry");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Alright!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                //Intent intent1 = new Intent(inquiries.this,.class);
                                //startActivity(intent1);
                            }
                        });
                        builder.create().show();
                    } else {
                        Toast.makeText(InquiryActivity.this, "Your details are invalid, Please retry! thank you!:).", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception ex) {
        }

    }
}
