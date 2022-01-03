package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reports extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    Button usersReport;
    Button dogsReport;
    Button feedbackReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        usersReport = (Button) findViewById(R.id.usersReport);
        usersReport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openUsersReport();
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

    public void ClickMenu(View view) {
        //Open drawer
        HomePageManager.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        //Close drawer
        HomePageManager.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        //Redirect activity to home
        HomePageManager.redirectActivity(this,HomePageManager.class);
    }

    public void ClickReports(View view) {
        //Recreate activity
        recreate();
    }

    public void ClickNewsletterUpdate(View view) {
        //Redirect activity to newsletter update
        HomePageManager.redirectActivity(this, NewsletterUpdate.class);
    }

    public void ClickCreateFeedbackMess(View view) {
        //Redirect activity to create feedback mess
        HomePageManager.redirectActivity(this, CreateFeedbackMess.class);
    }

    public void ClickBlockingUser(View view) {
        //Redirect activity to blocking user
        HomePageManager.redirectActivity(this, BlockingUser.class);
    }

    public void ClickAppUpdate(View view) {
        //Redirect activity to app update
        HomePageManager.redirectActivity(this, AppUpdate.class);
    }

    public void ClickLogout(View view) {
        //Close app
        HomePageManager.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        HomePageManager.closeDrawer(drawerLayout);
    }
}