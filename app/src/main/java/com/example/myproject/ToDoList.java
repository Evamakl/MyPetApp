package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class ToDoList extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickDogList(View view){
        //redirect activity to reminder
        navigation_drawer.redirectActivity(this,DogList.class);
    }

    public void ClickToDoList(View view){
        //recreate activity
        recreate();
    }

    public void ClickReminder(View view){
        //redirect activity to to do list
        navigation_drawer.redirectActivity(this,ToDoList.class);

    }
    public void Clicktips(View view){
        //redirect activity to information and tips
        navigation_drawer.redirectActivity(this,tips.class);

    }
    public void ClickLogOut(View view){
        //close app
        navigation_drawer.logout(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //close drawer
        navigation_drawer.closeDrawer(drawerLayout);
    }
}