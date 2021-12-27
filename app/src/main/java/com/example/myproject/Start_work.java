package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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
    private Intent intent;
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
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        editTextTextName = findViewById(R.id.editTextTextName);
        editTextTextEmail = findViewById(R.id.editTextTextEmail);
        Title = findViewById(R.id.Title);
        Title.setText("מסך ראשי");
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
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
                Intent intent = new Intent(Start_work.this, Exist_new_frame.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });
    }
    public void NavigationView()
    {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                if( id == R.id.AddDog){

                }
                else if( id == R.id.RemoveDog){

                }
                else if(id==R.id.about){
                    Intent intent = new Intent(Start_work.this, search_page.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }
                else if(id==R.id.calendar){
                    Intent intent = new Intent(Start_work.this, Calendar.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }
                else if(id==R.id.logout){
                    if(FirebaseAuth.getInstance().getCurrentUser() != null)
                        FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(Start_work.this, firstframe.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
    }
    public void openNewActivitybaseB (){
      //  Intent intent = new Intent(this, base_activity.class);
       // startActivity(intent);
    }
}

