package com.example.android.inticarpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReserveParking extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Button next;
    String lot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_parking);
        next = findViewById(R.id.btnrnext);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveParking.this, TimeSlot.class);
                intent.putExtra("lot", lot);
                startActivity(intent);
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
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
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
                startActivity(new Intent(getApplicationContext(),HelpCenter.class));
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


    public void onUndergroundClicked(View view) {
         lot = "Underground";

    }

    public void onSouthClicked(View view) {
        lot ="South Gate";

    }
    public void onCourtClicked(View view) {
        lot ="BasketBall Court";

    }
    public void setting(View view) {
        startActivity(new Intent(getApplicationContext(),Settings.class));
    }
}
