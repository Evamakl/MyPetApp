package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseDogPK extends AppCompatActivity {
    EditText Email;
    EditText dogsName;
    Button approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dog_pk);
        Email = (EditText)findViewById(R.id.editEmail);
        dogsName = (EditText)findViewById(R.id.ETdogs_name);
        approve = (Button)findViewById(R.id.approvedBT);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityApprove();
            }
        });
    }
    public void openNewActivityApprove() {
        Intent intent = new Intent(ChooseDogPK.this,DogList.class);
        startActivity(intent);
    }
}