package com.example.myproject;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.MyViewHolder> {
    private List<vaccine> vaccines;
    private Context context;
    public VaccineAdapter(Context context,List<vaccine> vaccines){
        this.context = context;
        this.vaccines = vaccines;
    }
    @NonNull
    @Override
    public VaccineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.mede_item,parent,false);
        return new VaccineAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineAdapter.MyViewHolder holder, int position) {
        holder.date.setText(vaccines.get(position).getDate());
        holder.name.setText(vaccines.get(position).getName());
    }

    @Override
    public int getItemCount() { return vaccines.size(); }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText name;
        private EditText date;
        private ConstraintLayout constraintLayout;
        public MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.Name);
            date = view.findViewById(R.id.Date);
            constraintLayout = view.findViewById(R.id.vaccine_constraint);
        }
    }
}
