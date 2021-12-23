package com.example.myproject;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class new_user extends AppCompatActivity {

    TextView password;
    TextView email;
    TextView name;
    TextView phone;
    Button end;
    Button goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        password = (TextView) findViewById(R.id.password);
        email = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById(R.id.username);
        phone = (TextView) findViewById(R.id.phone);


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

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

    }



    public void openNewPage() {
        Intent intent = new Intent(this, End.class);
        startActivity(intent);
    }

    public void returnBack() {
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

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phone){
        return  !(!phone.matches("(00972|0|\\+972)[5][0-9]{8}") && !phone.matches("(00970|0|\\+970)[5][0-9]{8}") && !phone.matches("(05[0-9]|0[12346789])([0-9]{7})") && !phone.matches("(00972|0|\\+972|0|)[2][0-9]{7}"));
    }

    public static boolean isAlpha(String name) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

}
