package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SighinPetKeeper_Test {
    //Check sigh in of the pet keeper
    @Test
    public void isPetKeeper_test() {
        User user = new User("55","test@gmail.com","123","admin");
        user.setType("PetKeeper");
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("Manager"));
        assertTrue(user.getType().equals("PetKeeper"));
    }
}
