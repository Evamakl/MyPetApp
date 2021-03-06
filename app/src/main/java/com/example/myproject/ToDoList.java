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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ValueEventListener;


public class ToDoList extends AppCompatActivity {
    private AddTodoDialog dialog;
    Button addNewTask;
    private RecyclerView recyclerView;
    private ToDoListAdapter toDoListAdapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");
    DrawerLayout drawerLayout;


    /*private User user = new User();
    private Intent intent;*/
    private ArrayList<TodoListClass> list;

    NavigationView navigation;
    ImageView MenuIcon,BackIcon;
    User user = new User();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        list = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.RecyclerView);
        addNewTask = (Button) findViewById(R.id.button_addNewTask);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAddNewTask();

            }
        });
        readTodo();
        //assign variable
        drawerLayout =findViewById(R.id.drawer_layout) ;
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                new PetKeeperNavigation(ToDoList.this,item.getItemId(),user);
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
                intent = new Intent(ToDoList.this, navigation_drawer.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
    }
    public void readTodo() {
        databaseReference.child(user.getUid()).child("listOfTodo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for( DataSnapshot temp : snapshot.getChildren()){
                    TodoListClass todo = temp.getValue(TodoListClass.class);
                    list.add(todo);
                }
                toDoListAdapter = new ToDoListAdapter(ToDoList.this,list,user);
                recyclerView.setLayoutManager(new GridLayoutManager(ToDoList.this, 1));
                recyclerView.setAdapter(toDoListAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void goAddNewTask() {
        dialog = new AddTodoDialog(ToDoList.this,"?????????? ???????? ????????",user);
        dialog.show(getSupportFragmentManager(),"opendialog");
    }

}










