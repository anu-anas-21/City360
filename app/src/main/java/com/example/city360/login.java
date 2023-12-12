package com.example.city360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class login extends AppCompatActivity {

    Button login;
    TextView suptxt;

    TextInputEditText etmail,etpass;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.btnlogin);
        suptxt=findViewById(R.id.uptxt);
        mAuth = FirebaseAuth.getInstance();
        etmail=findViewById(R.id.maillogin);
        etpass=findViewById(R.id.passwordlogin);

        suptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sup=new Intent(login.this, Register.class);
                startActivity(sup);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=String.valueOf(etmail.toString());
                String pass=String.valueOf(etpass.toString());
                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent sup=new Intent(login.this, Home.class);
                            startActivity(sup);
                            finish();
                        } else {
                            Toast.makeText(login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}