package com.example.myproject;
import org.junit.Test;

import static org.junit.Assert.*;
import com.example.myproject.loginExistsFrame;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    @Test
    public void isValidUserName_test() {
        assertTrue(loginExistsFrame.isValidUserName("ron"));
        assertFalse(loginExistsFrame.isValidUserName("r0n"));
        assertFalse(loginExistsFrame.isValidUserName("123"));
    }

    @Test
    public void isValidPassword_test() {
        assertFalse(loginExistsFrame.isValidPassword("12345"));
        assertFalse(loginExistsFrame.isValidPassword("1234ab"));
        assertFalse(loginExistsFrame.isValidPassword("123456789"));

    }

}