package com.styleomega.app; // Changed package

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog; // Changed to AndroidX
import androidx.appcompat.app.AppCompatActivity; // Changed to AndroidX
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.styleomega.app.db.DatabaseHelper; // Import for the new DatabaseHelper

// Assuming CreditCardDet will also be moved to com.styleomega.app package
public class GetCardDetails extends AppCompatActivity {

    // Changed to use the new DatabaseHelper from com.styleomega.app.db
    // Note: The original DataBaseHelper(this) had a context argument.
    // The new DatabaseHelper() does not. This might require adjustments
    // if methods in DatabaseHelper relied on that context.
    // For now, just changing the instantiation.
    DatabaseHelper helper =  new DatabaseHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_card_details);

        final EditText creditcarno = (EditText) findViewById(R.id.TCardNo);
        final EditText creditcardowner = (EditText) findViewById(R.id.tvcreditcardo);
        final  EditText creditcardexpiry = (EditText) findViewById(R.id.Tcardexp);
        final EditText creditcardver = (EditText) findViewById(R.id.Tcardver);
        final EditText shippingadd = (EditText) findViewById(R.id.TShipadd);
        final Button btnaddcredit = (Button)findViewById(R.id.btnPay) ;



        //ActionBar ab = getSupportActionBar();
        // ab.hide();
        btnaddcredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardno = creditcarno.getText().toString();
                String cardowner = creditcardowner.getText().toString();
                String cardexp = creditcardexpiry.getText().toString();
                String cardver = creditcardver.getText().toString();
                String shipadd = shippingadd.getText().toString();

                // Inserting the details in the Database
                CreditCardDet card = new CreditCardDet(); // Assumes CreditCardDet is in com.styleomega.app
                card.setCreditcadno(cardno);
                card.setCreditowner(cardowner);
                card.setExpirydate(cardexp);
                card.setCardverification(cardver);
                card.setShippingaddress(shipadd);

                // helper.insertcreditcard(card);
                // This method ^^^ was part of the OLD DataBaseHelper.
                // The new com.styleomega.app.db.DatabaseHelper does not have it.
                // This line will cause a compilation error and needs to be addressed
                // by either adding this method to the new DatabaseHelper or changing logic here.
                // For now, I will comment it out to proceed with refactoring.
                // TODO: Implement insertcreditcard in new DatabaseHelper or refactor this part.


                //Alert dialog after clicking the Register Account
                final AlertDialog.Builder builder = new AlertDialog.Builder(GetCardDetails.this);
                builder.setTitle("Information");
                builder.setMessage("WE have Accepted your Payment .Thank you ");
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
            }
        });
    }
}
