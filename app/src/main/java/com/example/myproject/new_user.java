package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FieldValue;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.example.myproject.new_user.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class new_user extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private View binding;

    TextView password;
    TextView email;
    TextView phone;
    Button end;
    Button goBack;

    public static boolean isValidPassword(String password){
        final String PASSWORD_PATTERN=
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        final Pattern pattern=Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phone) {
        return !(!phone.matches("(00972|0|\\+972)[5][0-9]{8}") && !phone.matches("(00970|0|\\+970)[5][0-9]{8}") && !phone.matches("(05[0-9]|0[12346789])([0-9]{7})") && !phone.matches("(00972|0|\\+972|0|)[2][0-9]{7}"));
    }


   // @Override
    public View onCreateView(LayoutInflater inflater,
                            ViewGroup container,
                            Bundle savedInstanceState) {
        binding = inflater.inflate(R.layout.activity_new_user, container, false);
        View view = binding.getRootView();
        setContentView(R.layout.activity_new_user);

        mAuth = FirebaseAuth.getInstance();

        password = (TextView) findViewById(R.id.password);
        password.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (!validation.isValidPassword(password.getText().toString())) {
                    password.setError(getString(R.string.InvalidPassword));
                }
            }
        });

        email = (TextView) findViewById(R.id.email);
        email.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!validation.isEmailValid(email.getText().toString())) {
                    email.setError(getString(R.string.InvalidEmail));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        phone = (TextView) findViewById(R.id.phone);
        phone.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (!validation.isValidPhoneNumber(phone.getText().toString())) {
                    phone.setError(getString(R.string.InvalidPhoneNumber));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


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
        return view;
    }

    public new_user() {

       // FirebaseApp.initializeApp(getContext());
        mAuth = FirebaseAuth.getInstance();
    }


    public void openNewPage() {
        Intent intent = new Intent(this, End.class);
        startActivity(intent);
    }

    public void returnBack() {
        //Intent intent = new Intent(this, Exist_new_frame.class);
        //startActivity(intent);
    }





    private boolean checkValidation() {
        if (!validation.isValidPassword(password.getText().toString())) {
            return false;
        }
        if (!validation.isEmailValid(email.getText().toString())) {
            return false;
        }
        if (!validation.isValidPhoneNumber(phone.getText().toString())) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}

