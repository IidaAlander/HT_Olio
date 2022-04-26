package com.example.loginactivity;

import java.util.HashMap;

public class User {

    private String password;
    private String name;

    public User(String _password, String _name){
        password = _password;
        name = _name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

