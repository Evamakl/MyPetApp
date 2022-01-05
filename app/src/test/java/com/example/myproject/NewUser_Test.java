package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NewUser_Test {
    //Checks if the password is valid
    @Test
    public void isValidPassword_test() {
        assertFalse(loginExistsFrame.isValidPassword("12345"));
        assertFalse(loginExistsFrame.isValidPassword("1234ab"));
        assertFalse(loginExistsFrame.isValidPassword("123456789"));
        assertTrue(loginExistsFrame.isValidPassword("A12345"));
    }
    //Checks if the email is valid
    @Test
    public static void isValidEmail_test() {
        assertFalse(new_user.isValidEmail("hadar"));
        assertFalse(new_user.isValidEmail("hadar@hadar"));
        assertTrue(new_user.isValidEmail("hadar@hadar.com"));
    }
    //Checks if the phone number is valid
    @Test
    public void isValidPhoneNumber_test() {
        assertFalse(new_user.isValidPhoneNumber("123456789"));
        assertTrue(new_user.isValidPhoneNumber("0523567122"));
    }
}
