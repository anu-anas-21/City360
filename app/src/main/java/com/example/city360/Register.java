 package com.example.city360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    boolean validate=false;

     private FirebaseAuth mAuth;

     ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etmail=findViewById(R.id.mail);
        etpassword=findViewById(R.id.password);
        lgin=findViewById(R.id.logintxt);
        bt=findViewById(R.id.btn);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        Handler handler=new Handler();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate=isValidate();
                String email=etmail.getText().toString();
                String password=etpassword.getText().toString();
                if (validate) {
                    progressBar.setVisibility(View.VISIBLE);
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
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    },3000);
                }
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
    private boolean isValidate() {
        String mailPat="[a-zA-Z0-9._-]+@[a-z]+[.]+[a-z]+";
        String mail=etmail.getText().toString();
        if (etmail.length()==0) {
            etmail.setError("Required");
            return false;
        }
        if (etpassword.length()==0) {
            etpassword.setError("Required");
            return false;
        }
        if (!mail.matches(mailPat)) {
            etmail.setError("Invalid Email");
            return false;
        }
        if (etpassword.length()< 8 || etpassword.length() > 16) {
            etpassword.setError("Must be 8 to 16 Characters");
        }
        return true;
    }
}