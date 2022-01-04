package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppUpdate_Test {
    @Test
    public void isUpdate_test(String version) {
        assertFalse(version.equals(R.id.tv_latest_version));
        assertTrue(version.equals(R.id.tv_latest_version));
    }

    @Test
    public void isManager_test(User user) {
        assertFalse(user.getType().equals("Owner"));
        assertFalse(user.getType().equals("PetKeeper"));
        assertTrue(user.getType().equals("Manager"));
    }
}
