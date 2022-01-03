package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Tips_Test {
    @Test
    public void isValidTip_test(String tip) {
        assertFalse(tip.length() == 0); //If the tip's length == 0 (it is empty), return False
        assertTrue(tip.length() > 0); //If the tip's length == 0 (it is empty), return False


    }
}
