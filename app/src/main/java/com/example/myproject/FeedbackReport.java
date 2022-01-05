package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedbackReport extends AppCompatActivity {

    DrawerLayout drawer_layout;
    NavigationView navigation;
    private User user = new User();
    private DrawerLayout chooseDog_layout;
    private ImageView MenuIcon,BackIcon;
    private Intent intent;
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;

    BarChart barChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("UsersRatings");
    int oneStar=0, twoStar=0, threeStar=0, fourStar=0, fiveStar=0, NumberOfStars = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_report);
        drawer_layout = findViewById(R.id.drawer_layout);
        barChart = findViewById(R.id.bar_chart);
        //initialize array list
        GetUsersRatings();
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        if(user.getType().toString().equals("PetKeeper")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.pet_keeper_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
        else if(user.getType().toString().equals("Owner")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.base_activity, menu);
            super.onCreateOptionsMenu(menu);
        }
        else if(user.getType().toString().equals("Manager")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.manager_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackReport.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        NavigationView.setNavigationItemSelectedListener(new com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                if(user.getType().toString().equals("Manager"))
                    new ManagerNavigation(FeedbackReport.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(FeedbackReport.this,id,user);
                else
                    new OwnerNavigation(FeedbackReport.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }
    }

    public void GetUsersRatings(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot temp : snapshot.getChildren()){
                    String type = (String) temp.child("UsersRatings").getValue();
                    if(type.equals("1"))
                        oneStar++;
                    else if(type.equals("2"))
                        twoStar++;
                    else if(type.equals("3"))
                        threeStar++;
                    else if(type.equals("4"))
                        fourStar++;
                    else if(type.equals("5"))
                        fiveStar++;
                }
                SetChart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void SetChart(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        //Add one star statistic
        //initialize bar chart entry
        BarEntry barEntry = new BarEntry(0,oneStar);
        //add value in array list
        barEntries.add(barEntry);
        //Add two star statistic
        //initialize bar chart entry
        barEntry = new BarEntry(1,twoStar);
        //add value in array list
        barEntries.add(barEntry);
        //Add three star statistic
        //initialize bar chart entry
        barEntry = new BarEntry(2,threeStar);
        //add value in array list
        barEntries.add(barEntry);
        //Add four star statistic
        //initialize bar chart entry
        barEntry = new BarEntry(3,fourStar);
        //add value in array list
        barEntries.add(barEntry);
        //Add five star statistic
        //initialize bar chart entry
        barEntry = new BarEntry(4,fiveStar);
        //add value in array list
        barEntries.add(barEntry);
        BarDataSet barDataSet = new BarDataSet(barEntries, "Ratings(1,2,3,4,5)");
        //set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //hide draw value
        barDataSet.setDrawValues(false);
        //set bar data
        barChart.setData(new BarData(barDataSet));
        //set animation
        barChart.animateY(5000);
        //set description text and color
        barChart.getDescription().setText("Users Ratings Chart");
        barChart.getDescription().setTextColor(Color.BLUE);
    }




}