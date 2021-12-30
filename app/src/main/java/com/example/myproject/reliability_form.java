package com.example.myproject;

import static com.example.myproject.HomePageManager.openDrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class reliability_form extends AppCompatActivity {
    Button next;
    DrawerLayout dogList_form;
    CheckBox checkBox;
    ImageView MenuIcon,BackIcon;
    User user = new User();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reliability_form);
        dogList_form =findViewById(R.id.reliability_form_layout) ;
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");

        next = (Button)findViewById(R.id.nextBT);
        checkBox = findViewById(R.id.checkBox);
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked())
                    openNewActivityNext();
                else
                    Toast.makeText(reliability_form.this, "Please click on the Check Box", Toast.LENGTH_SHORT).show();
            }
        });
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogList_form.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reliability_form.this,DogList.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }
    public void openNewActivityNext(){
        Intent intent = new Intent(reliability_form.this,ChooseDogPK.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }


}