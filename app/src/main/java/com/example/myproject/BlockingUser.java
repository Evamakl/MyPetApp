package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlockingUser extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawer_layout;

    private ImageView MenuIcon, BackIcon;
    private User user;
    private Intent intent;

    //DrawerLayout drawerLayout;
    NavigationView navigation;
    //Intent intent;
    //User user = new User();
    RecyclerView recyclerView;
    ArrayList<User> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking_user);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        drawer_layout = findViewById(R.id.drawer_layout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BlockingUser.this, Reports.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {

                new ManagerNavigation(BlockingUser.this, item.getItemId(), user);
                return false;
            }
        });

        list = new ArrayList<>();
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
                Intent intent = new Intent(BlockingUser.this, HomePageManager.class);
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
                    new ManagerNavigation(BlockingUser.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(BlockingUser.this,id,user);
                else
                    new OwnerNavigation(BlockingUser.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }

        recyclerView=findViewById(R.id.UsersRV);
        getUsers();
    }
    public void getUsers(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot temp : snapshot.getChildren()){
                    User getUser = temp.getValue(User.class);

                    list.add(getUser);
                }
                setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setAdapter(ArrayList<User>arrayList){
        BlockUserAdapter blockUserAdapter = new BlockUserAdapter(BlockingUser.this,arrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(blockUserAdapter);
    }

}