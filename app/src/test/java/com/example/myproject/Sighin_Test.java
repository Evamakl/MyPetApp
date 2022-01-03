package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class Sighin_Test {

    @Test
    public void isSignin_test(FirebaseUser user) {
        assertFalse(user == null); //If the user that we enter == null, return False
        assertTrue(user != null); //If the user != null (so he is in the DataBase), return True
    }
}
