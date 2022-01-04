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
        navigation = findViewById(R.id.NavigationView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                new PetKeeperNavigation(AppUpdate.this,item.getItemId(),user);
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

