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
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddTipDialog extends AppCompatDialogFragment {
    private android.content.Context Context;
    private Dialog dialog;
    private String Title;
    private EditText editText;
    private User User = new User();
    private int size =0;
    private FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("ManagerTips");
    public AddTipDialog(Context context, String title, User user,int size){
        super();
        Context = context;
        Title = title;
        User = user;
        size= size;
    }
    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.todo_text_input,null);
        editText = view.findViewById(R.id.addnewtask);
        alertDialog.setView(view).setTitle(Title);
        alertDialog.setNegativeButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(editText.getText().toString().length()>0) {
                    Random random = new Random();
                    reference.child(random.nextInt(999999999)+"").setValue(editText.getText().toString());
                }
            }
        });
        return  alertDialog.create();
    }

}
