package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exist_new_frame extends AppCompatActivity {
    Button New;
    Button exist;
    Button prev;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist_new_frame);
        New = (Button) findViewById(R.id.btNew);
        exist = (Button) findViewById(R.id.btExist);
        prev = (Button) findViewById(R.id.btprev);
        display = (TextView) findViewById(R.id.txtdisplay);
        New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityNew();
            }
        });
        exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityExist();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityPrev();
            }
        });
    }
   public void openNewActivityNew(){
        Intent intent = new Intent(this,new_user.class);
        startActivity(intent);
    }

      public void openNewActivityExist() {
           Intent intent = new Intent(this, loginExistFrame.class);
           startActivity(intent);
       }

      public void openNewActivityPrev() {
               Intent intent = new Intent(this, firstframe.class);
               startActivity(intent);

           }


}




