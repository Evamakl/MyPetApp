package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BlockingUser extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
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
        recyclerView=findViewById(R.id.UsersRV);
        getUsers();
    }
    public void getUsers(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot temp : snapshot.getChildren()){
                       User getUser = snapshot.getValue(User.class);
                    /*String username = (String)temp.child("username").getValue().toString();
                    String email = (String)temp.child("email").getValue().toString();
                    String phone = (String)temp.child("phone").getValue().toString();
                    list.add(new User("1",email,phone,username));*/
                    list.add(getUser);
                }
                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setAdapter(){
        BlockUserAdapter blockUserAdapter = new BlockUserAdapter(BlockingUser.this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(blockUserAdapter);
    }
    public void ClickMenu(View view) {
        //Open drawer
        HomePageManager.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        //Close drawer
        HomePageManager.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        //Redirect activity to home
        HomePageManager.redirectActivity(this,HomePageManager.class);
    }

    public void ClickReports(View view) {
        //Redirect activity to reports
        HomePageManager.redirectActivity(this, Reports.class);
    }

    public void ClickNewsletterUpdate(View view) {
        //Redirect activity to newsletter update
        HomePageManager.redirectActivity(this, NewsletterUpdate.class);
    }

    public void ClickCreateFeedbackMess(View view) {
        //Redirect activity to create feedback mess
        HomePageManager.redirectActivity(this, CreateFeedbackMess.class);
    }

    public void ClickBlockingUser(View view) {
        //Recreate activity
        recreate();
    }

    public void ClickAppUpdate(View view) {
        //Redirect activity to app update
        HomePageManager.redirectActivity(this, AppUpdate.class);
    }

    public void ClickLogout(View view) {
        //Close app
        HomePageManager.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        HomePageManager.closeDrawer(drawerLayout);
    }
}