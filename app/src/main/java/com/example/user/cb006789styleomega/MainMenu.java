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

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private List<Items> items;
    private ListView list1;
    private EditText search;
    private Button searchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        search = (EditText) findViewById(R.id.tsearch);

        searchbtn = (Button) findViewById(R.id.btnsearch);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper databaseHelper = new DataBaseHelper(view.getContext());

                try {
                    String su = search.getText().toString();
                    items = databaseHelper.SearchItemByItemName(su);
                    if (items.size() == 0) {
                        list1 = (ListView) findViewById(R.id.listViewList);
                        Toast.makeText(MainMenu.this, "no results! :)!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        list1 = (ListView) findViewById(R.id.listViewList);
                        list1.setAdapter(new MainMenuAdapter(view.getContext(), items));
                        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                                Items it = items.get(i);
                                Intent intent = new Intent(MainMenu.this, ItemPage.class);
                                intent.putExtra("it", it);
                                startActivity(intent);
                            }
                        });
                    }
                }catch (Exception ex)
                {
                    Intent intent = new Intent(MainMenu.this, MainMenu.class);
                    startActivity(intent);
                }
            }


        });

        try {

            items = new ArrayList<Items>();
            DataBaseHelper db = new DataBaseHelper(getBaseContext());

            items = db.AllItemsRetrieve(getIntent().getStringExtra("category"));
            if (items.size() == 0) {
                this.list1 = (ListView) findViewById(R.id.listViewList);
                Toast.makeText(MainMenu.this, "No Products to be Displayed!",
                        Toast.LENGTH_SHORT).show();
            } else {
                this.list1 = (ListView) findViewById(R.id.listViewList);
                this.list1.setAdapter(new MainMenuAdapter(this, items));
                this.list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                        Items it = items.get(i);
                        Intent intent = new Intent(MainMenu.this, ItemPage.class);
                        intent.putExtra("it", it);
                        startActivity(intent);
                    }
                });
            }
        }catch (Exception ex)
        {
            Toast.makeText(MainMenu.this, "No products present",
                    Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addtocart) {

            Intent intent1 = new Intent(MainMenu.this,AddToCartActivity.class);
            startActivity(intent1);
            // Handle the camera action
        } else if (id == R.id.home) {
            Intent intent2 = new Intent(MainMenu.this,Catergorys.class);
            startActivity(intent2);

        } else if (id == R.id.manageuser) {
            Intent intent2= new Intent(MainMenu.this,ManageUsers.class);
            startActivity(intent2);

        } else if (id == R.id.logout) {
            Intent intent3 = new Intent(MainMenu.this,LoginActivity.class);
            startActivity(intent3);

        } else if (id == R.id.checkout) {
            Intent intent2 = new Intent(MainMenu.this, GetCardDetails.class);
            startActivity(intent2);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
