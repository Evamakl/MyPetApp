package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SighinManager_Test {
    //Check sigh in of the manager
    @Test
    public void isManager_test(User user) {
        assertFalse(user.getType().equals("Owner")); //If the user type is owner, return False
        assertFalse(user.getType().equals("PetKeeper")); //If the user type is pet keeper, return False
        assertTrue(user.getType().equals("Manager")); //If the user type is manager, return True
    }
}
