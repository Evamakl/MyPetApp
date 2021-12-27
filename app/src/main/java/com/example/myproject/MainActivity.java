package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import org.jsoup.Jsoup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User("s","000","sss","sds");
        Intent intent = new Intent(this,Start_work.class);
        intent.putExtra("user",user);
        startActivity(intent);

    }

/*}*/


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            openNewActivity();
        }
    }

    private void openNewActivity() {

        Intent intent = new Intent(this,firstframe.class);
        startActivity(intent);
    }

}

