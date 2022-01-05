package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SighinManager_Test {
    @Test
    public void isManager_test() {
        User user = new User("55","test@gmail.com","123","admin");
        user.setType("Manager");
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
}