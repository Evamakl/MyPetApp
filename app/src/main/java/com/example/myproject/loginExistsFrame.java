package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginExistsFrame extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName, etPassword;
    Button btnSignIn, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_exists_frame);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSignIn.setOnClickListener((View.OnClickListener) this);
        btnBack.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        if(v==btnSignIn){
            if(etPassword.getText().toString().length()>0 && etUserName.getText().toString().length()>0) {
                etUserName.setText("");
                etPassword.setText("");
            }
        }
        if (v==btnBack){
            openPrevActivity();
        }
    }

    public void openNewActivity(){
        Intent intent = new Intent(this,NewActivityFrame.class);
        startActivity(intent);
    }

    public void openPrevActivity() {
        Intent intent = new Intent(this, Exist_new_frame.class);
        startActivity(intent);
    }

}