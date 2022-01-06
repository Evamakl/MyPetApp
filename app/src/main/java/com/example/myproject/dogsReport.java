package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Calendar;
import java.util.Date;

public class dogsReport extends AppCompatActivity {
    private DrawerLayout drawer_layout;
    //private NavigationView NavigationView;
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;
    private ImageView MenuIcon, BackIcon;
    private User user;
    private Intent intent;
    TextView TitleReport;
    BarChart barChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    int NumberOfDogs = 0;
    private EditText EditTextSearch;
    private TextView TextViewSearch;
    private Dialog dialog;
    private ListView ListViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_report);
        //menu and back from here
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        drawer_layout = findViewById(R.id.drawer_layout);
        NavigationView = findViewById(R.id.NavigationView);
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
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
                Intent intent = new Intent(dogsReport.this, Reports.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        //to here
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

        TitleReport = findViewById(R.id.TitleReport);
        TitleReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(dogsReport.this);
                dialog.setContentView(R.layout.dialog_search_spinner);
                dialog.getWindow().setLayout(800,1500);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditTextSearch = dialog.findViewById(R.id.EditTextSearch);
                ListViewSearch = dialog.findViewById(R.id.ListViewSearch);
                TextViewSearch = dialog.findViewById(R.id.TextViewSearch);
                TextViewSearch.setText("בחר שנה");
                ArrayList<String> years = new ArrayList<>();
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                int year = calendar.get(java.util.Calendar.YEAR);
                for(int i=0;i<year-1990;i++)
                    years.add((year-i)+"");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(dogsReport.this, R.layout.dropdown_item, years);
                ListViewSearch.setAdapter(adapter);
                EditTextSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { adapter.getFilter().filter(charSequence); }
                    @Override
                    public void afterTextChanged(Editable editable) { }
                });
                ListViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        dialog.dismiss();
                        TitleReport.setText(adapterView.getItemAtPosition(i).toString());
                        getNumberOfDogs(Integer.valueOf(TitleReport.getText().toString()));
                    }
                });
            }
        });
        barChart = findViewById(R.id.bar_chart);
    }
    public void getNumberOfDogs(int year) {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                for(int k=0;k<12;k++){
                    NumberOfDogs = 0;
                    int month = k+1;
                    for(DataSnapshot temp : snapshot.getChildren()){
                        User getUser = (User) temp.getValue(User.class);
                        for(int i=0; i < getUser.getDogs().size();i++){
                            String Date = getUser.getDogs().get(i).getAddedDate().toString();
                            String[] values = Date.split("/");
                            if(Integer.valueOf(values[1]) == month && Integer.valueOf(values[2]) == year) {
                                NumberOfDogs++;
                            }
                        }
                        BarEntry barEntry = new BarEntry(month,NumberOfDogs);
                        barEntries.add(barEntry);
                        BarDataSet barDataSet = new BarDataSet(barEntries, "Months ");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        barDataSet.setDrawValues(false);
                        barChart.setData(new BarData(barDataSet));
                        barChart.animateY(300);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}