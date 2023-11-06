package com.example.city360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {

    database myDb;
    Button bt;
    TextView txt;
    TextInputLayout logMail,logPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bt=findViewById(R.id.btnlogin);
        txt=findViewById(R.id.uptxt);
        logMail=findViewById(R.id.maillogin);
        logPass=findViewById(R.id.passwordlogin);

        myDb=new database(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=logMail.getEditText().getText().toString().trim();
                String password=logPass.getEditText().getText().toString().trim();
                if (username.equals(""))
                {
                    logMail.setError("Filed Cannot be Empty");
                }
                else if (password.equals(""))
                {
                    logPass.setError("Filed Cannot be Empty");
                }
                else
                {
                    Boolean result=myDb.checkMailPassword(username,password);
                    if (result==true)
                    {
                        Intent intent=new Intent(login.this,Home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sup=new Intent(login.this, Register.class);
                startActivity(sup);
            }
        });
    }
}