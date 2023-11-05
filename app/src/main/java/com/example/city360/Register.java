 package com.example.city360;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

 public class Register extends AppCompatActivity {
     TextInputLayout mail,pass,ph;
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
        bt=findViewById(R.id.btn);
        String regmail=mail.getEditText().getText().toString().trim();

        String regphone=ph.getEditText().getText().toString().trim();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this, login.class);
                startActivity(intent);
                //finish();
                
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
        String regpassword=pass.getEditText().getText().toString().trim();
        if (regpassword.length() < 8|| regpassword.length() > 16)
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