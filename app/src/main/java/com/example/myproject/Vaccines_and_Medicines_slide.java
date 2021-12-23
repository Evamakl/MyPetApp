package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vaccines_and_Medicines_slide extends AppCompatActivity {
    Button vac;
    Button med;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccines_and_medicines_slide);


        vac = (Button) findViewById(R.id.Vaccines);
        vac.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityVaccines();
            }

        });
        med = (Button) findViewById(R.id.Medicines);
        med.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityMediccines();
            }

        });
    }



    public void openNewActivityMediccines (){
      //  Intent intent = new Intent(this, /* שם של הLAYOUT של תרופות */.class);
       // startActivity(intent);
    }

    public void openNewActivityVaccines(){
        Intent intent = new Intent(this,Vaccines.class);
        startActivity(intent);
    }

}