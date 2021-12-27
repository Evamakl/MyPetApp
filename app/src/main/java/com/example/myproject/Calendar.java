package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calendar extends AppCompatActivity {
    EditText title;
    EditText location;
    EditText description;
    Button saveEvent;
    Intent intent;
    User User=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        intent = getIntent();
        User=(User)intent.getSerializableExtra("user");
        title = findViewById(R.id.titleEt);
        location = findViewById(R.id.locationEt);
        description = findViewById(R.id.descriptionEt);
        saveEvent = findViewById(R.id.saveEventbt);
        saveEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                && !description.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com, test2@yahoo.com, test3@yahoo.com");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }else {
                        Toast.makeText(Calendar.this, "There is no app that can support this action",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Calendar.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}