package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.FloatEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String _email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.singUpButton);

        ArrayList<String> userArrayList = com.example.loginactivity.MainActivitySingUp.returnUserList();

        // On login click check that user exists and that information given is correct
        // Without hashmap functions only for one admin user

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get given credentials
                _email = email.getText().toString();
                String _password = password.getText().toString();
                    if(_email.equals("Admin") && _password.equals("Admin")){
                        // correct
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        openMainActivityLoggedIn();
                    }
                else {
                    // incorrect
                    Toast.makeText(MainActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
                }}
        });

        // In case user doesn't have an account opens up a new view to create one
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivitySingUp();
            }
        });

    }

    // Methods for opening new activities on click
    public void openMainActivityLoggedIn() {
        Intent intent = new Intent(this, MainActivity_LoggedIn.class);
        startActivity(intent);
    }

    public void openMainActivitySingUp() {
        Intent intent = new Intent(this, MainActivitySingUp.class);
        startActivity(intent);
    }

    public static String getCurrentUser(){
        return _email;
    }

}