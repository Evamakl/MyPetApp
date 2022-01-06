package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PetOwnerOptionsOfDog extends AppCompatActivity {
    Button Personal_Dog_File;
    Button Med_dog_file;
    Button Med_dog_information;
    private User user = new User();
    private ImageView MenuItem, BackItem;
    private Dog dog;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Menu menu;
    private Intent intent;
    private Class ret;
    private DrawerLayout drawerLayout;
    private com.google.android.material.navigation.NavigationView NavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner_options_of_dog);
        init();
    }
    private void init(){
        setID();
        MenuIcon();
        BackIcon();
        NavigationView();
        if(user.getType().toString().equals("Owner"))
            setDogs();
        setButtons();
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        ret = (Class)intent.getSerializableExtra("return");
        Med_dog_information=findViewById(R.id.Med_Info);
        Med_dog_file = findViewById(R.id.Med_F);
        Personal_Dog_File = findViewById(R.id.Personal_F);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
        if(user.getType().toString().equals("PetKeeper")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.pet_keeper_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
    }
    private void setButtons(){
        Personal_Dog_File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(Personal_File.class);
            }
        });
        Med_dog_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(Vaccines_and_Medicines_slide.class);
            }
        });
        Med_dog_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(MedicalBackground.class);
            }
        });
    }
    private void StartActivity(Class Dest){
        Intent intent = new Intent(PetOwnerOptionsOfDog.this, Dest);
        intent.putExtra("dog",dog);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
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
                Intent intent = new Intent(PetOwnerOptionsOfDog.this, DogList.class);
                if(user.getType().equals("Owner"))
                    intent = new Intent(PetOwnerOptionsOfDog.this, Start_work.class);
                else if(user.getType().equals("Manager"))
                    intent = new Intent(PetOwnerOptionsOfDog.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();

               /* Intent intent = new Intent(PetOwnerOptionsOfDog.this, ret);
                intent.putExtra("user",user);
                intent.putExtra("dog",dog);
                startActivity(intent);
                finish();*/
            }
        });
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                if(user.getType().toString().equals("Manager"))
                    new ManagerNavigation(PetOwnerOptionsOfDog.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(PetOwnerOptionsOfDog.this,id,user);
                else
                    new OwnerNavigation(PetOwnerOptionsOfDog.this,id,user,item);
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

