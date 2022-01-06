package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RemoveDog_Test {
    @Test
    public void RemoveDog_DogOwner_test() {
        Dog dog = new Dog("Pincher","Simba","Male","null","Beer sheva","1/1/1999");
        assertFalse(dog == null); //If the dog == null, return False
        assertFalse(dog.getName().length() == 0 ); //If the dog name is null, return False
        assertTrue(dog.getName().length() > 0 ); //If the dog name length bigger than 0, return True
        assertTrue(dog != null); //If the dog != null, return True
        //check dog's name
        assertFalse(dog.getName().length() == 0);
        assertTrue(dog.getName().length()  > 0);
        assertFalse(dog.getName().contains("-") || dog.getName().contains("."));
        //assertTrue(dog.getName().contains("-") || dog.getName().contains("."));
        //check dog's Type
        assertFalse(dog.getType().length() == 0);
        assertTrue(dog.getType().length()  > 0);
        assertFalse(dog.getType().contains("-") || dog.getType().contains("."));
        //assertTrue(dog.getType().contains("-") || dog.getType().contains("."));
        //check dog's Gender
        assertFalse(dog.getGender().length() == 0);
        assertTrue(dog.getGender().length()  > 0);
        assertFalse(dog.getGender().contains("-") || dog.getGender().contains("."));
        //assertTrue(dog.getGender().contains("-") || dog.getGender().contains("."));
        //check dog's City
        assertFalse(dog.getCity().length() == 0);
        assertTrue(dog.getCity().length()  > 0);
        assertFalse(dog.getCity().contains("-") || dog.getCity().contains("."));
        //assertTrue(dog.getCity().contains("-") || dog.getCity().contains("."));
        //check dog's BirthDay
        assertFalse(dog.getBirthDay().length() == 0);
        assertTrue(dog.getBirthDay().length()  > 0);
        assertFalse(dog.getBirthDay().contains("-") || dog.getBirthDay().contains("."));
        //assertTrue(dog.getBirthDay().contains("-") || dog.getBirthDay().contains("."));
    }
}
