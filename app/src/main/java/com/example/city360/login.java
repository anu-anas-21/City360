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
    TextInputEditText lginmail,lginpass;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        login=findViewById(R.id.btnlogin);
        suptxt=findViewById(R.id.uptxt);
        lginmail=findViewById(R.id.maillogin);
        lginpass=findViewById(R.id.passwordlogin);
        progressBar=findViewById(R.id.progressbar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logmail=String.valueOf(lginmail.getText());
                String logpass=String.valueOf(lginpass.getText());

                if(TextUtils.isEmpty(logmail)){
                    lginmail.setError("Please Enter Email");
                }
                else if(TextUtils.isEmpty(logpass)){
                    lginpass.setError("Please Enter Password");
                }
                else {
                    mAuth.signInWithEmailAndPassword(logmail,logpass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(login.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(), Home.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(login.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        suptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sup=new Intent(login.this, Register.class);
                startActivity(sup);
                finish();
            }
        });
    }
}