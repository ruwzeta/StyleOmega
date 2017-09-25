package com.example.user.cb006789styleomega;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageEmail extends AppCompatActivity {
    private Button  btnupdateemail;
    private EditText etoldemail,etnewemail;
    DataBaseHelper db =  new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_email);
        btnupdateemail=(Button)findViewById(R.id.updateemail);



        btnupdateemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                etoldemail=(EditText)findViewById(R.id.etemail1);
                etnewemail=(EditText)findViewById(R.id.etemail2);

                String oldemail=  etoldemail.getText().toString();
                String newemail =etnewemail.getText().toString();

                SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                String userName = logindetails.getString("username", null);

                String dbpass=db.searchemail(userName);

                if (oldemail.equals(dbpass)) {
                    if(db.updateemail(newemail,userName))
                    {
                        Toast temp = Toast.makeText(ManageEmail.this, "Email Successfully Changed", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                    else
                    {
                        Toast temp = Toast.makeText(ManageEmail.this, "Email Not set ", Toast.LENGTH_SHORT);
                        temp.show();
                    }


                } else {

                    Toast temp = Toast.makeText(ManageEmail.this, "Incorrect Email.Plase Check  Again", Toast.LENGTH_SHORT);
                    temp.show();

                }


            }
        });
    }
}
