package com.example.myproject;

//import static com.example.myproject.new_user.isValidPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginExistsFrame extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName, etPassword;
    Button btnSignIn, btnBack;

    private String TAG = "loginExistsFrame";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_exists_frame);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSignIn.setOnClickListener((View.OnClickListener) this);
        btnBack.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            openNewActivity();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnSignIn) {
            if (etPassword.getText().toString().length() > 0 && etUserName.getText().toString().length() > 0) {
                /*if (isValidUserName(etUserName.getText().toString()) && isValidPassword(etPassword.getText().toString())) {
                    etUserName.setText("");
                    etPassword.setText("");*/

                    mAuth.signInWithEmailAndPassword(etUserName.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");

                                        FirebaseUser user = mAuth.getCurrentUser();
                                        openNewActivity();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });


                    //openNewActivity();
                }
            //}
        }
        if (v == btnBack) {
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

        private boolean isValidUserName(String name){
            String expression = "^[a-zA-Z]*$";
            CharSequence inputStr = name;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(inputStr);
            return matcher.matches();
        }
    }


