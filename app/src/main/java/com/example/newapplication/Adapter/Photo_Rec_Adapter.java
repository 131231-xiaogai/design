package com.example.newapplication.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapplication.R;
import com.example.newapplication.entity.Photo;

import java.util.List;

public class Photo_Rec_Adapter extends RecyclerView.Adapter<Photo_Rec_Adapter.ViewHolder> {
    private List<Photo> mPhotoList;

     static class ViewHolder extends RecyclerView.ViewHolder{
         View photoView;
         ImageView mphotoImage;
         TextView mphotoName;
         public ViewHolder(@NonNull View  view) {
             super(view);
             photoView =view;
             mphotoImage = (ImageView)view.findViewById(R.id.h_photo_image);
             mphotoName = (TextView)view.findViewById(R.id.h_photo_name);
         }
     }
     public Photo_Rec_Adapter(List<Photo> PhotoList){
         mPhotoList =PhotoList;
     }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        //添加监听
        holder.photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Photo photo = mPhotoList.get(position);
                Toast.makeText(v.getContext(),"你点击了"+photo.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.mphotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Photo photo = mPhotoList.get(position);
                Toast.makeText(v.getContext(),"你点击了图片"+photo.getName(),Toast.LENGTH_SHORT).show();}
        });

        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo=mPhotoList.get(position);
        holder.mphotoImage.setImageResource(photo.getImageId());
        holder.mphotoName.setText(photo.getName());
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }
}
