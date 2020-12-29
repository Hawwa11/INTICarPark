package com.example.android.inticarpark;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

//copy the implents and private
public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        private DrawerLayout drawer;
        TextView name,phone,id,vno,lot,timeslot,empty,date;
    ImageView menu,add;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button btndirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menu = findViewById(R.id.menu);
        name =findViewById(R.id.name);
        id=findViewById(R.id.ID);
        lot=findViewById(R.id.Plot);
        timeslot=findViewById(R.id.Timeslot);
        phone=findViewById(R.id.Phone);
        vno=findViewById(R.id.VNo);
        add=findViewById(R.id.imgadd);
        empty=findViewById(R.id.empty);
        date=findViewById(R.id.Date);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();


        if( name.getText().toString().isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        }
        DocumentReference documenentReference = fStore.collection("reservations").document(userID);
        documenentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("name"));
                id.setText(value.getString("intiid"));
                lot.setText(value.getString("lot"));
                phone.setText(value.getString("phone"));
                vno.setText(value.getString("vehicleno"));
                timeslot.setText(value.getString("timeslot"));
                date.setText(value.getString("date"));
             if( name.getText().toString().isEmpty() != true) {
            empty.setText(" ");
        }
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ReserveParking.class));
            }
        });

         btndirection = findViewById(R.id.btndirection);
        btndirection.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this, MapsActivity.class));

            }
        });
//copy this whole thing
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
