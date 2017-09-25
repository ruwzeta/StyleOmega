package com.example.user.cb006789styleomega;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Catergorys extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergorys);


        final ImageView redirectToWomen = (ImageView) findViewById(R.id.imwomen);

        final ImageView redirectToMen  =  (ImageView) findViewById(R.id.immen);

        final  ImageView redirectToKids =  (ImageView)findViewById(R.id.imkids);

        final  ImageView redirectToAcces =  (ImageView)findViewById(R.id.imacces);






        redirectToWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("category", "Women");
                startActivity(intent);
            }
        });
        redirectToMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("category", "Men");
                startActivity(intent);
            }
        });
        redirectToKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("category", "Kids");
                startActivity(intent);
            }
        });

        redirectToAcces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("category", "Accessories");
                startActivity(intent);
            }
        });



    }
}
