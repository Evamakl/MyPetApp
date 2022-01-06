package com.example.myproject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

public class ToDoList_Test {
    @Test
    public void toDoList_test() {
        TodoListClass ToDo = new TodoListClass("test","false");

        assertFalse(ToDo.getFlag().equals("true")); //If the flag of the ToDo is true, return False
        assertTrue(!ToDo.getFlag().equals("true")); //If the flag og the ToDo is not true, return True
        assertFalse(ToDo.getText().length() == 0); //If the ToDo's length == 0 (it is empty), return False
        assertTrue(ToDo.getText().length() > 0); //If the ToDo's length == 0 (it is empty), return False
    }
}
