package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Remainder_Test {
    @Test
    public void isValidTitle_test() {
        String title = "test";
        assertFalse(title.length() == 0); //If the message is empty, return False
        assertTrue(title.length() > 0); //If the message bigger than 0 (so it is not empty), return True
    }
    @Test
    public void isValidLocation_test() {
        String location = "test";
        assertFalse(location.length() == 0); //If the message is empty, return False
        assertTrue(location.length() > 0); //If the message bigger than 0 (so it is not empty), return True
    }
    @Test
    public void isValidDetails_test() {
        String details = "test";
        assertFalse(details.length() == 0); //If the message is empty, return False
        assertTrue(details.length() > 0); //If the message bigger than 0 (so it is not empty), return True
    }
}
