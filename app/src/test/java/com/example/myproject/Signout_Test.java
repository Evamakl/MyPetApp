package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class Signout_Test {
    @Test
    public void isSignout_test() {
        User user = new User();
        assertTrue(user != null); //If the user != null, return False
        assertFalse(user == null); //If the user == null, return True
    }
}
