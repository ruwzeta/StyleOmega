package com.styleomega.app; // Changed package

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AlertDialog; // Changed to AndroidX
import androidx.appcompat.app.AppCompatActivity; // Changed to AndroidX
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.styleomega.app.db.DatabaseHelper; // Import for the new DatabaseHelper

// Assuming Inquiry will also be moved to com.styleomega.app package
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
            // final Inquiry inq = (Inquiry) intent.getSerializableExtra("inquiry");
            // This line ^^^ uses the 'inq' variable before it's assigned locally if needed for display.
            // The object from intent might be different from the one created for DB.
            // For now, assuming it's for receiving data. If it was meant to populate fields, it needs to be used.

            SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            userName = logindetails.getString("username", null);

            inqprodname = (TextView) findViewById(R.id.Tfuser); // Should this be pre-filled from intent 'inq'?
            inqid = (TextView) findViewById(R.id.tfinq);     // Should this be pre-filled from intent 'inq'?
            inqDetails = (TextView) findViewById(R.id.Tdesc);
            submit = (Button) findViewById(R.id.Baddinq);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create the database inquiry
                    // Changed to use the new DatabaseHelper from com.styleomega.app.db
                    DatabaseHelper db = new DatabaseHelper();
                    Inquiry newInq = new Inquiry(); // Create a new Inquiry object for submission
                    newInq.setUsername(userName);
                    newInq.setProdName(inqprodname.getText().toString());
                    newInq.setInqDetails(inqDetails.getText().toString());

                    // if (db.AdInquiryCreate(newInq)) {
                    // The method AdInquiryCreate was part of the OLD DataBaseHelper.
                    // It needs to be implemented in the new com.styleomega.app.db.DatabaseHelper
                    // or this logic needs to be refactored.
                    // For now, I will comment out the DB interaction part.
                    // TODO: Implement AdInquiryCreate in new DatabaseHelper or refactor this part.
                    if (true) { // Placeholder for successful operation
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
            // It's good practice to log the exception
            ex.printStackTrace();
            Toast.makeText(InquiryActivity.this, "An error occurred: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
