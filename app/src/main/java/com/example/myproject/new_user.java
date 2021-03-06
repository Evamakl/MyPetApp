package com.example.myproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class new_user extends AppCompatActivity {
    private static final String TAG = "TAG";
    EditText fullName;
    EditText password;
    EditText confirmPass;
    EditText email;
    EditText phone;
    Button register;
    Button back;
    FirebaseAuth firebaseAuth;
    Intent intent;
    private String type;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_new_user);
        firebaseAuth = FirebaseAuth.getInstance();
        fullName = findViewById(R.id.fullNameET);
        email = findViewById(R.id.emailET);
        password = findViewById(R.id.passwordET);
        confirmPass = findViewById(R.id.confirmPassword);
        phone = findViewById(R.id.phoneET);
        register = findViewById(R.id.registerbt);
        back = findViewById(R.id.button_prev);
        intent = getIntent();
        type = (String)intent.getSerializableExtra("type");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(new_user.this,Exist_new_frame.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }
    private void Register() {
        String mail = email.getText().toString();
        String password1 = password.getText().toString();
        String password2 = confirmPass.getText().toString();
        String fName = fullName.getText().toString();
        String phoneNum = phone.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            email.setError("Enter your email!");
            return;
        }
        else if (TextUtils.isEmpty(password1)) {
            password.setError("Enter your password!");
            return;
        }
        else if (TextUtils.isEmpty(password2)) {
            confirmPass.setError("Confirm your password!");
            return;
        }
        else if (TextUtils.isEmpty(fName)) {
            fullName.setError("Enter your name!");
            return;
        }
        else if (TextUtils.isEmpty(phoneNum)) {
            phone.setError("Enter your phone number!");
            return;
        }
        else if(!password1.equals(password2)) {
            confirmPass.setError("Different password");
            return;
        }
        else if(password1.length()<6) {
            password.setError("length is too short");
            return;
        }
        else if (!isValidEmail(mail)) {
            email.setError("Invalid email");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(mail,password1).addOnCompleteListener(new_user.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
                    User user = new User(firebaseAuth.getCurrentUser().getUid(),mail, phoneNum,fName);
                    user.setType(type);
                    reference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                    Toast.makeText(new_user.this,"Successfully registered",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(new_user.this,loginExistsFrame.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(new_user.this,"Sign up fail",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public static boolean isValidUserName(String name) {
        String expression = "^[a-zA-Z]*$";
        CharSequence inputStr = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }
    public static Boolean isValidEmail(CharSequence target){
        return (!TextUtils.isEmpty(target)&& Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidPhoneNumber(String phone) {
        return !(!phone.matches("(00972|0|\\+972)[5][0-9]{8}") && !phone.matches("(00970|0|\\+970)[5][0-9]{8}") && !phone.matches("(05[0-9]|0[12346789])([0-9]{7})") && !phone.matches("(00972|0|\\+972|0|)[2][0-9]{7}"));
    }
}