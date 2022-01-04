package com.example.myproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveDogDialog  extends AppCompatDialogFragment {
    private TextInputLayout dog_name;
    private NavigationView navigationView;
    private Menu menu;
    private Context context;
    private User user = new User();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("Users");
    public RemoveDogDialog(Context context, User user){
        this.context = context;
        this.user=user;
    }
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.get_dogs_name_to_delete,null);
        dog_name = view.findViewById(R.id.TextInputLayoutName);
        builder.setView(view).setTitle("מחק כלב").setNegativeButton("אישור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                navigationView = ((Activity)context).findViewById(R.id.NavigationView);
                menu = navigationView.getMenu();
                for(int i=0; i<menu.findItem(R.id.Dogs).getSubMenu().size();i++) {
                    if (menu.findItem(R.id.Dogs).getSubMenu().getItem(i).getTitle().equals(dog_name.getEditText().getText().toString())) {
                        menu.findItem(R.id.Dogs).getSubMenu().removeItem(menu.findItem(R.id.Dogs).getSubMenu().getItem(i).getItemId());
                        for(int j=0; j<user.getDogs().size();j++){
                            if(user.getDogs().get(j).getName().equals(dog_name.getEditText().getText().toString())){
                                user.getDogs().remove(user.getDogs().get(j));
                                reference.child(user.getUid()).setValue(user);
                            }
                        }
                    }
                }
                Toast.makeText(context, "not found dog", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
