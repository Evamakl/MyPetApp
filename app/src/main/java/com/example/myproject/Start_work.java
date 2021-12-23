package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Start_work extends AppCompatActivity {
    private TextView Title;
    private EditText editTextTextName,editTextTextEmail;
    private ImageView MenuItem, BackItem;
    private DrawerLayout drawerLayout;
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
               // intent.putExtra("user",user);
                startActivity(intent);
                finish();
            }
        });
    }
    public void openNewActivitybaseB (){
      //  Intent intent = new Intent(this, base_activity.class);
       // startActivity(intent);
    }
}

