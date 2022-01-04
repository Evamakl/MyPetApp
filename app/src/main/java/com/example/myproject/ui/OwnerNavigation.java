package com.example.myproject.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.myproject.AddDogDialog;
import com.example.myproject.Calendar;
import com.example.myproject.Dog;
import com.example.myproject.DogList;
import com.example.myproject.FeedbackPage;
import com.example.myproject.PetAppChat;
import com.example.myproject.PetOwnerOptionsOfDog;
import com.example.myproject.R;
import com.example.myproject.RemoveDogDialog;
import com.example.myproject.Start_work;
import com.example.myproject.ToDoList;
import com.example.myproject.User;
import com.example.myproject.databinding.ActivityTechnicalSupport2Binding;
import com.example.myproject.firstframe;
import com.example.myproject.search_page;
import com.example.myproject.technicalSupport;
import com.example.myproject.tips;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class OwnerNavigation{
    private Intent intent;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private TextView UserFullName;
    private NavigationView navigation;
    public OwnerNavigation(Context context, int id, User user, @NonNull android.view.MenuItem item){
        navigation = ((Activity)context).findViewById(R.id.NavigationView);
        //UserFullName = navigation.getHeaderView(0).findViewById(R.id.FullName_item);
        //UserFullName.setText(user.getUsername());
        if( id == R.id.AddDog)
            AddDog(context,user);
        else if( id == R.id.RemoveDog)
            RemoveDog(context,user);
        if(id == R.id.about_item)
            StartActivity1(context, search_page.class, user);
        else if(id == R.id.calendar_item)
            StartActivity1(context, Calendar.class, user);
        else if(id == R.id.chat_item)
            StartActivity1(context, PetAppChat.class, user);
        else if(id == R.id.feedback_item)
            StartActivity1(context, FeedbackPage.class, user);
        else if(id == R.id.Support_item)
            StartActivity1(context, technicalSupport.class, user);
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
            for(int i=0; i<user.getDogs().size();i++) {
                if (user.getDogs().get(i).getName().equals(dogs_name))
                    StartActivity2(context, PetOwnerOptionsOfDog.class, user, user.getDogs().get(i));
            }
        }
    }
    private void StartActivity1(Context context, Class Destination, User user){
        intent = new Intent(context, Destination);
        intent.putExtra("user", user);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
    private void StartActivity2(Context context, Class Destination, User user, Dog dog){
        intent = new Intent(context, Destination);
        intent.putExtra("user", user);
        intent.putExtra("dog", dog);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
    private void AddDog(Context context, User user){
        AddDogDialog addDogDialog = new AddDogDialog(context,user);
        addDogDialog.show(((FragmentActivity)context).getSupportFragmentManager(),"show dialog");
    }
    private void RemoveDog(Context context, User user){
        RemoveDogDialog removeDogDialog = new RemoveDogDialog(context,user);
        removeDogDialog.show(((FragmentActivity)context).getSupportFragmentManager(),"show dialog");
    }
}