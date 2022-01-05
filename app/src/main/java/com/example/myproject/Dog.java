package com.example.myproject;

import androidx.annotation.Nullable;

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
    private String Background = "";
    private String operation = "";
    private String allergy = "";
    private Boolean allergies = false;
    private Boolean castration = false;
    private Boolean sterilization = false;
    private ArrayList<vaccine> vaccines;
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
    public void setDog(Dog dog){
        this.Background = dog.getBackground();
        this.operation = dog.getOperation();
        this.allergy = dog.getAllergy();
        this.allergies = dog.getAllergies();
        this.castration = dog.getCastration();
        this.sterilization = dog.getSterilization();
        this.Type = dog.getType();
        this.Name=dog.getName();
        this.Gender=dog.getGender();
        this.Image=dog.getImage();
        this.City=dog.getCity();
        this.BirthDay=dog.getBirthDay();
        this.AddedDate=dog.getAddedDate();
    }
    public void setAddedDate(String addedDate) {
        AddedDate = addedDate;
    }

    public ArrayList<vaccine> getVaccines() {
        return vaccines;
    }

    public boolean equals( Dog dog) {
        return this.getName().equals(dog.getName()) && this.getId().equals(dog.getId()) && this.getGender().equals(dog.getGender());
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public Boolean getSterilization() {
        return sterilization;
    }

    public void setSterilization(Boolean sterilization) {
        this.sterilization = sterilization;
    }

    public String getBackground() {
        return Background;
    }

    public void setBackground(String background) {
        Background = background;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Boolean getAllergies() {
        return allergies;
    }

    public void setAllergies(Boolean allergies) {
        this.allergies = allergies;
    }

    public Boolean getCastration() {
        return castration;
    }

    public void setCastration(Boolean castration) {
        this.castration = castration;
    }

    public void AddVaccine(vaccine vaccine) {
        this.vaccines.add(vaccine);
    }
    public void setVaccines(ArrayList<vaccine> vaccines) {
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
