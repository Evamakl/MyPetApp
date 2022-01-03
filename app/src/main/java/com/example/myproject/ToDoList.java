package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {
    private AddTodoDialog dialog;
    Button addNewTask;
    private RecyclerView recyclerView;
    private ToDoListAdapter toDoListAdapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");
    DrawerLayout drawerLayout;
    private User user = new User();
    private Intent intent;
    private ArrayList<TodoListClass> list;
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
    }
    public void readTodo() {
        if(user.getListOfTodo().size() >0) {
            for (int i = 0; i < user.getListOfTodo().size(); i++)
                list.add(user.getListOfTodo().get(i));
            toDoListAdapter = new ToDoListAdapter(ToDoList.this, list,user);
            recyclerView.setLayoutManager(new GridLayoutManager(ToDoList.this, 1));
            recyclerView.setAdapter(toDoListAdapter);
        }
    }
    public void goAddNewTask() {
        dialog = new AddTodoDialog(ToDoList.this,"הוספת מטלה חדשה",user);
        dialog.show(getSupportFragmentManager(),"opendialog");
    }

    public void ClickDogList(View view) {
        //redirect activity to reminder
        navigation_drawer.redirectActivity(this, DogList.class);
    }

    public void ClickToDoList(View view) {
        //recreate activity
        recreate();
    }

    public void ClickReminder(View view) {
        //redirect activity to to do list
        navigation_drawer.redirectActivity(this, ToDoList.class);

    }

    public void Clicktips(View view) {
        //redirect activity to information and tips
        navigation_drawer.redirectActivity(this, tips.class);

    }

    public void ClickLogOut(View view) {
        //close app
        navigation_drawer.logout(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        navigation_drawer.closeDrawer(drawerLayout);
    }



}

