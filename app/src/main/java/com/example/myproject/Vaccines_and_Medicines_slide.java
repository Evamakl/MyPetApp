package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class Vaccines_and_Medicines_slide extends AppCompatActivity {
    Button vac;
    Button med;
    private User user = new User();
    private Dog dog;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_and_medicines_slide);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        vac = (Button) findViewById(R.id.Vaccines);
        vac.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityVaccines();
            }

        });
        med = (Button) findViewById(R.id.Medicines);
        med.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityMediccines();
            }

        });
    }
    private void init(){
        setID();
        MenuIcon();
        BackIcon();
        NavigationView();
        setDogs();
        setButtons();
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        Med_dog_information=findViewById(R.id.Med_Info);
        Med_dog_file = findViewById(R.id.Med_F);
        Personal_Dog_File = findViewById(R.id.Personal_F);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.hello).setTitle( "שלום, " + user.getUsername());
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
                Intent intent = new Intent(PetOwnerOptionsOfDog.this, Start_work.class);
                intent.putExtra("user",user);
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