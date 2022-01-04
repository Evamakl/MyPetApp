package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class vaccines_info extends AppCompatActivity {

    Button cont;
    DrawerLayout dogList_layout;
    NavigationView navigation;
    User user = new User();
    Intent intent;
    private ImageView MenuItem, BackItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_info);
        cont = (Button) findViewById(R.id.continuebt);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        //navigation = findViewById(R.id.NavigationView);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityCon();
            }
        });
        /*navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(vaccines_info.this,item.getItemId(),user);
                return false;
            }
        });*/
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogList_layout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vaccines_info.this, Start_work.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

    }
    public void openNewActivityCon() {
        Intent intent = new Intent(this, vaccines_info_2.class);
        startActivity(intent);
    }
    public void openNewActivityBack() {
        Intent intent = new Intent(this, search_page.class);
        startActivity(intent);
    }
}