package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseDogPK extends AppCompatActivity {
    EditText Email;
    EditText dogsName;
    Button approve;
    User user = new User();
    Intent intent;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dog_pk);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        Email = (EditText)findViewById(R.id.editEmail);
        dogsName = (EditText)findViewById(R.id.ETdogs_name);
        approve = (Button)findViewById(R.id.approvedBT);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot temp : snapshot.getChildren()){//get all the users one by one from the db
                            User check_user = (User)temp.getValue(User.class);//enter the user into the variable
                            if(check_user.getEmail().equals(Email.getText().toString())){
                                for(int i=0; i<user.getDogs().size();i++){//run over the lst of the dogs(owner)
                                    if(dogsName.getText().toString().equals(user.getDogs().get(i).getName())){
                                        user.getDogsPK().add(new DogPK(Email.getText().toString(),user.getDogs().get(i)));
                                        openNewActivityApprove();
                                    }
                                }

                            }
                        }
                        Toast.makeText(ChooseDogPK.this, "this Email does not exist!, Try Again", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });
    }
    public void openNewActivityApprove() {
        //reference.child(user.getUid()).setValue(user);
        Intent intent = new Intent(ChooseDogPK.this,DogList.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}