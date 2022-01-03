package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class technicalSupport extends AppCompatActivity {
    private AddTipDialog dialog;
    private int size =0;
    Button addNewtip,remove_tip;
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
        setContentView(R.layout.activity_technical_support2);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        list = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.RecyclerView);
        addNewtip = (Button) findViewById(R.id.button_addNewTask);
        if(!user.getType().equals("Manager")){
            addNewtip.setVisibility(View.INVISIBLE);
        }
        else{
            addNewtip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goAddNewTask();
                }
            });
        }
        readTodo();
    }
    public void ClickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);

    }

    private static void openDrawer(DrawerLayout drawerLayout) {

        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout){
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //WHEN DRAWER IS OPEN
            //CLOSE DRAWER
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        //recreate activity
        recreate();
    }

    public void ClickDogList(View view){
        //redirect activity to dog list
        redirectActivity(this,DogList.class);
    }

    public void ClickToDoList(View view){
        //redirect activity to to do list
        // redirectActivity(this,ToDoList.class);
        intent = new Intent(technicalSupport.this,ToDoList.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

    public void ClickReminder(View view){
        //redirect activity to reminder
        redirectActivity(this,Reminder.class);

    }
    public void Clicktips(View view){
        //redirect activity to information and tips
        redirectActivity(this,tips.class);

    }
    public void ClickLogOut(View view){
        //redirect activity to to do list
        logout(this);
    }
    public static void logout(Activity activity) {
        //initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("LogOut");
        //set message
        builder.setMessage("Are you sure you want to logout ?");
        //Positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish activity
                activity.finishAffinity();
                //exit app
                System.exit(0);
            }
        });
        //negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //show dialog
        builder.show();


    }
    public static void redirectActivity(Activity activity, Class aClass) {
        //initialize intent
        Intent intent = new Intent(activity,aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //stat activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }
    public void readTodo() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()) {
                    String temp = (String)s.getValue();
                    if(!list.contains(temp))
                        list.add(temp);
                }
                managerTipsAdapter = new ManagerTipsAdapter(technicalSupport.this,list,user);
                recyclerView.setLayoutManager(new GridLayoutManager(technicalSupport.this, 1));
                recyclerView.setAdapter(managerTipsAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void goAddNewTask() {
        dialog = new AddTipDialog(technicalSupport.this,"הוספת טיפ חדש",user,size);
        dialog.show(getSupportFragmentManager(),"opendialog");
    }
    public void goCreateTechnicalSupport() {
       // Intent intent = new Intent(this, creare_technical_support_page.class);
        //startActivity(intent);
    }
    public void goEditExistingPage() {
        //Intent intent = new Intent(this, editExsitingPage.class);
        //startActivity(intent);
    }
}