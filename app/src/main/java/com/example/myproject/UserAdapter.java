package com.example.myproject;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> users;
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    public UserAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_select_view, parent, false);
        return new UserAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.FragmentUserName.setText(user.getUsername());
        holder.FragmentUserImage.setImageResource(R.mipmap.ic_launcher);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context, Message.class);
               intent.putExtra("user",user);
               context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() { return users.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView FragmentUserName;
        public ImageView FragmentUserImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            FragmentUserName = itemView.findViewById(R.id.FragmentUserName);
            FragmentUserImage = itemView.findViewById(R.id.FragmentUserImage);
        }
    }
}