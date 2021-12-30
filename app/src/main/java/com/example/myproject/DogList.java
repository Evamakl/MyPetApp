package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DogList extends AppCompatActivity {
    //initialize variable
    DrawerLayout dogList_layout;
    User user = new User();
    Intent intent;
    private ImageView MenuItem, BackItem;
    RecyclerView sa;
    TextView s, sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);
        //assign variable
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        dogList_layout = findViewById(R.id.dogList_layout);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogList_layout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigation_drawer.redirectActivity(DogList.this,navigation_drawer.class);
                Intent intent = new Intent(DogList.this, navigation_drawer.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
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

    public void clickAddDog2PK(View view) {
        navigation_drawer.redirectActivity(this,reliability_form.class);
    }
    public void ClickRemoveDogPK(View view) {navigation_drawer.redirectActivity(this,ChooseDogPK.class);
    }




}