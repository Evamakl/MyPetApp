package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class Signout_Test {
    @Test
    public void isSignout_test(FirebaseUser user) {
        assertFalse(user != null); //If the user != null, return False
        assertTrue(user == null); //If the user == null, return True
    }
}
