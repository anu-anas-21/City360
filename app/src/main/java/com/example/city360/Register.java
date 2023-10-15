 package com.example.city360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

 public class Register extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    Button bt;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        autoCompleteTextView=findViewById(R.id.menu);
        String[] usertype=getResources().getStringArray(R.array.type);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,usertype);
        autoCompleteTextView.setAdapter(adapter);
        txt=findViewById(R.id.logintxt);
        bt=findViewById(R.id.btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this, login.class);
                startActivity(intent);
            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this, login.class);
                startActivity(intent);
            }
        });
    }
}