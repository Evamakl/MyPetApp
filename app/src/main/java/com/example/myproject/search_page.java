package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class search_page extends AppCompatActivity {
    TextView first_title;
    TextView second_title;
    Button vaccin_button;
    Button food_button;
    Button shampo_button;
    Button walk_button;
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        first_title = (TextView) findViewById(R.id.txtdisplay);
        second_title = (TextView) findViewById(R.id.txtdisplay);
        vaccin_button = (Button) findViewById(R.id.vaccin_button);
        food_button = (Button) findViewById(R.id.food_button);
        shampo_button = (Button) findViewById(R.id.shampo_button);
        walk_button = (Button) findViewById(R.id.walk_button);
        back_button = (Button) findViewById(R.id.back_button);
        vaccin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityvaccin_button();
            }
        });
        food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityfood_button();
            }
        });
        shampo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityshampo_button();
            }
        });
        walk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivitywalk_button();
            }

        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewActivityback_button();
            }
        });
    }

    public void openNewActivityvaccin_button() {
            Intent intent = new Intent(this, vaccines_info.class);
            startActivity(intent);
    }
    public void openNewActivityfood_button() {
        Intent intent = new Intent(this, food_info.class);
        startActivity(intent);
    }
    public void openNewActivityshampo_button() {
        Intent intent = new Intent(this, shampo_info.class);
        startActivity(intent);
    }
    public void openNewActivitywalk_button() {
        Intent intent = new Intent(this, walk_info.class);
        startActivity(intent);
    }
    public void openNewActivityback_button() {
        //Intent intent = new Intent(this, new_user.class);
        //startActivity(intent);
    }

}


/*

 */