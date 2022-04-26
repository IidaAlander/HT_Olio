package com.example.loginactivity;


import java.util.ArrayList;

public class Theater {

    private String  ID;
    private String name;

    public Theater(String _ID, String _name){
        name = _name;
        ID = _ID;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

}

