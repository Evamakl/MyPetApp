package com.example.myproject;

//import static com.example.myproject.HomePageManager.openDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuInflater;
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
    private DrawerLayout drawer_layout;
    private ImageView MenuIcon, BackIcon;
    private NavigationView navigation;
    private Button saveEvent;
    private Intent intent;
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;
    User user = new User();
    //Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        intent = getIntent();

        drawer_layout =findViewById(R.id.drawer_layout) ;
        title = findViewById(R.id.titleEt);
        location = findViewById(R.id.locationEt);
        description = findViewById(R.id.descriptionEt);
        saveEvent = findViewById(R.id.saveEventbt);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        if(user.getType().toString().equals("PetKeeper")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.pet_keeper_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
        else if(user.getType().toString().equals("Owner")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.base_activity, menu);
            super.onCreateOptionsMenu(menu);
        }
        else if(user.getType().toString().equals("Manager")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.manager_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendar.this, navigation_drawer.class);
                if(user.getType().equals("Owner"))
                    intent = new Intent(Calendar.this, Start_work.class);
                else if(user.getType().equals("Manager"))
                    intent = new Intent(Calendar.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        NavigationView.setNavigationItemSelectedListener(new com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                if(user.getType().toString().equals("Manager"))
                    new ManagerNavigation(Calendar.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(Calendar.this,id,user);
                else
                    new OwnerNavigation(Calendar.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }

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