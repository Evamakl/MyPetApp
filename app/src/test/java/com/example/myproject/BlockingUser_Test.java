package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class BlockingUser_Test {
    @Test
    public void isExist_test() {
        User user = new User();
        assertTrue(user != null); //If the user != null, return False
        assertFalse(user == null); //If the user == null, return True
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
