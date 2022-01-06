package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DogListPkAdapter  extends RecyclerView.Adapter<DogListPkAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Dog> list;
    private User user = new User();
    public DogListPkAdapter(Context context, ArrayList<Dog> list, User user) {
        this.context = context;
        this.list = list;
        this.user = user;
    }
    @NonNull
    @Override
    public DogListPkAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.see_dog, parent, false);
        return new DogListPkAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogListPkAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.dogName.setText(list.get(position).getName());
        //if(list.get(position).getImage().equals("")){
            holder.dogImage.setImageResource(R.mipmap.ic_launcher);
        /*}
        else {
            Glide.with(context).asBitmap().load(list.get(position).getImage()).into(new CustomTarget<Bitmap>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                    holder.dogImage.setBackground(new BitmapDrawable(context.getResources(), resource));
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) { }
            });
        }*/
        holder.PickDogPK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PetOwnerOptionsOfDog.class);
                intent.putExtra("user",user);
                intent.putExtra("dog",list.get(position));
                intent.putExtra("return", DogList.class);
                ((Activity)context).startActivity(intent);
                ((Activity) context).finish();
            }
        });

    }

    public int getItemCount() { return list.size(); }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dogName;
        private ImageView dogImage;
        private ConstraintLayout PickDogPK;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dogName = itemView.findViewById(R.id.dogName);
            dogImage = itemView.findViewById(R.id.dogImage);
            PickDogPK = itemView.findViewById(R.id.PickDogPK);
        }
    }
}
