package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

class add_tips extends AppCompatActivity {

    Button getInWriteTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tip);
        getInWriteTip = (Button) findViewById(R.id.button_getInWriteTip);
        getInWriteTip.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                goWriteTip();
            }

        });
    }
    public void goWriteTip() {
        Intent intent = new Intent(this, write_tip.class);
        startActivity(intent);
    }
}