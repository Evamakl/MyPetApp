package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class technicalSupport extends AppCompatActivity {
    private AddTipDialog dialog;
    private int size =0;
    Button addNewtip,remove_tip;
    private RecyclerView recyclerView;
    private ManagerTipsAdapter managerTipsAdapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("ManagerTips");
    DrawerLayout drawerLayout;
    private User user = new User();
    private Intent intent;
    private ArrayList<String> list;
    //NavigationView navigation;
    private com.google.android.material.navigation.NavigationView NavigationView;
    private ImageView MenuItem, BackItem;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_support2);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        list = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.RecyclerView);
        addNewtip = (Button) findViewById(R.id.button_addNewTask);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
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
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(technicalSupport.this, navigation_drawer.class);
                if(user.getType().equals("Owner"))
                    intent = new Intent(technicalSupport.this, Start_work.class);
                else if(user.getType().equals("Manager"))
                    intent = new Intent(technicalSupport.this, HomePageManager.class);
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
                    new ManagerNavigation(technicalSupport.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(technicalSupport.this,id,user);
                else
                    new OwnerNavigation(technicalSupport.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }
        if(!user.getType().equals("Manager")){
            addNewtip.setVisibility(View.INVISIBLE);
        }
        else{
            addNewtip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goAddNewTask();
                }
            });
        }
        readTodo();
    }

    public void readTodo() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot s : snapshot.getChildren()) {
                    String temp = (String)s.getValue();
                    list.add(temp);
                }
                managerTipsAdapter = new ManagerTipsAdapter(technicalSupport.this,list,user);
                recyclerView.setLayoutManager(new GridLayoutManager(technicalSupport.this, 1));
                recyclerView.setAdapter(managerTipsAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void goAddNewTask() {
        dialog = new AddTipDialog(technicalSupport.this,"הוספת טיפ חדש",user,size);
        dialog.show(getSupportFragmentManager(),"opendialog");
    }
    public void goCreateTechnicalSupport() {
       // Intent intent = new Intent(this, creare_technical_support_page.class);
        //startActivity(intent);
    }
    public void goEditExistingPage() {
        //Intent intent = new Intent(this, editExsitingPage.class);
        //startActivity(intent);
    }
}