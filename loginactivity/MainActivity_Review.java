package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity_Review extends AppCompatActivity {

    // Intialization of variables

    RatingBar ratingBar;
    Button buttonSubmit;
    Button btDatePicker;
    TextView textViewRatingDisplay;
    Spinner spinnerMovie;
    int index;
    float _stars;
    String _name;
    String _comment;
    String date;
    DatePickerDialog datePickerDialog;
    EditText comment;

    // Implementation of Singleton

    MovieTheater mt = MovieTheater.getInstance();
    com.example.loginactivity.DatePicker dp = com.example.loginactivity.DatePicker.getInstance();

    // Retrieve list of movies
    ArrayList<String> arrayList = mt.returnMovieList();

    // Create list of reviews
    public static ArrayList<Review> reviews_list = new ArrayList<Review>();

    // Get current user
    String currentUser = com.example.loginactivity.MainActivity.getCurrentUser();


    public MainActivity_Review() throws IOException, SAXException {
    }

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_review);
        context = MainActivity_Review.this;

        ratingBar = findViewById(R.id.ratingBar);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewRatingDisplay = findViewById(R.id.textViewRatingDisplay);
        spinnerMovie = findViewById(R.id.spinnerMovie);
        comment = findViewById(R.id.editTextComment);
        btDatePicker = findViewById(R.id.btDatePicker);
        btDatePicker.setText(dp.getTodaysDate());
        initDatePicker();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Set the list of movies to be shown in dropdown spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMovie.setAdapter(arrayAdapter);

        // Spinner, choosing the movie to review
        spinnerMovie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                // Save values of chosen spinner items to be used later
                index = position;
                String getValue = String.valueOf(adapterView.getItemAtPosition(position));
                _name = getValue;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Set rating bar to function and display change, save int of starts
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                textViewRatingDisplay.setText(String.valueOf(ratingBar.getRating()));
                // Save the amount of starts to be used later
                _stars = ratingBar.getRating();
            }
        });


        // Button to submit a review
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Usage of editText input, getting comment
                comment.setInputType(InputType.TYPE_CLASS_TEXT);
                if (comment.getText().toString().isEmpty() == true) {
                    _comment = "No comments left";
                } else {
                    _comment = comment.getText().toString().trim();
                }
                // Save reviews into a file in a separate method
                if(date == null){
                    date = "No date left";
                }
                saveReview(_name, _stars, _comment, date);
                reviews_list.add(new Review(_name ,_comment, date, _stars));
                // Return back to the "main screen"
                openMainActivityLoggedIn();
            }
        });
    }

    public void openDatePicker (View v) {
        datePickerDialog.show();
    }

    // Intialize datePicker and set correct values for dates
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                date = dp.makeDateString(day, month, year);
                btDatePicker.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);

    }
    private void saveReview(String _name, float _stars, String _comment, String date) {
        // Save the parameters of the review to be read later
        try {
            String s = (currentUser +".csv");
            FileOutputStream fos = openFileOutput(s, MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            String review = (_name + " / " + _stars + " / " + _comment + " / " + date + "\n");
            osw.append(review);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "Error in input");
        } finally {
            System.out.println("Saved");
        }
    }

    public static ArrayList<String> sortReviewList(){
        Collections.sort(reviews_list, new Comparator<Review>() {
            @Override
            public int compare(Review r1, Review r2) {
                return r1.getStars().compareTo(r2.getStars());
            }
        });
        ArrayList<String> rl = new ArrayList<>();
        for(int i = 0; i < reviews_list.size(); i++){
            Review r = reviews_list.get(i);
            String review = (r.getTitle() + " / " + r.getStars() + " / " + r.getComment() + " / " + r.getDate());
            rl.add(review);
        }
        return rl;
    }

    public void openMainActivityLoggedIn() {
        Intent intent = new Intent(this, MainActivity_LoggedIn.class);
        startActivity(intent);
    }
}