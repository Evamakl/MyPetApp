package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class Start_work extends base_activity {
    ImageButton ToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);
        ToolBar= (ImageButton) findViewById(R.id.TOOLBAR);
        ToolBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {openNewActivitybaseB();
            }
        });

    }
    public void openNewActivitybaseB (){
        Intent intent = new Intent(this, base_activity.class);
        startActivity(intent);
    }
}

