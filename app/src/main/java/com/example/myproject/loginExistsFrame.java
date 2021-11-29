package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if(isValidUserName(etUserName.getText().toString()) && isValidPassword(etPassword.getText().toString())){
                    etUserName.setText("");
                    etPassword.setText("");
                    openNewActivity();
                }
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

    public static boolean isValidPassword(String password) {
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidUserName(String name) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

}