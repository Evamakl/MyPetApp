package com.example.myproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class User implements Serializable {
    private String Uid;
    private String Email;
    private String Phone;
    private String Username;
    private String Type;
    private ArrayList<DogPK> dogsPK;
    private ArrayList<Dog> dogs;
    private Boolean Block = false;
    private ArrayList<TodoListClass> listOfTodo;
    private ArrayList<tipsPKClass> listPKtip;
    private Boolean rate = false;


    public User(String uid, String email, String phone, String username) {
        Uid = uid;
        Email = email;
        Phone = phone;
        Username = username;

        dogs = new ArrayList<>();
        listOfTodo = new ArrayList<>();
        listPKtip = new ArrayList<>();
        dogsPK = new ArrayList<>();
    }

    public ArrayList<TodoListClass> getListOfTodo() {
        return listOfTodo;
    }

    public ArrayList<tipsPKClass> getListPKtip() {
        return listPKtip;
    }

    public void setListOfTodo(ArrayList<TodoListClass> listOfTodo) {
        this.listOfTodo = listOfTodo;
    }

    public void setListPKtip(ArrayList<tipsPKClass> listPKtip) {
        this.listPKtip = listPKtip;
       /* this.dogs = new ArrayList<>();
        this.dogsPK = new ArrayList<>();*/
    }
    public User() {
        this.dogs = new ArrayList<>();
        this.dogsPK = new ArrayList<>();
        this.listOfTodo = new ArrayList<>();
        this.listPKtip = new ArrayList<>();
    }
    public ArrayList<DogPK> getDogsPK() {
        return dogsPK;
    }

    public void setDogsPK(ArrayList<DogPK> dogsPK) {
        this.dogsPK = dogsPK;

    }

    public Boolean getRate() {
        return rate;
    }

    public void setRate(Boolean rate) {
        this.rate = rate;
    }

    public void setEmail(String email) { Email = email; }
    public void setPhone(String phone) { Phone = phone; }
    public void setUsername(String username) { Username = username; }
    public String getEmail() { return Email; }
    public String getPhone() { return Phone; }
    public String getUsername() { return Username; }
    public String getType() { return Type; }

    public Boolean getBlock() {
        return Block;
    }

    public void setBlock(Boolean block) {
        Block = block;
    }

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
