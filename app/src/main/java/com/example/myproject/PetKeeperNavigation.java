package com.example.myproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;

import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class PetKeeperNavigation {
    private Intent intent;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private TextView UserFullName;
    private NavigationView navigation;
    public PetKeeperNavigation(Context context, int id, User user){
        navigation = ((Activity)context).findViewById(R.id.NavigationView);
        //UserFullName = navigation.getHeaderView(0).findViewById(R.id.FullName_item);
        //UserFullName.setText(user.getUsername());
        if(id == R.id.DogListPK_item)
            StartActivity(context, DogList.class, user);
        else if(id == R.id.Todolist_item)
            StartActivity(context, ToDoList.class, user);
        else if(id == R.id.Reminder_item)
            StartActivity(context, Calendar.class, user);
        else if(id == R.id.Tips_item)
            StartActivity(context, tips.class, user);
        else if (id == R.id.Chat_item)
            StartActivity(context, PetAppChat.class, user);
        else if (id == R.id.Support_item)
            StartActivity(context, technicalSupport.class, user);
        else if(id == R.id.feedback_item)
            StartActivity(context, FeedbackPage.class, user);
        else if(id == R.id.logout_item) {
            //initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(((Activity)context));
            builder.setTitle("LogOut");
            //set message
            builder.setMessage("Are you sure you want to logout ?");
            //Positive yes button
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //finish activity
                    //activity.finishAffinity();
                    //exit app
                    if(firebaseAuth.getCurrentUser() != null)
                        firebaseAuth.signOut();
                    context.startActivity(new Intent(context, firstframe.class));
                    ((Activity)context).finish();
                }
            });
            //negative no button
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Dismiss dialog
                    dialog.dismiss();
                }
            });
            //show dialog
            builder.show();
        }
    }
    private void StartActivity(Context context, Class Destination, User user){
        intent = new Intent(context, Destination);
        intent.putExtra("user", user);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
