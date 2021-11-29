package com.example.myproject;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class new_user extends AppCompatActivity {

    Button end;
    Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        end = (Button) findViewById(R.id.button_end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                openNewPage();
            }
        });

        goBack = (Button) findViewById(R.id.button_goBack);
        goBack.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                returnBack();
            }
        });

    }

    public void openNewPage() {
        Intent intent = new Intent(this, End.class);
        startActivity(intent);
    }

    public void returnBack() {
       // Intent intent = new Intent(this, Exist_new_frame.class);
      //  startActivity(intent);
    }

}