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
import com.google.firebase.auth.FirebaseAuth;

public class search_page extends AppCompatActivity {
    TextView first_title;
    TextView second_title;
    Button vaccin_button;
    Button food_button;
    Button shampo_button;
    Button walk_button;
    DrawerLayout dogList_layout;
    NavigationView navigation;
    User user = new User();
    Intent intent;
    private ImageView MenuItem, BackItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        first_title = (TextView) findViewById(R.id.first_titleTV);
        second_title = (TextView) findViewById(R.id.second_titleTV);
        vaccin_button = (Button) findViewById(R.id.vaccin_buttonBT);
        food_button = (Button) findViewById(R.id.food_buttonBT);
        shampo_button = (Button) findViewById(R.id.shampo_buttonBT);
        walk_button = (Button) findViewById(R.id.walk_buttonBT);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        navigation = findViewById(R.id.NavigationView);


        vaccin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityvaccin_button();
            }
        });
        food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityfood_button();
            }
        });
        shampo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityshampo_button();
            }
        });
        walk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivitywalk_button();
            }

        });
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(search_page.this,item.getItemId(),user);
                return false;
            }
        });
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogList_layout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_page.this, navigation_drawer.class);
                if(user.getType().equals("Owner"))
                    intent = new Intent(search_page.this, Start_work.class);
                else if(user.getType().equals("Manager"))
                    intent = new Intent(search_page.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }

    public void openNewActivityLogout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openNewActivityvaccin_button() {
            Intent intent = new Intent(this, vaccines_info.class);
            startActivity(intent);
    }
    public void openNewActivityfood_button() {
        Intent intent = new Intent(this, food_info.class);
        startActivity(intent);
    }
    public void openNewActivityshampo_button() {
        Intent intent = new Intent(this, shampo_info.class);
        startActivity(intent);
    }
    public void openNewActivitywalk_button() {
        Intent intent = new Intent(this, walk_info.class);
        startActivity(intent);
    }
    public void openNewActivityback_button() {
        //Intent intent = new Intent(this, new_user.class);
        //startActivity(intent);
    }

}
