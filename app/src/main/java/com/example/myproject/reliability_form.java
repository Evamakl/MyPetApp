package com.example.myproject;

//import static com.example.myproject.HomePageManager.openDrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class reliability_form extends AppCompatActivity {
    Button next;
    DrawerLayout dogList_form;
    NavigationView navigation;
    CheckBox checkBox;
    private String op;
    private ImageView MenuItem, BackItem;
    User user = new User();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliability_form);
        dogList_form = findViewById(R.id.reliability_form_layout);
        intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        op = (String) intent.getSerializableExtra("op");
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        next = (Button) findViewById(R.id.nextBT);
        checkBox = findViewById(R.id.checkBox);
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(reliability_form.this, item.getItemId(), user);
                return false;
            }
        });
        MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogList_form.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reliability_form.this, DogList.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()) {
                    Intent intent = new Intent(reliability_form.this, ChooseDogPK.class);
                    intent.putExtra("user", user);
                    intent.putExtra("op", op);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(reliability_form.this, "Please click the check box!", Toast.LENGTH_LONG).show();
            }
        });



    }


}