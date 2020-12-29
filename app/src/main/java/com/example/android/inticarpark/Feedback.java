package com.example.android.inticarpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {
    Firebase Ref;
EditText email, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        email =findViewById(R.id.tvfemail);
        message=findViewById(R.id.tvfmessage);



    }
    public void back(View view) {
        startActivity(new Intent(getApplicationContext(),HelpCenter.class));
    }

    public void sendfeedback(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/html");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { "inti@newinti.edu.my" });
        i.putExtra(Intent.EXTRA_SUBJECT, "Feedback From INTI Car Park App");
        i.putExtra(Intent.EXTRA_TEXT, "Name:" + email.getText().toString() + "\n" +message.getText().toString() );
        try{
            startActivity(Intent.createChooser(i, "Please select Email"));
        }
        catch(android.content.ActivityNotFoundException ex){
            Toast.makeText(this,"No email client found.", Toast.LENGTH_SHORT).show();
        }


    }
    public void setting(View view) {
        startActivity(new Intent(getApplicationContext(),Settings.class));
    }
}
