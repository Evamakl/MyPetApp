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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.ui.OwnerNavigation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class HomePageManager extends AppCompatActivity {
    //Initialize variable
    private TextView greetings;
    private ImageView MenuItem, BackItem;
    private DrawerLayout drawerLayout;
    private NavigationView NavigationView;
    private Menu menu;
    private User user;
    private  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_manager);
        init();
    }
    private void init(){
        setID();
        MenuIcon();
        BackIcon();
        NavigationView();
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        greetings = (TextView) findViewById(R.id.greetings);

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
        menu.findItem(R.id.FullName_item).setTitle(greetings.getText());

    }

    private void MenuIcon(){
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
    }
    private void BackIcon(){
        BackItem.setOnClickListener(new View.OnClickListener() {
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
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {

                new ManagerNavigation(HomePageManager.this, item.getItemId(), user);
                return false;
            }
        });
    }
}