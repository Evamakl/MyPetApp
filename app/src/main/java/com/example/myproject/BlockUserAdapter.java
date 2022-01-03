package com.example.myproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BlockUserAdapter  extends RecyclerView.Adapter<BlockUserAdapter.MyViewHolder> {
    private List<User> list;
    private Context context;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("Users");
    public BlockUserAdapter(Context context,List<User> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public BlockUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.block_user,parent,false);
        return new BlockUserAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockUserAdapter.MyViewHolder holder, int position) {
        holder.userName.setText(list.get(position).getUsername());
        holder.userInfo.setText(list.get(position).getEmail()+", "+list.get(position).getPhone());
        if(list.get(position).getBlock()){
            holder.unblock.setVisibility(View.INVISIBLE);
            holder.block.setVisibility(View.VISIBLE);
            holder.block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot temp : snapshot.getChildren()){
                                User getUser = snapshot.getValue(User.class);
                                if((getUser.getEmail()+", "+getUser.getPhone()).equals(holder.userInfo.getText()))
                                    getUser.setBlock(true);
                                reference.child((String)temp.getKey()).setValue(getUser);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });
                }
            });
        }
        else{
            holder.unblock.setVisibility(View.VISIBLE);
            holder.block.setVisibility(View.INVISIBLE);
            holder.unblock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot temp : snapshot.getChildren()){
                                User getUser = snapshot.getValue(User.class);
                                if((getUser.getEmail()+", "+getUser.getPhone()).equals(holder.userInfo.getText()))
                                    getUser.setBlock(false);
                                reference.child((String)temp.getKey()).setValue(getUser);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() { return list.size(); }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private TextView userInfo;
        private ImageView block;
        private ImageView unblock;

        public MyViewHolder(View view){
            super(view);
            userName = view.findViewById(R.id.userName);
            userInfo = view.findViewById(R.id.userInfo);
            block=view.findViewById(R.id.blockImage);
            unblock=view.findViewById(R.id.unblockImage);
        }
    }
}
