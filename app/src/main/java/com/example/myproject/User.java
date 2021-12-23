package com.example.myproject;

import java.io.Serializable;

public class User implements Serializable {
    private String Email;
    private String Phone;
    private String Username;
    private String Type;
    private Dog[] dogs;
    public User(String email, String phone, String username) {
        Email = email;
        Phone = phone;
        Username = username;
    }
    public void setEmail(String email) { Email = email; }
    public void setPhone(String phone) { Phone = phone; }
    public void setUsername(String username) { Username = username; }
    public String getEmail() { return Email; }
    public String getPhone() { return Phone; }
    public String getUsername() { return Username; }
}
