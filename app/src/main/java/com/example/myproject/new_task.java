package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class new_task extends AppCompatActivity {

    Button saveNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        saveNewTask = (Button) findViewById(R.id.button_saveTask);
        saveNewTask.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                goToDoListUpdate();
            }

        });
    }

    public void goToDoListUpdate() {
        Intent intent = new Intent(this, ToDoList.class);
        startActivity(intent);
    }

   /* public void onBtnClick (View view) {
        TextView txtNewTask = findViewById(R.id.txtNewTask);
        EditText.edtTxtTask = findViewById(R.id.edtTxtTask);
        txtNewTask.setText("Task 1: " + edtTxtTask.getText().toString());
    }*/
}