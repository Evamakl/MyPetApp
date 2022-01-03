package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManagerTipsAdapter extends RecyclerView.Adapter<ManagerTipsAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<String> list;
    private User user = new User();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("ManagerTips");
    public ManagerTipsAdapter(Context context, ArrayList<String> list, User user){
        this.context = context;
        this.list = list;
        this.user = user;
    }
    @NonNull
    @Override
    public ManagerTipsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.new_tip,parent,false);
        return new ManagerTipsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagerTipsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText("* "+list.get(position));
        if(user.getType().equals("Manager")) {
            holder.DeleteTip.setVisibility(View.VISIBLE);
            holder.DeleteTip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ArrayList<String> list = new ArrayList<>();
                            String to_delete = holder.textView.getText().toString();
                            int index =-1;
                            for(DataSnapshot temp : snapshot.getChildren()){
                                String text = "* "+(String) temp.getValue().toString();
                                if(text.equals(to_delete))
                                    temp.getRef().removeValue();

                            }
                            Intent intent = new Intent(context,context.getClass());
                            intent.putExtra("user",user);
                            ((Activity)context).startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            });

        }

    }
    public int getItemCount() { return  list.size(); }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText textView;
        private ImageView DeleteTip;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.toDo);
            DeleteTip = itemView.findViewById(R.id.DeleteTip);
        }
    }
}
