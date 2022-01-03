package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SigninOwner_Tsst {
    //Check sigh in of the owner
    @Test
    public void isOwner_test(User user) {
        assertFalse(user.getType().equals("Manager")); //If the user type is manager, return False
        assertFalse(user.getType().equals("PetKeeper")); //If the user type is pet keeper, return False
        assertTrue(user.getType().equals("Owner")); //If the user type is owner, return True
    }
}
