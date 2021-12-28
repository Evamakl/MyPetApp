package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Personal_File extends AppCompatActivity  {

    ImageView dog_image;
    private TextInputLayout dog_name,city,Gender,BirthDay,type,timeOut;
    private FirebaseAuth mAuth;
    private ImageView DogPic,addDogPic;
    private Button Done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        dog_name = findViewById(R.id.TextInputLayoutName);
        city = findViewById(R.id.TextInputLayoutCity);
        Gender = findViewById(R.id.TextInputLayoutGender);
        BirthDay = findViewById(R.id.TextInputLayoutDate);
        type = findViewById(R.id.TextInputLayoutType);
        timeOut = findViewById(R.id.TextInputLayoutTime);
        DogPic = findViewById(R.id.DogPic);
        addDogPic = findViewById(R.id.addpicdog);
        Done = findViewById(R.id.Done);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckValues()){

                }
            }
        });
    }
    private Boolean CheckValues(){
        if(dog_name.getEditText().getText().length() == 0) {
            dog_name.setHelperText("חובה להזין את שם הכלב");
            return false;
        }else
        if (type.getEditText().getText().length() == 0){
            type.setHelperText("חובה להזין את סוג הכלב");
            return false;
        }
        if (Gender.getEditText().getText().length() == 0){
            Gender.setHelperText("חובה להזין את מין הכלב");
            return false;
        }
        if (BirthDay.getEditText().getText().length() == 0){
            BirthDay.setHelperText("חובה להזין את תאריך לידה של הכלב");
            return false;
        }
        if (timeOut.getEditText().getText().length() == 0){
            timeOut.setHelperText("חובה להזין את זמני יציאה לטיול הכלב");
            return false;
        }
        if (city.getEditText().getText().length() == 0){
            city.setHelperText("חובה להזין את עיר הכלב");
            return false;
        }
        return true;
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