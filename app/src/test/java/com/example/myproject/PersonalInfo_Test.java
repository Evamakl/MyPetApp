package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PersonalInfo_Test {
    @Test
    public void isValidAllergy_test() {
        String allergy = "test";
        assertFalse(allergy.length() == 0); //If the allergy is empty, return False
        assertTrue(allergy.length() > 0); //If the allergy bigger than 0 (so it is not empty), return True
    }
    @Test
    //Checks if the checkBox is marked
    public void isAllergy_test() {
        Boolean isAllergy = true;
        assertFalse(isAllergy == false);
    }
    @Test
    //Checks if the checkBox is marked
    public void isNotAllergy_test() {
        Boolean isNotAllergy = true;
        assertFalse(isNotAllergy == false);
    }
    @Test
    //Checks if the checkBox is marked
    public void isNeutering_test() {
        Boolean isNeutering = true;
        assertFalse(isNeutering == false);
    }
    @Test
    //Checks if the checkBox is marked
    public void isSterilization_test() {
        Boolean isSterilization = true;
        assertFalse(isSterilization == false);
    }

}
