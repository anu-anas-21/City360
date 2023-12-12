package com.example.city360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    TextView txt;

    ImageView locationDet,hospitals,schools,hotels,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        locationDet=findViewById(R.id.locations);
        hospitals=findViewById(R.id.hospitals);
        schools=findViewById(R.id.schools);
        hotels=findViewById(R.id.hotels);
        logout=findViewById(R.id.signout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Home.this, login.class);
                startActivity(intent);
                finish();
            }
        });
        locationDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, locations.class);
                startActivity(intent);
            }
        });

        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,hospitals.class);
                startActivity(intent);
            }
        });

        schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,schools.class);
                startActivity(intent);
            }
        });

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,hotels.class);
                startActivity(intent);
            }
        });
    }
}