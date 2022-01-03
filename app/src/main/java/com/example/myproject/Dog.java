package com.example.myproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Dog implements Serializable {
    private String id = "0";
    private String Type = "null";
    private String Name = "null";
    private String Gender = "null";
    private String Image = "null";
    private String City = "null";
    private String BirthDay = "null";
    private String AddedDate = "null";
    private ArrayList<Vaccines> vaccines;
    public Dog(String type, String name, String gender, String image, String city, String birthDay) {
        Type = type;
        Name = name;
        Gender = gender;
        Image = image;
        City = city;
        BirthDay = birthDay;
        vaccines = new ArrayList<>();
    }
    public Dog(){ vaccines = new ArrayList<>(); };
    public String getId() {
        return id;
    }
    public void AddDog(Dog dog, String size){
        id = size;
        Type = dog.getType();
        Name = dog.getName();
        Gender = dog.getGender();
        Image = dog.getImage();
        City = dog.getCity();
        BirthDay = dog.getBirthDay();
    }
    public void setId(String id){ this.id = id; }
    public String getType() {
        return Type;
    }

    public String getAddedDate() {
        return AddedDate;
    }

    public void setAddedDate(String addedDate) {
        AddedDate = addedDate;
    }

    public ArrayList<Vaccines> getVaccines() {
        return vaccines;
    }

    public void setVaccines(ArrayList<Vaccines> vaccines) {
        this.vaccines = vaccines;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getImage() {
        return Image;
    }

    public String getCity() {
        return City;
    }

    public String getBirthDay() {
        return BirthDay;
    }
}
