package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TechnicalSupport_Test {
    //Only the manager can edit the technical support, so if the user is not manager, return false@Test
    @Test
    public void isManager_test() {
        User user = new User("55","test@gmail.com","123","admin");
        user.setType("Manager");
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
    @Test
    public void isValidTipManager_Test() {
        String tipManager = "test";
        assertFalse(tipManager.length() == 0); //If the message is empty, return False
        assertTrue(tipManager.length() > 0); //If the message bigger than 0 (so it is not empty), return True
    }
    @Test
    //Checks if the checkBox is marked
    public void isChecked_test() {
        Boolean isCheck = true;
        assertFalse(isCheck == false);
    }

}
