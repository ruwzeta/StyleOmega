package com.example.user.cb006789styleomega;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView about =  (TextView)findViewById(R.id.txtaboutus);

         about.setText("Style Omega Fashions offers unique apparel, and accessories to women who value versatility, style, and comfort. Style Omega Fashions prides itself on providing excellent customer service. There is always a friendly face to greet you at the door and help you find whatever you need. That's not even mentioning our great selection of fun and beautiful clothing, shoes, jewelry, handbags and much more!  Call or come by today and let us help you redesign or add to your existing wardrobe.  We have women's clothing and accessories to fit all budgets while maintaining quality.\n" +
                 "If you are  first-time visitor or long-standing customer, we hope you will be thrilled with every aspect of your Style Omega Fashions shopping experience.\n");
    }
}
