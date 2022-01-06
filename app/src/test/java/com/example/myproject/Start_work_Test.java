package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Start_work_Test {
    @Test
    public void getUser_test() {
        User user = new User("0","test@gmail.com","0531111111","eva eva");
        //check user's name
        assertFalse(user == null);
        assertFalse(user.getUsername().length() == 0 );
        assertTrue(user.getUsername().length() > 0 );
        assertTrue(user != null);
//        assertFalse(user.getType().contains("-") || user.getType().contains("."));
//        assertTrue(user.getType().contains("-") || user.getType().contains("."));
        //check user's email
        assertFalse(user.getEmail().length() == 0);
        assertTrue(user.getEmail().length()  > 0);
        assertFalse(isValidEmail("hadar@ss"));
        assertFalse(isValidEmail("hadar@hadar"));
        assertTrue(isValidEmail(user.getEmail()));
        //check user's Phone
        assertFalse(user.getPhone().length() == 0);
        assertTrue(user.getPhone().length()  > 0);
        //assertFalse(user.getPhone().contains("-") || user.getPhone().contains("."));
        //assertTrue(user.getPhone().contains("-") || user.getPhone().contains("."));
        //check user's Uid
        assertFalse(user.getUid().length() == 0);
        assertTrue(user.getUid().length()  > 0);
        //assertFalse(user.getUid().contains("-") || user.getUid().contains("."));
        //assertTrue(user.getUid().contains("-") || user.getUid().contains("."));
    }
    private boolean isValidEmail(String email) {
        return email.matches("[A-Za-z0-9]+@[A-Za-z0-9]+.com");
    }
}
