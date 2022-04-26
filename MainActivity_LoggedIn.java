package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity_LoggedIn extends AppCompatActivity {

    Button myMoviesBtn;
    Button myTheatersBtn;
    Button leaveReviewBtn;
    Button listReviewBtn;
    Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logged_in);

        myMoviesBtn = findViewById(R.id.myMoviesBtn);
        myTheatersBtn = findViewById(R.id.myTheatersBtn);
        leaveReviewBtn = findViewById(R.id.reviewBtn);
        listReviewBtn = findViewById(R.id.listReviewBtn);
        logOutBtn = findViewById(R.id.logOutBtn);

        // No functionality yet
        myMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // No functionality yet
        myTheatersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        leaveReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivityReview();
            }
        });

        listReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openMainActivityListReview(); }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivityLogIn();
            }
        });
    }

    public void openMainActivityReview() {
        Intent intent = new Intent(this, MainActivity_Review.class);
        startActivity(intent);
    }

    public void openMainActivityListReview() {
        Intent intent = new Intent(this, MainActivity_ListReview.class);
        startActivity(intent);
    }

    public void openMainActivityLogIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}