package com.example.user.cb006789styleomega;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetCardDetails extends AppCompatActivity {

    DataBaseHelper helper =  new DataBaseHelper(this);
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
                CreditCardDet card = new CreditCardDet();
                card.setCreditcadno(cardno);
                card.setCreditowner(cardowner);
                card.setExpirydate(cardexp);
                card.setCardverification(cardver);
                card.setShippingaddress(shipadd);
                helper.insertcreditcard(card);

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
