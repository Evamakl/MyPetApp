/*package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class vaccines_info extends AppCompatActivity {

    DrawerLayout drawer_layout;
    NavigationView navigation;
    User user = new User();
    Intent intent;
    private TextView VaccineInfo;
    private ImageView MenuItem, BackItem;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("NewsLetter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_info);
        VaccineInfo = findViewById(R.id.info);
        MenuItem = findViewById(R.id.MenuItem);
        BackItem = findViewById(R.id.BackItem);
        drawer_layout = findViewById(R.id.drawer_layout);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                VaccineInfo.setText("");
                for(DataSnapshot temp : snapshot.getChildren()){
                    String text = (String)temp.getValue();
                    VaccineInfo.setText(VaccineInfo.getText().toString()+"\n"+text);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //navigation = findViewById(R.id.NavigationView);

        /*navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(vaccines_info.this,item.getItemId(),user);
                return false;
            }
        });*/
      /*  MenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.open();
            }
        });
        BackItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vaccines_info.this, Start_work.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

    }

}*/