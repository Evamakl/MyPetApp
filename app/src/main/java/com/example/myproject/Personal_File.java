package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Personal_File extends AppCompatActivity  {

    ImageView dog_image;
    TextView dog_birth_day;
    TextView city;
    TextView dog_name;
    TextView dog_type;
    TextView time_outside;
    TextView Sex;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        dog_image = (ImageView) findViewById(R.id.dog_image);
        dog_birth_day = (TextView) findViewById(R.id.dog_birth_day);
        city = (TextView) findViewById(R.id.city);
        dog_name = (TextView) findViewById(R.id.dog_name);
        dog_type = (TextView) findViewById(R.id.dog_type);
        time_outside = (TextView) findViewById(R.id.time_outside);
        Sex = (TextView) findViewById(R.id.gender);


        if (dog_birth_day.getText().toString().length() > 0 && city.getText().toString().length() > 0 && dog_name.getText().toString().length() > 0
                && dog_type.getText().toString().length() > 0 && time_outside.getText().toString().length() > 0
                && Sex.getText().toString().length() > 0) {
            if (isValidName(dog_name.getText().toString()) && isValidDate(dog_birth_day.getText().toString())
                    && isValidCity(city.getText().toString()) && isValidtime_out(time_outside.getText().toString())
                    && isValidSex(Sex.getText().toString())) {
                dog_birth_day.setText("");
                city.setText("");
                time_outside.setText("");
                dog_type.setText("");
                Sex.setText("");
            }
        }
    }


    private boolean isValidName(String name) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private boolean isValidCity(String city) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = city;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private boolean isValidtime_out(String time) {
        String expression = "^[1-24]:[0-59]*$";
        CharSequence inputStr = time;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    private boolean isValidSex(String s) {
        String expression = "^[M/F]*$";
        CharSequence inputStr = s;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
    private boolean isValidDate(String d) {
        String expression = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        CharSequence inputStr = d;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

}
/*
class date {
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:ms");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

 */