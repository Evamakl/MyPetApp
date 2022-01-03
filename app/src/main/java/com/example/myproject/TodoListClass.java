package com.example.myproject;

import java.io.Serializable;

public class TodoListClass implements Serializable {
    String text ="";
    String flag ="";
    public TodoListClass(String text, String flag) {
        this.text = text;
        this.flag = flag;
    }
    public TodoListClass(){};

    public String getText() {
        return text;
    }

    public String getFlag() {
        return flag;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
