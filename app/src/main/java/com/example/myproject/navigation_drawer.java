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
import android.widget.ImageView;
import android.widget.TextView;

public class navigation_drawer extends AppCompatActivity {
    //initialize veriables
    DrawerLayout drawerLayout;
    ImageView MenuIcon,BackIcon;
    User user = new User();
    Intent intent;
    TextView UserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        //assign variable
        drawerLayout =findViewById(R.id.drawer_layout) ;
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        UserInfo = findViewById(R.id.UserInfo);
        UserInfo.setText("שלום, "+ user.getUsername() );
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(navigation_drawer.this);
            }
        });
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
        redirectActivity(this,ToDoList.class);

    }

    public void ClickReminder(View view){
        //redirect activity to reminder
        //Intent intent1 = new Intent(navigation_drawer.this,Calendar.class);
        //intent1.putExtra("user",user);
        //startActivity(intent1);
      redirectActivity(this,Calendar.class);

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
                //activity.finishAffinity();
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
        activity.finishAffinity();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }
}