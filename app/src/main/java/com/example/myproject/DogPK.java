package com.example.myproject;

public class DogPK {

    String OwnerEmail;
    Dog dog;

    public DogPK(String ownerEmail, Dog dog) {
        OwnerEmail = ownerEmail;
        this.dog = dog;
    }

    public String getOwnerEmail() {
        return OwnerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        OwnerEmail = ownerEmail;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
