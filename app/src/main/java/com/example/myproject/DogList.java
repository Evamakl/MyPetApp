package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class DogList extends AppCompatActivity {
    //initialize variable
    DrawerLayout dogList_layout;
    NavigationView navigation;
    User user = new User();
    Intent intent;
    private ImageView MenuItem, BackItem;
    RecyclerView see_dog_RV;
    TextView addDog, removeDog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);
        //assign variable
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        intent = getIntent();
        see_dog_RV = findViewById(R.id.see_dog_RV);
        addDog = findViewById(R.id.add_dog);
        addDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DogList.this, reliability_form.class);
                intent.putExtra("user", user);
                intent.putExtra("op", "add");
                startActivity(intent);
                finish();
            }
        });
        removeDog = findViewById(R.id.remove_dog);
        removeDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DogList.this, ChooseDogPK.class);
                intent.putExtra("user", user);
                intent.putExtra("op", "delete");
                startActivity(intent);
                finish();
            }
        });

        user = (User) intent.getSerializableExtra("user");
        dogList_layout =findViewById(R.id.drawer_layout) ;
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(DogList.this,item.getItemId(),user);
                return false;
            }
        });
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogList_layout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DogList.this, navigation_drawer.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        setDogs();
    }
    private void setDogs(){
        DogListPkAdapter dogListPkAdapter = new DogListPkAdapter(this,user.getDogs(),user);
        see_dog_RV.setLayoutManager(new GridLayoutManager(this,1));
        see_dog_RV.setAdapter(dogListPkAdapter);
    }
}