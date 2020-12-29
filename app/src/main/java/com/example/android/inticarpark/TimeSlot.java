package com.example.android.inticarpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TimeSlot extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Button next,back;
    String ts1,ts2,ts3,ts4,ts5,ts6,ts7,ts8,ts9,ts10,ts11,ts12;
    String[] timeslot;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slot);
        next = findViewById(R.id.btntnext);
        back = findViewById(R.id.btntback);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        final String lot = bundle.getString("lot");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeSlot.this, ConfirmDetails.class);
                intent.putExtra("lot", lot);
                intent.putExtra("ts1",ts1);
//                intent.putExtra("ts2",ts2);
//                intent.putExtra("ts3",ts3);
//                intent.putExtra("ts4",ts4);
//                intent.putExtra("ts5",ts5);
//                intent.putExtra("ts6",ts6);
//                intent.putExtra("ts7",ts7);
//                intent.putExtra("ts8",ts8);
//                intent.putExtra("ts9",ts9);
//                intent.putExtra("ts10",ts10);
//                intent.putExtra("ts11",ts11);
//                intent.putExtra("ts12",ts12);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ReserveParking.class));
            }
        });


        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_car:
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(),Settings.class));

                break;
            case R.id.nav_reserveparking:
                startActivity(new Intent(getApplicationContext(),ReserveParking.class));
                break;
            case R.id.nav_helpcenter:
                startActivity(new Intent(getApplicationContext(),HelpCenter.class));
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logged Out.", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
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



    public void time1(View view) {
        ts1 = "7AM - 8AM";

    }
    public void time2(View view) {
        ts1 = "8AM - 9AM";

    }
    public void time3(View view) {
        ts1 = "9AM - 10AM";

    }
    public void time4(View view) {
        ts1 = "10AM - 11AM";

    }
    public void time5(View view) {
        ts1 = "11AM - 12PM";

    }
    public void time6(View view) {
        ts1 = "12PM - 1PM";

    }
    public void time7(View view) {
        ts1 = "1PM - 2PM";

    }
    public void time8(View view) {
        ts1 = "2PM - 3PM";

    }
    public void time9(View view) {
        ts1 = "3PM - 4PM";

    }
    public void time10(View view) {
        ts1 = "4PM - 5PM";

    }
    public void time11(View view) {
        ts1 = "5PM - 6PM";

    }
    public void time12(View view) {
        ts1 = "6PM - 7PM";

    }
    public void setting(View view) {
        startActivity(new Intent(getApplicationContext(),Settings.class));
    }
}
