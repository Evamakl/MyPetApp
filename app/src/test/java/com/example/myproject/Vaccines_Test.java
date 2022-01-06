package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Vaccines_Test {
    @Test
    public void isValidVaccine_test() {
        String vaccine = "test";
        assertFalse(vaccine.length() == 0); //If the vaccine is empty, return False
        assertTrue(vaccine.length() > 0); //If the vaccine bigger than 0 (so it is not empty), return True
    }@Test
    public void isValidDate_test() {
        String date = "test";
        assertFalse(date.length() == 0); //If the date is empty, return False
        assertTrue(date.length() > 0); //If the date bigger than 0 (so it is not empty), return True
    }
    //Check sigh in of the owner
    @Test
    public void isOwner_test() {
        User user = new User("55","test@gmail.com","123","admin");
        user.setType("Owner");
        assertFalse(user.getType().equals("Manager"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Owner"));
    }
}
