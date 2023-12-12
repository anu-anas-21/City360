 package com.example.city360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Type;

 public class Register extends AppCompatActivity {
     TextInputEditText etmail,etpassword;
    Button bt;
    TextView lgin;

     private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etmail=findViewById(R.id.mail);
        etpassword=findViewById(R.id.password);
        lgin=findViewById(R.id.logintxt);
        bt=findViewById(R.id.btn);
        mAuth = FirebaseAuth.getInstance();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=String.valueOf(etmail.toString());
                String password=String.valueOf(etpassword.toString());
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(Register.this,login.class);
                            startActivity(intent);
                            finish();;
                        } else {
                            Toast.makeText(Register.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}