package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reliability_form extends AppCompatActivity {
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliability_form);
        next = (Button)findViewById(R.id.nextBT);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityNext();
            }


        });

    }
    public void openNewActivityNext(){
        Intent intent = new Intent(reliability_form.this,ChooseDogPK.class);
        startActivity(intent);

    }
}