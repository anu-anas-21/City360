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
     TextInputEditText etmail,etpassword,confirmpassword;
    Button bt;
    TextView lgin;
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
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        etmail=findViewById(R.id.mail);
        etpassword=findViewById(R.id.password);
        lgin=findViewById(R.id.logintxt);
        confirmpassword=findViewById(R.id.confirmpassword);
        progressBar=findViewById(R.id.progressbar);
        bt=findViewById(R.id.btn);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email=String.valueOf(etmail.getText());
                String password=String.valueOf(etpassword.getText());
                String confirmpass=String.valueOf(confirmpassword.getText());

                if(TextUtils.isEmpty(email)){
                    etmail.setError("Please Enter Email");
                }
                else if(TextUtils.isEmpty(password)){
                    etpassword.setError("Please Enter Password");
                }
                else if(!password.equals(confirmpass)){
                    etpassword.setError("Password and Confirm Password Does not Match");
                    confirmpassword.setError("Password and Confirm Password Does not Match");
                }
                else if (password.length() < 8|| password.length() > 16) {
                    etpassword.setError("Must be 8 to 16 Characters");
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Register.this, "Account Created Successfully.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Register.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
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
}