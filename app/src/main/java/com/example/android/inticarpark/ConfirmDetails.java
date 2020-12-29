package com.example.android.inticarpark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        private DrawerLayout drawer;


    TextView name,phone,intiid,plot,timeslot,date;

    EditText vehicleno;
    Button confirm,back;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_details);

        name =findViewById(R.id.tvdetailname);
        intiid=findViewById(R.id.tvdetailid);
        phone=findViewById(R.id.tvdetailphone);
        vehicleno=findViewById(R.id.eddetailvehicleno);
        plot=findViewById(R.id.tvdetailplot);
        confirm=findViewById(R.id.btnconfirm);
        back=findViewById(R.id.btndetailback);
        timeslot=findViewById(R.id.tvdetailts);


        fAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        final String lot = bundle.getString("lot");
        final String ts1 = bundle.getString("ts1");

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference docref1 = fStore.collection("users").document(userID);
        docref1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        name.setText(value.getString("fname"));
                        intiid.setText(value.getString("intiid"));
                        phone.setText(value.getString("phone"));
                        vehicleno.setText(value.getString("vehicleno"));

            }
        });

        plot.setText(lot);
        timeslot.setText(ts1);

        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), TimeSlot.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String Vehicleno = vehicleno.getText().toString().trim();
                String ID = intiid.getText().toString().trim();
                String Phoneno = phone.getText().toString().trim();
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documenentReference = fStore.collection("reservations").document(userID);
                Map<String, Object> preserve = new HashMap<>();
                preserve.put("name", Name);
                preserve.put("phone", Phoneno);
                preserve.put("intiid", ID);
                preserve.put("vehicleno", Vehicleno);
                preserve.put("lot", lot);
                preserve.put("timeslot", ts1);
                preserve.put("date", date);
                documenentReference.set(preserve);
                Toast.makeText(ConfirmDetails.this, "Parking Reserved Succesfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Dashboard.class));

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


    public void setting(View view) {
        startActivity(new Intent(getApplicationContext(),Settings.class));
    }
}
