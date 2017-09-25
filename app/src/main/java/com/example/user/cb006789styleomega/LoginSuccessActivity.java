package com.example.user.cb006789styleomega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        String username = getIntent().getStringExtra("username");
        final TextView homepageLink = (TextView) findViewById(R.id.tvHomePageHere);
        final TextView aboutusLink  = (TextView) findViewById(R.id.tvabout);
        TextView tv = (TextView) findViewById(R.id.tvusername);
        tv.setText(username);
        aboutusLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepageIntent = new Intent(LoginSuccessActivity.this, About.class);
                LoginSuccessActivity.this.startActivity(homepageIntent);
            }
        });

        homepageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepageIntent = new Intent(LoginSuccessActivity.this, Catergorys.class);
                LoginSuccessActivity.this.startActivity(homepageIntent);
            }
        });

    }

}
