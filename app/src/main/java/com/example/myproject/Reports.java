package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Reports extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    Button usersReport;
    Button dogsReport;
    Button feedbackReport;
    private ImageView MenuIcon, BackIcon;
    NavigationView navigation;
    Intent intent;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        usersReport = (Button) findViewById(R.id.usersReport);
        usersReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openUsersReport();
            }
        });
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new ManagerNavigation(Reports.this,item.getItemId(),user);
                return false;
            }
        });
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Reports.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        dogsReport = (Button) findViewById(R.id.dogsReport);
        dogsReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDogsReport();
            }
        });

        feedbackReport = (Button) findViewById(R.id.feedbackReport);
        feedbackReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openFeedbackReport();
            }
        });
    }

    public void openDogsReport (){
        Intent intent = new Intent(this, dogsReport.class);
        startActivity(intent);
    }

    public void openUsersReport (){
        Intent intent = new Intent(this, UsersReport.class);
        startActivity(intent);
    }

    public void openFeedbackReport (){
        Intent intent = new Intent(this, FeedbackReport.class);
        startActivity(intent);
    }
}