package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.text.TextUtils;
import android.util.Patterns;

import org.junit.Test;

public class NewUser_Test {
    //Checks if the password is valid
    @Test
    public void isValidPassword_test() {
        assertFalse(isValidPassword("12345"));
        assertTrue(isValidPassword("1234ab"));
        assertTrue(isValidPassword("123456789"));
        assertTrue(isValidPassword("A12345"));
    }

    private boolean isValidPassword(String password) {
        return password.length() > 5;
    }

    //Checks if the email is valid
    @Test
    public void isValidEmail_test() {
        assertFalse(isValidEmail("hadar@ss"));
        assertFalse(isValidEmail("hadar@hadar"));
        assertTrue(isValidEmail("hadar@hadar.com"));
    }

    private boolean isValidEmail(String email) {
        return email.matches("[A-Za-z0-9]+@[A-Za-z0-9]+.com");
    }

    //Checks if the phone number is valid
    @Test
    public void isValidPhoneNumber_test() {
        assertFalse(isValidPhoneNumber("123456789"));
        assertTrue(isValidPhoneNumber("0523567122"));
    }

    private boolean isValidPhoneNumber(String phone) {
        return phone.length() == 10 ;
    }
}
