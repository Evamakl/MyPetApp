package com.example.myproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddDogDialog extends AppCompatDialogFragment{
    private TextInputLayout dog_name,city,Gender,BirthDay,type;
    private ImageView DogPic,addDogPic;
    private NavigationView navigationView;
    private Button Done;
    private Menu menu;
    private Context context;
    private User user = new User();
    public AddDogDialog(Context context, User user){
        this.context = context;
        this.user=user;
    }
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dogs_name,null);
        dog_name = view.findViewById(R.id.TextInputLayoutName);
        city = view.findViewById(R.id.TextInputLayoutCity);
        Gender = view.findViewById(R.id.TextInputLayoutGender);
        BirthDay = view.findViewById(R.id.TextInputLayoutDate);
        BirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        type = view.findViewById(R.id.TextInputLayoutType);
        DogPic = view.findViewById(R.id.DogPic);
        addDogPic = view.findViewById(R.id.addpicdog);
        Done = view.findViewById(R.id.Done);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckValues()){
                    navigationView = ((Activity)context).findViewById(R.id.NavigationView);
                    menu = navigationView.getMenu();
                    Dog dog = new Dog(type.getEditText().getText().toString(),dog_name.getEditText().getText().toString(),Gender.getEditText().getText().toString(),"",city.getEditText().getText().toString(),BirthDay.getEditText().getText().toString());
                    menu.findItem(R.id.Dogs).getSubMenu().add(dog_name.getEditText().getText().toString());
                    user.AddDog(dog);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
                    reference.setValue(user);
                    dismiss();
                    Intent intent = new Intent(context, Start_work.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    ((Activity)context).finish();
                }
            }
        });
        builder.setView(view).setTitle("הוסף כלב");
        return builder.create();
    }

    private Boolean CheckValues(){
        if(dog_name.getEditText().getText().length() == 0) {
            dog_name.setHelperText("חובה להזין את שם הכלב");
            return false;
        }else if(!dog_name.getEditText().getText().toString().matches("[a-zA-Z ]+")) {
                dog_name.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
                return  false;
        }else
            dog_name.setHelperText("");


        if (type.getEditText().getText().length() == 0){
            type.setHelperText("חובה להזין את סוג הכלב");
            return false;
        }else
        {
            if(!type.getEditText().getText().toString().matches("[a-zA-Z ]+"))
            {

                type.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            }
        }



        if (Gender.getEditText().getText().length() == 0){
            Gender.setHelperText("חובה להזין את מין הכלב");
            return false;
        }else
        {
            if(!Gender.getEditText().getText().toString().matches("[a-zA-Z ]+"))
            {

                Gender.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            }
        }



        if (BirthDay.getEditText().getText().length() == 0){
            BirthDay.setHelperText("חובה להזין את תאריך לידה של הכלב");
            return false;
        }



        if (city.getEditText().getText().length() == 0){
            city.setHelperText("חובה להזין את עיר הכלב");
            return false;
        }else
        {
            if(!city.getEditText().getText().toString().matches("[a-zA-Z ]+"))
            {
                city.setHelperText("ENTER ONLY ALPHABETICAL CHARACTER");
            }
        }
        return true;
    }

}