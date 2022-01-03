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

public class AddTodoDialog extends AppCompatDialogFragment {
    private Context Context;
    private Dialog dialog;
    private String Title;
    private EditText editText;
    private User User = new User();
    private FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("Users");
    public AddTodoDialog(Context context, String title, User user){
        super();
        Context = context;
        Title = title;
        User = user;
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
                    User.getListOfTodo().add(new TodoListClass(editText.getText().toString(),"0"));
                    reference.child(User.getUid()).setValue(User);
                    Intent intent = new Intent(Context, ToDoList.class);
                    intent.putExtra("user", User);
                    startActivity(intent);
                }
            }
        });
        return  alertDialog.create();
    }
}
