package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
public class MedicalBackground extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView MenuItem, BackItem;
    private DrawerLayout drawerLayout;
    private EditText editTextTextName,editTextTextEmail;


    private User user;
    private Dog dog;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_background);
        init();
    }
    private void init() {
        setID();
        MenuIcon();
        BackIcon();

    }
    private void setID() {
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        editTextTextName = findViewById(R.id.editTextTextName);
        editTextTextEmail = findViewById(R.id.editTextTextEmail);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
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
                Intent intent = new Intent(MedicalBackground.this, PetOwnerOptionsOfDog.class);
                intent.putExtra("user",user);
                intent.putExtra("dog",dog);
                startActivity(intent);
                finish();
            }
        });
    }





}

