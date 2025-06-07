package com.styleomega.app; // Changed package

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity; // Changed to AndroidX
import android.os.Bundle;
import androidx.appcompat.widget.SearchView; // Changed to AndroidX
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

// Assuming MainMenu will also be moved to com.styleomega.app package
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
