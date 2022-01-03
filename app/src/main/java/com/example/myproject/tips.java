package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

   
 
    private ArrayList<tipsPKClass> listTips;

    NavigationView navigation;
    ImageView MenuIcon,BackIcon;
    User user = new User();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        listTips = new ArrayList<>();
       // drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.RecyclerView);
        addPKtip = findViewById(R.id.PKtip);
        readTodo();
        PKtipButton();
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

        //assign variable
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        //assign variable
        drawerLayout =findViewById(R.id.drawer_layout) ;
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                new PetKeeperNavigation(tips.this,item.getItemId(),user);
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
                intent = new Intent(tips.this, navigation_drawer.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }

}

