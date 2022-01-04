package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FeedbackReport_Test {
    @Test
    public void isManager_test(User user) {
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
}
