package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class firstframe extends AppCompatActivity {

        Button button_pet_keeper;
        Button button_Pet;
        Button Boss_button;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                //FirebaseAuth.getInstance().signOut();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference().child("Users");
                reference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = new User();
                        user = snapshot.getValue(User.class);
                        Intent intent = new Intent(firstframe.this,Start_work.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            setContentView(R.layout.activity_firstframe);
            button_pet_keeper = (Button) findViewById(R.id.Pet_Keeper_Button);
            button_pet_keeper.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    openNewActivityPet_keeper();
                }

            });
            button_Pet = (Button) findViewById(R.id.Pet_Button);
            button_Pet.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    openNewActivityPet();
                }

            });
            Boss_button=(Button) findViewById(R.id.Boss_Button);
            Boss_button.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                   openNewActivityBoss();
                }

            });
        }

        public void openNewActivityPet_keeper (){
            Intent intent = new Intent(this, Exist_new_frame.class);
            intent.putExtra("type","PetKeeper");
            startActivity(intent);
        }

        public void openNewActivityPet(){
        Intent intent = new Intent(this, Exist_new_frame.class);
            intent.putExtra("type","Owner");
            startActivity(intent);
        }

        public void openNewActivityBoss(){
         Intent intent = new Intent(this,loginExistsFrame.class);
            intent.putExtra("type","Manager");
            startActivity(intent);

        // Intent intent = new Intent(this,LoginExistsFrame.class);
        // startActivity(intent);

       }

}
