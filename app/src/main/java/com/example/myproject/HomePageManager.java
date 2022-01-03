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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class HomePageManager extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    TextView greetings;
    User user = new User();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_manager);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        greetings = (TextView) findViewById(R.id.greetings);

        //greetings
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);

        if (time >= 0 && time < 12){
            greetings.setText("בוקר טוב, ");
        }
        else if (time >= 12 && time < 16){
            greetings.setText("צהריים טובים, ");
        }
        else if (time >= 16 && time < 21){
            greetings.setText("ערב טוב, ");
        }
        else if (time >= 21 && time < 24){
            greetings.setText("לילה טוב, ");
        }
        else {
            greetings.setText("שלום, ");
        }
        greetings.setText(greetings.getText() + user.getUsername());
    }

    public void ClickMenu(View view) {
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Close drawer Layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        //Recreate activity
        recreate();
    }

    public void ClickReports(View view) {
        //Redirect activity to reports
        redirectActivity(this,Reports.class);
    }

    public void ClickNewsletterUpdate(View view) {
        //Redirect activity to newsletter update
        redirectActivity(this,NewsletterUpdate.class);
    }

    public void ClickCreateFeedbackMess(View view) {
        //Redirect activity to create feedback mess
        HomePageManager.redirectActivity(this, CreateFeedbackMess.class);
    }

    public void ClickBlockingUser(View view) {
        //Redirect activity to blocking user
        HomePageManager.redirectActivity(this, BlockingUser.class);
    }

    public void ClickAppUpdate(View view) {
        //Redirect activity to app update
        HomePageManager.redirectActivity(this, AppUpdate.class);
    }

    public void ClickLogout(View view){
        //Close app
        logout(this);
    }

    public static void logout(Activity activity) {
       FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            firebaseAuth.signOut();
        }
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure you want to logout?");
        //Positive yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });

    }

    public static void redirectActivity(Activity activity,Class aclass) {
        //Initialize intent
        Intent intent = new Intent(activity,aclass);
        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}