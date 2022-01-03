package com.example.myproject;
import org.junit.Test;

import static org.junit.Assert.*;
import com.example.myproject.loginExistsFrame;
import com.example.myproject.new_user;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import javax.xml.validation.Validator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class PasswordAndEmail_Test {
/*
 
    public void isValidUserName_test() {
        assertTrue(loginExistsFrame.isValidUserName("ron"));
        assertFalse(loginExistsFrame.isValidUserName("r0n"));
        assertFalse(loginExistsFrame.isValidUserName("123"));
        //@Test
        //public void addition_isCorrect() { assertEquals(4, 2 + 2); }
    }*/

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
/*
    @Test
    public void isValidPhoneNumber_test() {
        assertFalse(new_user.isValidPhoneNumber("123456789"));
        assertTrue(new_user.isValidPhoneNumber("0523567122"));
    }
    */
}




