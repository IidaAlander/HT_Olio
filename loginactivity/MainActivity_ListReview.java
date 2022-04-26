package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity_ListReview extends AppCompatActivity {

    Context context = null;
    ListView listReview;
    Button btnReturn;

    float starsMax = 0.0F;

    ArrayList<String> reviewedMovies = new ArrayList<String>();

    // Get the current user
    String currentUser = com.example.loginactivity.MainActivity.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_review);

        context = MainActivity_ListReview.this;
        listReview = findViewById(R.id.listReviews);
        btnReturn = findViewById(R.id.buttonReturn);

        // Retrieve the list of reviewed movies and set it into listView
        reviewedMovies = com.example.loginactivity.MainActivity_Review.sortReviewList();
        Collections.reverse(reviewedMovies);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, reviewedMovies);
        listReview.setAdapter(adapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivityLoggedIn();
            }
        });

    }
    public void openMainActivityLoggedIn() {
        Intent intent = new Intent(this, MainActivity_LoggedIn.class);
        startActivity(intent);
    }

}




        /* Open the written file to be read and displayed in listView
        try {
            String s = (currentUser +".csv");
            InputStream in = context.openFileInput(s);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            // Separate different parts of line to put reviews into order
            while ((line = br.readLine()) != null) {
                String [] separated = line.split("/");
                // System.out.println(separated[1]);
                // float starsReview = Float.parseFloat(separated[1]);
                float starsReview = Float.valueOf(separated[1]);
                reviewedMovies.add(line);
            }
            in.close();

            // Set movies into arrayadapter to be displayed in a listview
            //Collections.reverse(reviewedMovies);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, reviewedMovies);
            listReview.setAdapter(adapter);
        } catch (IOException e) {
            Log.e("IOException", "Error in input");
        } finally {
            System.out.println("Loaded");
        }*/