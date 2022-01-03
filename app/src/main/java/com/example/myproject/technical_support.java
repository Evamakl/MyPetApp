package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class technical_support extends AppCompatActivity {
    private AddTodoDialog dialog;
    Button addNewtip, remove_tip;
    private RecyclerView recyclerView;
    private ManagerTipsAdapter managerTipsAdapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("ManagerTips");
    DrawerLayout drawerLayout;
    private User user = new User();
    private Intent intent;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_support);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        list = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.RecyclerView);
        addNewtip = (Button) findViewById(R.id.button_addNewTask);
        remove_tip = (Button) findViewById(R.id.finish_task);
        addNewtip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // goAddNewTask();
            }
        });
        readTodo();
    }
    public void readTodo() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    list.add(s.getValue().toString());
                }
                managerTipsAdapter = new ManagerTipsAdapter(technical_support.this,list,user);
                recyclerView.setLayoutManager(new GridLayoutManager(technical_support.this, 1));
                recyclerView.setAdapter(managerTipsAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void goAddNewTask() {
        //dialog = new AddTodoDialog(technical_support.this,"הוספת מטלה חדשה",user);
       // dialog.show(getSupportFragmentManager(),"opendialog");
    }
    public void goCreateTechnicalSupport() {
      //  Intent intent = new Intent(this, creare_technical_support_page.class);
       // startActivity(intent);
    }
    public void goEditExistingPage() {
        //Intent intent = new Intent(this, editExsitingPage.class);
        //startActivity(intent);
    }
}