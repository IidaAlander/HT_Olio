package com.example.loginactivity;

public class Review {

    private String comment;
    private String title;
    private String date;
    private Float stars;

    public Review(String _title, String _comment, String _date, float _stars) {
        title = _title;
        comment = _comment;
        date = _date;
        stars = _stars;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public Float getStars() { return stars; }

}
