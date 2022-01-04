package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Feedback_Test {
    @Test
    public void isValidFeedback_test(String feedback) {
        assertFalse(feedback.length() == 0); //If the feedback is empty, return False
        assertTrue(feedback.length() > 0); //If the feedback bigger than 0 (so it is not empty), return True
    }
    @Test
    public void isManager_test(User user) {
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
}
