package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Vaccines_and_Medicines_slide extends AppCompatActivity {
    Button vac;
    Button med;
    private User user = new User();
    private ImageView MenuItem, BackItem;
    private Dog dog;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Menu menu;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private com.google.android.material.navigation.NavigationView NavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_and_medicines_slide);
        init();
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
        vac = (Button) findViewById(R.id.Vaccines);
        med = (Button) findViewById(R.id.Medicines);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
    }
    private void setButtons(){
        vac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity(Vaccines.class);
            }

        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // StartActivity();
            }
        });
    }
    private void StartActivity(Class Dest){
        Intent intent = new Intent(Vaccines_and_Medicines_slide.this, Dest);
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
                Intent intent = new Intent(Vaccines_and_Medicines_slide.this, PetOwnerOptionsOfDog.class);
                intent.putExtra("user",user);
                intent.putExtra("dog",dog);
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
                new OwnerNavigation(Vaccines_and_Medicines_slide.this,id,user,item);
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