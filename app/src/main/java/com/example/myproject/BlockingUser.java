package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlockingUser extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    private ImageView MenuIcon, BackIcon;
    NavigationView navigation;
    Intent intent;
    User user = new User();
    RecyclerView recyclerView;
    ArrayList<User> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking_user);
        list = new ArrayList<>();
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new ManagerNavigation(BlockingUser.this,item.getItemId(),user);
                return false;
            }
        });
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BlockingUser.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        recyclerView=findViewById(R.id.UsersRV);
        getUsers();
    }
    public void getUsers(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot temp : snapshot.getChildren()){
                    User getUser = temp.getValue(User.class);

                    list.add(getUser);
                }
                setAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setAdapter(ArrayList<User>arrayList){
        BlockUserAdapter blockUserAdapter = new BlockUserAdapter(BlockingUser.this,arrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(blockUserAdapter);
    }

}