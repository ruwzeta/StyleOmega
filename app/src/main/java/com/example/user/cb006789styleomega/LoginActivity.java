package com.example.user.cb006789styleomega;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        final EditText username = (EditText) findViewById(R.id.TFusername);
        final EditText password = (EditText) findViewById(R.id.TFpassword);
        final Button bLogin = (Button) findViewById(R.id.Blogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        final Button loginLink = (Button) findViewById(R.id.Blogin);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText a = (EditText) findViewById(R.id.TFusername);
                String str = a.getText().toString();

                EditText b = (EditText) findViewById(R.id.TFpassword);
                String pass = b.getText().toString();

                String password = helper.searchPass(str);
                if (pass.equals(password)) {

                    Intent loginIntent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    loginIntent.putExtra("username", str);
                    SharedPreferences logindetails = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = logindetails.edit();
                    editor.putString("username", str);
                    editor.putString("password", password);
                    editor.commit();
                    LoginActivity.this.startActivity(loginIntent);
                } else if (str.equals("admin") && pass.equals("admin")) {
                    Intent loginIntent = new Intent(LoginActivity.this, AdminHome.class);
                    startActivity(loginIntent);
                } else {

                    Toast temp = Toast.makeText(LoginActivity.this, "Username and Password don't match!", Toast.LENGTH_SHORT);
                    temp.show();

                }


            }
        });
    }
}
