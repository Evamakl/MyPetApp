package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UsersReport extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private ImageView MenuIcon, BackIcon;
    NavigationView navigation;
    Intent intent;
    User user = new User();
    BarChart barChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    int petKeeper=0, owner=0, NumberOfUsers = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_report);

        drawerLayout = findViewById(R.id.drawer_layout);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new ManagerNavigation(UsersReport.this,item.getItemId(),user);
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
                intent = new Intent(UsersReport.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        barChart = findViewById(R.id.bar_chart);

        //initialize array list
        GetUserStatistic();
    }
    public void GetUserStatistic(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot temp : snapshot.getChildren()){
                    String type = (String) temp.child("type").getValue();
                    if(type.equals("PetKeeper"))
                        petKeeper++;
                    else if(type.equals("Owner"))
                        owner++;
                }
                SetChart();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void SetChart(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        //Add Owner statistic
        //initialize bar chart entry
        BarEntry barEntry = new BarEntry(0,owner);
        //add value in array list
        barEntries.add(barEntry);
        //Add PetKeeper statistic
        //initialize bar chart entry
        barEntry = new BarEntry(1,petKeeper);
        //add value in array list
        barEntries.add(barEntry);
        //initialize bar data set
        BarDataSet barDataSet = new BarDataSet(barEntries, "Users(Owner, PetKeeper)");
        //set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //hide draw value
        barDataSet.setDrawValues(false);
        //set bar data
        barChart.setData(new BarData(barDataSet));
        //set animation
        barChart.animateY(5000);
        //set description text and color
        barChart.getDescription().setText("Users Type Chart");
        barChart.getDescription().setTextColor(Color.BLUE);
    }

}