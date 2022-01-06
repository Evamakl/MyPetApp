package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChatSendUsers_Test {
    @Test
    public void checkIsNotMe_test() {
        User user = new User("55","test@gmail.com","123","admin");
        assertTrue(user.getEmail().equals(user.getEmail()));
    }
    @Test
    public void checkUserToSend_Test() {
        User user = new User("55","test@gmail.com","123","admin");
        assertFalse(user.getUid().isEmpty());
        assertFalse(user.getUsername().isEmpty());
        assertFalse(user.getEmail().isEmpty());
        assertTrue(!user.getEmail().isEmpty());
    }
}
