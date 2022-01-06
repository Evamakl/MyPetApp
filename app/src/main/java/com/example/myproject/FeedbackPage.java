package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedbackPage extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Rank");
    private float userRate = 3;
    private TextView ratingText;                                       
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference reference = database.getReference().child("UsersRatings");
    private User user;
    private Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        AppCompatButton Submit = findViewById(R.id.Submit);
        ratingText=findViewById(R.id.ratingText);
        RatingBar RatingBar = findViewById(R.id.RatingBar);
        ImageView ratingImage = findViewById(R.id.ratingImage);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //rankText.setText("");
                String text = (String)snapshot.getValue();
                ratingText.setText(text);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RatingBar.getNumStars() > 0) {
                    if(!user.getRate()) {
                        String index = String.valueOf((int) userRate);
                        databaseReference.child(index).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                long cur = (long) snapshot.getValue();
                                databaseReference.child(index).setValue(cur + 1);
                                Intent intent = new Intent(FeedbackPage.this, Start_work.class);
                                if (user.getType().toString().equals("PetKeeper"))
                                    intent = new Intent(FeedbackPage.this, navigation_drawer.class);
                                user.setRate(true);
                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users").child(user.getUid()).child("rate");
                                databaseReference.setValue(true);
                                Toast.makeText(FeedbackPage.this, "Rating Submitted", Toast.LENGTH_SHORT).show();
                                intent.putExtra("user", user);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                    else {
                        Toast.makeText(FeedbackPage.this, "You cant rate again!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FeedbackPage.this, Start_work.class);
                        if (user.getType().toString().equals("PetKeeper"))
                            intent = new Intent(FeedbackPage.this, navigation_drawer.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                }
            }
        });

        RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(android.widget.RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 1) {
                    ratingImage.setImageResource(R.drawable.one_star);
                }
                else if (rating <=2) {
                    ratingImage.setImageResource(R.drawable.two_star);
                }
                else if (rating <= 3) {
                    ratingImage.setImageResource(R.drawable.three_star);
                }
                else if (rating <= 4) {
                    ratingImage.setImageResource(R.drawable.four_star);
                }
                else if (rating <= 5) {
                    ratingImage.setImageResource(R.drawable.five_star);
                }
                //animate emoji image
                animateImage(ratingImage);
                //selected rating by user
                userRate = rating;
            }
        });
    }

    private void animateImage (ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }
}