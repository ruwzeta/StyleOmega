package com.example.user.cb006789styleomega;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewInquiry extends AppCompatActivity {


    private List<Inquiry> inquiries ;
    private ListView listofInquiries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_inquiry);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //full screen view finished

        try {
            inquiries = new ArrayList<Inquiry>();
            DataBaseHelper db = new DataBaseHelper(getBaseContext());
            //database methods down fall comming on
            inquiries = db.InquiriesfindAll();
            if (inquiries.size() == 0) {
                //this.listofInquiries = (ListView) findViewById(R.id.inqlist);
                //Intent intent = new Intent(ContactClothStore.this, ContactClothStore.class);
                //startActivity(intent);
            } else {

               this.listofInquiries = (ListView) findViewById(R.id.inqlist);
                this.listofInquiries.setAdapter(new InquiryAdapter(this, inquiries));
            }
        }catch (Exception ex)
        {
            Toast.makeText(ViewInquiry.this,"Error :" +  ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }



    }
}