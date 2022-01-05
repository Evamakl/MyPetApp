package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NewsLetter_Test {
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
