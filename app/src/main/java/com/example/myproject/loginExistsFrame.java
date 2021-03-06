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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginExistsFrame extends AppCompatActivity implements View.OnClickListener {

    EditText etUserName, etPassword;
    Button btnSignIn, btnBack;

    private String TAG = "loginExistsFrame";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_exists_frame);

        // Initialize Firebase Auth

        etUserName = (EditText) findViewById(R.id.emailET);
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
            FirebaseAuth.getInstance().signOut();
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
                                    reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Log.d(TAG, "signInWithEmail:success");
                                            User user = new User();
                                            user = snapshot.getValue(User.class);
                                            if(!user.getBlock()) {
                                                Intent intent = new Intent(loginExistsFrame.this, Start_work.class);
                                                if (user.getType().equals("PetKeeper"))
                                                    intent = new Intent(loginExistsFrame.this, navigation_drawer.class);
                                                else if (user.getType().equals("Manager"))
                                                    intent = new Intent(loginExistsFrame.this, HomePageManager.class);
                                                intent.putExtra("user", user);
                                                startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(loginExistsFrame.this, "User is blocked!", Toast.LENGTH_SHORT).show();
                                                mAuth.signOut();
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) { }
                                    });
                                    // Sign in success, update UI with the signed-in user's information
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
        Intent intent = new Intent(this,search_page.class);
        startActivity(intent);
    }

    public void openPrevActivity() {
        Intent intent = new Intent(this, firstframe.class);
        startActivity(intent);
    }

    public static boolean isValidPassword(String password) {
        final String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()???[{}]:;',?/*~$^+=<>]).{8,20}$";
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



