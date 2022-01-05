package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
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
    BarChart barChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("UsersRatings");
    long oneStar=0, twoStar=0, threeStar=0, fourStar=0, fiveStar=0, NumberOfStars = 5;
    private DrawerLayout drawerLayout;
    private NavigationView NavigationView;
    private Menu menu;
    private ImageView MenuIcon, BackIcon;
    private User user;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_report);
        barChart = findViewById(R.id.bar_chart);
        //initialize array list
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        GetUsersRatings();
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
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
                intent = new Intent(FeedbackReport.this, Reports.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        NavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {

                new ManagerNavigation(FeedbackReport.this, item.getItemId(), user);
                return false;
            }
        });
    }

    public void GetUsersRatings(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                oneStar = (long)snapshot.child("1").getValue();
                twoStar = (long)snapshot.child("2").getValue();
                threeStar = (long)snapshot.child("3").getValue();
                fourStar = (long)snapshot.child("4").getValue();
                fiveStar = (long)snapshot.child("5").getValue();
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
        barChart.animateY(300);

    }




}