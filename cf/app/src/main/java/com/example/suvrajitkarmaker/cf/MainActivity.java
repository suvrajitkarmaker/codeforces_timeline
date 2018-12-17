package com.example.suvrajitkarmaker.cf;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView edt;
    ImageView img;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // edt=findViewById(R.id.fullname);
       // edt.setText("Suvra");
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.fullname);
        TextView university = (TextView) headerView.findViewById(R.id.univer);
        new DownloadImageFromInternet((ImageView) headerView.findViewById(R.id.userimage))
                .execute("https:"+fetchData.titlePhoto);


        navUsername.setText(fetchData.fullname);
        university.setText(fetchData.organization);


        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFrament()).commit();


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        if (item.getItemId()==R.id.nav_profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFrament()).commit();
        }
        if (item.getItemId()==R.id.nav_contest){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContestFrament()).commit();
        }




        drawer.closeDrawer(GravityCompat.START);


        return true;

    }


}
