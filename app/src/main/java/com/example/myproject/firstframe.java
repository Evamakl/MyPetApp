package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class firstframe extends AppCompatActivity {

        Button button_pet_keeper;
        Button button_Pet;
        Button Boss_button;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_firstframe);
            button_pet_keeper = (Button) findViewById(R.id.Pet_Keeper_Button);
            button_pet_keeper.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    // openNewActivity();
                }

            });
            button_Pet = (Button) findViewById(R.id.Pet_Button);
            button_Pet.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    // openNewActivity();
                }

            });
            Boss_button=(Button) findViewById(R.id.Boss_Button);
            Boss_button.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    //openNewActivity();
                }

            });
        }

        public void openNewActivity(){
            //Intent intent = new Intent(this, NewActivity.class);
           // startActivity(intent);
        }

    }
