package com.example.city360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    Button login;
    TextView suptxt;

    TextInputEditText etmail,etpass;

    boolean validate;

    private FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.btnlogin);
        suptxt=findViewById(R.id.uptxt);
        mAuth = FirebaseAuth.getInstance();
        etmail=findViewById(R.id.maillogin);
        etpass=findViewById(R.id.passwordlogin);
        progressBar=findViewById(R.id.progressbar);
        Handler handler=new Handler();

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
                String pass=etpass.getText().toString();
                String mail=etmail.getText().toString();
                validate=isValidate();
                if (validate) {
                    progressBar.setVisibility(View.VISIBLE);
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
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    },3000);
                }
            }
        });
    }
    private boolean isValidate() {
        if (etmail.length()==0) {
            etmail.setError("Required");
            return false;
        }
        if (etpass.length()==0) {
            etpass.setError("Required");
            return false;
        }
        return true;
    }
}