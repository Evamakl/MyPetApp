package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class PetAppChat extends AppCompatActivity {
        private User user = new User();
        private TextView Title, TextViewSearchLanguage;
        private ImageView MenuItem, BackItem;
        private TabLayout tabLayout;
        private ViewPager viewPager;
        private ViewPagerAdapter viewPagerAdapter;
        private androidx.drawerlayout.widget.DrawerLayout drawerLayout;
        private NavigationView UserNavigationView;
        private Intent intent;
        private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        private NavigationView NavigationView;
        private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetapp_chat);
        init();
    }

    private void init(){
        setID();
        MenuIcon();
        BackIcon();
        NavigationView();
    }
    private void setID(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ChatFragment(), "Chats");
        viewPagerAdapter.addFragment(new UsersFragment(), "Users");
        viewPager.setAdapter(viewPagerAdapter);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView = findViewById(R.id.NavigationView);
        NavigationView.getMenu().clear();
        if(user.getType().equals("Manager"))
            NavigationView.inflateMenu(R.menu.manager_menu);
        else if(user.getType().equals("PetKeeper"))
            NavigationView.inflateMenu(R.menu.pet_keeper_menu);
       else
            NavigationView.inflateMenu(R.menu.base_activity);
        menu = NavigationView.getMenu();
        tabLayout.setupWithViewPager(viewPager);
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
                Intent intent = new Intent(PetAppChat.this, navigation_drawer.class);
                if(user.getType().equals("Owner"))
                    intent = new Intent(PetAppChat.this, Start_work.class);
                else if(user.getType().equals("Manager"))
                    intent = new Intent(PetAppChat.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
                /*Intent intent = new Intent(PetAppChat.this, Exist_new_frame.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();*/
            }
        });
    }
    public void NavigationView() {
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                if(user.getType().equals("Manager"))
                    new ManagerNavigation(PetAppChat.this, item.getItemId(), user);
                else if(user.getType().equals("PetKeeper"))
                    new PetKeeperNavigation(PetAppChat.this, item.getItemId(), user);
                else
                    new OwnerNavigation(PetAppChat.this, item.getItemId(), user,item);

                return false;
            }
        });
    }
}