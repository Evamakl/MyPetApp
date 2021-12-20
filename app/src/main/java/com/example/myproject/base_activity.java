package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class base_activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private String TAG = "base_activity";
    private FirebaseAuth mAuth;
    private String userType;
    MenuItem name;
    MenuItem type;
    MenuItem logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //context=this;
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();
        name = menu.findItem(R.id.name);
        type = menu.findItem(R.id.type);
        logout = menu.findItem(R.id.logout);

        initUserName();
        initUserType();
    }

    private void initUserName() {

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (currentUser != null) {
            db.collection("Users").whereEqualTo("Uid", currentUser.getUid())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (currentUser.getUid().equals(String.valueOf(document.getData().get("Uid")))) {
                                    name.setTitle(String.valueOf(document.getData().get("Name")));
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    });

        } else {
            name.setTitle("Error getting Name");
        }
    }

    private void initUserType() {

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (currentUser != null) {
            db.collection("Users").whereEqualTo("Uid", currentUser.getUid())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                switch (String.valueOf(document.getData().get("UserType"))) {
                                    case "1":
                                        type.setTitle("בעל חיית מחמד");
                                        userType = "1";
                                        break;
                                    case "2":
                                        type.setTitle("PET-KEEPER");
                                        userType = "2";
                                        break;
                                    case "3":
                                        type.setTitle("מנהל");
                                        userType = "3";
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    });

        } else {
            name.setTitle("Error getting Name");
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.personal_file) {
            if (userType.equals("1")) {
                Intent intent = new Intent(this, base_activity/*menu*/.class);
                startActivity(intent);
            }

            /*
            pet Keeper SLide

            if(userType.equals("2"))
            {
                Intent intent = new Intent(this,.class);
                startActivity(intent);
            }
            /*
            Manager File

             else {
                Intent intent = new Intent(this, EmployerProfile.class);
                startActivity(intent);
            }
           */
        } else if (id == R.id.personal_file) {
            Intent intent = new Intent(this, Personal_File.class);
            startActivity(intent);

        }
        /*else if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);

        } else if (id == R.id.logout) {
            SingOut();

        } else if (id == R.id.edit) {
            Intent intent = new Intent(this, EditProfile.class);
            startActivity(intent);

        } else if (id == R.id.git) {
            String url = "https://github.com/0xAlon/GetJob";
            Intent browserIntent =
                    new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }


        //drawer.closeDrawer(GravityCompat.START);
            return true;
        }


        public void SingOut () {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

*/


   return true; }
}

