package com.example.myproject;

import java.io.Serializable;

public class Dog implements Serializable {
    private String id = "0";
    private String Type = "null";
    private String Name = "null";
    private String Gender = "null";
    private String Image = "null";
    private String City = "null";
    private String TimeOut = "null";
    private String BirthDay = null;
    public Dog(String type, String name, String gender, String image, String city, String birthDay,String TimeOut) {
        Type = type;
        Name = name;
        Gender = gender;
        Image = image;
        City = city;
        BirthDay = birthDay;
        this.TimeOut =TimeOut;
    }
    public void AddDog(Dog dog, String size){
        id = size;
        Type = dog.Type;
        Name = dog.Name;
        // להשלים
//        private String Gender = "null";
//        private String Image = "null";
//        private String City = "null";
//        private String BirthDay = null;
    }
    public void setId(String id){ this.id = id; }
    public String getType() {
        return Type;
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
