package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class vaccines_info extends AppCompatActivity {

    Button cont;
    Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_info);
        cont = (Button) findViewById(R.id.continuebt);
        back = (Button) findViewById(R.id.backbt);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityCon();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityBack();
            }
        });
    }
    public void openNewActivityCon() {
        Intent intent = new Intent(this, vaccines_info_2.class);
        startActivity(intent);
    }
    public void openNewActivityBack() {
        Intent intent = new Intent(this, search_page.class);
        startActivity(intent);
    }
}