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

public class dogsReport extends AppCompatActivity {

    DrawerLayout drawer_layout;
    private ImageView MenuIcon, BackIcon;
    NavigationView navigation;
    BarChart barChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    int NumberOfDogs = 0;
    private User user = new User();
    private Intent intent;
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_report);

        drawer_layout = findViewById(R.id.drawer_layout);
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
                Intent intent = new Intent(dogsReport.this, HomePageManager.class);
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
                    new ManagerNavigation(dogsReport.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(dogsReport.this,id,user);
                else
                    new OwnerNavigation(dogsReport.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }

        barChart = findViewById(R.id.bar_chart);

        //initialize array list
        GetUserStatistic();
    }
    public void GetUserStatistic(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int dogNumber = 0;
                for(DataSnapshot temp : snapshot.getChildren()){
                    User getUser = (User) temp.getValue(User.class);
                    for(int i=0; i < getUser.getDogs().size();i++){
                        NumberOfDogs++;
                    }
                }
                SetChart();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void SetChart(){
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        //Add dogs statistic
        //initialize bar chart entry
        BarEntry barEntry = new BarEntry(0,NumberOfDogs);
        //add value in array list
        barEntries.add(barEntry);
        //initialize bar data set
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dogs");
        //set colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //hide draw value
        barDataSet.setDrawValues(false);
        //set bar data
        barChart.setData(new BarData(barDataSet));
        //set animation
        barChart.animateY(5000);
        //set description text and color
        barChart.getDescription().setText("Dogs Type Chart");
        barChart.getDescription().setTextColor(Color.BLUE);
    }
}