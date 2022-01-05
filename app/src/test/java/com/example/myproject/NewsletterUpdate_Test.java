package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NewsletterUpdate_Test {
    @Test
    public void isValidNewsletter_test() {
        String update = "test";
        assertFalse(update.length() == 0); //If the update is empty, return False
        assertTrue(update.length() > 0); //If the update bigger than 0 (so it is not empty), return True
    }
    @Test
    public void isManager_test() {
        User user = new User("55","test@gmail.com","123","admin");
        user.setType("Manager");
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
}
