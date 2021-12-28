package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DogList extends AppCompatActivity {
    //initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        //assign variable
        drawerLayout= findViewById(R.id.drawer_layout);

    }
    public void ClickDogList(View view){
        //recreate activity
        recreate();
    }

    public void ClickToDoList(View view){
        //redirect activity to to do list
        navigation_drawer.redirectActivity(this,ToDoList.class);

    }

    public void ClickReminder(View view){
        //redirect activity to reminder
        navigation_drawer.redirectActivity(this,Reminder.class);

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
    public void clickAddDog2PK(View view) {
        DogList.redirectActivity(this,reliability_form.class);
    }
    public void ClickRemoveDogPK(View view) {
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //stat activity
        activity.startActivity(intent);
    }


}