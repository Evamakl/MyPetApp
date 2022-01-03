package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class navigation_drawer extends AppCompatActivity {
    //initialize veriables
    DrawerLayout drawerLayout;
    private User user = new User();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        setContentView(R.layout.activity_navigation_drawer);
    //assign variable
        drawerLayout =findViewById(R.id.drawer_layout) ;

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
        intent = new Intent(navigation_drawer.this,ToDoList.class);
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
         intent = new Intent(navigation_drawer.this,tips.class);
         intent.putExtra("user",user);
         startActivity(intent);
       // redirectActivity(this,tips.class);

    }
    public void ClickLogOut(View view){
        //redirect activity to to do list
        logout(this);
    }

    public static void logout(Activity activity) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.signOut();
        }
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

    public static void redirectActivity(Activity activity,Class aClass) {
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
}