package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AddDog_Test {
    @Test
    public void addDog_test(Dog dog) {
        assertFalse(dog == null); //If the dog == null, return False
        assertFalse(dog.getName().length() == 0 ); //If the dog name is null, return False
        assertTrue(dog.getName().length() > 0 ); //If the dog name length bigger than 0, return True
        assertTrue(dog != null); //If the dog != null, return True
    }
}
