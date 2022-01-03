package com.example.myproject;

import static com.example.myproject.HomePageManager.openDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Calendar extends AppCompatActivity {
    EditText title;
    EditText location;
    EditText description;
    //DrawerLayout ;
    DrawerLayout drawerLayout;
    private ImageView MenuItem, BackItem;
    NavigationView navigation;
    Button saveEvent;
    Intent intent;

    User user = new User();
    //Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        intent = getIntent();
        User=(User)intent.getSerializableExtra("user");

        drawerLayout =findViewById(R.id.drawer_layout) ;
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        title = findViewById(R.id.titleEt);
        location = findViewById(R.id.locationEt);
        description = findViewById(R.id.descriptionEt);
        saveEvent = findViewById(R.id.saveEventbt);
/*
        saveEvent.setOnClickListener(new View.OnClickListener()
        {
*/
        //user = (User) intent.getSerializableExtra("user");
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(Calendar.this,item.getItemId(),user);
                return false;
            }
        });
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this, navigation_drawer.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        saveEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                && !description.getText().toString().isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("allDay", true);
                    intent.putExtra("rrule", "FREQ=YEARLY");
                    intent.putExtra("title", description.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    intent.putExtra("eventLocation", location.getText().toString());
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Calendar.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}