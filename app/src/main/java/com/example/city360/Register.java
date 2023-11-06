 package com.example.city360;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Type;

 public class Register extends AppCompatActivity {
     database myDb;
     TextInputLayout mail,pass,ph,type;
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
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        ph=findViewById(R.id.phone);
        type=findViewById(R.id.userType);
        bt=findViewById(R.id.btn);
        myDb=new database(this);

        String regMail=mail.getEditText().getText().toString().trim();
        String regPhone=ph.getEditText().getText().toString().trim();
        String regPassword=pass.getEditText().getText().toString().trim();
        String regType= type.getEditText().getText().toString().trim();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean mailResult=myDb.checkMail(regMail);
                if (mailResult == false)
                {
                    Boolean regResult=myDb.insertData(regMail,regPassword,regPhone,regType);
                    if (regResult == true)
                    {
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Register.this, login.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Register.this, "User Already Exists.\n Please Sign in", Toast.LENGTH_SHORT).show();
                }
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
    private boolean isValidPass()
    {
        String regPassword=pass.getEditText().getText().toString().trim();
        if (regPassword.length() < 8|| regPassword.length() > 16)
        {
            pass.setError("Must be 8 to 16 Characters");
            return false;
        }
        else {
            pass.setError(null);
            return true;
        }
    }

}