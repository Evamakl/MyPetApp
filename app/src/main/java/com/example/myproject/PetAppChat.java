package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import androidx.viewpager.widget.ViewPager;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class PetAppChat extends AppCompatActivity {
        private User user = new User();
        private TextView Title, TextViewSearchLanguage;
        private ImageView BackIcon, MenuIcon;
        private TabLayout tabLayout;
        private ViewPager viewPager;
        private ViewPagerAdapter viewPagerAdapter;
        private androidx.drawerlayout.widget.DrawerLayout drawerLayout;
        private NavigationView UserNavigationView;
        private Intent intent;
        private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetapp_chat);
        init();
    }
    private void init(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ChatFragment(), "Chats");
        viewPagerAdapter.addFragment(new UsersFragment(), "Users");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}