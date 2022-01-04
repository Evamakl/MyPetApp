package com.example.myproject;

import java.io.Serializable;
import java.util.Date;

public class vaccine implements Serializable {
    public String getName() { return name; }
    public String getDate() { return date; }
    private String name = "";
    private String date = "";
    public vaccine(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public vaccine() {
    }
}