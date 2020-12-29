package com.example.android.inticarpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.okhttp.HttpUrl;

public class HelpCenter extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
TextView q1,q2,q3;
Button Feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_help_center);
            Feedback = findViewById(R.id.btnfeedback);

            Feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Feedback.class));
                }
            });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_car:
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(), Settings.class));

                break;
            case R.id.nav_reserveparking:
                startActivity(new Intent(getApplicationContext(), ReserveParking.class));
                break;
            case R.id.nav_helpcenter:
                startActivity(new Intent(getApplicationContext(), HelpCenter.class));
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logged Out.", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    public void download(View view) {
        Intent browser = new Intent(Intent.ACTION_VIEW,Uri.parse("https://drive.google.com/file/d/1i0QQk5OYOeWHyDWkgp-WHei8aARnjuyO/view?usp=sharing"));
        startActivity(browser);
    }
    public void q1(View view) {
        startActivity(new Intent(getApplicationContext(),HC_Q1.class));
    }
    public void q2(View view) {
        startActivity(new Intent(getApplicationContext(),HC_Q2.class));
    }

    public void q3(View view) {
        startActivity(new Intent(getApplicationContext(),HC_Q3.class));
    }
    public void setting(View view) {
        startActivity(new Intent(getApplicationContext(),Settings.class));
    }
}
