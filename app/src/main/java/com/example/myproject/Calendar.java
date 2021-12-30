package com.example.myproject;

import static com.example.myproject.HomePageManager.openDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Calendar extends AppCompatActivity {
    EditText title;
    EditText location;
    EditText description;
    //DrawerLayout ;
    DrawerLayout drawer_form;
    ImageView MenuIcon,BackIcon;
    Button saveEvent;
    User user = new User();
    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        drawer_form =findViewById(R.id.drawer_layout) ;
        //intent1 = getIntent();
       // user = (User)intent1.getSerializableExtra("user");
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        title = findViewById(R.id.titleEt);
        location = findViewById(R.id.locationEt);
        description = findViewById(R.id.descriptionEt);
        saveEvent = findViewById(R.id.saveEventbt);
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawer_form.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_form.open();
            }
        });





        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                && !description.getText().toString().isEmpty()) {
                   /* Intent intent = new Intent(Intent.ACTION_INSERT);
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
                    }*/
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("allDay", true);
                    intent.putExtra("rrule", "FREQ=YEARLY");
                    intent.putExtra("title", description.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    intent.putExtra("eventLocation", location.getText().toString());
                    startActivity(intent);

//                    Intent intent1 = new Intent(Calendar.this,navigation_drawer.class);
//                    intent1.putExtra("user",user);
//                    startActivity(intent1);
                }
                else {
                    Toast.makeText(Calendar.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}