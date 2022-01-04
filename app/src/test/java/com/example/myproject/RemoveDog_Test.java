package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class RemoveDog_Test {
    @Test
    public void isRemoveDog_test(FirebaseUser dog) {
        assertFalse(dog != null); //If the dog that we enter == null, return False
        assertTrue(dog == null); //If the dog != null (so he is in the DataBase), return True
    }
}
