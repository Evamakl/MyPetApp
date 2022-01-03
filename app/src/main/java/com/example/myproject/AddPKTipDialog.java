package com.example.myproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Random;

public class AddPKTipDialog extends AppCompatDialogFragment {
    private Context Context;
    private Dialog dialog;
    private String Title;
    private EditText editText;
    private User User = new User();
    private FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("Tips");
    public AddPKTipDialog(Context context, String title, User user){
        super();
        Context = context;
        Title = title;
        User = user;
    }
    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.pet_keepers_tips_input,null);
        editText = view.findViewById(R.id.addnewPKtip);
        alertDialog.setView(view).setTitle(Title);
        alertDialog.setNegativeButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(editText.getText().toString().length()>0) {
                    Random random = new Random();
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    int year = calendar.get(java.util.Calendar.YEAR);
                    int month = calendar.get(java.util.Calendar.MONTH)+1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    tipsPKClass tipsPKClass = new tipsPKClass(editText.getText().toString(),"שם: "+ User.getUsername()+", תאריך: "+day+"/"+month+"/"+year);
                    reference.child(random.nextInt(999999999)+"").setValue(tipsPKClass);
                }
            }
        });
        return  alertDialog.create();
    }
}

