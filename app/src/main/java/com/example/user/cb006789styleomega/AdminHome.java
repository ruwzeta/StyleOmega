package com.example.user.cb006789styleomega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHome extends AppCompatActivity {

    Button CREATEButton,SIGNout,Viewinq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        this.Viewinq=(Button)findViewById(R.id.btnviewinq) ;
        this.Viewinq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this,ViewInquiry.class);
                startActivity(intent);
            }
        });
        this.CREATEButton = (Button) findViewById(R.id.btnaddprod);
        this.CREATEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this,AddProductsActivity.class);
                startActivity(intent);
            }
        });

        this.SIGNout = (Button) findViewById(R.id.adminSignOut);
        this.SIGNout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
