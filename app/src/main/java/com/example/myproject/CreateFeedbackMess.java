package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateFeedbackMess extends AppCompatActivity {

    //Initialize variable
    EditText editRank;
    Button addRank;
    DrawerLayout drawerLayout;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Rank");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_feedback_mess);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        editRank=findViewById(R.id.rankText);
        addRank=findViewById(R.id.addRankText);

        addRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editRank.getText().toString().length() > 0)
                    reference.setValue(editRank.getText().toString());
                Toast.makeText(CreateFeedbackMess.this, "rank updated", Toast.LENGTH_SHORT).show();
            }
        });
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
        //Redirect activity to newsletter update
        HomePageManager.redirectActivity(this, NewsletterUpdate.class);
    }

    public void ClickCreateFeedbackMess(View view) {
        //Recreate activity
        recreate();
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