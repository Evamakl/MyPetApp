package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class NewsletterUpdate extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter_update);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
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
        //Redirect activity to reports
        HomePageManager.redirectActivity(this, Reports.class);
    }

    public void ClickNewsletterUpdate(View view) {
        //Recreate activity
        recreate();
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