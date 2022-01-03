package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;

public class navigation_drawer extends AppCompatActivity {
    //initialize veriables
    DrawerLayout drawerLayout;

    NavigationView navigation;
    ImageView MenuIcon,BackIcon;
    User user = new User();
    Intent intent;
    TextView UserInfo;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        setContentView(R.layout.activity_navigation_drawer);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        //assign variable
        drawerLayout =findViewById(R.id.drawer_layout) ;


    /*public void ClickToDoList(View view){
        //redirect activity to to do list
       // redirectActivity(this,ToDoList.class);
        intent = new Intent(navigation_drawer.this,ToDoList.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }*/

    
    /*public void Clicktips(View view){
        //redirect activity to information and tips
         intent = new Intent(navigation_drawer.this,tips.class);
         intent.putExtra("user",user);
         startActivity(intent);
       // redirectActivity(this,tips.class);

    }*/
    

    
  
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                new PetKeeperNavigation(navigation_drawer.this,item.getItemId(),user);
                return false;
            }
        });
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

                //initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(navigation_drawer.this);
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
                        if(firebaseAuth.getCurrentUser() != null)
                            firebaseAuth.signOut();
                        startActivity(new Intent(navigation_drawer.this, firstframe.class));
                        finish();
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
                builder.show();            }
        });
    }
}