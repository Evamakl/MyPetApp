package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ChooseDogPK extends AppCompatActivity {
    EditText Email;
    EditText dogsName;
    Button approve;
    private String op;
    DrawerLayout chooseDog_layout;
    ImageView MenuIcon,BackIcon;
    User user = new User();
    Intent intent;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference().child("Users");
    private Menu menu;
    private com.google.android.material.navigation.NavigationView NavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dog_pk);
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        op = (String)intent.getSerializableExtra("op");
        Email = (EditText)findViewById(R.id.editEmail);
        dogsName = (EditText)findViewById(R.id.ETdogs_name);
        chooseDog_layout =findViewById(R.id.chooseDog_layout) ;
        MenuIcon = findViewById(R.id.MenuItem);
        BackIcon = findViewById(R.id.BackItem);
        NavigationView = findViewById(R.id.NavigationView);
        menu = NavigationView.getMenu();
        menu.findItem(R.id.hello).setTitle( "שלום, " + user.getUsername());
        MenuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDog_layout.open();
            }
        });
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseDogPK.this, reliability_form.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }
        });
        approve = (Button)findViewById(R.id.approvedBT);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot temp : snapshot.getChildren()){//get all the users one by one from the db
                            User check_user = (User)temp.getValue(User.class);//enter the user into the variable
                            if(check_user.getEmail().toString().equals(Email.getText().toString())){
                                for(int i=0; i<check_user.getDogs().size();i++){//run over the lst of the dogs(owner)
                                    if(dogsName.getText().toString().equals(check_user.getDogs().get(i).getName().toString())){
                                        if(op.toString().equals("add")) {
                                            boolean exsist = false;
                                            for (int k = 0; k < user.getDogs().size(); k++)
                                                if (user.getDogs().get(k).equals(check_user.getDogs().get(i)))
                                                    exsist = true;
                                            if (!exsist) {
                                                user.getDogsPK().add(new DogPK(Email.getText().toString(), check_user.getDogs().get(i)));
                                                user.getDogs().add(check_user.getDogs().get(i));
                                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                DatabaseReference databaseReference = database.getReference().child("Users").child(user.getUid());
                                                databaseReference.setValue(user);
                                                Toast.makeText(ChooseDogPK.this, "dog was updated", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ChooseDogPK.this, DogList.class);
                                                intent.putExtra("user", user);
                                                startActivity(intent);
                                                finish();
                                                return;
                                            } else {
                                                Toast.makeText(ChooseDogPK.this, "The dog: " + dogsName.getText().toString() + "already exsists", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ChooseDogPK.this, DogList.class);
                                                intent.putExtra("user", user);
                                                startActivity(intent);
                                                finish();
                                                return;
                                            }
                                        }
                                        else if(op.toString().equals("delete")){
                                            for (int k = 0; k < user.getDogs().size(); k++)
                                                if (user.getDogs().get(k).equals(check_user.getDogs().get(i))) {
                                                    for(int o=1; o<user.getDogsPK().size();o++)
                                                        if(user.getDogsPK().get(o).dog.equals(user.getDogs().get(k)))
                                                            user.getDogsPK().remove(o);
                                                    user.getDogs().remove(k);
                                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                    DatabaseReference databaseReference = database.getReference().child("Users").child(user.getUid());
                                                    databaseReference.setValue(user);
                                                    Toast.makeText(ChooseDogPK.this, "dog was removed", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(ChooseDogPK.this, DogList.class);
                                                    intent.putExtra("user", user);
                                                    startActivity(intent);
                                                    finish();
                                                    return;
                                                }
                                            Toast.makeText(ChooseDogPK.this, "The dog: " + dogsName.getText().toString() + "not exsists", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ChooseDogPK.this, DogList.class);
                                            intent.putExtra("user", user);
                                            startActivity(intent);
                                            finish();
                                            return;
                                        }
                                    }
                                }
                                Toast.makeText(ChooseDogPK.this, check_user.getEmail() +" dont have dog " +dogsName.getText().toString()+ " !, Try Again", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        Toast.makeText(ChooseDogPK.this, "this Email does not exist!, Try Again", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });
    }
}