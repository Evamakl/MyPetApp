package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TechnicalSupport_Test {
    //Only the manager can edit the technical support, so if the user is not manager, return false
    @Test
    public void isManager_test(User user) {
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
    @Test
    public void isValidTipManager_test(String tipManager) {
        assertFalse(tipManager.length() == 0); //If the tipManager's length == 0 (it is empty), return False
        assertTrue(tipManager.length() > 0); //If the tipManager's length != 0 (it is not empty), return True
    }
    @Test
    //Checks if the checkBox is marked
    public void isChecked_test(Boolean isCheck) {
        assertFalse(isCheck == false);
    }

}
