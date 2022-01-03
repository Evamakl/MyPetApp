package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class NewsletterUpdate extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    Button addButton;
    ArrayList<String> list;
    RecyclerView recyclerView;
    TextInputLayout newsText;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("NewsLetter");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter_update);
        list = new ArrayList<>();
        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
        addButton=findViewById(R.id.addButton);
        newsText=findViewById(R.id.newsText);
        recyclerView = findViewById(R.id.newsletterRV);
        getNewsLetter();
        AddButton();
    }
    public void getNewsLetter(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for( DataSnapshot temp : snapshot.getChildren()){
                    String text = (String)temp.getValue();
                    if(!list.contains(text))
                        list.add(text);
                }
                setAdapter();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    public void setAdapter(){
        NewsLetterAdapter newsLetterAdapter = new NewsLetterAdapter(NewsletterUpdate.this,list);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(newsLetterAdapter);
    }
    public void AddButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsText.getEditText().getText().length() >0){
                    Random random = new Random();
                    reference.child(random.nextInt(999999999)+ "").setValue(newsText.getEditText().getText().toString());
                    Toast.makeText(NewsletterUpdate.this, "text added", Toast.LENGTH_SHORT).show();
                    newsText.getEditText().setText("");

                }
                else{
                    Toast.makeText(NewsletterUpdate.this, "Need to add text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void ClickMenu(View view) {
        //Open drawer
        HomePageManager.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        //Close drawer
        HomePageManager.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        //Redirect activity to home
        HomePageManager.redirectActivity(this,HomePageManager.class);
    }

    public void ClickReports(View view) {
        //Redirect activity to reports
        HomePageManager.redirectActivity(this, Reports.class);
    }

    public void ClickNewsletterUpdate(View view) {
        //Recreate activity
        recreate();
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

    public void ClickLogout(View view) {
        //Close app
        HomePageManager.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        HomePageManager.closeDrawer(drawerLayout);
    }
}