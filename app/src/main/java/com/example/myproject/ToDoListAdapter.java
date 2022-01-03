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

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<TodoListClass> list;
    private User user = new User();
    Button finish_task;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference reference = firebaseDatabase.getReference().child("Users");
    public ToDoListAdapter(Context context, ArrayList<TodoListClass> list, User user){
        this.context = context;
        this.list = list;
        this.user = user;
        finish_task = ((Activity)context).findViewById(R.id.finish_task);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.check_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(list.get(position).getText());
        if (list.get(position).getFlag().equals("0"))
            holder.checkBox.setChecked(false);
        else
            holder.checkBox.setChecked(true);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    list.get(position).setFlag("1");
            }
        });
        finish_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i< list.size();i++){
                    if(list.get(i).flag.equals("1")){
                        list.remove(list.get(i));
                    }
                }
                user.setListOfTodo(list);
                reference.child(user.getUid()).setValue(user);
                Intent intent = new Intent(context,ToDoList.class);
                intent.putExtra("user",user);
                ((Activity)context).startActivity(intent);
            }
        });
    }
    public int getItemCount() { return  list.size(); }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.toDo);
            checkBox = itemView.findViewById(R.id.checkBoxToDo);
        }
    }
}
