package com.example.myproject;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import javax.xml.validation.Validator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    //@Test
    //public void addition_isCorrect() { assertEquals(4, 2 + 2); }



    @Test
    public void isValidPassword_test() {
        assertFalse(new_user.isValidPassword("12345"));
        assertFalse(new_user.isValidPassword("1234ab"));
        assertFalse(new_user.isValidPassword("123456789"));

    }

    @Test
    public void isEmailValid_test() {
        assertFalse(new_user.isEmailValid("hadar"));
        assertFalse(new_user.isEmailValid("hadar@hadar"));
        assertTrue(new_user.isEmailValid("hadar@hadar.com"));
    }

    @Test
    public void isValidPhoneNumber_test() {
        assertFalse(new_user.isValidPhoneNumber("123456789"));
        assertTrue(new_user.isValidPhoneNumber("0523567122"));
    }
}

    /*
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
     */



