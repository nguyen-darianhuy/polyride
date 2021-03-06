package com.polyride.controller;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.support.design.widget.TextInputLayout;

import android.support.annotation.NonNull;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.polyride.R;

public class Create extends AppCompatActivity{  //NOSONAR
    private static final String TAG = "EmailPassword";



    private TextInputLayout email;
    private TextInputLayout password;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // already have an account button
        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLogin();
            }
        });

        //  signup button
        button = findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivity3();
            }
        });
    }

    public void openActivity3(){
        Log.d("CLICK", "Register Activity Page");

        // text fields
        email = findViewById(R.id.textInputLayout3);
        password = findViewById(R.id.textInputLayout4);

        // buttons
        Button signUpButton = findViewById(R.id.button5);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = email.getEditText().getText().toString().trim();
                String inputPass = password.getEditText().getText().toString().trim();
                createAccount(inputEmail, inputPass);
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    void createAccount(String email, String pass){
        if(email == null || password == null){
            Toast.makeText(getApplicationContext(), "Email or Password cannot be left empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(), "Account Made.",
                                    Toast.LENGTH_SHORT).show();
                            openMain2Activity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
        // [END create_user_with_email]
        });
    }

    void openMain2Activity(){
        Intent intent = new Intent(this, RidesActivity.class);
        startActivity(intent);
    }


    void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
