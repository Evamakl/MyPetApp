package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import java.io.IOException;

public class AppUpdate extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;
    private ImageView MenuIcon, BackIcon;
    NavigationView navigation;
    Intent intent;
    User user = new User();
    TextView tvCurrentVersion, tvLatestVersion;
    String sCurrentVersion, sLatestVersion;
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_update);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        tvCurrentVersion = findViewById(R.id.tv_current_version);
        tvLatestVersion = findViewById(R.id.tv_latest_version);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        if(user.getType().toString().equals("PetKeeper")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.pet_keeper_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
        else if(user.getType().toString().equals("Owner")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.base_activity, menu);
            super.onCreateOptionsMenu(menu);
        }
        else if(user.getType().toString().equals("Manager")) {
            menu.clear();
            new MenuInflater(this).inflate(R.menu.manager_menu, menu);
            super.onCreateOptionsMenu(menu);
        }
        menu.findItem(R.id.FullName_item).setTitle( "שלום, " + user.getUsername());
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppUpdate.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        NavigationView.setNavigationItemSelectedListener(new com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();
                if(user.getType().toString().equals("Manager"))
                    new ManagerNavigation(AppUpdate.this,id,user);
                else if(user.getType().toString().equals("PetKeeper"))
                    new PetKeeperNavigation(AppUpdate.this,id,user);
                else
                    new OwnerNavigation(AppUpdate.this,id,user,item);
                return false;
            }
        });
        if(user.getType().toString().equals("Owner")) {
            menu = NavigationView.getMenu();
            for (int i = 0; i < user.getDogs().size(); i++)
                menu.findItem(R.id.Dogs).getSubMenu().add(user.getDogs().get(i).getName());
        }

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
                intent = new Intent(AppUpdate.this, HomePageManager.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });

        //Get latest version from play store
        new GetLatestVersion().execute();
   }


   private class GetLatestVersion extends AsyncTask<String,Void,String> {

       @Override
       protected String doInBackground(String... strings) {
           try {
               sLatestVersion = Jsoup
                       .connect("https://play.google.com/store/apps/details?id="
                       + getPackageName())
                       .timeout(30000)
                       .get()
                       .select("div.hAyfc:nth-child(4)>"+
                               "span:nth-child(2) > div:nth-child(1)"+
                               "> span:nth-child(1)")
                       .first()
                       .ownText();
           } catch (IOException e) {
               e.printStackTrace();
           }
           return sLatestVersion;
       }

       @Override
       protected void onPostExecute(String s) {
           //get current version
           sCurrentVersion = BuildConfig.VERSION_NAME;
           //set current version on TextView
           tvCurrentVersion.setText(sCurrentVersion);
           //set latest version on TextView
           tvLatestVersion.setText(sLatestVersion);

           if (sLatestVersion != null) {
               //Version convert to float
               float cVersion = Float.parseFloat(sCurrentVersion);
               float lVersion = Float.parseFloat(sLatestVersion);
               //Check condition(latest version is greater than current version)
               if (lVersion > cVersion) {
                   //Create update AlertDialog
                   updateAlarteDialog();
               }
           }
       }
   }

    private void updateAlarteDialog() {
        //Initialize AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set title
        builder.setTitle(getResources().getString(R.string.app_name));
        //Set message
        builder.setMessage("Update Available");
        //Set non cancelable
        builder.setCancelable(false);

        //On update
        builder.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Open play store
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + getPackageName())));
                //Dismiss AlertDialog
                dialog.dismiss();
            }
        });

        //On cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancel AlertDialog
                dialog.cancel();
            }
        });

        //Show AlertDialog
        builder.show();
    }

}

