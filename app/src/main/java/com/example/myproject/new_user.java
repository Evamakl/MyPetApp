package com.example.myproject;


import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.utilities.Validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.myproject.validation;

public class new_user extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView password;
    TextView email;
    TextView phone;
    Button end;
    Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

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

    }

    public new_user() {

        FirebaseApp.initializeApp(getContext());
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


    @Override
    public void onClick(View v) {

        if (checkValidation()) {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(getActivity(), task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            Map<String, Object> userData = new HashMap<>();
                            userData.put("Email", String.valueOf(email.getText().toString()));
                            userData.put("PhoneNumber", Integer.parseInt(phone.getText().toString()));


                        }
                    }).addOnFailureListener(Throwable::printStackTrace);
        }

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

}