package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PetOwnerOptionsOfDog extends AppCompatActivity {
    Button Personal_Dog_File;
    Button Med_dog_file;
    Button Med_dog_information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner_options_of_dog);
        Personal_Dog_File = (Button) findViewById(R.id.Personal_F);
        Personal_Dog_File.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityPersonalDogFile();
            }

        });
        Med_dog_file = (Button) findViewById(R.id.Med_F);
        Med_dog_file.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityMedDogFile();
            }

        });
        Med_dog_information=(Button) findViewById(R.id.Med_Info);
        Med_dog_information.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewActivityMedDogInformation();
            }

        });
    }

    public void openNewActivityPersonalDogFile (){
        Intent intent = new Intent(this, Personal_File.class);
        startActivity(intent);
    }

    public void openNewActivityMedDogFile(){
        Intent intent = new Intent(this, Vaccines_and_Medicines_slide.class);
        startActivity(intent);
    }

    public void openNewActivityMedDogInformation(){
        //Intent intent = new Intent(this,.class);
        //startActivity(intent);
    }
    }

