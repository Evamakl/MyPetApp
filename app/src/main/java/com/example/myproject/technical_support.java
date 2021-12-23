package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

class technical_support extends AppCompatActivity {

    Button newPage;
    Button existingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_support);
        newPage = (Button) findViewById(R.id.button_newPage);
        newPage.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                goCreateTechnicalSupport();
            }

        });
        existingPage = (Button) findViewById(R.id.button_existingPage);
        existingPage.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                goEditExistingPage();
            }

        });
    }
    public void goCreateTechnicalSupport() {
        Intent intent = new Intent(this, creare_technical_support_page.class);
        startActivity(intent);
    }
    public void goEditExistingPage() {
        //Intent intent = new Intent(this, editExsitingPage.class);
        //startActivity(intent);
    }
}