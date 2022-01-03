package com.example.myproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewsLetterAdapter extends RecyclerView.Adapter<NewsLetterAdapter.MyViewHolder> {
    private List<String> list;
    private Context context;
    private Button updateButton;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("NewsLetter");
    public NewsLetterAdapter(Context context,List<String> list){
        this.context = context;
        this.list = list;
        updateButton = ((Activity)context).findViewById(R.id.updateButton);
    }
    @NonNull
    @Override
    public NewsLetterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.news_letter,parent,false);
        return new NewsLetterAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsLetterAdapter.MyViewHolder holder, int position) {
        holder.text.setText(list.get(position));
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> newList = new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    newList.add(holder.text.getText().toString());
                }
                reference.setValue(newList);
            }
        });
    }

    @Override
    public int getItemCount() { return list.size(); }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText text;
        public MyViewHolder(View view){
            super(view);
            text = view.findViewById(R.id.editTextTextNewsLetter);
        }
    }
}
