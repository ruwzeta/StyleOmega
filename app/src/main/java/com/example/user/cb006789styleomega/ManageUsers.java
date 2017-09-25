package com.example.user.cb006789styleomega;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ManageUsers extends AppCompatActivity {
    private EditText etoldpass;
    private EditText etnewpass;
    private Button btnupdatepass;
    private TextView tvemaillink;
    DataBaseHelper db =new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);



        btnupdatepass=(Button)findViewById(R.id.updatepass);
        tvemaillink=(TextView) findViewById(R.id.tvupdateemail);

        tvemaillink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(ManageUsers.this, ManageEmail.class);
                ManageUsers.this.startActivity(Intent);
            }
        });

        btnupdatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                etoldpass=(EditText)findViewById(R.id.etPassword);
                etnewpass=(EditText)findViewById(R.id.etpass2);

                String oldpass=  etoldpass.getText().toString();
                String newpass =etnewpass.getText().toString();

                SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                String userName = logindetails.getString("username", null);

                String dbpass=db.searchPass(userName);

                if (oldpass.equals(dbpass)) {
                    if(db.updatepass(newpass,userName))
                    {
                        Toast temp = Toast.makeText(ManageUsers.this, "Password Successfully Changed", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                    else
                    {
                        Toast temp = Toast.makeText(ManageUsers.this, "Password Not set ", Toast.LENGTH_SHORT);
                        temp.show();
                    }


                } else {

                    Toast temp = Toast.makeText(ManageUsers.this, "Incorrect Password.Plase Check  Again", Toast.LENGTH_SHORT);
                    temp.show();

                }


            }
        });


    }
}
