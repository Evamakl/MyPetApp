package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class HomePageManager extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    TextView greetings;
    NavigationView navigation;
    ImageView MenuIcon, BackIcon;
    User user = new User();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_manager);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        greetings = (TextView) findViewById(R.id.greetings);
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                new ManagerNavigation(HomePageManager.this, item.getItemId(), user);
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

                //initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(HomePageManager.this);
                builder.setTitle("LogOut");
                //set message
                builder.setMessage("Are you sure you want to logout ?");
                //Positive yes button
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish activity
                        //activity.finishAffinity();
                        //exit app
                        if (firebaseAuth.getCurrentUser() != null)
                            firebaseAuth.signOut();
                        startActivity(new Intent(HomePageManager.this, firstframe.class));
                        finish();
                    }

                });
                //negative no button
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Dismiss dialog
                        dialog.dismiss();
                    }
                });
                //show dialog
                builder.show();
            }
        });

        //greetings
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);

        if (time >= 0 && time < 12) {
            greetings.setText("בוקר טוב, ");
        } else if (time >= 12 && time < 16) {
            greetings.setText("צהריים טובים, ");
        } else if (time >= 16 && time < 21) {
            greetings.setText("ערב טוב, ");
        } else if (time >= 21 && time < 24) {
            greetings.setText("לילה טוב, ");
        } else {
            greetings.setText("שלום, ");
        }
        greetings.setText(greetings.getText() + user.getUsername());
    }


}