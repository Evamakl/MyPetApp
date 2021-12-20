package com.example.myproject;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class personal_information_center extends AppCompatActivity {


    Button tips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_infomation_center);

        tips = (Button) findViewById(R.id.button_tips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewPage();
            }
        });


    }


    public void openNewPage() {
        Intent intent = new Intent(this, tips.class);
        startActivity(intent);
    }
}


