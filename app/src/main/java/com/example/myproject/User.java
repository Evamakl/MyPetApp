package com.example.myproject;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String Uid;
    private String Email;
    private String Phone;
    private String Username;
    private String Type = "Owner";
    private ArrayList<Dog> dogs;
    public User(String uid, String email, String phone, String username) {
        Uid = uid;
        Email = email;
        Phone = phone;
        Username = username;
        this.dogs = new ArrayList<>();
    }

    public User() { this.dogs = new ArrayList<>(); }

    public void setEmail(String email) { Email = email; }
    public void setPhone(String phone) { Phone = phone; }
    public void setUsername(String username) { Username = username; }
    public String getEmail() { return Email; }
    public String getPhone() { return Phone; }
    public String getUsername() { return Username; }
    public String getType() { return Type; }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs; }
        public void AddDog(Dog dog){
            dogs.add(dog);
            dogs.get(dogs.size()-1).setId(String.valueOf(dogs.size()-1));
        }
}
