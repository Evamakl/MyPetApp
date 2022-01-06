package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateFeedbackMess extends AppCompatActivity {

    //Initialize variable
    private DrawerLayout drawerLayout;
    private Menu menu;
    private ImageView MenuIcon, BackIcon;
    private Intent intent;
    EditText editRank;
    Button addRank;
    DrawerLayout drawer_layout;
    private NavigationView navigation;
    private User user = new User();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Rank");
    private com.google.android.material.navigation.NavigationView NavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_feedback_mess);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
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
                intent = new Intent(CreateFeedbackMess.this, Reports.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {

                new ManagerNavigation(CreateFeedbackMess.this, item.getItemId(), user);
                return false;
            }
        });

        //Assign variable
        drawer_layout = findViewById(R.id.drawer_layout);
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
                Intent intent = new Intent(CreateFeedbackMess.this, navigation_drawer.class);
                if(user.getType().equals("Owner"))
                    intent = new Intent(CreateFeedbackMess.this, Start_work.class);
                else if(user.getType().equals("Manager"))
                    intent = new Intent(CreateFeedbackMess.this, HomePageManager.class);
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
                    new ManagerNavigation(CreateFeedbackMess.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(CreateFeedbackMess.this,id,user);
                else
                    new OwnerNavigation(CreateFeedbackMess.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }

        editRank=findViewById(R.id.rankText);
        addRank=findViewById(R.id.addRankText);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = (String)snapshot.getValue();
                if(text.length() > 0)
                    editRank.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editRank.getText().toString().length() > 0)
                    reference.setValue(editRank.getText().toString());
                Toast.makeText(CreateFeedbackMess.this, "rank updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}