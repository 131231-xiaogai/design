package com.example.newapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newapplication.R;
import com.example.newapplication.entity.Photo;
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private int resourceId;
    public PhotoAdapter(Context context, int textViewResourceId, List<Photo> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);//获取当前的photo实例
//        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        View view;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else {
            view = convertView;
        }
        ImageView photoImage = (ImageView)view.findViewById(R.id.photo_image);
        TextView photoName = (TextView)view.findViewById(R.id.photo_name);
        photoImage.setImageResource(photo.getImageId());
        photoName.setText(photo.getName());
        return view;
    }
}
