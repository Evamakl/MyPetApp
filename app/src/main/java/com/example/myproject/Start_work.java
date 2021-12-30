package com.example.myproject;

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
        setDogs();
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
                Intent intent = new Intent(Start_work.this, Exist_new_frame.class);
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
                if( id == R.id.AddDog){
                    AddDog();
                }
                else if( id == R.id.RemoveDog){
                    RemoveDog();
                }
                else  if( id == R.id.hello){ }
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
                    //initialize alert dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(Start_work.this);
                    builder.setTitle("LogOut");
                    //set message
                    builder.setMessage("Are you sure you want to logout ?");
                    //Positive yes button
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(FirebaseAuth.getInstance().getCurrentUser() != null)
                                FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(Start_work.this, firstframe.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                            finish();
                        }
                    });
                    //negative no button
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Dismiss dialog
                            dialog.dismiss();
                        }
                    });
                    //show dialog
                    builder.show();
                }
                else{
                    String dogs_name = item.getTitle().toString();
                    for(int i=0; i<user.getDogs().size();i++){
                        if(user.getDogs().get(i).getName().equals(dogs_name)){
                            Intent intent = new Intent(Start_work.this, PetOwnerOptionsOfDog.class);
                            intent.putExtra("dog",user.getDogs().get(i));
                            intent.putExtra("user",user);
                            startActivity(intent);
                        }
                    }
                }
                return false;
            }
        });
    }
    private void setDogs(){
        menu = NavigationView.getMenu();
        for(int i=0; i<user.getDogs().size();i++)
            menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
    }
    private void AddDog(){
        AddDogDialog addDogDialog = new AddDogDialog(Start_work.this,user);
        addDogDialog.show(getSupportFragmentManager(),"show dialog");
    }
    private void RemoveDog(){
        RemoveDogDialog removeDogDialog = new RemoveDogDialog(Start_work.this,user);
        removeDogDialog.show(getSupportFragmentManager(),"show dialog");
    }
    public void openNewActivitybaseB (){
      //  Intent intent = new Intent(this, base_activity.class);
       // startActivity(intent);
    }
}

