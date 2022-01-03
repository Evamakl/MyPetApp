package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackPage extends Dialog {

    private float userRate = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("UsersRatings");

    public FeedbackPage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        final AppCompatButton Submit = findViewById(R.id.Submit);
        final RatingBar RatingBar = findViewById(R.id.RatingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RatingBar.getNumStars() > 0) {
                    reference.setValue(RatingBar.getNumStars());
                }
                //Toast.makeText(FeedbackPage.this, "Rating Submitted", Toast.LENGTH_SHORT).show();
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