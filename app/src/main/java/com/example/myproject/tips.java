package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;


public class tips extends AppCompatActivity {
    private AddPKTipDialog dialog;
    Button addPKtip;
    private RecyclerView recyclerView;
    private TipsPKAdapter tipsPKAdapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Tips");
    DrawerLayout drawerLayout;
    private User user = new User();
    private ImageView MenuIcon,BackIcon;
    private Intent intent;
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;
    private ArrayList<tipsPKClass> listTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        listTips = new ArrayList<>();
        recyclerView = findViewById(R.id.RecyclerView);
        addPKtip = findViewById(R.id.PKtip);
        readTodo();
        PKtipButton();
        //assign variable
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        //assign variable
        drawerLayout =findViewById(R.id.drawer_layout) ;
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
                drawerLayout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tips.this, navigation_drawer.class);
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
                    new ManagerNavigation(tips.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(tips.this,id,user);
                else
                    new OwnerNavigation(tips.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }
    }
    public void PKtipButton(){
        addPKtip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAddNewPKtip();
            }
        });
    }
    public void readTodo() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot temp : snapshot.getChildren()){
                    tipsPKClass tip = (tipsPKClass)temp.getValue(tipsPKClass.class);
                    if(!listTips.contains(tip))
                        listTips.add(tip);
                }
                tipsPKAdapter = new TipsPKAdapter(tips.this, listTips,user);
                recyclerView.setLayoutManager(new GridLayoutManager(tips.this, 1));
                recyclerView.setAdapter(tipsPKAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void goAddNewPKtip() {
        dialog = new AddPKTipDialog(tips.this,"הוסף טיפ חדש",user);
        dialog.show(getSupportFragmentManager(),"opendialog");
    }

   

}






