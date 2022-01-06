package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class vaccines_info_2 extends AppCompatActivity {
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_info2);

        back = (Button) findViewById(R.id.backbt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityBack();
            }
        });
    }
    public void openNewActivityBack() {
        Intent intent = new Intent(this, search_page.class);
        startActivity(intent);
    }
}