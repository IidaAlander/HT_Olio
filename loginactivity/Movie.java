package com.example.loginactivity;

import java.util.ArrayList;

public class Movie {

    private String title;
    private String genre;
    private String year;

    public Movie(String _title, String _genre, String _year) {
        title = _title;
        genre = _genre;
        year = _year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<String> getReviews() {
        ArrayList<String> gr = new ArrayList<>();
        return gr;
    }
}




