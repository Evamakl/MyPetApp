package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class TipsPKAdapter extends RecyclerView.Adapter<TipsPKAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<tipsPKClass> listTips;
    private User user = new User();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("Users");
    public TipsPKAdapter(Context context, ArrayList<tipsPKClass> list, User user){
        this.context = context;
        listTips = list;
        this.user = user;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_tips_pklist,parent,false);
        return new MyViewHolder(view);
    }

   // @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(listTips.get(position).getText());
        holder.TipInfo.setText(listTips.get(position).getUserInfo());
    }
    public int getItemCount() { return  listTips.size(); }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView, TipInfo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.PKtip);
            TipInfo = itemView.findViewById(R.id.TipInfo);
        }
    }
}
