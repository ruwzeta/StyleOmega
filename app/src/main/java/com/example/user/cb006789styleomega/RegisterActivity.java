package com.example.user.cb006789styleomega;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    DataBaseHelper helper =  new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar ab = getSupportActionBar();
        ab.hide();
    }

    public void onSignUpClick(View v)
    {
        if (v.getId()==R.id.Bsignupbutton)
        {
            EditText name = (EditText) findViewById(R.id.tfulluname);
            EditText uname = (EditText) findViewById(R.id.tuname);
            EditText pass1 = (EditText) findViewById(R.id.TFpass1);
            EditText pass2 = (EditText) findViewById(R.id.Tpass2);
            EditText email = (EditText) findViewById(R.id.TEmail);

            String namestr = name.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            String emailstr = email.getText().toString();

            if (!pass1str.equals(pass2str)) {
                //Displays a pop-up message
                Toast pass = Toast.makeText(RegisterActivity.this, " Passwords don't match! " , Toast.LENGTH_SHORT);
                pass.show();
            }
            else{
                // Inserting the details in the Database
                UserDetails user = new UserDetails();
                user.setFullname(namestr);
                user.setUsername(unamestr);
                user.setPassword(pass1str);
                user.setEmail(emailstr);
                helper.insertUser(user);

                //Alert dialog after clicking the Register Account
                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Information");
                builder.setMessage("You have Successfully Registered to Style Omega .");
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


        }
    }
}
