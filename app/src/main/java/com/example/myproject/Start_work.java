package com.example.myproject;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myproject.ui.OwnerNavigation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Start_work extends AppCompatActivity {
    private TextView Title;
    private EditText editTextTextName,editTextTextEmail;
    private ImageView MenuItem, BackItem;
    private DrawerLayout drawerLayout;
    private NavigationView NavigationView;
    private Menu menu;
    private User user;
    private  FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Intent intent;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);
        init();
    }
    private void init(){
        setID();
        setInformation();
        MenuIcon();
        BackIcon();
        NavigationView();
        setDogs();
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        editTextTextName = findViewById(R.id.editTextTextName);
        editTextTextEmail = findViewById(R.id.editTextTextEmail);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.hello).setTitle( "שלום, " + user.getUsername());
    }
    private void setInformation(){
        editTextTextName.setText(user.getUsername());
        editTextTextEmail.setText(user.getEmail());
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
                if(firebaseAuth.getCurrentUser() != null)
                    firebaseAuth.signOut();
                Intent intent = new Intent(Start_work.this, Exist_new_frame.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                new OwnerNavigation(Start_work.this,id,user,item);
                return false;
            }
        });
    }
    private void setDogs(){
        menu = NavigationView.getMenu();
        for(int i=0; i<user.getDogs().size();i++)
            menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
    }
}

