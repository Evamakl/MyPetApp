package com.example.myproject;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import de.hdodenhof.circleimageview.CircleImageView;

public class Message extends AppCompatActivity {
    private TextView username, TextSend;
    private DatabaseReference reference;
    private CircleImageView profileImage;
    private ImageView BackIcon, ButtonSend;
    private Intent intent;
    private MessageAdapter messageAdapter;
    private List<Chat> chats;
    private RecyclerView recyclerView;
    private String PublicKey, PrivateKey, Key;
    private byte secretKey[];
    private Cipher cipher,decipher;
    private SecretKeySpec secretKeySpec;
    private User user = new User();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();
        BackIcon();
        TextSend();
    }
    private void init(){
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        recyclerView = findViewById(R.id.MessageRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        TextSend = findViewById(R.id.TextSend);
        ButtonSend = findViewById(R.id.ButtonSend);
        profileImage = findViewById(R.id.ProfileImage);
        BackIcon = findViewById(R.id.BackIcon);
        username = findViewById(R.id.userName);
        profileImage.setImageResource(R.mipmap.ic_launcher);
        username.setText(user.getUsername());
        readMessage(firebaseAuth.getUid(),user.getUid());
    }
    private void TextSend(){
        ButtonSend.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(!TextSend.getText().toString().equals(""))
                    sendMessage(firebaseAuth.getCurrentUser().getUid(), user.getUid(), TextSend.getText().toString());
                TextSend.setText("");
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        try{
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("sender", sender);
            hashMap.put("receiver", receiver);
            hashMap.put("message", message);
            databaseReference.child("Chats").push().setValue(hashMap);
        }
        catch (Exception ignored){}
    }
    private void readMessage(String sender, String receiver){
        chats = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(sender) && chat.getSender().equals(receiver) || chat.getReceiver().equals(receiver) && chat.getSender().equals(sender)){
                        try {
                            chats.add(chat);
                        } catch (Exception e) { e.printStackTrace(); }
                    }
                    messageAdapter = new MessageAdapter(Message.this, chats);
                    recyclerView.setAdapter(messageAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
    private void BackIcon(){
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });
    }
}