package com.example.myproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class OwnerNavigation {

    private Intent intent;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private TextView UserFullName;
    private NavigationView navigation;
    public OwnerNavigation(Context context, int id, User user,@NonNull android.view.MenuItem item){
        navigation = ((Activity)context).findViewById(R.id.NavigationView);
        if( id == R.id.AddDog)
            AddDog(context,user);
        else if( id == R.id.RemoveDog)
            RemoveDog(context,user);
        else  if( id == R.id.FullName_item){ }
        else if(id == R.id.about_item)
            StartActivity(context, search_page.class, user);
        else if(id == R.id.calendar_item)
            StartActivity(context, Calendar.class, user);
        else if(id == R.id.chat_item)
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
        else{
            String dogs_name = item.getTitle().toString();
            for(int i=0; i<user.getDogs().size();i++){
                if(user.getDogs().get(i).getName().equals(dogs_name)){
                    Intent intent = new Intent(context, PetOwnerOptionsOfDog.class);
                    intent.putExtra("dog",user.getDogs().get(i));
                    intent.putExtra("user",user);
                    ((Activity)context).startActivity(intent);
                }
            }
        }
    }
    private void AddDog(Context context, User user){
        AddDogDialog addDogDialog = new AddDogDialog(context,user);
        addDogDialog.show(((FragmentActivity)context).getSupportFragmentManager(),"show dialog");
    }
    private void RemoveDog(Context context, User user){
        RemoveDogDialog removeDogDialog = new RemoveDogDialog(context,user);
        removeDogDialog.show(((FragmentActivity)context).getSupportFragmentManager(),"show dialog");
    }
    private void StartActivity(Context context, Class Destination, User user){
        intent = new Intent(context, Destination);
        intent.putExtra("user", user);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
