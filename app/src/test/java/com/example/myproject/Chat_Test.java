package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Chat_Test {
    @Test
    public void isValidMessage_test(String msg) {
        assertFalse(msg.length() == 0); //If the message is empty is False
        assertTrue(msg.length() > 0); //If the message bigger than 0 (so it is not empty), is True
    }
}
