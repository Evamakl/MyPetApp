package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedicalBackground extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView MenuItem, BackItem;
    private Menu menu;
    private Intent intent;
    private Button buttonSave;
    private DrawerLayout drawerLayout;
    private com.google.android.material.navigation.NavigationView NavigationView;
    private TextInputLayout TextInputLayoutMed_Backg,TextInputLayoutMed_Procedure,TextInputLayoutAllergies;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
    private User user;
    private CheckBox checkBoxNO,checkBoxYES,checkBoxCastration,checkBoxSTERILIZATION;
    private Dog dog;
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
        setButtons();
        NavigationView();
        setDogs();
        setInfo();
    }
    private void setID() {
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        dog = (Dog)intent.getSerializableExtra("dog");
        TextInputLayoutMed_Backg = findViewById(R.id.TextInputLayoutMed_Backg);
        TextInputLayoutMed_Procedure = findViewById(R.id.TextInputLayoutMed_Procedure);
        TextInputLayoutAllergies = findViewById(R.id.TextInputLayoutAllergies);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.hello).setTitle( "שלום, " + user.getUsername());
        buttonSave = findViewById(R.id.buttonSave);
        checkBoxNO = findViewById(R.id.checkBoxNO);
        checkBoxYES = findViewById(R.id.checkBoxYES);
        checkBoxCastration = findViewById(R.id.checkBoxCastration);
        checkBoxSTERILIZATION = findViewById(R.id.checkBoxSTERILIZATION);
    }
    private void setInfo(){
        if(dog.getAllergies()) {
            checkBoxYES.setChecked(true);
            checkBoxNO.setChecked(false);
            TextInputLayoutAllergies.setVisibility(View.VISIBLE);
            TextInputLayoutAllergies.getEditText().setText(dog.getAllergy());
        }
        else {
            checkBoxNO.setChecked(true);
            TextInputLayoutAllergies.setVisibility(View.GONE);
        }
        if(dog.getCastration())
            checkBoxCastration.setChecked(true);
        else
            checkBoxCastration.setChecked(false);
        if(dog.getSterilization())
            checkBoxSTERILIZATION.setChecked(true);
        else
            checkBoxSTERILIZATION.setChecked(false);
        TextInputLayoutMed_Backg.getEditText().setText(dog.getBackground());
        TextInputLayoutMed_Procedure.getEditText().setText(dog.getOperation());
    }
    private void setButtons(){
        checkBoxYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxYES.isChecked()) {
                    checkBoxNO.setChecked(false);
                    TextInputLayoutAllergies.setVisibility(View.VISIBLE);
                }else
                    TextInputLayoutAllergies.setVisibility(View.GONE);
            }
        });
        checkBoxNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxNO.isChecked()) {
                    checkBoxYES.setChecked(false);
                    TextInputLayoutAllergies.setVisibility(View.GONE);
                }
            }
        });
        checkBoxCastration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxCastration.isChecked())
                    checkBoxSTERILIZATION.setChecked(false);
            }
        });
        checkBoxSTERILIZATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxSTERILIZATION.isChecked())
                    checkBoxCastration.setChecked(false);
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = 0;
                for(int i=0; i< user.getDogs().size();i++)
                    if(user.getDogs().get(i).equals(dog))
                        index = i;
                dog.setBackground(TextInputLayoutMed_Backg.getEditText().getText().toString());
                dog.setAllergy(TextInputLayoutAllergies.getEditText().getText().toString());
                dog.setOperation(TextInputLayoutMed_Procedure.getEditText().getText().toString());
                dog.setAllergies(false);
                if(checkBoxYES.isChecked())
                    dog.setAllergies(true);
                if(checkBoxNO.isChecked())
                    dog.setAllergies(false);
                if(checkBoxCastration.isChecked())
                    dog.setCastration(true);
                else
                    dog.setCastration(false);
                if(checkBoxSTERILIZATION.isChecked())
                    dog.setSterilization(true);
                else
                    dog.setSterilization(false);
                user.getDogs().get(index).setDog(dog);
                databaseReference.child(user.getUid()).setValue(user);
                Toast.makeText(MedicalBackground.this, "The Information saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void StartActivity(Class Dest){
        Intent intent = new Intent(MedicalBackground.this, Dest);
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
                Intent intent = new Intent(MedicalBackground.this, PetOwnerOptionsOfDog.class);
                intent.putExtra("user",user);
                intent.putExtra("dog",dog);
                startActivity(intent);
                finish();
            }
        });
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                new OwnerNavigation(MedicalBackground.this,id,user,item);
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

